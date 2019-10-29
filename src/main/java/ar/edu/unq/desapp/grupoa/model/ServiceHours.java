package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Entity
@NoArgsConstructor
public class ServiceHours {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private LocalTime openingHours;
    private LocalTime closingHours;

    public ServiceHours(DayOfWeek day, LocalTime opening, LocalTime closing) {
        this.day = day;
        this.openingHours = opening;
        this.closingHours = closing;
    }

}
