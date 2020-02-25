package ar.edu.unq.desapp.grupoa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PurchaseDTO {
    private String userEmail;
    private OrderDTO order;
}
