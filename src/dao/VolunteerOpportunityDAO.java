package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.SessionConnection;
import models.VolunteerOpportunity;

/**
* @author Abernard13
* Contributers: yellol (Alex), robutlerr (Amari Butler), JFernandez03 (June), atclark123 (Amore)
* DOC last edited: 05/08 @ 03:22AM
* VolunteerOpportunityDAO is a data-access object that interfaces the java model class for 
* VolunteerOpportunity with its database table.
* Allows for inserts and modifications
* @attributes sessionConnection
* @methods  SESSION:  VolunteerOpportunityDAO
* @methods  OUTPUT:  getAllVolunteerOpportunities, getOrganizerForOpportunity, saveResourceForUser
* @methods  MODIFICATIONS:  upsertOrganization, insertVolunteerOpportunity, modifyVolunteerOpportunity
*/
public class VolunteerOpportunityDAO {

    private Connection sessionConnection;

    /**
     * Constructor for VolunteerOpportunityDAO
     * @param connObject connection object to the database
     */
    public VolunteerOpportunityDAO(SessionConnection connObject) {
        this.sessionConnection = connObject.getConnection();
    }

    /**
     * 
     * @param orgName organization name
     * @param address address of the organization
     * @param contactInfo contact information of the organization
     * @throws SQLException
     */
    private void upsertOrganization(String orgName, String address, String contactInfo) throws SQLException {
        String upsertQuery =
            "INSERT INTO \"Organization\" (org_name, address, contact_info) " +
            "VALUES (?, ?, ?) " +
            "ON CONFLICT (org_name) DO UPDATE SET " +
            "address = EXCLUDED.address, contact_info = EXCLUDED.contact_info";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(upsertQuery)) {
            stmt.setString(1, orgName);
            stmt.setString(2, address);
            stmt.setString(3, contactInfo);
            stmt.executeUpdate();
        }
    }

    /**
     * 
     * @param opportunity VolunteerOpportunity object to be inserted
     * @param creatorId creator's user ID
     * @return 'insertVolunteerOpportunity'
     */
    public boolean insertVolunteerOpportunity(VolunteerOpportunity opportunity, int creatorId) {
        String query =
            "INSERT INTO swegrg25.\"Volunteer_Opportunities\" " +
            "(org_name, vo_description, event_date, ongoing, requirements, coordinate_location, " +
            "address, contact_info, location_notes, user_data_index, permanent) " +
            "VALUES (?, ?, ?, ?, ?::json, ?, ?, ?, ?, ?, ?)";

        try {
            sessionConnection.setAutoCommit(false);

            upsertOrganization(opportunity.getOrgName(), opportunity.getAddress(), opportunity.getContactInfo());

            try (PreparedStatement stmt = sessionConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, opportunity.getOrgName());
                stmt.setString(2, opportunity.getVoDescription());
                stmt.setTimestamp(3, opportunity.getEventDate());
                stmt.setBoolean(4, opportunity.isOngoing());
                stmt.setString(5, opportunity.getRequirementsJson());
                stmt.setString(6, opportunity.getCoordinateLocation());
                stmt.setString(7, opportunity.getAddress());
                stmt.setString(8, opportunity.getContactInfo());
                stmt.setString(9, opportunity.getLocationNotes());
                stmt.setInt(10, creatorId);
                stmt.setString(11, opportunity.getPermanent());

                int affected = stmt.executeUpdate();
                if (affected > 0) {
                    try (ResultSet keys = stmt.getGeneratedKeys()) {
                        if (keys.next()) {
                            opportunity.setVoDataIndex(keys.getLong(1));
                        }
                    }
                    sessionConnection.commit();
                    return true;
                }
            }

            sessionConnection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**  
    * Update an existing opportunity
    * @param opportunity VolunteerOpportunity object to be modified
    * @param userId ID of the user making the modification
    * @return 'modifyVolunteerOpportunity'
    */
    public boolean modifyVolunteerOpportunity(VolunteerOpportunity opportunity, int userId) {
        String sql =
            "UPDATE swegrg25.\"Volunteer_Opportunities\" SET " +
            "org_name = ?, vo_description = ?, event_date = ?, ongoing = ?, requirements = ?::json, " +
            "coordinate_location = ?, address = ?, contact_info = ?, location_notes = ?, permanent = ? " +
            "WHERE vo_data_index = ?";

        try {
            sessionConnection.setAutoCommit(false);

            if (opportunity.getUserDataIndex() != userId) {
                return false;
            }

            upsertOrganization(opportunity.getOrgName(), opportunity.getAddress(), opportunity.getContactInfo());

            try (PreparedStatement stmt = sessionConnection.prepareStatement(sql)) {
                stmt.setString(1, opportunity.getOrgName());
                stmt.setString(2, opportunity.getVoDescription());
                stmt.setTimestamp(3, opportunity.getEventDate());
                stmt.setBoolean(4, opportunity.isOngoing());
                stmt.setString(5, opportunity.getRequirementsJson());
                stmt.setString(6, opportunity.getCoordinateLocation());
                stmt.setString(7, opportunity.getAddress());
                stmt.setString(8, opportunity.getContactInfo());
                stmt.setString(9, opportunity.getLocationNotes());
                stmt.setString(10, opportunity.getPermanent());
                stmt.setLong(11, opportunity.getVoDataIndex());

                int affected = stmt.executeUpdate();
                if (affected > 0) {
                    sessionConnection.commit();
                    return true;
                }
            }

            sessionConnection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
    * Retrieve all volunteer opportunities
    * @return 'getAllVolunteerOpportunities'
    */
    public List<VolunteerOpportunity> getAllVolunteerOpportunities() {
        List<VolunteerOpportunity> opportunities = new ArrayList<>();
        String query = "SELECT * FROM swegrg25.\"Volunteer_Opportunities\"";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                VolunteerOpportunity opp = new VolunteerOpportunity();
                opp.setVoDataIndex(rs.getLong("vo_data_index"));
                opp.setVoDataPk(rs.getString("vo_data_pk"));
                opp.setOrgName(rs.getString("org_name"));
                opp.setUserDataIndex(rs.getLong("user_data_index"));
                opp.setVoDescription(rs.getString("vo_description"));
                opp.setEventDate(rs.getTimestamp("event_date"));
                opp.setOngoing(rs.getBoolean("ongoing"));
                opp.setRequirementsJson(rs.getString("requirements"));
                opp.setCoordinateLocation(rs.getString("coordinate_location"));
                opp.setAddress(rs.getString("address"));
                opp.setContactInfo(rs.getString("contact_info"));
                opp.setLocationNotes(rs.getString("location_notes"));
                opp.setPermanent(rs.getString("permanent"));
                opportunities.add(opp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return opportunities;
    }

    
    /**
    * Get org name for a given opportunity
    * @param voDataIndex the index of the volunteer opportunity
    * @return 'getOrganizerForOpportunity'
    */
    public String getOrganizerForOpportunity(long voDataIndex) {
        String sql = "SELECT org_name FROM \"Volunteer_Opportunities\" WHERE vo_data_index = ?";
        try (PreparedStatement stmt = sessionConnection.prepareStatement(sql)) {
            stmt.setLong(1, voDataIndex);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getString("org_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
    * @param userId the ID of the user
    * @param resourceDataPk the primary key of the resource data to be saved
    * @param userCategory the category of the resource (e.g.,"volunteer_opportunity")")
    * @return 'saveResourceForUser'
    */
    public boolean saveResourceForUser(long userId, String resourceDataPk, String userCategory) {
        String query = "INSERT INTO swegrg25.\"Save_Resource\" (user_data_index, resource_data_pk, user_category) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = sessionConnection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.setString(2, resourceDataPk);
            stmt.setString(3, userCategory);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error saving resource for user: " + e.getMessage());
            return false;
        }
    }
}
