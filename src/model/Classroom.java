package model;

public class Classroom {
	private static String roomNumber;
	private Building buildingName;
	
	public Classroom(Building buildingName, String roomNumber) {
		this.buildingName = buildingName;
		this.roomNumber = buildingName.toString().substring(0, 1) + roomNumber;
		
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public Building getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(Building buildingName) {
		this.buildingName = buildingName;
	}
	
}
