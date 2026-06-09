package dao;

import models.ClothingStore;
import models.SessionConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 3:32AM
* ClothingStoreDAO is a data-access object that interfaces the java model class for 
* ClothingStore with its database table.
* This is a location-based data structure; does not allow for internal modifications, but includes
* functionality for saving to a user's profile.
* @attributes  sessionConnection
* @methods  SESSION:  ClothingStoreDAO
* @methods  OUTPUT:  getAllMembers, getFilteredMembers
* @methods  MODIFICATIONS:  saveClothingStoreForUser
*/
public class ClothingStoreDAO {

    private Connection sessionConnection;

    /**
    * Constructor for ClothingStoreDAO
    * @param connObject  SessionConnection object that contains the connection to the database
    */
    public ClothingStoreDAO(SessionConnection connObject)
    {
        this.sessionConnection = connObject.getConnection();
    }

    /**
    * @return 'getAllClothingStores'
    */
    public List<ClothingStore> getAllClothingStores() {
        List<ClothingStore> stores = new ArrayList<>();
        String query = "SELECT * FROM swegrg25.\"Clothing_Stores\"";
    
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
    
            while (rs.next()) {
                ClothingStore store = new ClothingStore();
                store.setClothingStoreDataIndex(rs.getLong("cs_data_index"));
                store.setOrgName(rs.getString("org_name"));
                store.setOrgDataIndex(rs.getLong("org_data_index"));
                store.setUserDataIndex(rs.getLong("user_data_index"));
                store.setPriceRange(rs.getString("price_range"));
                store.setHoursOfOperation(rs.getString("hours_of_operation")); // Assuming stored as JSON string
                store.setAddress(rs.getString("address"));
                store.setContactInfo(rs.getString("contact_info"));
                store.setCoordinateLocation(rs.getString("coordinate_location"));
                store.setLocationNotes(rs.getString("location_notes"));
                store.setPermanent(rs.getString("permanent"));
                stores.add(store);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return stores;
    }
    
    /**
    * @param clothingStoreId id used to identify the clothing store
    * @return 'getClothingStore'
    */
    public ClothingStore getClothingStore(long clothingStoreId) {
        String query = "SELECT * FROM swegrg25.\"Clothing_Stores\" WHERE cs_data_index = ?";
    
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, clothingStoreId);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ClothingStore store = new ClothingStore();
                    store.setClothingStoreDataIndex(rs.getLong("cs_data_index"));
                    store.setOrgName(rs.getString("org_name"));
                    store.setOrgDataIndex(rs.getLong("org_data_index"));
                    store.setUserDataIndex(rs.getLong("user_data_index"));
                    store.setPriceRange(rs.getString("price_range"));
                    store.setHoursOfOperation(rs.getString("hours_of_operation")); // Still JSON string
                    store.setAddress(rs.getString("address"));
                    store.setContactInfo(rs.getString("contact_info"));
                    store.setCoordinateLocation(rs.getString("coordinate_location"));
                    store.setLocationNotes(rs.getString("location_notes"));
                    store.setPermanent(rs.getString("permanent"));
                    return store;
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }
    

    /**
    * @param userId e.g., 123456789
    * @param resourceDataPk  e.g., "clst001"
    * @param userCategory  resource category for the user, e.g., "clothing"
    * @return 'saveResourceForUser'
    */
    public boolean saveResourceForUser(long userId, String resourceDataPk, String userCategory) {
        String query = "INSERT INTO swegrg25.\"Save_Resource\" (user_data_index, resource_data_pk, user_category) VALUES (?, ?, ?)";
    
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.setString(2, resourceDataPk);  // e.g., "clst001"
            stmt.setString(3, userCategory);
    
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
    
        } catch (SQLException e) {
            System.err.println("Error saving resource for user: " + e.getMessage());
            return false;
        }
    }
    
}
