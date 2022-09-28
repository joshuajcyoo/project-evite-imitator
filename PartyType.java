
// utilizes the Menu enum from HW08 to create the enum for the types of party
public enum PartyType {
	
	BIRTHDAY_PARTY("Birthday"),
	WORK_PARTY("Work"),
	HOLIDAY_PARTY("Holiday"),
	RETIREMENT_PARTY("Retirement");
	
	private String description;
	
	private PartyType(String description) {
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	
	public static int getNumOptions() {
		return PartyType.values().length;
	}
	
	public static PartyType getOption(int num) {
		return PartyType.values()[num-1];
	}
	
	public static String getMenuOptions() {
		String promptPT = "\nWhat kind of party would you like to host?";

		for(PartyType pt : PartyType.values()){ 
			promptPT += "\n" + (pt.ordinal() + 1) + ": " + pt.getDisplayString();
		}
		
		return promptPT;
	}
	
	public static void printMenuOptions() {
		String promptPT = getMenuOptions();
		System.out.println(promptPT);
	}
	
}

