package models;

/**
* DOC last edited: 05/08 @ 05:22AM
* Shelter is a class-model that represents the database entity of Shelter, and connects it to this data-type's DAO
* @attributes shDataPk, shDataIndex, orgName, orgDataIndex, userDataIndex, averageWaitTime, spaceAvailable, maximumCapacity, 
* ...... hoursOfOperation, address, contactInfo, coordinateLocation, locationNotes, permanent
* @methods  OUTPUT:  getShDataPk, getShDataIndex, getOrgName, getOrgDataIndex, getUserDataIndex, getAverageWaitTime, getAddress, getContactInfo, getAddress, getServicesProvided, getCoordinateLocation, getLocationNotes, getPermanent
* @methods  INPUT:  setShDataPk, setShDataIndex, setOrgName, setOrgDataIndex, setUserDataIndex, setAverageWaitTime, setAddress, setContactInfo, setAddress, setServicesProvided, setCoordinateLocation, setLocationNotes, setPermanent, setShelterLocation
*/
public class Shelter {
    private String shDataPk;           
    private long shDataIndex;          
    private String orgName;            
    private long orgDataIndex;         
    private long userDataIndex;        
    private int spaceAvailable;        
    private int maximumCapacity;       
    private String hoursOfOperation;   
    private String address;            
    private String contactInfo;        
    private String coordinateLocation; 
    private String locationNotes;      
    private String permanent;          

    /**
     * Default constructor.  Initializes the object with default values.
     * This constructor is used when creating a new instance of the Shelter class without any initial data.
     */
    public Shelter() {}

    /**
     * Constructor with all fields. Initializes the object with input values.
     * This constructor is used when creating a new instance of the Shelter class without any initial data.
     * @param shDataPk returns the primary key
     * @param shDataIndex sets the incremental data
     * @param orgName the linked organization-name
     * @param orgDataIndex the linked organization-incremental index
     * @param userDataIndex the linked user-incremental
     * @param spaceAvailable the available space
     * @param maximumCapacity the maximum capacity
     * @param hoursOfOperation the hours
     * @param address the listed Address
     * @param contactInfo the Contact Info
     * @param coordinateLocation the Coordinates
     * @param locationNotes the Location Notes
     * @param permanent the permanent value of the Shelter entity
     */
    public Shelter(String shDataPk, long shDataIndex, String orgName, long orgDataIndex, long userDataIndex,
                   int spaceAvailable, int maximumCapacity, String hoursOfOperation, String address,
                   String contactInfo, String coordinateLocation, String locationNotes, String permanent) {
        this.shDataPk = shDataPk;
        this.shDataIndex = shDataIndex;
        this.orgName = orgName;
        this.orgDataIndex = orgDataIndex;
        this.userDataIndex = userDataIndex;
        this.spaceAvailable = spaceAvailable;
        this.maximumCapacity = maximumCapacity;
        this.hoursOfOperation = hoursOfOperation;
        this.address = address;
        this.contactInfo = contactInfo;
        this.coordinateLocation = coordinateLocation;
        this.locationNotes = locationNotes;
        this.permanent = permanent;
    }

    // Getters and Setters
    /**
    * @return returns the primary key of the Shelter entity
    */
    public String getShDataPk() {
        return shDataPk;
    }
    /**
    * @param shDataPk sets the primary key of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setShDataPk(String shDataPk) {
        this.shDataPk = shDataPk;
    }
    /**
    * @return returns the incremental data of the Shelter entity
    */
    public long getShDataIndex() {
        return shDataIndex;
    }
    /**
    * @param shDataIndex sets the incremental data of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setShDataIndex(long shDataIndex) {
        this.shDataIndex = shDataIndex;
    }

    /**
    * @return returns the linked organization-name of the Shelter entity
    */
    public String getOrgName() {
        return orgName;
    }
    /**
    * @param orgName sets the linked organization-name of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
    * @return returns the linked organization-incremental index of the Shelter entity
    */
    public long getOrgDataIndex() {
        return orgDataIndex;
    }
    /**
    * @param orgDataIndex sets the linked organization-incremental index of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgDataIndex(long orgDataIndex) {
        this.orgDataIndex = orgDataIndex;
    }

    /**
    * @return returns the linked user-incremental index of the Shelter entity
    */
    public long getUserDataIndex() {
        return userDataIndex;
    }
    /**
    * @param userDataIndex sets the linked user-incremental index of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setUserDataIndex(long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }

    /**
    * @return returns the available space of the Shelter entity
    */
    public int getSpaceAvailable() {
        return spaceAvailable;
    }
    /**
    * @param spaceAvailable sets the available space of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setSpaceAvailable(int spaceAvailable) {
        this.spaceAvailable = spaceAvailable;
    }

    /**
    * @return returns the maximum capacity of the Shelter entity
    */
    public int getMaximumCapacity() {
        return maximumCapacity;
    }
    /**
    * @param maximumCapacity sets the maximum capacity of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    /**
    * @return returns the hours of the Shelter entity
    */
    public String getHoursOfOperation() {
        return hoursOfOperation;
    }
    /**
    * @param hoursOfOperation sets the hours of Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    /**
    * @return returns the listed Address of the Shelter entity
    */
    public String getAddress() {
        return address;
    }
    /**
    * @param address sets the listed Address of an Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
    * @return returns the Contact Info of the Shelter entity
    */
    public String getContactInfo() {
        return contactInfo;
    }
    /**
    * @param contactInfo sets the Contact Info of an Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
    * @return returns the Coordinates Info of the Shelter entity
    */
    public String getCoordinateLocation() {
        return coordinateLocation;
    }
    /**
    * @param coordinateLocation sets the Coordinates of an Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCoordinateLocation(String coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    /**
    * @return returns the Location Notes of the Shelter entity
    */
    public String getLocationNotes() {
        return locationNotes;
    }
    /**
    * @param locationNotes sets the Location Notes of an Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    /**
    * @return returns the permanent value of the Shelter entity
    */
    public String getPermanent() {
        return permanent;
    }

    /**
    * @param permanent sets the permanent value of an Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    /**
    * @param location sets the address of an Shelter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    // Used for DAO mapping
    public void setShelterLocation(String location) {
        this.address = location;
    }
}
