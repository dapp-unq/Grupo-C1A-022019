package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEffectivePeriodException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NonNull
@NoArgsConstructor
@Entity
public class EffectivePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
