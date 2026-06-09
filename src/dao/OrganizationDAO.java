package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Organization;
import models.SavedEvent;
import models.SessionConnection;

/**
 * * OrganizationDAO is a data-access object that interfaces the Java model class for Organization with its database table.
 */
public class OrganizationDAO {

    private Connection sessionConnection;

    /**
    * Constructor for OrganizationDAO
    * @param connObject  SessionConnection object to establish a connection to the database
    */
    public OrganizationDAO(SessionConnection connObject) {
        this.sessionConnection = connObject.getConnection();
    }

    /**
    * @param orgName the name of the organization to retrieve details for
    * @return Organization object containing details of the organization
    */
    public Organization getOrganizationDetailsByOrgName(String orgName) {
        String query = "SELECT o.org_data_index, o.org_name, o.address, o.contact_info, " +
                "  (SELECT COALESCE(json_agg(to_jsonb(v)), '[]') FROM swegrg25.\"Volunteer_Opportunities\" v WHERE v.org_data_index = o.org_data_index) AS volunteer_opportunities, " +
                "  (SELECT COALESCE(json_agg(to_jsonb(c)), '[]') FROM swegrg25.\"Clothing_Stores\" c WHERE c.org_data_index = o.org_data_index) AS clothing_stores, " +
                "  (SELECT COALESCE(json_agg(to_jsonb(f)), '[]') FROM swegrg25.\"Food_Banks\" f WHERE f.org_data_index = o.org_data_index) AS food_banks, " +
                "  (SELECT COALESCE(json_agg(to_jsonb(s)), '[]') FROM swegrg25.\"Shelters\" s WHERE s.org_data_index = o.org_data_index) AS shelters, " +
                "  (SELECT COALESCE(json_agg(to_jsonb(m)), '[]') FROM swegrg25.\"Med_Centers\" m WHERE m.org_data_index = o.org_data_index) AS med_centers, " +
                "  (SELECT COALESCE(json_agg(to_jsonb(cc)), '[]') FROM swegrg25.\"Community_Centers\" cc WHERE cc.org_data_index = o.org_data_index) AS community_centers " +
                "FROM swegrg25.\"Organization\" o " +
                "WHERE o.org_name = ?";
    
        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setString(1, orgName);
    
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("No organization found with name: " + orgName);
                    return null;
                }
                
                if (rs.next()) {
                    Organization org = new Organization();
                    org.setOrgDataIndex(rs.getLong("org_data_index"));
                    org.setOrgName(rs.getString("org_name"));
                    org.setAddress(rs.getString("address"));
                    org.setContactInfo(rs.getString("contact_info"));
    
                    Gson gson = new Gson();
                    org.setVolunteerOpportunities(deserializeJson(rs.getString("volunteer_opportunities"), new TypeToken<List<Object>>() {}));
                    org.setClothingStores(deserializeJson(rs.getString("clothing_stores"), new TypeToken<List<Object>>() {}));
                    org.setFoodBanks(deserializeJson(rs.getString("food_banks"), new TypeToken<List<Object>>() {}));
                    org.setShelters(deserializeJson(rs.getString("shelters"), new TypeToken<List<Object>>() {}));
                    org.setMedCenters(deserializeJson(rs.getString("med_centers"), new TypeToken<List<Object>>() {}));
                    org.setCommunityCenters(deserializeJson(rs.getString("community_centers"), new TypeToken<List<Object>>() {}));
    
                    return org;
                }
            } catch (SQLException e) {
                System.err.println("SQLState: " + e.getSQLState());
                System.err.println("Error Code: " + e.getErrorCode());
                System.err.println("Message: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }
    
    /**
     * @return a list of all saved resources
     */
    public List<SavedEvent> getAllResources() {
    List<SavedEvent> savedEvents = new ArrayList<>();

    String query = "SELECT user_data_index, resource_data_pk, saved_at, user_category FROM swegrg25.\"Save_Resource\"";

    try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            SavedEvent event = new SavedEvent();
            event.setUserDataIndex(rs.getLong("user_data_index"));
            event.setResourceDataPk(rs.getString("resource_data_pk"));
            event.setSavedAt(rs.getTimestamp("saved_at"));
            event.setUserCategory(rs.getString("user_category"));

            savedEvents.add(event);
        }

    } catch (SQLException e) {
        System.err.println("Error fetching saved resources: " + e.getMessage());
        e.printStackTrace();
    }

    return savedEvents;
}

    // Helper method to deserialize JSON string into a List using Gson
    /**
    * @param jsonString the JSON string to deserialize
    * @param typeToken the TypeToken representing the type to deserialize to
    * @return 'deserializeJson'
    */
    private <T> T deserializeJson(String jsonString, TypeToken<T> typeToken) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, typeToken.getType());
    }
}
