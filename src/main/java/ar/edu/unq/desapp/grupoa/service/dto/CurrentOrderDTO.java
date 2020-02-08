package ar.edu.unq.desapp.grupoa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentOrderDTO {
    private UserDTO user;
    private List<OrderDTO> orders;

    public void setProviderEmailToOrders(String email){
        orders.forEach(orderDto -> orderDto.getMenu().setProviderEmail(email));
    }
}
