package ar.edu.unq.desapp.grupoa.service.dto;

import ar.edu.unq.desapp.grupoa.model.enums.DeliveryType;
import ar.edu.unq.desapp.grupoa.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private MenuDTO menu;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime deliveryDateAndHour;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime orderDateAndHour;
    private String providerEmail;
    private Integer quantity;
    private DeliveryType typeDelivery;
    private Status status;
    private Integer ranking;
    private Integer value;
}
