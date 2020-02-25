package ar.edu.unq.desapp.grupoa.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessDayCheckDTO {
    private String motivo;
    private String tipo;
    private String original;
    private int dia;
    private int mes;
    private String id;
}


