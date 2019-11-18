package ar.edu.unq.desapp.grupoa.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EffectivePeriodDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate initialDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}
