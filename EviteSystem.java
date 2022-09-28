import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class EviteSystem {

	// creates the instance variable for the BFF
	private BFF bff;
	// creates variables for the login
	private boolean loggedIn = false;
	private Guest user = null;
	// creates the instance variable for the HashMap that holds all of the registered users from the users.txt file
	private Map<String, Guest> userMap;
	// creates the instance variable for the ArrayList that stores all of the created parties
	private ArrayList<Party> parties;
	
	// creates the default constructor and initializes all of the instance variables
	public EviteSystem() {
		bff = new BFF();
		
		userMap = new HashMap<>();
		// uses the file reader to input the users from the text file into the Evite system map of users
		FileReader.readUserTXTFile("src/users.txt", userMap);
		
		parties = new ArrayList<>();
		// uses the file reader to input the parties from the text file into the Evite system list of parties
		FileReader.readPartiesTXTFile("src/parties.txt", parties);
	}
	
	// creates the method that runs the entire Evite program
	public void run() {
    	
		// uses a boolean variable for the do-while loop to indicate whether to exit the entire Evite program after a user logs out or not
		boolean exit = true;
		do {
			System.out.println("🎉 Welcome to Evite! 🎉\n");
			
			// prompts the user to enter the email address
			String email = bff.inputWord("Enter your email:");
			// calls the isRegistered method to check if the email is registered and stores it in a variable
			boolean emailRegisterCheck = isRegistered(email);
			
			// uses an if-else statement to login the user depending on whether the email is registered or not
				// accounts for if the email entered is already registered in the system
			if (emailRegisterCheck) {
				System.out.println("\nHi " + userMap.get(email).getName() + "! Welcome back to Evite!");
				
				boolean successfulLogin = login(email);
				// uses while loop to ensure that the user enters a correct password
				while (!successfulLogin) {
					successfulLogin = login(email);
				}	
				// updates the login variables
				loggedIn = true;
				user = userMap.get(email);	
			}
			
			// uses an if-else statement to login the user depending on whether the email is registered or not
				// accounts for if the email entered is NOT registered in the system
			else if (!emailRegisterCheck ){
				System.out.println("It looks like you aren't registered with us yet. Welcome! Let's create your new account.");
				
				// gathers name and password from the new user
				String newName = bff.inputWord("Please enter your first name:");
				String newPassword = bff.inputWord("Please enter your new password:");
				// stores the new email, name, and password into the userMap as a new guest
				userMap.put(email, new Guest(email, newName, newPassword, true));
				// updates the login variables
				loggedIn = true;
				user = userMap.get(email);
				
				// uses FileWriter to write newly registered user to the existing users.txt file to save their information for multiple runs
					// CREDIT TO: DelftStack [https://www.delftstack.com/howto/java/append-to-a-text-file-in-java/]
				try (FileWriter fw = new FileWriter("src/users.txt", true)) {
					fw.write("\r\n" + email + "/" + newName + "/" + newPassword + "/" + false + "/" + false);
					fw.close();
				} catch (FileNotFoundException e) {
					System.out.println("Couldn't write to the file.");
					e.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("Your new account has been created. You are now registered!\n");
			}
			
			// prompts the user to choose whether to enter Evite as a Host or Guest
			System.out.println("Do you want to host an event, or manage your guest status at an upcoming event?");
			String guestOrHost = bff.inputWord("Enter HOST if you interested in hosting, or GUEST if you are an invited guest:").toUpperCase();
			// uses a while loop to ensure that the user enters one of the correct two options
			while ((!(guestOrHost.equals("GUEST"))) && (!(guestOrHost.equals("HOST")))) {
				System.out.println("Invalid response.");
				guestOrHost = bff.inputWord("Enter HOST if you interested in hosting, or GUEST if you are an invited guest:").toUpperCase();
			}
			System.out.print("\n");
			
			// uses an if-else if statement to check if the user decides to be a Guest or Host
				// accounts for if the user chooses GUEST
			if (guestOrHost.equals("GUEST")) {
				// sets the Guest status to true
				user.setIsGuest(true);
				
				// uses a do-while loop to continue allowing the user to perform Guest actions until the user desires to logout
				do {
					// utilizes the  enum from HW08 to display and gather user input from the GuestMenu
					GuestMenu.printMenuOptions();
					int choice = bff.inputInt("Please enter a number:", 1, GuestMenu.getNumOptions());
					GuestMenu option = GuestMenu.getOption(choice);
					
					// utilizes the switch statement from HW08 to accordingly deal with each option for the user input
					switch(option) {
					
					// accounts for the case that the guest wishes to view all of their invitations
					case VIEW_INVITATIONS:
						System.out.println("\nSelect a party to view...");
						
						int guestViewOptionNumber = 1;
						// creates an ArrayList used to store all of the parties the current user has been invited to
						ArrayList<Party> guestParties = new ArrayList<>();
						// uses an outer for loop to loop through all of the parties
						for (int i = 0; i < parties.size(); i++) {
							// uses a nested for loop to through all of the guests for each party
							for (String g: parties.get(i).getListOfGuests()) {
								// checks if the user is on the guest list of the specific party being checked
								if (g.equals(user.getEmail())) {
									// prints out the option of the party for the guest to see, and adds the party to the ArrayList of parties the guest is invited to
									System.out.println(guestViewOptionNumber + ") " + parties.get(i).getPartyName());
									guestParties.add(parties.get(i));
									// updates the party number to prepare for the next party to be shown
									guestViewOptionNumber++;
								}
							}
						}
						
						// uses an if-else statement to ensure that the guest has been invited to at least one party
							// accounts for if the current user has NOT been invited to any parties
						if (guestParties.isEmpty()) {
							System.out.println("You have not created any parties yet.");
						}
							// accounts for if the current user has created at least one party to modify
						else {
							// gathers the user input for their choice of which party to view
							Party selectedParty = guestParties.get((bff.inputInt("\nEnter the number of the party below:", 1, guestParties.size())) - 1);
					
							// calls the showPartyInfo interface method to display the information about the event to the host
							System.out.println("\nHere is the information about this party:");
							selectedParty.showPartyInfo();
					
							// gathers the input for whether the user wants to see the entire guest list and RSVP status for the party
							boolean viewGuestList = bff.inputBoolean("\nWould you like to see the guest list and RSVP status for this party? (true/false)");
							if (viewGuestList) {
								System.out.println("\nHere is the entire list and each guest's current RSVP status:");
								// uses a for each loop to loop through each guest in the guest list and display a user-friendly indication of their RSVP status
								for (String invitedGuest: selectedParty.getGuestStatus().keySet()) {
									System.out.println("  " + invitedGuest + ": " + selectedParty.getGuestStatus().get(invitedGuest));
								}
							}
						}
						System.out.print("\n");
						
						break;
					// accounts for the case that the user wishes to make an RSVP change to a party
					case RSVP:
						System.out.println("\nSelect a party to change your RSVP...");
						
						int guestRSVPOptionNumber = 1;
						// creates an ArrayList used to store all of the parties the current user has been invited to
						ArrayList<Party> guestParties2 = new ArrayList<>();
						// uses an outer for loop to loop through all of the parties
						for (int i = 0; i < parties.size(); i++) {
							// uses a nested for loop to through all of the guests for each party
							for (String g: parties.get(i).getListOfGuests()) {
								// checks if the user is on the guest list of the specific party being checked
								if (g.equals(user.getEmail())) {
									// prints out the option of the party for the guest to see, and adds the party to the ArrayList of parties the guest is invited to
									System.out.println(guestRSVPOptionNumber + ": " + parties.get(i).getPartyName());
									guestParties2.add(parties.get(i));
								}
							}
						}
						
						// uses an if-else statement to ensure that the guest has at least one party to make an RSVP choice for
							// accounts for if the current user has NOT been invited to any parties
						if (guestParties2.isEmpty()) {
							System.out.println("You have not created any parties yet.");
						}
							// accounts for if the current user has created at least one party to make an RSVP choice for
						else {
							// gathers the user input for their choice of which RSVP status
							Party selectedParty = guestParties2.get((bff.inputInt("\nEnter the number of the party below:", 1, guestParties2.size())) - 1);
							System.out.print("\n");
							
							// utilizes the Menu enum from HW08 to display and gather user input for the guest's desired RSVP status
							StatusRSVP.printMenuOptions();
							int statusRSVPChoice = bff.inputInt("Please enter a number:", 1, StatusRSVP.getNumOptions());
							StatusRSVP statusRSVPOption = StatusRSVP.getOption(statusRSVPChoice);
						
							// updates the RSVP status for the guest in the existing party's HashMap
							selectedParty.getGuestStatus().put(user.getEmail(), statusRSVPOption);
							System.out.println("\nRSVP status updated successfully!");
						}
						System.out.print("\n");
						
						break;
					// accounts for the case that the user wishes to logout
					case LOGOUT:
						// updates the login variables to reflect a logout
						loggedIn = false;
						user = null;
						// notifies the user of the successful logout
						System.out.println("\nYou have successfully logged out.");
						System.out.print("\n");
						
						break; 
					default:
						System.out.println("Unexpected error.");
						System.out.print("\n");
					}
				} while (loggedIn);
			}
			
			// uses an if-else if statement to check if the user decides to be a Guest or Host
				// accounts for if the user chooses HOST
			else if (guestOrHost.equals("HOST")) {
				// uses an if statement to ensure that the Guest user is also an instance of a Host user
				if (user instanceof Host) {
					// typecasts the current Guest to convert into a Host
					Host h = (Host) user;
					// sets both the Guest status and Host status to true
					h.setIsGuest(true);
					h.setIsHost(true);
				}
				
				// uses a do-while loop to continue allowing the user to perform Host actions until the user desires to logout
				do {
					// utilizes the Menu enum from HW08 to display and gather user input from the menu for a host
					HostMenu.printMenuOptions();
					int hostChoice = bff.inputInt("Please enter a number:", 1, HostMenu.getNumOptions());
					HostMenu hostOption = HostMenu.getOption(hostChoice);
					
					// utilizes the switch statement from HW08 to accordingly deal with each option for the user input
					switch(hostOption) {
					
					// accounts for the case that that the user wants to create a new party
					case CREATE_PARTY:
						// utilizes the Menu enum from HW08 to display and gather user input from the menu for the specific type of party
						PartyType.printMenuOptions();
						int partyTypeChoice = bff.inputInt("Please enter a number:", 1, PartyType.getNumOptions());
						PartyType partyTypeOption = PartyType.getOption(partyTypeChoice);
						
						// gathers the user input for every detail of a new party, and stores the inputs in variables
						String partyName = bff.inputLine("\nWhat is the name of this party?");
						String location = bff.inputLine("Where will this party take place?");
						String date = bff.inputLine("What is the date that this party will take place?");
						String startTime = bff.inputLine("At what time will this party start?");
						String endTime = bff.inputLine("At what time will this party end?");
						int numberOfGuests = bff.inputInt("How many guests would you like to invite (email)?");
						System.out.print("\n");
						
						// creates an ArrayList used to store all of the invited guests by email
						ArrayList<String> listOfGuests = new ArrayList<String>();
						// creates a HashMap used to store all of the invited guests by email and their default RSVP status of UNDECIDED
						HashMap<String, StatusRSVP> guestStatus = new HashMap<String, StatusRSVP>();
						// uses a for loop to gather input to account for the number of guests the user wishes to invite
						// stores the guests' information in both the ArrayList and HashMap
						for (int i = 1; i <= numberOfGuests; i++) {
							String newGuest = bff.inputWord("Please enter a guest's email:");
							listOfGuests.add(newGuest);
							guestStatus.put(newGuest, StatusRSVP.UNDECIDED);
						}
						System.out.println("\nGuest list created successfully!");
						
						// uses an if-else if statement to account for the specific type of party the user decides to create
							// accounts for if the user creates a BIRTHDAY_PARTY
						if (partyTypeOption.equals(PartyType.getOption(1))) {
							// gathers the input for the unique name of the birthday person
							String birthdayName = bff.inputLine("\nWhat is the name of the person whose birthday is being celebrated?");
							// creates a new party with the gathered information, and stores the party in the overall ArrayList of all parties
							Party p = new BirthdayParty (partyTypeOption, user.getEmail(), partyName, location, date, startTime, endTime,
														numberOfGuests, listOfGuests, guestStatus, birthdayName);
							parties.add(p);
						}
							// accounts for if the user creates a WORK_PARTY
						else if (partyTypeOption.equals(PartyType.getOption(2))) {
							// gathers the input for the unique company name
							String companyName = bff.inputLine("\nWhat is the name of the company that is hosting the party?");
							// creates a new party with the gathered information, and stores the party in the overall ArrayList of all parties
							Party p = new WorkParty (partyTypeOption, user.getEmail(), partyName, location, date, startTime, endTime,
														numberOfGuests, listOfGuests, guestStatus, companyName);
							parties.add(p);
						}
							// accounts for if the user creates a HOLIDAY_PARTY
						else if (partyTypeOption.equals(PartyType.getOption(3))) {
							// gathers the input for the unique name of the holiday being celebrated
							String holiday = bff.inputLine("\nWhat is the holiday that is being celebrated?");
							// creates a new party with the gathered information, and stores the party in the overall ArrayList of all parties
							Party p = new HolidayParty (partyTypeOption, user.getEmail(), partyName, location, date, startTime, endTime,
									numberOfGuests, listOfGuests, guestStatus, holiday);
							parties.add(p);
						}
							// accounts for if the user creates a RETIREMENT_PARTY
						else if (partyTypeOption.equals(PartyType.getOption(4))) {
							// gathers the input for the unique name of the retired person
							String retiredName = bff.inputLine("\nWhat is the name of the person who is retiring?");
							// creates a new party with the gathered information, and stores the party in the overall ArrayList of all parties
							Party p = new RetirementParty (partyTypeOption, user.getEmail(), partyName, location, date, startTime, endTime,
									numberOfGuests, listOfGuests, guestStatus, retiredName);
							parties.add(p);
						}
						
						// creates the comma-separated list of emails used for the formatting for writing the list of guests back to the file
						String stringListOfGuests = "";
						for (String g: listOfGuests) {
							stringListOfGuests = stringListOfGuests + g + ",";
						}
						// removes the unwanted comma at the end of the string
						stringListOfGuests = stringListOfGuests.substring(0, stringListOfGuests.length() - 1);
						// uses FileWriter to write newly created party to the existing parties.txt file to save the party information for multiple runs
							// CREDIT TO: DelftStack [https://www.delftstack.com/howto/java/append-to-a-text-file-in-java/]
						try (FileWriter fw = new FileWriter("src/parties.txt", true)) {
							fw.write("\r\n" + partyTypeOption.getDisplayString() + "/" + user.getEmail() + "/" + partyName + "/" + 
										location + "/" + date + "/" + startTime + "/" + endTime + "/" + numberOfGuests + "/" + 
										stringListOfGuests + "/" + null);
							fw.close();
						} catch (FileNotFoundException e) {
							System.out.println("Couldn't write to the file.");
							e.printStackTrace();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						System.out.println("\nParty created! Your guests will receive their invites shortly.\n");
						
						break;
					// accounts for the case that that the user wants to modify an existing party
					case MODIFY_PARTY:
						System.out.println("\nSelect a party to modify...");
						
						int partyModifyOptionNumber = 1;
						// creates an ArrayList used to store all of the parties created by the current user
						ArrayList<Party> hostParties = new ArrayList<>();
						// uses a for loop to loop through all of the parties
						for (int i = 0; i < parties.size(); i++) {
							// checks if the user is the host of the specific party being checked
							if (parties.get(i).getHostEmail().equals(user.getEmail())) {
								// prints out the option of the party for the host to see, and adds the party to the ArrayList of parties created by the host
								System.out.println(partyModifyOptionNumber + ": " + parties.get(i).getPartyName());
								hostParties.add(parties.get(i));
								// updates the partyNumber to prepare for the next party to be shown
								partyModifyOptionNumber++;
							}
						}
						
						// uses an if-else statement to ensure that the host has actually created at least one party to modify
							// accounts for if the current user has NOT created any parties
						if (hostParties.isEmpty()) {
							System.out.println("You have not created any parties yet.");
						}
							// accounts for if the current user has created at least one party to modify
						else {
							// gathers the user input for their choice of which party to modify
							Party selectedParty = hostParties.get((bff.inputInt("\nEnter the number of the party below:", 1, hostParties.size())) - 1);
							
							boolean finishModifying = false;
							// uses a do-while loop to allow users to keep modifying the party until they indicate they are finished
							do {
								// utilizes the Menu enum from HW08 to display and gather user input for which component of the party to modify 
								ModifyParty.printMenuOptions();
								int modifyPartyChoice = bff.inputInt("Please enter a number:", 1, ModifyParty.getNumOptions());
								ModifyParty modifyPartyOption = ModifyParty.getOption(modifyPartyChoice);
								System.out.print("\n");
								
								// utilizes the switch statement from HW08 to accordingly deal with each option for the user input
								switch(modifyPartyOption) {
								
								// accounts for the case that the user wants to update the name of the party
								case MODIFY_PARTY_NAME:
									String newPartyName = bff.inputLine("What would you like the new party name to be?");
									selectedParty.setPartyName(newPartyName);
									System.out.println("\nParty name changed successfully!");
									
									break;
								// accounts for the case that the user wants to update the location of the party
								case MODIFY_LOCATION:
									String newLocation = bff.inputLine("What is the new location of the party?");
									selectedParty.setLocation(newLocation);
									System.out.println("\nLocation changed succesfully!");
									
									break;
								// accounts for the case that the user wants to update the date of the party
								case MODIFY_DATE:
									String newDate = bff.inputLine("What is the new date of the party?");
									selectedParty.setDate(newDate);
									System.out.println("\nDate changed successfully!");
									
									break;
								// accounts for the case that the user wants to update the party start time
								case MODIFY_START_TIME:
									String newStartTime = bff.inputLine("What is the new start time of the party?");
									selectedParty.setStartTime(newStartTime);
									System.out.println("\nStart time changed successfully!");
									
									break;
								// accounts for the case that the user wants to update the party end time
								case MODIFY_END_TIME:
									String newEndTime = bff.inputLine("What is the new end time of the party?");
									selectedParty.setEndTime(newEndTime);
									System.out.println("\nEnd time changed successfully!");
									
									break;
								// accounts for the case that the user wants to add more guests
								case ADD_GUESTS:
									// gathers the input for how many more guests the user would like to add
									int numberOfMoreGuests = bff.inputInt("How many more guests would you like to add?");
									// updates the number of guests at the party to reflect the number of guests added
									selectedParty.setNumberOfGuests(selectedParty.getNumberOfGuests() + numberOfMoreGuests);
									
									// uses a for loop to gather input to account for and add each additional guest
									for (int i = 1; i <= numberOfMoreGuests; i++) {
										String additionalGuest = bff.inputWord("Please enter a guest's email:");
										// adds each additional guest's information into the party's existing list of guests and HashMap of guest RSVP status
										selectedParty.getListOfGuests().add(additionalGuest);
										selectedParty.getGuestStatus().put(additionalGuest, StatusRSVP.UNDECIDED);
									}
									
									System.out.println("\nGuest(s) added successfully!");
									
									break;	
								// accounts for the case that the user wants to remove guests
								case REMOVE_GUESTS:
									// uses an if-else statement to ensure that it is actually possible to remove guests
										// accounts for if the party does NOT contain any guests at all
									if (selectedParty.getNumberOfGuests() == 0) {
										System.out.println("This party has no guests yet. Invite some guests!");
									}
										// accounts for if the party contains at least 1 guest to remove
									else {
										System.out.println("This party has " + selectedParty.getNumberOfGuests() + " guests invited so far.");
										// gathers the user input for how many guests the user would like to remove
										int numberOfLessGuests = bff.inputInt("How many guests would you like to remove?", 1, selectedParty.getNumberOfGuests());
										// updates the number of guests at the party to reflect the number of guests removed
										selectedParty.setNumberOfGuests(selectedParty.getNumberOfGuests() - numberOfLessGuests);
										
										// uses a for-each loop to print out the entire list of guests for the user to see
										System.out.println("Here is a list your invited guests (by email):");
										for (String invitedGuestEmail: selectedParty.getListOfGuests()) {
											System.out.println("  " + invitedGuestEmail);
										}
										System.out.print("\n");
										
										// uses a for loop to account for and remove each guest
										for (int i = 1; i <= numberOfLessGuests; i++) {
											String removedGuest = bff.inputWord("Enter the email of a guest you would like to remove:");
											
											// uses a while loop to ensure that the removed guest is actually on the list of invited guests
											while (!selectedParty.getListOfGuests().contains(removedGuest)) {
												System.out.println("This email is not on your list of invited guests. Please ensure you entered the email correctly.");
												removedGuest = bff.inputWord("\nEnter the email of a guest you would like to remove:");
											}
											// removes the guest from the party's existing ArrayList list of guests and HashMap guests' RSVP status
											selectedParty.getListOfGuests().remove(removedGuest);
											selectedParty.getGuestStatus().remove(removedGuest);
										}
										System.out.println("\nGuest(s) removed successfully!");
									}
									
									break;
								// accounts for the case that the user is finished modifying parties
								case FINISHED:
									// updates the boolean to exit the do-while loop and the ModifyParty menu
									finishModifying = true;
									
									break;
								default:
									System.out.println("Unexpected error.");
									System.out.print("\n");
								}	
							
							} while (!finishModifying);
						}
						System.out.print("\n");
						
						break;
					// accounts for the case that the user wants to view the information of an existing party
					case VIEW_PARTY:
						System.out.println("\nSelect a party to view...");
						
						int partyViewOptionNumber = 1;
						// creates an ArrayList used to store all of the parties created by the current user
						ArrayList<Party> hostParties2 = new ArrayList<>();
						// uses a for loop to loop through all of the parties
						for (int i = 0; i < parties.size(); i++) {
							// checks if the user is the host of the specific party being checked
							if (parties.get(i).getHostEmail().equals(user.getEmail())) {
								// prints out the option of the party for the host to see, and adds the party to the ArrayList of parties created by the host
								System.out.println(partyViewOptionNumber + ": " + parties.get(i).getPartyName());
								hostParties2.add(parties.get(i));
								// updates the party number to prepare for the next party to be shown
								partyViewOptionNumber++;
							}
						}
						
						// uses an if-else statement to ensure that the host has actually created at least one party to view
							// accounts for if the current user has NOT created any parties
						if (hostParties2.isEmpty()) {
							System.out.println("You have not created any parties yet.");
						}
						// accounts for if the current user has created at least one party to view
						else {
							// gathers the user for their choice of which party to modify
							Party selectedParty = hostParties2.get((bff.inputInt("\nEnter the number of the party below:", 1, hostParties2.size())) - 1);
							System.out.print("\n");
							
							System.out.println("Here is what your event information will display to your invited guests:");
							// calls the showPartyInfo interface method to display the information about the event to the host
							selectedParty.showPartyInfo();
						}
						System.out.print("\n");
						
						break;
					// accounts for the case that the user wishes to logout
					case LOGOUT:
						// updates the login variables to reflect a logout
						loggedIn = false;
						user = null;
						// notifies the user of a successful logout
						System.out.println("\nYou have successfully logged out.");
						System.out.print("\n");
						
						break;
					default:
						System.out.println("Unexpected error.");
						System.out.print("\n");
					}
					
				} while (loggedIn);
			}
		
			exit = bff.inputBoolean("Would you still like to use Evite? (true/false)");
			System.out.print("\n");
    	} while (exit);
		
		System.out.println("Thank you for using Evite! 🎉");

	}
	
    // utilizes the isRegistered method from HW08 to check if the current user already has a registered account with the email
	private boolean isRegistered(String email) {
		// uses an if statement to return a true value if the email is found within the map
		if(userMap.containsKey(email)) {
			return true;
		}
		return false;
	}
    
	// utilizes the login method from HW08 to login the user into the Evite system
    private boolean login(String email) {
		// creates a counter for the number of incorrect attempts
		int incorrectAttempts = 1;
		// creates a check for the correct password used after the do-while loop exits
		boolean correctPassword = false;
			
		// uses a do-while loop to prompt entering the password until the correct password is entered or the incorrect attempts reach the maximum
		do {
			// prompts the user to enter the password
			String password = bff.inputWord("Please enter your password: ");
			// uses an if-else statement to check if the password is correct
			if (password.equals(userMap.get(email).getPassword().strip())) {
				// assigns the correctPassword variable to true if the password is correct
				correctPassword = true;
			}
			else {
				System.out.println("Incorrect password.");
				incorrectAttempts++;
				
				// uses an if statement to break out of the do-while loop if the number of incorrect attempts exceeds three
				if (incorrectAttempts > 3) {
					break;
				}
			}	
		} while (!correctPassword);
		
		if (correctPassword) {
			System.out.println("Login successful. Welcome back!\n");
			return true;
		}
		else {
			System.out.println("Please ensure you are entering your password correctly.");
			return false;
		}
		
    }
	
	// creates the main method that runs the program
    public static void main(String[] args) {
		EviteSystem e = new EviteSystem();
		e.run();
	}
	
}
	
