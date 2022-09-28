
public class Host extends Guest{

	// creates the instance variable for the premium status
	private boolean isHost;

	// creates the constructor and updates to include the premium status
	public Host(String email, String name, String password, boolean isGuest, boolean isHost) {
		super(email, name, password, isGuest);
		this.isHost = isHost;
	}

	// creates the accessor for the host status
	public boolean getIsHost() {
		return isHost;
	}
	
	// creates the mutator for the host status
	public void setIsHost(boolean isHost) {
		this.isHost = isHost;
	}

}