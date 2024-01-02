package helpers;

public class Helper {
	
	public static String plusRemoval(String letterGrade) {
		if(letterGrade.contains(("_PLUS"))) {
			letterGrade = letterGrade.charAt(0) + "+";
		}		
		return letterGrade;
	}
	public static String addingPlus(String letterGrade) {
		if(letterGrade.contains(("+"))) {
			letterGrade = letterGrade.charAt(0) + "_PLUS";
		}		
		return letterGrade;
	}
}
