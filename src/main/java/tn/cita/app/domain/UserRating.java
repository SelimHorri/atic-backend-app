package tn.cita.app.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserRating {
	
	VERY_BAD(1),
	BAD(2),
	NEUTRAL(3),
	GOOD(4),
	PERFECT(5);
	
	private final int userRate;
	
}








