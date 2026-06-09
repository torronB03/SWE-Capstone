package models;

/**
 * FoodBank.java
 * This class represents a Food Bank entity with various attributes and methods to manage its data.
 * It includes fields for primary key, organization name, user data index, accommodation limit,
 * hours of operation, location coordinates, address, contact information, location notes,
 * and permanent status.
 */
public class FoodBank {
    private String fbDataPk;           // Primary key (e.g., 'foba00001')
    private Long fbDataIndex;          // Auto-generated index (bigint)
    private String orgName;            // Organization name
    private Long orgDataIndex;         // Foreign key to Organization (org_data_index)
    private Long userDataIndex;        // Foreign key to ENDUSER (user_data_index)
    private Integer accommodationLimit; // Accommodation limit (integer)
    private String hoursOfOperation;   // Hours of operation (JSON as String)
    private String coordinateLocation; // Geographic location (as text)
    private String address;            // Address of the food bank
    private String contactInfo;        // Contact information
    private String locationNotes;      // Location notes (JSON as String)
    private String permanent;          // Permanent status ('event', 'volunteer', 'place')

    /**
     * Default constructor.  Initializes the object with default values.
     * This constructor is used when creating a new instance of the FoodBank class without any initial data.
     */
    public FoodBank() {}

    // Constructor for creating a FoodBank instance with necessary fields
    /**
     * Constructor with all fields. Initializes the object with input values.
     * This constructor is used when creating a new instance of the FoodBank class without any initial data.
     * @param orgName the primary key of the FoodBank entity
     * @param orgDataIndex  the incremental data of the FoodBank entity
     * @param userDataIndex the linked organization-name
     * @param accommodationLimit the saved accommodation limit
     * @param hoursOfOperation the operating hours
     * @param coordinateLocation the Coordinates Info
     * @param address the listed Address
     * @param contactInfo the Contact Info
     * @param locationNotes the Location Notes
     * @param permanent the permanent value
     */
    public FoodBank(String orgName, 
                    Long orgDataIndex, 
                    Long userDataIndex, 
                    Integer accommodationLimit, 
                    String hoursOfOperation, 
                    String coordinateLocation, 
                    String address, 
                    String contactInfo, 
                    String locationNotes, 
                    String permanent) {
        this.orgName = orgName;
        this.orgDataIndex = orgDataIndex;
        this.userDataIndex = userDataIndex;
        this.accommodationLimit = accommodationLimit;
        this.hoursOfOperation = hoursOfOperation;
        this.coordinateLocation = coordinateLocation;
        this.address = address;
        this.contactInfo = contactInfo;
        this.locationNotes = locationNotes;
        this.permanent = permanent;
    }

    // Getters and Setters
    /**
    * @return returns the primary key of the FoodBank entity
    */
    public String getFbDataPk() {
        return fbDataPk;
    }
    /**
    * @param fbDataPk sets the primary key of FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setFbDataPk(String fbDataPk) {
        this.fbDataPk = fbDataPk;
    }

    /**
    * @return returns the incremental data of the FoodBank entity
    */
    public Long getFbDataIndex() {
        return fbDataIndex;
    }
    /**
    * @param fbDataIndex sets the incremental data of FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setFbDataIndex(Long fbDataIndex) {
        this.fbDataIndex = fbDataIndex;
    }

    /**
    * @return returns the linked organization-name of the FoodBank entity
    */
    public String getOrgName() {
        return orgName;
    }
    /**
    * @param orgName sets the linked organization-name of FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
    * @return returns the linked organization-incremental index of the FoodBank entity
    */
    public Long getOrgDataIndex() {
        return orgDataIndex;
    }
    /**
    * @param orgDataIndex sets the linked organization-incremental index of FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgDataIndex(Long orgDataIndex) {
        this.orgDataIndex = orgDataIndex;
    }

    /**
    * @return returns the linked user-incremental index of the FoodBank entity
    */
    public Long getUserDataIndex() {
        return userDataIndex;
    }
    /**
    * @param userDataIndex sets the linked user-incremental index of FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setUserDataIndex(Long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }

    /**
    * @return returns the saved accommodation limit of the FoodBank entity
    */
    public Integer getAccommodationLimit() {
        return accommodationLimit;
    }
    /**
    * @param accommodationLimit sets the saved accommodation limit of FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAccommodationLimit(Integer accommodationLimit) {
        this.accommodationLimit = accommodationLimit;
    }

    /**
    * @return returns the operating hours of the FoodBank entity
    */
    public String getHoursOfOperation() {
        return hoursOfOperation;
    }
    /**
    * @param hoursOfOperation sets the operating hours of a FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    /**
    * @return returns the Coordinates Info of the FoodBank entity
    */
    public String getCoordinateLocation() {
        return coordinateLocation;
    }
    /**
    * @param coordinateLocation sets the Coordinates of an FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCoordinateLocation(String coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    /**
    * @return returns the listed Address of the FoodBank entity
    */
    public String getAddress() {
        return address;
    }
    /**
    * @param address sets the listed Address of an FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
    * @return returns the Contact Info of the FoodBank entity
    */
    public String getContactInfo() {
        return contactInfo;
    }
    /**
    * @param contactInfo sets the Contact Info of an FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
    * @return returns the Location Notes of the FoodBank entity
    */
    public String getLocationNotes() {
        return locationNotes;
    }
    /**
    * @param locationNotes sets the Location Notes of an FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    /**
    * @return returns the permanent value of the FoodBank entity
    */
    public String getPermanent() {
        return permanent;
    }
     /**
    * @param permanent sets the permanent value of an FoodBank object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setPermanent(String permanent) {
        if ("event".equals(permanent) || "volunteer".equals(permanent) || "place".equals(permanent)) {
            this.permanent = permanent;
        } else {
            throw new IllegalArgumentException("Invalid value for permanent: " + permanent);
        }
    }
}
