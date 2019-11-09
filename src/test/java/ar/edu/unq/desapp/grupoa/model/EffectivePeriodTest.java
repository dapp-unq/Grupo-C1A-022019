package ar.edu.unq.desapp.grupoa.model;

import ar.edu.unq.desapp.grupoa.model.exceptions.InvalidEffectivePeriodException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;


public class EffectivePeriodTest {

    @Test
    public void whenCreatingAValidEffectivePeriodItSuccess() {
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = initialDate.plusDays(7L);

        EffectivePeriod effectivePeriod = new EffectivePeriod(initialDate, endDate);
        assertEquals(initialDate, effectivePeriod.getInitialDate());
        assertEquals(endDate, effectivePeriod.getEndDate());
    }

    @Test(expected = InvalidEffectivePeriodException.class)
    public void whenCreatingAndInvalidEffectivePeriodItThrowsException() {
        LocalDate initialDate = LocalDate.now();
        LocalDate endDate = initialDate.plusDays(7L);

        new EffectivePeriod(endDate, initialDate);
    }

}