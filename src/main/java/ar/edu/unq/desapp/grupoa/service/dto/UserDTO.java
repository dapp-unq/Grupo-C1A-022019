package ar.edu.unq.desapp.grupoa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String location;
    private List<OrderDTO> orderHistory;
    private BigDecimal balance;
}
