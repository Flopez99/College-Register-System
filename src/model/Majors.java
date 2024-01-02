package model;

import java.util.Arrays;

public enum Majors {
	CSE, MAT, ENG ;

	public static String[] getMajors(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
