import java.util.ArrayList;
import java.util.HashMap;

public class RetirementParty extends Party implements PartyInfo{

	// creates the instance variable
	private String retiredName;

	// creates the constructor and updates to include the retired name
	public RetirementParty(PartyType partyType, String hostEmail, String partyName, String location, String date, String startTime,
			String endTime, int numberOfGuests, ArrayList<String> listOfGuests, HashMap<String, StatusRSVP> guestStatus,
			String retiredName) {
		super(partyType, hostEmail, partyName, location, date, startTime, endTime, numberOfGuests, listOfGuests, guestStatus);
		this.retiredName = retiredName;
	}

	// creates the accessor for the retired name
	public String getRetiredName() {
		return retiredName;
	}

	// creates the mutator for the retired name
	public void setRetiredName(String retiredName) {
		this.retiredName = retiredName;
	}
	
	// overrides the showPartyInfo method to include the message for the retiree
	@Override
	public void showPartyInfo() {
		System.out.println("\nHi! You have been cordially invited to the following party:\n\n" + partyName + "\n  Date: " + 
							date + "\n  Start Time: " + startTime + "\n  End Time (approx.): " + endTime + "\n  Location: " + 
							location + "\n  Number of invited guests: " + numberOfGuests + 
							"\n\nIt's a retirement party! Don't forget to bring " + retiredName + " a gift!");
	}
	
}
