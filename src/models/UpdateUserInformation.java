package models;

/**
 * UpdateUserInformation class is used to update the user information.
 * It contains the username and profile description of the user.
 */
public class UpdateUserInformation {
    private String userName;
    private String profileDescription;

    /**
     * Default constructor. Initializes the object with default values.
     * @return username
     */
    public String getUserName() {
        return userName;
    }
    /**
     * @param userName sets the username
     */
    /**
     * @param userName sets the username
     */
    public void setUserName (String userName) {
            this.userName = userName;
    }

    /**
     * @return profile description
     */
    public String getProfileDescription() {
        return profileDescription;
    }
    /**
     * @param profileDescription sets the profile description
     */
    public void setProfileDescription (String profileDescription){
        this.profileDescription = profileDescription;
    }
 }