package ar.edu.unq.desapp.grupoa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
@Entity
@SequenceGenerator(name="ServiceHoursSeq", sequenceName="SEVICEHOURSseq", allocationSize=1)
@NoArgsConstructor
public class ServiceHours {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ServiceHoursSeq")
    private Long id;
	@Enumerated(EnumType.STRING)
    private DayOfWeek day;
    private LocalTime openingHours;
    private LocalTime closingHours;

    public ServiceHours(final DayOfWeek day, final LocalTime opening, final LocalTime closing) {
        this.day = day;
        this.openingHours = opening;
        this.closingHours = closing;
    }

}
