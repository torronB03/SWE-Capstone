package models;

import java.sql.Timestamp;

/**
* VolunteerOpportunity is a class-model that represents the database entity of VolunteerOpportunity, and connects it to this data-type's DAO
* @attributes voDataIndex,voDataPk, orgName, userDataIndex, voDescription, eventDate, ongoing, requirementsJson, coordinateLocation, address, contactInfo, locationNotes, permanent
* @methods  OUTPUT:  getVoDataIndex, getVoDataPk, getOrgName, getUserDataIndex, getVoDescription, getEventDate, isOngoing, getRequirementsJson, getCoordinateLocation, getAddress, getContactInfo, getLocationNotes, getPermanent
* @methods  INPUT:  setVoDataIndex, setVoDataPk, setOrgName, setUserDataIndex, setVoDescription, setEventDate, setOngoing, setRequirementsJson, setCoordinateLocation, setAddress, setContactInfo, setLocationNotes, setPermanent
*/
public class VolunteerOpportunity {
    private long voDataIndex;
    private String voDataPk;
    private String orgName;
    private long userDataIndex;
    private String voDescription;
    private Timestamp eventDate;
    private boolean ongoing;
    private String requirementsJson;
    private String coordinateLocation;
    private String address;
    private String contactInfo;
    private String locationNotes;
    private String permanent;

    /**
     * Default constructor.  Initializes the object with default values.
     * This constructor is used when creating a new instance of the Shelter class without any initial data.
     */
    public VolunteerOpportunity() {
    }

    /**
     * Constructor with all fields. Initializes the object with input values.
     * This constructor is used when creating a new instance of the VolunteerOpportunity class without any initial data.
     * @param voDataIndex sets the opportunity increment 
     * @param voDataPk sets the opportunity PK / primary key
     * @param orgName sets the name to the opportunity
     * @param userDataIndex sets the user increment linked to the volunteer opportunity
     * @param voDescription sets the opportunity description
     * @param eventDate sets the opportunity event date
     * @param ongoing sets a flag that indicates if the opportunity is ongoing
     * @param requirementsJson sets the requirements as a json string
     * @param coordinateLocation sets the coordinates of the opportunity
     * @param address sets the address of the opportunity
     * @param contactInfo sets the contact info of the opportunity
     * @param locationNotes sets the location notes of the opportunity
     * @param permanent sets the permanent value of the opportunity
     */
    public VolunteerOpportunity(long voDataIndex, String voDataPk, String orgName, long userDataIndex,
                                 String voDescription, Timestamp eventDate, boolean ongoing,
                                 String requirementsJson, String coordinateLocation, String address,
                                 String contactInfo, String locationNotes, String permanent) {
        this.voDataIndex = voDataIndex;
        this.voDataPk = voDataPk;
        this.orgName = orgName;
        this.userDataIndex = userDataIndex;
        this.voDescription = voDescription;
        this.eventDate = eventDate;
        this.ongoing = ongoing;
        this.requirementsJson = requirementsJson;
        this.coordinateLocation = coordinateLocation;
        this.address = address;
        this.contactInfo = contactInfo;
        this.locationNotes = locationNotes;
        this.permanent = permanent;
    }
    
    /**
     * @return gets the opportunity increment
     */
    public long getVoDataIndex() { return voDataIndex; }
    /**
     * @param voDataIndex sets the opportunity increment
     */
    public void setVoDataIndex(long voDataIndex) { this.voDataIndex = voDataIndex; }

    /**
     * @return gets the opportunity PK / primary key
     */
    public String getVoDataPk() { return voDataPk; }
    /**
     * @param voDataPk sets the opportunity PK / primary key
     */
    public void setVoDataPk(String voDataPk) { this.voDataPk = voDataPk; }

    /**
     * @return gets the linked organization to the opportunity 
     */
    public String getOrgName() { return orgName; }
    /**
     * @param orgName sets the linked organization to the opportunity 
     */
    public void setOrgName(String orgName) { this.orgName = orgName; }

    /**
     * @return gets the user increment linked to the volunteer opportunity
     */
    public long getUserDataIndex() { return userDataIndex; }
    /**
     * @param userDataIndex sets the user increment linked to the volunteer opportunity
     */
    public void setUserDataIndex(long userDataIndex) { this.userDataIndex = userDataIndex; }

    /**
     * @return gets the opportunity description
     */
    public String getVoDescription() { return voDescription; }
    /**
     * @param voDescription sets the opportunity description
     */
    public void setVoDescription(String voDescription) { this.voDescription = voDescription; }

    /**
     * @return gets the opportunity event date
     */
    public Timestamp getEventDate() { return eventDate; }
    /**
     * @param eventDate sets the opportunity event date
     */
    public void setEventDate(Timestamp eventDate) { this.eventDate = eventDate; }

    /**
     * @return gets a flag that indicates if the opportunity is ongoing
     */
    public boolean isOngoing() { return ongoing; }
    /**
     * @param ongoing sets a flag that indicates if the opportunity is ongoing
     */
    public void setOngoing(boolean ongoing) { this.ongoing = ongoing; }

    /**
     * @return gets the json reqs
     */
    public String getRequirementsJson() {
        return (requirementsJson == null || requirementsJson.isBlank()) ? "{}" : requirementsJson;
    }
    /**
     * @param requirementsJson sets the json reqs
     */
    public void setRequirementsJson(String requirementsJson) { this.requirementsJson = requirementsJson; }

    /**
     * @return gets the coordinates of the opportunity 
     */
    public String getCoordinateLocation() { return coordinateLocation; }
    /**
     * @param coordinateLocation sets the coordinates of the opportunity 
     */
    public void setCoordinateLocation(String coordinateLocation) { this.coordinateLocation = coordinateLocation; }

    /**
     * @return gets the address of the opportunity 
     */
    public String getAddress() { return address; }
    /**
     * @param address sets the address of the opportunity 
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * @return gets the contact info of the opportunity 
     */
    public String getContactInfo() { return contactInfo; }
    /**
     * @param contactInfo sets the contact info of the opportunity 
     */
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    /**
     * @return gets the location notes of the opportunity 
     */
    public String getLocationNotes() { return locationNotes; }
    /**
     * @param locationNotes sets the location notes of the opportunity 
     */
    public void setLocationNotes(String locationNotes) { this.locationNotes = locationNotes; }

    /**
     * @return gets the permanent value of the opportunity 
     */
    public String getPermanent() { return permanent; }
    /**
     * @param permanent sets the permanent value of the opportunity
     */
    public void setPermanent(String permanent) { this.permanent = permanent; }
}
