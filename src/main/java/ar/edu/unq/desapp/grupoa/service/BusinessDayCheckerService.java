package ar.edu.unq.desapp.grupoa.service;

import ar.edu.unq.desapp.grupoa.service.dto.BusinessDayCheckDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class BusinessDayCheckerService {

    @Value("${business.day.checker.api.url}")
    private String url;

    private final RestTemplate restTemplate;

    private static final String REST_URI = "/api/v2/feriados/";

    @Autowired
    public BusinessDayCheckerService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isBusinessDay(final LocalDate date) {
        final BusinessDayCheckDTO[] response = restTemplate.getForObject(getUrl(String.valueOf(date.getYear())), BusinessDayCheckDTO[].class);
        return !checkIfDayIsNotBusinessDay(Arrays.asList(response), date.getMonthValue(), date.getDayOfMonth());
    }

    private boolean checkIfDayIsNotBusinessDay(final List<BusinessDayCheckDTO> businessDayCheckDtoList, final int month, final int day) {
        return businessDayCheckDtoList.stream().anyMatch(dto -> month == dto.getMes() && day == dto.getDia());
    }

    private String getUrl(final String year) {
        return new StringBuilder("http://")
                .append(url)
                .append(REST_URI)
                .append(year).toString();
    }


}
