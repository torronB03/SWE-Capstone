package utils;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * DBConnectionPool.java
 * This class implements a singleton pattern to manage a connection pool for a PostgreSQL database.
 * primarily to make sure not too many connections are opened or closed in succession, etc.
 * This class must not be instantiated more than once.
 */
public class DBConnectionPool {

  private static DBConnectionPool instance;
  private BasicDataSource dataSource;

  /**
   * Private constructor to initialize the connection pool with PostgreSQL settings.
   */
  private DBConnectionPool() {
    dataSource = new BasicDataSource();
    dataSource.setDriverClassName("org.postgresql.Driver");
    dataSource.setUrl(
        "jdbc:postgresql://db:5432/" + System.getenv("DB_NAME") + "?user=" + System.getenv("DB_USER") + "&password="
            + System.getenv("DB_PASS") + "&currentSchema=swegrg25");
    dataSource.setUsername(System.getenv("DB_USER"));
    dataSource.setPassword(System.getenv("DB_PASS"));
    dataSource.setMinIdle(5);
    dataSource.setMaxIdle(10);
    dataSource.setMaxOpenPreparedStatements(100);
  }

  /**
   * Synchronized method to get the singleton instance of DBConnectionPool.
   * @return the singleton instance of DBConnectionPool
   */
  public static synchronized DBConnectionPool getRunningInstance() {
    if (instance == null) {
      instance = new DBConnectionPool();
    }
    return instance;
  }

  /**
   * Method to get a connection from the pool.
   * @return a Connection object from the pool
   * @throws SQLException if a database access error occurs
   */
  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  /**
   * Method to close the connection pool.
   * This should be called when the application is shutting down to release resources.
   */
  public void closePool() {
    try {
      if (dataSource != null) {
        dataSource.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}