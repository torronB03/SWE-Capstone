package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import adapter.LocalDateAdapter;
import adapter.LocalDateTimeAdapter;
import adapter.LocalTimeAdapter;
import dao.ClothingStoreDAO;
import dao.CommunityCenterDAO;
import dao.CommunityEventDAO;
import dao.FoodBankDAO;
import dao.MedicalCenterDAO;
import dao.OrganizationDAO;
import dao.ShelterDAO;
import dao.VolunteerOpportunityDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.CommunityEvent;
import models.SessionConnection;
import models.User;
import models.VolunteerOpportunity;

/**
 * TestAPIServlet is a servlet that handles API requests for the application.
 * It provides endpoints for various resources such as clothing stores, food banks, community centers, etc.
 * @methods  OUTPUT:  doGet, doPost
 */
@WebServlet("/api/*")
public class TestAPIServlet extends HttpServlet {
    /**
     * gson is a library that converts Java objects into JSON and vice versa.
     */
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .create();

    /**
     * Logger for this servlet
     */
    Logger svltLog;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();
        if (svltLog == null) {
            svltLog = Logger.getLogger(this.getClass().getName() + "Servlet");
        }
        svltLog.warning("Request received.");

        if (path == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing endpoint");
            return;
        }

        HttpSession userSession = request.getSession(true);
        Object loginData = userSession.getAttribute("UserData");
        if (loginData != null && loginData instanceof User)
        {
            // USER IS LOGGED IN
        }
        else
        {
            // USER IS NOT LOGGED IN
        }

        Object connCheck = userSession.getAttribute("Connection");
        if (connCheck == null || connCheck instanceof SessionConnection)
        {
            SessionConnection.createConnectionforSession(userSession, request.getRemoteAddr());
            connCheck = userSession.getAttribute("Connection"); // refetch
        }

        SessionConnection userConnection = (SessionConnection)connCheck;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        switch (path) {
            case "/clothing-stores":
                ClothingStoreDAO csDAO = new ClothingStoreDAO(userConnection);
                out.print(gson.toJson(csDAO.getAllClothingStores()));
                break;
            case "/foodbanks":
                FoodBankDAO fbDAO = new FoodBankDAO(userConnection);
                out.print(gson.toJson(fbDAO.getAllFoodBanks()));
                break;
            case "/community-centers":
                CommunityCenterDAO ccDAO = new CommunityCenterDAO(userConnection);
                out.print(gson.toJson(ccDAO.getAllCommunityCenters()));
                break;
            case "/volunteer-opportunities":
                VolunteerOpportunityDAO vlDAO = new VolunteerOpportunityDAO(userConnection);
                out.print(gson.toJson(vlDAO.getAllVolunteerOpportunities()));
                svltLog.warning("Sending back opportunities...");
                break;
            case "/all-data":
                Map<String, Object> allData = new HashMap<>();
                List<Object> combinedEvents = new ArrayList<>();
                System.out.println("Getting clothing stores...");
                combinedEvents.addAll(new ClothingStoreDAO(userConnection).getAllClothingStores());
                System.out.println("Getting food banks...");
                combinedEvents.addAll(new FoodBankDAO(userConnection).getAllFoodBanks());
                System.out.println("Getting medical centers...");
                combinedEvents.addAll(new MedicalCenterDAO(userConnection).getAllMedicalCenters());
                System.out.println("Getting shelters...");
                combinedEvents.addAll(new ShelterDAO(userConnection).getAllShelters());
                System.out.println("Getting volunteer opportunities...");
                combinedEvents.addAll(new VolunteerOpportunityDAO(userConnection).getAllVolunteerOpportunities());
                System.out.println("Getting community events...");
                combinedEvents.addAll(new CommunityEventDAO(userConnection).getAllCommunityEvents());

                allData.put("events", combinedEvents);
                out.print(gson.toJson(allData));
                break;
            case "/community-events":
                CommunityEventDAO eDAO = new CommunityEventDAO(userConnection);
                out.print(gson.toJson(eDAO.getAllCommunityEvents()));
                break;
            case "/get-event":
                System.out.println("Entered /get-event api");
                String name = request.getParameter("name");
                System.out.println("Name id: " + name);
                System.out.println("DAO intitating");
                OrganizationDAO oDAO = new OrganizationDAO(userConnection);     // CHANGE TO SEARCH ALL EVENTS TYPES
                System.out.println("DAO done");
                System.out.println("Calling DAO function");
                out.print(gson.toJson(oDAO.getOrganizationDetailsByOrgName(name)));
                System.out.println("Finished DAO function");
                break;
            case "/medical-centers":
                MedicalCenterDAO mcDAO = new MedicalCenterDAO(userConnection);
                out.print(gson.toJson(mcDAO.getAllMedicalCenters()));
                break;
            case "/shelters":
                ShelterDAO sDAO = new ShelterDAO(userConnection);
                out.print(gson.toJson(sDAO.getAllShelters()));
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown API endpoint: " + path);
                break;
        }
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String path = request.getPathInfo();
    if (path == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing Path Endpoint");
        return;
    }

    HttpSession userSession = request.getSession(true);
    Object loginData = userSession.getAttribute("UserData");
    boolean loggedIn = false;
    if (loginData != null && loginData instanceof User)
    {
        loggedIn = true;
    }

