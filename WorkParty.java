import java.util.ArrayList;
import java.util.HashMap;

public class WorkParty extends Party implements PartyInfo{

	// creates the instance variable
	private String companyName;

	// creates the constructor and updates to include the company name
	public WorkParty(PartyType partyType, String hostEmail, String partyName, String location, String date, String startTime,
			String endTime, int numberOfGuests, ArrayList<String> listOfGuests, HashMap<String, StatusRSVP> guestStatus,
			String companyName) {
		super(partyType, hostEmail, partyName, location, date, startTime, endTime, numberOfGuests, listOfGuests, guestStatus);
		this.companyName = companyName;
	}

	// creates the accessor for the company name
	public String getCompanyName() {
		return companyName;
	}

	// creates the mutator for the company name
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	// overrides the showPartyInfo method to include the company message
	@Override
	public void showPartyInfo() {
		System.out.println("\nHi! You have been cordially invited to the following party:\n\n" + partyName + 
							"\n  Date: " + date + "\n  Start Time: " + startTime + "\n  End Time (approx.): " + 
							endTime + "\n  Location: " + location + "\n  Number of invited guests: " + numberOfGuests + 
							"\n\n" + companyName + " cordially invites you to our wonderful event!");
	}
	
}
