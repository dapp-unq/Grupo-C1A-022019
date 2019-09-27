package ar.edu.unq.desapp.grupoa.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

import lombok.Getter;

@Getter
public class ServiceHours {
	private DayOfWeek day;
	private LocalTime openingHours;
	private LocalTime closingHours;

	public ServiceHours(DayOfWeek day, LocalTime opening, LocalTime closing) {
		this.day = day;
		this.openingHours = opening;
		this.closingHours = closing;
	}

}
