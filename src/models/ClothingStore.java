package models;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June)
* DOC last edited: 05/08 @ 04:12AM
* ClothingStore is a class-model that represents the database entity of ClothingStore, and connects it to this data-type's DAO
* @attributes clothingStoreDataIndex, orgName, orgDataIndex, userDataIndex, priceRange, hoursOfOperation,
* ...... address, contactInfo, coordinateLocation, locationNotes, permanent
* @methods  OUTPUT:  getClothingStoreDataIndex, getOrgName, getOrgDataIndex, getUserDataIndex, getPriceRange, getHoursOfOperation, getAddress, getContactInfo, getCoordinateLocation, getLocationNotes, getPermanent
* @methods  INPUT:  setClothingStoreDataIndex, setOrgName, setOrgDataIndex, setUserDataIndex, setPriceRange, setHoursOfOperation, setAddress, setContactInfo, setCoordinateLocation, setLocationNotes, setPermanent
*/
public class ClothingStore {
    private long clothingStoreDataIndex;
    private String orgName;
    private long orgDataIndex;
    private long userDataIndex;
    private String priceRange;
    private String hoursOfOperation; // JSON stored as String
    private String address;
    private String contactInfo;
    private String coordinateLocation;
    private String locationNotes;
    private String permanent;

    /**
    * Constructor for the ClothingStore class. Initializes the object with default values.
    * This constructor is used when creating a new instance of the ClothingStore class without any initial data.
    */
    public ClothingStore() {}

    // --- ID ---
    /**
    * @return returns the incremental data of the ClothingStore entity
    */
    public long getClothingStoreDataIndex() {
        return clothingStoreDataIndex;
    }
    /**
    * @param clothingStoreId the incremental data of ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setClothingStoreDataIndex(long clothingStoreId) {
        this.clothingStoreDataIndex = clothingStoreId;
    }

    // --- Org Name ---
    /**
    * @return returns the linked organization-name of the ClothingStore entity
    */
    public String getOrgName() {
        return orgName;
    }
    /**
    * @param orgName the linked organization-name of ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    // --- Org Data Index ---
    /**
    * @return returns the linked organization-incremental index of the ClothingStore entity
    */
    public long getOrgDataIndex() {
        return orgDataIndex;
    }
    /**
    * @param orgDataIndex the linked organization-incremental index of ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setOrgDataIndex(long orgDataIndex) {
        this.orgDataIndex = orgDataIndex;
    }

    // --- User Data Index ---
    /**
    * @return returns the linked user-incremental index of the ClothingStore entity
    */
    public long getUserDataIndex() {
        return userDataIndex;
    }
    /**
    * @param userDataIndex the linked user-incremental index of ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setUserDataIndex(long userDataIndex) {
        this.userDataIndex = userDataIndex;
    }

    // --- Price Range ---
    /**
    * @return returns the saved price range of the ClothingStore entity
    */
    public String getPriceRange() {
        return priceRange;
    }
    /**
    * @param priceRange the saved price range of ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
    }

    // --- Hours Of Operation ---
    /**
    * @return returns the operating hours of the ClothingStore entity
    */
    public String getHoursOfOperation() {
        return hoursOfOperation;
    }
    /**
    * @param hoursOfOperation the operating hours of a ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    // --- Address ---
    /**
    * @return returns the listed Address of the ClothingStore entity
    */
    public String getAddress() {
        return address;
    }
    /**
    * @param address the listed Address of an ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setAddress(String address) {
        this.address = address;
    }

    // --- Contact Info ---
    /**
    * @return returns the Contact Info of the ClothingStore entity
    */
    public String getContactInfo() {
        return contactInfo;
    }
    /**
    * @param contactInfo the Contact Info of an ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // --- Coordinate Location ---
    /**
    * @return returns the Coordinates Info of the ClothingStore entity
    */
    public String getCoordinateLocation() {
        return coordinateLocation;
    }
    /**
    * @param coordinateLocation the Coordinates of an ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setCoordinateLocation(String coordinateLocation) {
        this.coordinateLocation = coordinateLocation;
    }

    // --- Location Notes ---
    /**
    * @return returns the Location Notes of the ClothingStore entity
    */
    public String getLocationNotes() {
        return locationNotes;
    }
    /**
    * @param locationNotes the Location Notes of an ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setLocationNotes(String locationNotes) {
        this.locationNotes = locationNotes;
    }

    // --- Permanent (event / volunteer / place) ---
    /**
    * @return returns the permanent value of the ClothingStore entity
    */
    public String getPermanent() {
        return permanent;
    }
    /**
    * @param permanent the permanent value of an ClothingStore object as it exists (to interface with 
    * the database entry in the DAO linked list)
    */
    public void setPermanent(String permanent) {
        this.permanent = permanent;
    }
}
