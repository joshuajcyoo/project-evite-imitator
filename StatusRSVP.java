
// utilizes the Menu enum from HW08 to create the enum for guests' status RSVP
public enum StatusRSVP {

	ATTENDING("Attending"),
	NOT_ATTENDING("Not Attending"),
	UNDECIDED("Undecided");
	
	private String description;
	
	private StatusRSVP(String description){
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	
	public static int getNumOptions() {
		return StatusRSVP.values().length;
	}
	
	public static StatusRSVP getOption(int num) {
		return StatusRSVP.values()[num-1];
	}
	
	public static String getMenuOptions() {
		String promptRSVP = "What would you like to RSVP with?";

		for(StatusRSVP rsvp : StatusRSVP.values()){ //array from the enum
			promptRSVP += "\n" + (rsvp.ordinal() + 1) + ": " + rsvp.getDisplayString();
		}
		
		return promptRSVP;
	}
	
	public static void printMenuOptions() {
		String promptRSVP = getMenuOptions();
		System.out.println(promptRSVP);
	}
}
