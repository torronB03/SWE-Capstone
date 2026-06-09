package models;
import java.util.List;

/**
* DOC last edited: 05/08 @ 05:22AM
* Organization is a class-model that represents the database entity of CommunityEvent, and connects it to this data-type's DAO
* @attributes orgDataIndex, orgName, address, contactInfo, volunteerOpportunities, clothingStores, foodBanks, shelters, medCenters, communityCenters
* @methods  OUTPUT:  getOrgDataIndex, getOrgName, getOrgName, getAddress, getContactInfo, getVolunteerOpportunities, getClothingStores, getFoodBanks, getShelters, getMedCenters, getCommunityCenters
* @methods  INPUT:  setOrgDataIndex, setOrgName, setOrgName, setAddress, setContactInfo, setVolunteerOpportunities, setClothingStores, setFoodBanks, setShelters, setMedCenters, setCommunityCenters
*/
public class Organization {
    private long orgDataIndex;
    private String orgName;
    private String address;
    private String contactInfo;
    private List<Object> volunteerOpportunities;
    private List<Object> clothingStores;
    private List<Object> foodBanks;
    private List<Object> shelters;
    private List<Object> medCenters;
    private List<Object> communityCenters;

    // Getters and setters for each field
    /**
     * @return gets organization increment 
     */
    public long getOrgDataIndex() {
        return orgDataIndex;
    }
    /**
     * @param orgDataIndex sets organization within an interface object
     */
    public void setOrgDataIndex(long orgDataIndex) {
        this.orgDataIndex = orgDataIndex;
    }

    /**
     * @return gets the organization name
     */
    public String getOrgName() {
        return orgName;
    }
    /**
     * @param orgName sets the organization name within an interface object
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return gets an address
     */
    public String getAddress() {
        return address;
    }
    /**
     * @param address sets an address within an interface object
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return gets contact info
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
     * @return gets the listed volunteer opportunities 
     */
    public List<Object> getVolunteerOpportunities() {
        return volunteerOpportunities;
    }
    /**
     * @param volunteerOpportunities sets the listed volunteer opportunities for an interface object
     */
    public void setVolunteerOpportunities(List<Object> volunteerOpportunities) {
        this.volunteerOpportunities = volunteerOpportunities;
    }

    /**
     * @return gets any clothing stores
     */
    public List<Object> getClothingStores() {
        return clothingStores;
    }
    /**
     * @param clothingStores sets any clothing stores  within an interface object
     */
    public void setClothingStores(List<Object> clothingStores) {
        this.clothingStores = clothingStores;
    }

    /**
     * @return gets any food banks
     */
    public List<Object> getFoodBanks() {
        return foodBanks;
    }
    /**
     * @param foodBanks sets any food banks within an interface object
     */
    public void setFoodBanks(List<Object> foodBanks) {
        this.foodBanks = foodBanks;
    }

    /**
     * @return gets any shelters
     */
    public List<Object> getShelters() {
        return shelters;
    }
    /**
     * @param shelters sets any shelters within an interface object
     */
    public void setShelters(List<Object> shelters) {
        this.shelters = shelters;
    }

    /**
     * @return gets any medical centers
     */
    public List<Object> getMedCenters() {
        return medCenters;
    }
    /**
     * @param medCenters sets any medical centers within an interface object
     */
    public void setMedCenters(List<Object> medCenters) {
        this.medCenters = medCenters;
    }

    /**
     * @return gets any community centers
     */
    public List<Object> getCommunityCenters() {
        return communityCenters;
    }
    /**
     * @param communityCenters sets any community centers within an interface object
     */
    public void setCommunityCenters(List<Object> communityCenters) {
        this.communityCenters = communityCenters;
    }
}
