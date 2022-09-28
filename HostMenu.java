
// utilizes the Menu enum from HW08 to create the enum for the hosts' menu interface
public enum HostMenu {

	CREATE_PARTY("Create a new party"),
	MODIFY_PARTY("Modify an existing party"),
	VIEW_PARTY("View your parties"),
	LOGOUT("Logout");
	
	private String description;
	
	private HostMenu(String description){
		this.description = description;
	}
	
	public String getDisplayString(){
		return this.description;
	}
	
	public static int getNumOptions() {
		return HostMenu.values().length;
	}
	
	public static HostMenu getOption(int num) {
		return HostMenu.values()[num-1];
	}
	
	public static String getMenuOptions() {
		String promptHM = "What would you like to do?";

		for(HostMenu hm : HostMenu.values()){ 
			promptHM += "\n" + (hm.ordinal() + 1) + ": " + hm.getDisplayString();
		}
		
		return promptHM;
	}
	
	public static void printMenuOptions() {
		String promptHM = getMenuOptions();
		System.out.println(promptHM);
	}
	
}

