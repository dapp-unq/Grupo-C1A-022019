package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalTime;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
public class ServiceHours {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Enumerated(STRING)
    private DayOfWeek day;
    private LocalTime openingHours;
    private LocalTime closingHours;

    public ServiceHours(final DayOfWeek day, final LocalTime opening, final LocalTime closing) {
        this.day = day;
        this.openingHours = opening;
        this.closingHours = closing;
    }

}
