package ar.edu.unq.desapp.grupoa.service.dto;

import ar.edu.unq.desapp.grupoa.model.enums.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuDTO {
    private String name;
    private String description;
    private String image;
    private List<Category> category;
    private Integer deliveryPrice;
    private EffectivePeriodDTO effectivePeriod;
    private List<LocalTime> deliverySchedules;
    private LocalTime averageDeliveryTime;
    private Integer price;
    private Integer dailyStock;
    private OfferDTO offer1;
    private OfferDTO offer2;
    private List<Integer> ranking;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String providerEmail;
}
