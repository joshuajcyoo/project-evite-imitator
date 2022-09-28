import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileReader {

	// utilizes the FileReader methods from HW09 to read the users.txt file and put all of the users into the HashMap
	public static boolean readUserTXTFile(String file, Map<String, Guest> users) {
		boolean read = true;
		ArrayList<String> fileData;
		try {
			fileData = FileReader.readFile(file);
			
			for (int i = 0; i < fileData.size(); i++) {
				String line = fileData.get(i);
				Guest g = parseUserData(line);
				users.put(g.getEmail(), g);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file to read: " + file);
			//e.printStackTrace();
			read = false;
		}
		return read;
	}
	
	// utilizes the FileReader methods from HW09 to read the parties.txt file and put all of the users into the HashMap
	public static boolean readPartiesTXTFile(String file, ArrayList<Party> parties) {
		boolean read = true;
		ArrayList<String> fileData;
		try {
			fileData = FileReader.readFile(file);
			
			for (int i = 0; i < fileData.size(); i++) {
				String line = fileData.get(i);
				
				Scanner sc = new Scanner(line);
				
				sc.useDelimiter("/");
				
				String partyType = sc.next();
				String hostEmail = sc.next();
				String partyName = sc.next();
				String location = sc.next();
				String date = sc.next();
				String startTime = sc.next();
				String endTime = sc.next();
				int numberOfGuests = sc.nextInt();
				String stringListOfGuests = sc.next();
				String distinct = sc.next();
				
				// creates the ArrayList to store the list of guests in the correct format
				ArrayList<String> listOfGuests = new ArrayList<>(Arrays.asList(stringListOfGuests.split(",")));
				// creates the HashMap to store the guests and their default RSVP status
				HashMap<String, StatusRSVP> guestStatus = new HashMap<>();
				// uses separate scanner to read and create the list of guests

				
				sc.close();

				
				// uses an if-else statement to return the correct party based on the specific party type
					// accounts for if the party is a BIRTHDAY party
				if (partyType.equals("Birthday")) {
					parties.add(new BirthdayParty (PartyType.BIRTHDAY_PARTY, hostEmail, partyName, location, date, 
													startTime, endTime, numberOfGuests, listOfGuests, guestStatus, distinct)); 
				}
					// accounts for if the party is a WORK party
				else if (partyType.equals("Work")) {
					parties.add(new WorkParty (PartyType.WORK_PARTY, hostEmail, partyName, location, date, 
												startTime, endTime, numberOfGuests, listOfGuests, guestStatus, distinct));
				}
					// accounts for if the party is a HOLIDAY party
				else if (partyType.equals("Holiday")) {
					parties.add(new HolidayParty(PartyType.HOLIDAY_PARTY, hostEmail, partyName, location, date, 
												startTime, endTime, numberOfGuests, listOfGuests, guestStatus, distinct));
				}
					// accounts for if the party is a HOLIDAY party
				else if (partyType.equals("Retirement")) {
					parties.add(new RetirementParty(PartyType.RETIREMENT_PARTY, hostEmail, partyName, location, date, 
												startTime, endTime, numberOfGuests, listOfGuests, guestStatus, distinct));
				}
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file to read: " + file);
			//e.printStackTrace();
			read = false;
		}
		return read;
	}
	
	// creates a specific parseData method for reading the users file
	private static Guest parseUserData(String line) {
		Scanner sc = new Scanner(line);
		
		sc.useDelimiter("/");

		String email = sc.next();
		String name = sc.next();
		String password = sc.next();
		boolean isGuest = sc.nextBoolean(); 
		boolean isHost = sc.nextBoolean();
		
		sc.close();
		
		return new Guest(email, name, password, isGuest);
	}
	
	public static ArrayList<String> readFile(String fileName) throws FileNotFoundException {

		ArrayList<String> fileLines = new ArrayList<>();

		FileInputStream fis = new FileInputStream(fileName);
		Scanner scan = new Scanner(fis);
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			fileLines.add(line);
		}
		
		try {
			fis.close();
			scan.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileLines;
		
	}
	
}
