import java.util.ArrayList;
import java.util.HashMap;

public class HolidayParty extends Party{

	// creates the instance variable
	private String holiday;

	// creates the constructor and updates to include the holiday
	public HolidayParty(PartyType partyType, String hostEmail, String partyName, String location, String date, String startTime,
			String endTime, int numberOfGuests, ArrayList<String> listOfGuests, HashMap<String, StatusRSVP> guestStatus, String holiday) {
		super(partyType, hostEmail, partyName, location, date, startTime, endTime, numberOfGuests, listOfGuests, guestStatus);
		this.holiday = holiday;
	}

	// creates the accessor for the holiday
	public String getHoliday() {
		return holiday;
	}

	// creates the mutator for the holiday
	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}
	
	// overrides the showPartyInfo method to include the holiday message
	@Override
	public void showPartyInfo() {
		System.out.println("\nHi! You have been cordially invited to the following party:\n\n" + partyName + 
							"\n  Date: " + date + "\n  Start Time: " + startTime + "\n  End Time (approx.): " + 
							endTime + "\n  Location: " + location + "\n  Number of invited guests: " + numberOfGuests + 
							"\n\nIt's " + holiday + "! Hope you're in a festive mood!");
	} 
	
	
}
