package model;

import java.util.Arrays;

public enum LetterGrade {
	A, B_PLUS, B, C_PLUS, C, D_PLUS, D, F, NO_GRADE;
	
	public static String[] getLetterGrade(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
