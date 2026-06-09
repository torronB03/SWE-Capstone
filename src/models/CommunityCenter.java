package models;

import java.util.List;
/**
 * @author Abernard13
 * Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
 * DOC last edited: 05/08 @ 5:14PM
 * CommunityCenter is a model class that represents a community center entity.
 * It contains attributes and methods to manage the data related to community centers.
 * @attributes  ccDataPk, ccDataIndex, orgName, orgDataIndex, userDataIndex, hoursOfOperation, coordinateLocation,
 * address, contactInfo, offeredServices, locationNotes, permanent
 * @methods  SESSION:  CommunityCenter
 * @methods  OUTPUT:  getCcDataPk, getCcDataIndex, getOrgName, getOrgDataIndex, getUserDataIndex,
 * getHoursOfOperation, getCoordinateLocation, getAddress, getContactInfo,
 * getOfferedServices, getLocationNotes, getPermanent
 * @methods  MODIFICATIONS:  setCcDataPk, setCcDataIndex, setOrgName, setOrgDataIndex,
 * setUserDataIndex, setHoursOfOperation, setCoordinateLocation,
 * setAddress, setContactInfo, setOfferedServices,
 * setLocationNotes, setPermanent
 */
public class CommunityCenter {
    private String ccDataPk;
    private long ccDataIndex;  
    private String orgName;  
    private long orgDataIndex;  
    private long userDataIndex;  
    private List<String> hoursOfOperation;  
    private String coordinateLocation;  
    private String address;  
    private String contactInfo;  
    private String offeredServices;  
    private String locationNotes;  
    private String permanent;  

    /**
     * Default constructor.  Initializes the object with default values.
     * This constructor is used when creating a new instance of the ClothingStore class without any initial data.
     */
    public CommunityCenter() {}

    /**
     * Constructor with all fields. Initializes the object with input values.
     * This constructor is used when creating a new instance of the CommunityCenter class without any initial data.
     * @param ccDataPk is the primary key of the CommunityCenter
     * @param ccDataIndex is the incremental data
     * @param orgName is the linked organization-name
     * @param orgDataIndex is the linked organization-incremental index
     * @param userDataIndex is the linked user-incremental index
     * @param hoursOfOperation is the operating hours
     * @param coordinateLocation is the Coordinates Info
     * @param address is the listed Address
     * @param contactInfo is  the Contact Info
     * @param offeredServices is the saved service information
     * @param locationNotes is the Location Notes
     * @param permanent is the permanent value
     */
    public CommunityCenter(String ccDataPk, long ccDataIndex, String orgName, long orgDataIndex, long userDataIndex, 
                           List<String> hoursOfOperation, String coordinateLocation, String address, 
                           String contactInfo, String offeredServices, String locationNotes, String permanent) {
        this.ccDataPk = ccDataPk;
        this.ccDataIndex = ccDataIndex;
        this.orgName = orgName;
        this.orgDataIndex = orgDataIndex;
        this.userDataIndex = userDataIndex;
        this.hoursOfOperation = hoursOfOperation;
        this.coordinateLocation = coordinateLocation;
        this.address = address;
        this.contactInfo = contactInfo;
        this.offeredServices = offeredServices;
        this.locationNotes = locationNotes;
        this.permanent = permanent;
    }

    // Getters and Setters
    /**
    * @return returns the primary key of the CommunityCenter entity
    */
    public String getCcDataPk() {
        return ccDataPk;
    }
    /**
    * @param ccDataPk sets the primary key of CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCcDataPk(String ccDataPk) {
        this.ccDataPk = ccDataPk;
    }

    /**
    * @return returns the incremental data of the CommunityCenter entity
    */
    public long getCcDataIndex() {
        return ccDataIndex;
    }
    /**
    * @param ccDataIndex sets the incremental data of CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCcDataIndex(long ccDataIndex) {
        this.ccDataIndex = ccDataIndex;
    }

    /**
    * @return returns the linked organization-name of the CommunityCenter entity
    */
    public String getOrgName() {
        return orgName;
    }
    /**
    * @param orgName sets the linked organization-name of CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
    * @return returns the linked organization-incremental index of the CommunityCenter entity
    */
    public long getOrgDataIndex() {
        return orgDataIndex;
    }
    /**
    * @param orgDataIndex sets the linked organization-incremental index of CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgDataIndex(long orgDataIndex) {
        this.orgDataIndex = orgDataIndex;
    }

    /**
    * @return returns the linked user-incremental index of the CommunityCenter entity
    */
    public long getUserDataIndex() {
        return userDataIndex;
    }
    /**
    * @param userDataIndex sets the linked user-incremental index of CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setUserDataIndex(long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }

    /**
    * @return returns the operating hours of the CommunityCenter entity
    */
    public List<String> getHoursOfOperation() {
        return hoursOfOperation;
    }
    /**
    * @param hoursOfOperation sets the operating hours of a CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setHoursOfOperation(List<String> hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    /**
    * @return returns the Coordinates Info of the CommunityCenter entity
    */
    public String getCoordinateLocation() {
        return coordinateLocation;
    }
    /**
    * @param coordinateLocation sets the Coordinates of an CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCoordinateLocation(String coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    /**
    * @return returns the listed Address of the CommunityCenter entity
    */
    public String getAddress() {
        return address;
    }
    /**
    * @param address sets the listed Address of an CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
    * @return returns the Contact Info of the CommunityCenter entity
    */
    public String getContactInfo() {
        return contactInfo;
    }
    /**
    * @param contactInfo sets the saved Contact Info of CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
    * @return returns the saved service information of the CommunityCenter entity
    */
    public String getOfferedServices() {
        return offeredServices;
    }
    /**
    * @param offeredServices sets the Contact Info of an CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOfferedServices(String offeredServices) {
        this.offeredServices = offeredServices;
    }

    /**
    * @return returns the Location Notes of the CommunityCenter entity
    */
    public String getLocationNotes() {
        return locationNotes;
    }
    /**
    * @param locationNotes sets the Location Notes of an CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    /**
    * @return returns the permanent value of the CommunityCenter entity
    */
    public String getPermanent() {
        return permanent;
    }
    /**
    * @param permanent sets the permanent value of an CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }

    // Support for uniform DAO mapping (setLocation maps to address)
    /**
    * @param location sets the listed Address of an CommunityCenter object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCommunityCenterLocation(String location) {
        this.address = location;
    }
}
