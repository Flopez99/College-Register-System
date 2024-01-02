package model;

import java.util.Arrays;
import java.util.Random;

public enum FacultyTitle {
	PROF, ASSOCIATE_PROF, ASSISTANT_PROF, INSTRUCTOR;
	
	public static String[] getTitles(Class<? extends Enum<?>> e) {
	    return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
	}
}
