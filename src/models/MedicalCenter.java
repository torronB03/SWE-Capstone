package models;

/**
 * 
 */
public class MedicalCenter {
    private String medDataPk;        // Primary key as string (generated as 'med' + med_data_index)
    private long medDataIndex;       // Auto-generated long for the index (primary key)
    private String orgName;          // Organization name (foreign key)
    private long orgDataIndex;       // Organization data index (foreign key)
    private long userDataIndex;      // User data index (foreign key)
    private int averageWaitTime;     // Average wait time (integer)
    private String address;          // Address
    private String contactInfo;      // Contact info (phone number)
    private String servicesProvided; // JSON string or object for services
    private String coordinateLocation; // Coordinate location in text (could be geo-location info)
    private String locationNotes;    // JSON string or object for additional location notes
    private String permanent;        // Permanent status (event, volunteer, or place)

    /**
     * Default constructor.  Initializes the object with default values.
     * This constructor is used when creating a new instance of the MedicalCenter class without any initial data.
     */
    public MedicalCenter() {}

    /**
     * Constructor with all fields. Initializes the object with input values.
     * This constructor is used when creating a new instance of the MedicalCenter class without any initial data.
     * @param medDataPk the primary key of the MedicalCenter entity
     * @param medDataIndex the incremental data of the MedicalCenter
     * @param orgName the linked organization-name
     * @param orgDataIndex the linked organization-incremental index of the organizer entity
     * @param userDataIndex the linked user-incremental index of the user entity
     * @param averageWaitTime the average wait time
     * @param address the listed Address
     * @param contactInfo the Contact Info
     * @param servicesProvided the services provided by a MedicalCenter object
     * @param coordinateLocation the Coordinates Info of the MedicalCenter
     * @param locationNotes the Location Notes
     * @param permanent the permanent value
     */
    public MedicalCenter(String medDataPk, long medDataIndex, String orgName, long orgDataIndex, long userDataIndex,
                         int averageWaitTime, String address, String contactInfo, String servicesProvided,
                         String coordinateLocation, String locationNotes, String permanent) {
        this.medDataPk = medDataPk;
        this.medDataIndex = medDataIndex;
        this.orgName = orgName;
        this.orgDataIndex = orgDataIndex;
        this.userDataIndex = userDataIndex;
        this.averageWaitTime = averageWaitTime;
        this.address = address;
        this.contactInfo = contactInfo;
        this.servicesProvided = servicesProvided;
        this.coordinateLocation = coordinateLocation;
        this.locationNotes = locationNotes;
        this.permanent = permanent;
    }

    // Getters and Setters
    /**
    * @return returns the primary key of the MedicalCenter entity
    */
    public String getMedDataPk() {
        return medDataPk;
    }
    /**
    * @param medDataPk sets the primary key of MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setMedDataPk(String medDataPk) {
        this.medDataPk = medDataPk;
    }

    /**
    * @return returns the incremental data of the MedicalCenter entity
    */
    public long getMedDataIndex() {
        return medDataIndex;
    }
    /**
    * @param medDataIndex sets the incremental data of MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setMedDataIndex(long medDataIndex) {
        this.medDataIndex = medDataIndex;
    }

    /**
    * @return returns the linked organization-name of the MedicalCenter entity
    */
    public String getOrgName() {
        return orgName;
    }
    /**
    * @param orgName sets the linked organization-name of MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
    * @return returns the linked organization-incremental index of the MedicalCenter entity
    */
    public long getOrgDataIndex() {
        return orgDataIndex;
    }
    /**
    * @param orgDataIndex sets the linked organization-incremental index of MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgDataIndex(long orgDataIndex) {
        this.orgDataIndex = orgDataIndex;
    }

    /**
    * @return returns the linked user-incremental index of the MedicalCenter entity
    */
    public long getUserDataIndex() {
        return userDataIndex;
    }
    /**
    * @param userDataIndex sets the linked user-incremental index of MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setUserDataIndex(long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }

    /**
    * @return returns the average wait time of the MedicalCenter entity
    */
    public int getAverageWaitTime() {
        return averageWaitTime;
    }
    /**
    * @param averageWaitTime sets the average wait time of MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAverageWaitTime(int averageWaitTime) {
        this.averageWaitTime = averageWaitTime;
    }

    /**
    * @return returns the listed Address of the MedicalCenter entity
    */
    public String getAddress() {
        return address;
    }
    /**
    * @param address sets the listed Address of an MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
    * @return returns the Contact Info of the MedicalCenter entity
    */
    public String getContactInfo() {
        return contactInfo;
    }
    /**
    * @param contactInfo sets the Contact Info of an MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
    * @return returns the services provided by the MedicalCenter entity
    */
    public String getServicesProvided() {
        return servicesProvided;
    }
    /**
    * @param servicesProvided sets the services provided by a MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setServicesProvided(String servicesProvided) {
        this.servicesProvided = servicesProvided;
    }

    /**
    * @return returns the Coordinates Info of the MedicalCenter entity
    */
    public String getCoordinateLocation() {
        return coordinateLocation;
    }
    /**
    * @param coordinateLocation sets the Coordinates of an MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCoordinateLocation(String coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    /**
    * @return returns the Location Notes of the MedicalCenter entity
    */
    public String getLocationNotes() {
        return locationNotes;
    }
    /**
    * @param locationNotes sets the Location Notes of an MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    /**
    * @return returns the permanent value of the MedicalCenter entity
    */
    public String getPermanent() {
        return permanent;
    }
    /**
    * @param permanent sets the permanent value of an MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    /**
    * @param location sets the address of an MedicalCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    // DAO requires this method for uniform DAO mapping
    public void setMedicalCenterLocation(String location) {
        this.address = location;
    }
}
