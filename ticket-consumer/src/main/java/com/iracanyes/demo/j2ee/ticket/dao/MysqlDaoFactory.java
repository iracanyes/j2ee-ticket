/**
 * Description:
 * Initialize the connection with the DAO of database.
 * Pre-load the object in memory
 *
 */
package com.iracanyes.demo.j2ee.ticket.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MysqlDaoFactory {
  private final String url;
  private final String username;
  private final String password;

  private static final Logger logger = Logger.getLogger(MysqlDaoFactory.class.getName());

  MysqlDaoFactory(String url, String username, String password){
    this.url = url;
    this.username = username;
    this.password = password;
  }

  public static MysqlDaoFactory getInstance(){
    try {
      // Loading of the driver
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception ex) {
      // handle the error
      logger.log(Level.SEVERE, ()-> "Driver class not found: com.mysql.cj.jdbc.Driver");
    }

    // Loading af configuration properties
    MysqlDaoFactory mysqlDaoFactoryInstance = null;
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    try(InputStream input = classLoader.getResourceAsStream("config.properties");) {
      Properties props = new Properties();

      if (input == null) {
        logger.log(Level.SEVERE, "config.properties file not found.");
      }
      props.load(input);

      // Here, we set config properties needed to connect with a specific Data provider
      mysqlDaoFactoryInstance =  new MysqlDaoFactory(
              props.getProperty("db.url"),
              props.getProperty("db.user"),
              props.getProperty("db.password")
      );


    }catch(IOException e){
      logger.log(Level.SEVERE, "config.properties file error", e);
    }

    return mysqlDaoFactoryInstance;
  }

  /**
   *
   * @return Connection
   * @throws SQLException Connection with MySQL DB failed
   */
  public Connection getConnection() throws SQLException{
    return DriverManager.getConnection(url, username, password);
  }

  public FullNameDao getFullNameDao(){
    // Returns the FullNameDoaImpl and pass as argument the DaoFactory which allow to connect to db
    return new FullNameMysqlDaoImpl(this);
  }

}
