package ar.edu.unq.desapp.grupoa.service.dto;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private MenuDTO menu;
    private String providerName;
    private LocalDateTime deliveryDateAndHour;
    private LocalDateTime orderDateAndHour;
    private Integer quantity;
    private DeliveryType typeDelivery;
    private Status status;
    private Integer ranking;
    private Integer value;
}
