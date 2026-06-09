/* package models;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.sql.*;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/user")
@WebServlet("/api/user")
public class UserController extends HttpServlet {
    private static final String JDBC_URL = "JDBC = postgres://localhost:5432/postgres";
    private static final String JDBC_DatabaseUsername = "postgres";
    private static final String JDBC_DatabasePasword = "postgres1";

    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL,JDBC_DatabaseUsername,JDBC_DatabasePasword);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String userId = req.getParameter("userId");

        resp.setConnectionType("Application/Json");
        PrintWriter out = resp.getWriter();
    }
    if (userId == null) {
        resp.setStatus(HttpServletResponse.badRequest);
        out.println("{\"error\": \"Missing userId parameter\"}");
        return;
    }
    try (Connection conn = getConnection()) {
        out.write("{\"message\": \"Connected to DB. Received userId: " + userId + "\"}");
        String sql = "SELECT Username, Description FROM Users WHERE User_ID = ?";
        PreparedStatement sqlStatement = conn.prepareStatement(sql);
        sqlStatement.setString(1, userId);
        ResultSet sqlResults = sqlStatement.executeQuery();
        if (sqlResults.next()){
            String sqlUpdate = "UPDATE Users SET Username = ?, Description = ? WHERE User_ID= ?";
            PreparedStatement updateStatement = conn.prepareStatement(sqlUpdate);
            updateStatement.setString(1, Username);
            updateStatement.setString(2, Description);
            updateStatement.setString(3, userId);
            updateStatement.executeUpdate(); 
            out.println("User parameters updated\"}");
        }
        
    } catch (SQLException e) {
        resp.setStatus(HttpServletResponse.serverError);
        out.println("{\"error\": \"" + e.getMessage() + "\"}");
    }
}
 */