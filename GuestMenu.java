
// utilizes the Menu enum from HW08 to create the enum for the guests' menu interface
public enum GuestMenu {

	VIEW_INVITATIONS("View invitations"),
	RSVP("Change an RSVP"),
	LOGOUT("Logout");
	
	private String description;
	
	private GuestMenu(String description){
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	
	public static int getNumOptions() {
		return GuestMenu.values().length;
	}
	
	public static GuestMenu getOption(int num) {
		return GuestMenu.values()[num-1];
	}
	
	public static String getMenuOptions() {
		String promptGM = "What would you like to do?";

		for(GuestMenu gm : GuestMenu.values()){ //array from the enum
			promptGM += "\n" + (gm.ordinal() + 1) + ": " + gm.getDisplayString();
		}
		
		return promptGM;
	}
	
	public static void printMenuOptions() {
		String promptGM = getMenuOptions();
		System.out.println(promptGM);
	}
	
}

