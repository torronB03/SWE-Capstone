package models;

/**
* User.java
* User is a class-model that represents the database entity of ClothingStore, and connects it to this data-type's DAO
* @attributes id, passwordHash, firstName, lastName, username, over18, transport, authenticated
* @methods  OUTPUT:  getAuth, getUserID, getUserEmail, getUserPasswordHash, getFullName, getFirstName, getLastName, getUsername, isOver18, getTransport
* @methods  INPUT:  authenticate, setUserID, setUserEmail, setUserPasswordHash, setFirstName, setLastName, setUsername, setOver18, getTransport
 */
public class User {
    private int id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String username;
    private boolean over18;
    private String transport;

    private boolean authenticated = false;

    // model constructor
    /**
     * Default constructor. Initializes the object with default values.
     * @param email set user ID
     * @param passwordHash set user email
     */
    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    /**
     * Constructor for creating a new user with all fields.
     * @param id set user ID
     * @param email set user email
     * @param passwordHash set user password hash
     * @param firstName set user first name
     * @param lastName set user last name
     * @param username set user username
     * @param over18 set user over18 status
     * @param transport set user transport type
     */
    public User ( int id,
                String email,
                String passwordHash,
                String firstName,
                String lastName,
                String username,
                boolean over18,
                String transport){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.over18 = over18;
        this.transport = transport;
    }

    // Auth
    /**
     * @return gives authenticated field
     */
    public boolean getAuth() { return authenticated; }
    /**
     * sets authenticated to true
     */
    public void authenticate() 
    {
        // do some validation here with data and database maybe?
        authenticated = true;
    }

    // Core fields
    /**
     * @return get id value
     */
    public int getUserId() { return id; }
    /**
     * @param id ID value to change user ID to
     */
    public void setUserId(int id) { this.id = id; }

    /**
     * @param email email value to change user email to
     */
    public void setUserEmail(String email) { this.email = email; }
    /**
     * @return get email value
     */
    public String getUserEmail() { return email; }

    /**
     * @return get password value
     */
    public String getUserPasswordHash() { return passwordHash; }
    /**
     * @param passwordHash password value to change user password to
     */
    public void setUserPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    // Extra profile fields
    /**
     * @return get first name and last name combined
     */
    public String getFullName() { return firstName + " " + lastName; }
    /**
     * @return get first name
     */
    public String getFirstName() { return firstName; }
    /**
     * @return get last name
     */
    public String getLastName() { return lastName; }
    /**
     * @param name name value to change user first name to
     */
    public void setFirstName(String name) { this.firstName = name; }
    /**
     * @param name name value to change user last name to
     */
    public void setLastName(String name) { this.lastName = name; }

    /**
     * @return get user's username
     */
    public String getUsername() { return username; }
     /**
     * @param username username value to change user username to
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * @return get user's over18 status
     */
    public boolean isOver18() { return over18; }
     /**
     * @param over18 over18 value to change user over18 status to
     */
    public void setOver18(boolean over18) { this.over18 = over18; }

    /**
     * @return get user's transport type
     */
    public String getTransport() { return transport; }
     /**
     * @param transport transport value to change user transport to
     */
    public void setTransport(String transport) { this.transport = transport; }
}