    Object connCheck = userSession.getAttribute("Connection");
    if (connCheck == null || connCheck instanceof SessionConnection)
    {
        SessionConnection.createConnectionforSession(userSession, request.getRemoteAddr());
        connCheck = userSession.getAttribute("Connection"); // refetch
    }

    SessionConnection userConnection = (SessionConnection)connCheck;

    response.setContentType("application/json");
    PrintWriter out = response.getWriter();

    try {
        switch (path) {
            case "/create-event": {
                 // cancel if not logged in. uncomment to enable check
                //if (!loggedIn) { break; }

                String eventTitle = request.getParameter("eventName");
                String eventOrganizerName = request.getParameter("eOrganizerName");                
                LocalDate eventDate = LocalDate.parse(request.getParameter("eventDate"));
                LocalTime eventTime = LocalTime.parse(request.getParameter("eventTime"));
                String eventAddress = request.getParameter("eventAddress1");
                String eventCity = request.getParameter("eventCity");
                String eventState = request.getParameter("eventState");
                String eventZipcode = request.getParameter("eventZipCode");
                String eventDescription = request.getParameter("eventDescription");

                User user = (User)loginData;

                CommunityEvent event = new CommunityEvent(eventTitle, eventOrganizerName, eventDate, eventTime,
                        eventAddress, eventCity, eventState, eventZipcode, eventDescription, user.getUserId());

                boolean eventSaved = new CommunityEventDAO(userConnection).insertCommunityEvent(event);

                if (eventSaved) {
                    response.sendRedirect(request.getContextPath() + "/pages/event_created.html");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create event");
                }
                break;
            }

            case ("/create-volunteer"):
                // cancel if not logged in. uncomment to enable check
                //if (!loggedIn) { break; }

                // volunteer form
                // get the information from the user's form submission
                String title = request.getParameter("opportunityName");
                String address = request.getParameter("addressLine1");
                String city = request.getParameter("city");
                String state = request.getParameter("state");
                String zipcode = request.getParameter("zipcode");
                String volunteersNeeded = request.getParameter("newVolunteer");
                String description = request.getParameter("description");
                String contactInfo = request.getParameter("contact-info");
                String voPk = request.getParameter("voDataPk");
                // String permanent = request.getParameter("permanent");
                String permanent = "volunteer";
                VolunteerOpportunity vo = new VolunteerOpportunity();
                long voDataIndex = vo.getVoDataIndex();  
                //long voDataIndex = -1;
                String locationNotes = "~~~~~~~~~~~";
                String coordinates = "fake coordinates";

                System.out.println("FORM TYPE: " + request.getParameter("formType"));
                System.out.println("Date: " + request.getParameter("date"));
                System.out.println("Time: " + request.getParameter("time"));


                // convert the date into timestamp form to properly pass the correct parameter data type
                String date = request.getParameter("date");
                String time = request.getParameter("time");
                String dateTime = date + " " + time;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(dateTime,formatter);
                Timestamp timestamp = Timestamp.valueOf(localDateTime);

                // convert the time into a boolean
                boolean ongoing = false;
                // getting the current date and time
                LocalDateTime currentDate = LocalDateTime.now();
                if (!currentDate.isBefore(localDateTime)) {
                    ongoing = true;
                }

                VolunteerOpportunityDAO volunteerOpportunityDAO = new VolunteerOpportunityDAO(userConnection);
                System.out.println("IVE CREATED THE DAO ");

                // grab user data from the session grabbed earlier
                User user = (User)loginData;

                // instantiate an object of the model class
                VolunteerOpportunity volunteerOpportunity = new VolunteerOpportunity(voDataIndex, voPk, title, user.getUserId(), description, timestamp, ongoing, volunteersNeeded, coordinates, address, contactInfo, locationNotes, permanent);

                // call the database DAO function to insert the new event into the database as a new entry
                boolean transferToDatabaseVol = volunteerOpportunityDAO.insertVolunteerOpportunity(volunteerOpportunity, user.getUserId());

                // if successful, print a message; else error
                if (transferToDatabaseVol) {
                    //response.setContentType("application/json");
                    //response.setCharacterEncoding("UTF-8");
                    //response.getWriter().write("{\"status\": \"success\"}");
                    response.sendRedirect(request.getContextPath() + "/pages/volunteer_created.html");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create opportunity");
                }
                break;            
            // case "/edit-event":
            //     CommunityEvent event = new CommunityEvent();
            //     boolean editEvent = new CommunityEvent().modifyCommunityEvent(event);
            //     // if successful, print a message; else error
            //     if (editEvent) {
            //         out.print(gson.toJson("Event edited successfully!"));
            //     } else {
            //         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to edit event");
            //     }
            //     break;
            // case "/edit-volunteer":
            //     volunteer = new VolunteerOpportunity();
            //     boolean editVolunteer = new VolunteerOpportunityDAO().modifyVolunteerOpportunity(volunteer);
            //     // if successful, print a message; else error
            //     if (editVolunteer) {
            //         out.print(gson.toJson("Volunteer Opportunity edited successfully!"));
            //     } else {
            //         response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to edit volunteer opportunity");
            //     }
            //     break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unknown API endpoint: " + path);
        }
    } catch (Exception e) {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
    }
}
}