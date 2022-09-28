import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Party implements PartyInfo{

	// creates the instance variables
	protected PartyType partyType;
	protected String hostEmail;
	protected String partyName;
	protected String location;
	protected String date;
	protected String startTime;
	protected String endTime;
	protected int numberOfGuests;
	private ArrayList<String> listOfGuests;
	private HashMap<String, StatusRSVP> guestStatus;

	// creates the default constructor
	public Party(PartyType partyType, String hostEmail, String partyName, String location, String date, String startTime, String endTime,
			int numberOfGuests, ArrayList<String> listOfGuests, HashMap<String, StatusRSVP> guestStatus) {
		this.partyType = partyType;
		this.hostEmail = hostEmail;
		this.partyName = partyName;
		this.location = location;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.numberOfGuests = numberOfGuests;
		this.listOfGuests = listOfGuests;
		this.guestStatus = guestStatus;
	}

	// creates the accessor for the party type
	public PartyType getPartyType() {
		return partyType;
	}
	
	// creates the mutator for the party type
	public void setPartyType(PartyType partyType) {
		this.partyType = partyType;
	}
	
	// creates the accessor for the host email
	public String getHostEmail() {
		return hostEmail;
	}
	
	// creates the accessor for the party name
	public String getPartyName() {
		return partyName;
	}

	// creates the mutator for the party name
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	// creates the accessor for the location of the party
	public String getLocation() {
		return location;
	}

	// creates the mutator for the location of the party
	public void setLocation(String location) {
		this.location = location;
	}

	// creates the accessor for the date of the party
	public String getDate() {
		return date;
	}

	// creates the mutator for the date of the party
	public void setDate(String date) {
		this.date = date;
	}

	// creates the accessor for the start time of the party
	public String getStartTime() {
		return startTime;
	}

	// creates the mutator for the start time of the party
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	// creates the accessor for the end time of the party
	public String getEndTime() {
		return endTime;
	}
	
	// creates the mutator for the end time of the party
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	// creates the accessor for the number of guests
	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	// creates the mutator for the number of guests
	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
	
	// creates the accessor for the list of guests
	public ArrayList<String> getListOfGuests() {
		return listOfGuests;
	}
		
	// creates the mutator for the list of guests
	public void setListOfGuests(ArrayList<String> listOfGuests) {
		this.listOfGuests = listOfGuests;
	}
	
	// creates the accessor for the HashMap of guests and their RSVP status
	public HashMap<String, StatusRSVP> getGuestStatus() {
		return guestStatus;
	}

	// creates the mutator for the HashMap of guests and their RSVP status
	public void setGuestStatus(HashMap<String, StatusRSVP> guestStatus) {
		this.guestStatus = guestStatus;
	}
	
	@Override
	public void showPartyInfo() {
		System.out.println(partyName + " on " + date + ", starting at " + startTime + " and ending at " + endTime + 
							"\nLocation: " + location + "\nNumber of guests: " + numberOfGuests);
	}
	
}
