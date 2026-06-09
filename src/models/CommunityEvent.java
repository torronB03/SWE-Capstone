package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
* CommunityEvent is a class-model that represents the database entity of CommunityEvent, and connects it to this data-type's DAO
* @attributes eDataPk, name, eventDate, eventTime, description, category, spaceAvailable, attendeeCount, 
* ...... userDataIndex, address, contactInfo, coordinateLocation, locationNotes, permanent
* @methods  OUTPUT:  getShDataPk, getShDataIndex, getOrgName, getOrgDataIndex, getUserDataIndex, getAverageWaitTime, getAddress, getContactInfo, getAddress, getServicesProvided, getCoordinateLocation, getLocationNotes, getPermanent
* @methods  INPUT:  setShDataPk, setShDataIndex, setOrgName, setOrgDataIndex, setUserDataIndex, setAverageWaitTime, setAddress, setContactInfo, setAddress, setServicesProvided, setCoordinateLocation, setLocationNotes, setPermanent, setCommunityEventLocation
*/

public class CommunityEvent {
    private String eDataPk;
    private String name;              // Previously "title"
    private String contactInfo;       // Previously "organizerName"
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String address;
    private String locationNotes;     // Optional: Add if your DB has it
    private String description;
    private String category;          // Optional: If stored in DB
    private int attendeeCount;        // Optional
    private Object coordinateLocation; // Assuming PostGIS or similar
    private String permanent;
    private long userDataIndex;

    /**
     * Default constructor.  Initializes the object with default values.
     * This constructor is used when creating a new instance of the CommunityEvent class without any initial data.
     */
    public CommunityEvent() {}

    /**
     * Constructor (only include what you need for inserts)
     * @param name -
     * @param contactInfo -
     * @param eventDate -
     * @param eventTime -
     * @param address -
     * @param city -
     * @param state -
     * @param zipcode -
     * @param description -
     * @param userDataIndex -
     */
    public CommunityEvent(String name,
                          String contactInfo,
                          LocalDate eventDate,
                          LocalTime eventTime,
                          String address,
                          String city,
                          String state,
                          String zipcode,
                          String description,
                          long userDataIndex) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.address = address;
        this.description = description;
        this.userDataIndex = userDataIndex;
    }

    // Getters and setters
    /**
     * @return returns the primary key of the CommunityEvent entity
     */
    public String geteDataPk() {
        return eDataPk;
    }
    /**
     * sets the primary key of the CommunityEvent entity within an interface object
     * @param eDataPk sets the primary key of CommunityEvent object as it exists (to interface with
     * the database entry in the DAO linked list)
     */
    public void seteDataPk(String eDataPk) {
        this.eDataPk = eDataPk;
    }

    /**
     * @return returns the name of the event
     */
    public String getName() {
        return name;
    }
    /**
     * @param name sets teh name of the event within an interface object
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return obtains the contact info
     */
    public String getContactInfo() {
        return contactInfo;
    }
    /**
     * @param contactInfo sets the contact info within an interface object
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * @return obtains the event date
     */
    public LocalDate getEventDate() {
        return eventDate;
    }
    /**
     * @param eventDate sets the event date info within an interface object
     */
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    /**
     * @return gets the event time
     */
    public LocalTime getEventTime() {
        return eventTime;
    }
    /**
     * @param eventTime setes the event time  within an interface object
     */
    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * @return gets an event's address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address sets the event address within an interface object
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return gets the notes of an event's location notes
     */
    public String getLocationNotes() {
        return locationNotes;
    }
    /**
     * @param locationNotes sets the location notes within an interface object
     */
    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    /**
     * @return gets the event description
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description sets the description within an interface object
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return gets the category of the event
     */
    public String getCategory() {
        return category;
    }
    /**
     * @param category sets the category within an interface object
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return gets the attendee count of the event
     */
    public int getAttendeeCount() {
        return attendeeCount;
    }
    /**
     * @param attendeeCount sets the attendee count within an interface object
     */
    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    /**
     * @return gets the coordinate location
     */
    public Object getCoordinateLocation() {
        return coordinateLocation;
    }
    /**
     * @param coordinateLocation sets the coordinate location within an interface object
     */
    public void setCoordinateLocation(Object coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    /**
     * @return gets the permanent value of the event
     */
    public String isPermanent() {
        return permanent;
    }
    /**
     * @param permanent sets the permanent value of the event within an interface object
     */
    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    /**
     * @return gets the user increment
     */
    public long getUserDataIndex() {
        return userDataIndex;
    }
    /**
     * @param userDataIndex sets the user increment within an interface object
     */
    public void setUserDataIndex(long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }
}
