import java.util.ArrayList;
import java.util.HashMap;

public class BirthdayParty extends Party implements PartyInfo{

	// creates the instance variable
	private String birthdayName;

	// creates the constructor and updates to include the birthday name
	public BirthdayParty(PartyType partyType, String hostEmail, String partyName, String location, String date, String startTime,
			String endTime, int numberOfGuests, ArrayList<String> listOfGuests, HashMap<String, StatusRSVP> guestStatus,
			String birthdayName) {
		super(partyType, hostEmail, partyName, location, date, startTime, endTime, numberOfGuests, listOfGuests, guestStatus);
		this.birthdayName = birthdayName;
	}

	// creates the accessor for the birthday name
	public String getBirthdayName() {
		return birthdayName;
	}

	// creates the mutator for the birthday name
	public void setBirthdayName(String birthdayName) {
		this.birthdayName = birthdayName;
	}
	
	// overrides the showPartyInfo() method to print a user-oriented invitation
	@Override
	public void showPartyInfo() {
		System.out.println("\nHi! You have been cordially invited to the following party:\n\n" + partyName + "\n  Date: " + 
							date + "\n  Start Time: " + startTime + "\n  End Time (approx.): " + endTime + "\n  Location: " + 
							location + "\n  Number of invited guests: " + numberOfGuests + "\n\nIt's a birthday party! Don't forget to bring "
							+ birthdayName + " a gift!");
	}
	
}
