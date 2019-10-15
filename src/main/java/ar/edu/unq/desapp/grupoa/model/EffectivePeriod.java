package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEffectivePeriodException;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
@NonNull
public class EffectivePeriod {
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
