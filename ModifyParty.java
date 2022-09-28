
// utilizes the Menu enum from HW08 to create the enum for the hosts' menu interface
public enum ModifyParty {

	MODIFY_PARTY_NAME("Change the name of the party"),
	MODIFY_LOCATION("Change the location of the party"),
	MODIFY_DATE("Change the date of the party"),
	MODIFY_START_TIME("Change the start time of the party"),
	MODIFY_END_TIME("Change the end time of the party"),
	ADD_GUESTS("Add more guests to the party"),
	REMOVE_GUESTS("Remove guests from the party"),
	FINISHED("Finished");
	
	private String description;
	
	private ModifyParty (String description) {
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	
	public static int getNumOptions() {
		return ModifyParty.values().length;
	}
	
	public static ModifyParty getOption(int num) {
		return ModifyParty.values()[num-1];
	}
	
	public static String getMenuOptions() {
		String promptMP = "\nWhat would you like to modify about the party?";

		for(ModifyParty mp : ModifyParty.values()){ 
			promptMP += "\n" + (mp.ordinal() + 1) + ": " + mp.getDisplayString();
		}
		
		return promptMP;
	}
	
	public static void printMenuOptions() {
		String promptMP = getMenuOptions();
		System.out.println(promptMP);
	}
	
}

