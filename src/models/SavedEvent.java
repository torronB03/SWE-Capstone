package models;

import java.sql.Timestamp;

/**
 * SavedEvent is a class-model that represents the database entity of SavedEvent, and connects it to this data-type's DAO
 * @attributes userDataIndex, resourceDataPk, savedAt, userCategory
 * @methods  OUTPUT:  getUserDataIndex, getResourceDataPk, getSavedAt, getUserCategory
 * @methods  INPUT:  setUserDataIndex, setResourceDataPk, setSavedAt, setUserCategory
 */
public class SavedEvent {
    private long userDataIndex;
    private String resourceDataPk;
    private Timestamp savedAt;
    private String userCategory;

    // Default constructor
    /**
     * Default constructor. Initializes the object with default values.
     */
    public SavedEvent() {}

    // Parameterized constructor
    /**
     * Constructor with all fields. Initializes the object with input values.
     * @param userDataIndex sets the user increment linked to the saved event
     * @param resourceDataPk sets the resource primary key
     * @param savedAt sets the timestamp when the event was saved
     * @param userCategory sets the category of the event
     */
    public SavedEvent(long userDataIndex, String resourceDataPk, Timestamp savedAt, String userCategory) {
        this.userDataIndex = userDataIndex;
        this.resourceDataPk = resourceDataPk;
        this.savedAt = savedAt;
        this.userCategory = userCategory;
    }

    // Getters and Setters

    /**
     * @return gets the user increment linked to the saved event
     */
    public long getUserDataIndex() {
        return userDataIndex;
    }
    /**
     * @param userDataIndex sets the user increment linked to the saved event
     */
    public void setUserDataIndex(long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }

    /**
     * @return gets the resource primary key
     */
    public String getResourceDataPk() {
        return resourceDataPk;
    }

    /**
     * @param resourceDataPk sets the resource primary key
     */
    public void setResourceDataPk(String resourceDataPk) {
        this.resourceDataPk = resourceDataPk;
    }
    /**
     * @return gets the timestamp when the event was saved
     */
    public Timestamp getSavedAt() {
        return savedAt;
    }
    /**
     * @param savedAt sets the timestamp when the event was saved
     */
    public void setSavedAt(Timestamp savedAt) {
        this.savedAt = savedAt;
    }
    /**
     * @return gets the category of the event
     */
    public String getUserCategory() {
        return userCategory;
    }
    /**
     * @param userCategory sets the category of the event
     */
    public void setUserCategory(String userCategory) {
        this.userCategory = userCategory;
    }

    // Optional: toString method for easier debugging/logging
    @Override
    /**
     * @return string representation of the SavedEvent object
     */
    public String toString() {
        return "SavedEvent{" +
                "userDataIndex=" + userDataIndex +
                ", resourceDataPk='" + resourceDataPk + '\'' +
                ", savedAt=" + savedAt +
                ", userCategory='" + userCategory + '\'' +
                '}';
    }
}
