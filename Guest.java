
public class Guest extends User {
	
	// creates the instance variable for the guest status
	private boolean isGuest;

	// creates the constructor and updates to include the guest status
	public Guest(String email, String name, String password, boolean isGuest) {
		super(email, name, password);
		this.isGuest = isGuest;
	}

	// creates the accessor for the guest status
	public boolean getIsGuest() {
		return isGuest;
	}

	// creates the mutator for the guest status
	public void setIsGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	@Override
	public String toString() {
		return getEmail() + " --> " + getName() + "; pass: " + getPassword();
	}
	
	
}
