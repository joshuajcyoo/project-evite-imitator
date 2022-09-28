import java.util.Objects;

public abstract class User {

	// creates the instance variables
	private String email;
	private String name;
	private String password;
	
	// creates the default constructor
	public User(String email, String name, String password) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
	}
	
	// creates the accessor for the email
	public String getEmail() {
		return email;
	}

	// creates the mutator for the email
	public void setEmail(String email) {
		this.email = email;
	}

	// creates the accessor for the name
	public String getName() {
		return name;
	}

	// creates the mutator for the name
	public void setName(String name) {
		this.name = name;
	}

	// creates the accessor for the password
	public String getPassword() {
		return password;
	}

	// creates the mutator for the password
	public void setPassword(String password) {
		this.password = password;
	}

	// creates the equals method for the user
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}
	
	
	
}
