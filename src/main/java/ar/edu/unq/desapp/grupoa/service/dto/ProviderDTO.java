package ar.edu.unq.desapp.grupoa.service.dto;

import ar.edu.unq.desapp.grupoa.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProviderDTO {
    private String name;
    private String logo;
    private City city;
    private String location;
    private String description;
    private String website;
    private String email;
    private String phoneNumber;
    private List<ServiceHoursDTO> openingHoursDays;
    private List<City> deliveryCities;
    private List<MenuDTO> currentMenus;
    private List<CurrentOrderDTO> orders;
    private BigDecimal balance;
    private Integer menusRemoved;
}
