package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEffectivePeriodException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

@Getter
@NonNull
@NoArgsConstructor
@Entity
@SequenceGenerator(name="EffectivePeriodSeq", sequenceName="EFFECTIVEPERIODseq", allocationSize=1)
public class EffectivePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EffectivePeriodSeq")
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
