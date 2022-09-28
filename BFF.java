import java.util.Scanner;

public class BFF{
	private Scanner sc;

	public BFF(){
		sc = new Scanner(System.in);
	}

	public String inputWord(String prompt) {
		System.out.print(prompt + "\n> ");
		String word = sc.next();
		sc.nextLine(); 
		return word;
	}

	public String inputLine(String prompt) {
		System.out.print(prompt + "\n> " );
		return sc.nextLine();
	}

	public int inputInt(String prompt) {
		boolean gotInt = false;
		int num = 0;
		while(!gotInt){
			System.out.print(prompt + "\n> ");
			if(!sc.hasNextInt()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not an int. Please enter a whole number");

			}
			else{
				gotInt = true;
				num = sc.nextInt();
				sc.nextLine();//clear
			}
		}


		return num;
	}

	public int inputInt(String prompt, int minValue, int maxValue) {

		int number = inputInt(prompt);
		while (!(number >= minValue && number <= maxValue))
		{
			System.out.println("Please enter a valid number in the range (" + minValue + " and " + maxValue + "): ");
			number = inputInt(prompt);
		}
		return number;
	}

	public int inputInt(String question, int max) {
		return inputInt(question, 0, max);
	}

	public double inputDouble(String prompt) {

		boolean gotDouble = false;
		double num = 0.0;
		while(!gotDouble){
			System.out.println (prompt);
			if(!sc.hasNextDouble()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not a double. Please enter a floating point number");

			}
			else{
				gotDouble = true;
				num = sc.nextDouble();
				sc.nextLine();//clear
			}
		}
		return num;
	}

	public double inputDouble(String prompt, double min, double max) {

		double number = inputDouble(prompt);
		boolean closeEnough =Math.abs(number - min) <= 0.001 
				|| Math.abs(number - max) <= 0.001  ;
		while (!((number >= min && number <= max) || closeEnough ))
		{
			System.out.println("Please enter a valid number in the range (" + min + " and " + max + "): ");
			number = inputDouble(prompt);
		}
		return number;

	}

	public boolean inputBoolean(String prompt) {
		boolean gotBool = false;
		boolean bool = false;
		while(!gotBool){ // no good
			System.out.println (prompt);
			if(!sc.hasNextBoolean()){
				String garbage = sc.nextLine();
				System.out.println(garbage + " was not a boolean. Please enter either true or false");

			}
			else{
				gotBool = true; //input was good
				bool = sc.nextBoolean(); //get the answer
				sc.nextLine();//clear
			}
		}

		return bool;

	}

	public boolean inputYesNo(String prompt) {

		System.out.println(prompt);
		String output = sc.nextLine();
		while (!(output.equalsIgnoreCase("y") || output.equalsIgnoreCase("n") 
				|| output.equalsIgnoreCase("yes") || output.equalsIgnoreCase("no"))) {
			System.out.println("Sorry, invalid input. Input yes or no.");
			output = sc.nextLine();
		}

		return (output.equalsIgnoreCase("y") || output.equalsIgnoreCase("yes"));


	}
	public void print(String msg){
		System.out.println(msg);   
	}
	
	public void printFancy(String msg){
		System.out.println("********************************");
		System.out.println(msg);   
		System.out.println("********************************");
	}


	public String inputWord(String prompt, String choice1, String choice2){
		String userChoice = inputWord(prompt);
		while (!(userChoice.equalsIgnoreCase(choice1) || userChoice.equalsIgnoreCase(choice2))){
			this.print("Sorry " + userChoice + " not recongized as valid response. Please type \"" + choice1 + "\" or \"" + choice2 + "\"");
			userChoice = inputWord(prompt);

		}
		return userChoice;
	}

	public String inputWord(String prompt, String choice1, String choice2, String choice3){
		String userChoice = inputWord(prompt);
		while (!(userChoice.equalsIgnoreCase(choice1) || userChoice.equalsIgnoreCase(choice2) || userChoice.equalsIgnoreCase(choice3))){
			this.print("Sorry " + userChoice + " not recongized as valid response. Please type \"" + choice1 + "\" or \"" + choice2 + "\" or \"" + choice3  +"\"");
			userChoice = inputWord(prompt);

		}
		return userChoice;
	}
	
}
