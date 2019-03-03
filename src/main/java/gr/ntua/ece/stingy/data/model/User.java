package gr.ntua.ece.stingy.data.model;

public class User {

    private final long id;
    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;
    private final String token;
    private final String key;
    private final String email;
    private final String phoneNumber;
    private final String profilePic;
    private final long points;
    
    
    
	public User(long id, String username, String password, String firstName, String lastName, String token, String key,
			String email, String phoneNumber, String profilePic, long points) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.token = token;
		this.key = key;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.profilePic = profilePic;
		this.points = points;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @return the profilePic
	 */
	public String getProfilePic() {
		return profilePic;
	}
	/**
	 * @return the points
	 */
	public long getPoints() {
		return points;
	}
   
    
}