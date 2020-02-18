package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEffectivePeriodException;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NonNull
@NoArgsConstructor
@Entity
@JsonIgnoreProperties("hibernateLazyInitializer")
public class EffectivePeriod {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private LocalDate initialDate;
    private LocalDate endDate;

    public EffectivePeriod(final LocalDate initialDate, final LocalDate endDate) {
        validateInitialDateIsBeforeEndDate(initialDate, endDate);
        this.initialDate = initialDate;
        this.endDate = endDate;
    }

    private void validateInitialDateIsBeforeEndDate(final LocalDate initialDate, final LocalDate endDate) {
        if (!initialDate.isBefore(endDate))
            throw new InvalidEffectivePeriodException("El periodo indicado para el menu es invalido");
    }
}
