package ar.edu.unq.desapp.grupoa.service.dto;

import ar.edu.unq.desapp.grupoa.model.enums.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CityDTO {
    private City name;
    private String title;
}
