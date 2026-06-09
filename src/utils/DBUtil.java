package utils;

import java.sql.*;
import java.util.logging.*;

/**
 * DBUtil.java 
 */
public class DBUtil {

  // Database connection parameters
  private static String DB_URL= "jdbc:postgresql://db:5432/" + System.getenv("DB_NAME") + "?user=" + System.getenv("DB_USER") + "&password=" + System.getenv("DB_PASS") + "&currentSchema=swegrg25";

  // JDBC connection setup
  /**
   * @return Connection object to the database
   * @throws SQLException if a database access error occurs
   */
  public static Connection startConnection() throws SQLException {
    try {
      Class.forName("org.postgresql.Driver");
      Logger connLog = Logger.getLogger("DBUtil");
      connLog.warning("Connecting to database?");
      return DriverManager.getConnection(DB_URL);
    } catch (ClassNotFoundException e) {
      throw new SQLException("PostgreSQL JDBC Driver not found", e);
    }
  }

  // Query the database and return results
  /**
   * @param query SQL query to be executed
   * @return ResultSet object containing the results of the query
   * @throws SQLException if a database access error occurs
   */
  public static ResultSet queryDatabase(String query) throws SQLException {
    Connection connection = startConnection();
    Statement stmt = connection.createStatement();
    return stmt.executeQuery(query);
  }

  // run modifications
  /**
   * @param command SQL command to be executed
   * @throws SQLException if a database access error occurs
   */
  public static void runSQLNoReturn(String command) throws SQLException {
    try {
      Connection connection = startConnection();
      Statement stmt = connection.createStatement();
      stmt.execute(command);
      closeResources(connection, stmt, null);
    } catch (SQLException e) {
      return;
    }

  }

  // Close the database resources
  /**
   * @param connection Connection object to be closed
   * @param stmt Statement object to be closed
   * @param rs ResultSet object to be closed
   */
  public static void closeResources(Connection connection, Statement stmt, ResultSet rs) {
    try {
      if (rs != null)
        rs.close();
      if (stmt != null)
        stmt.close();
      if (connection != null)
        connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}