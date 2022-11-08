package com.iracanyes.demo.j2ee.ticket;

import com.iracanyes.demo.j2ee.ticket.model.bean.intro.FullName;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FullNameService{
  private Connection connection;
  private Properties props = new Properties();
  private static final  Logger logger = Logger.getLogger(FullNameService.class.getName());
  private static final String SELECT_ALL = "SELECT firstname, lastname FROM javaee.names;";
  private static final String INSERT_INTO = "INSERT INTO javaee.names (firstname, lastname ) VALUES( ?, ? );";

  private List<FullName> names = new ArrayList<>();

  public FullNameService() throws IOException {
    try {
      // Loading of the driver
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (Exception ex) {
      // handle the error
      FullNameService.logger.log(Level.SEVERE, ()-> "Driver Not found: com.mysql.cj.jdbc.Driver");
    }

    // Loading af configuration properties
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    try(InputStream input = classLoader.getResourceAsStream("config.properties");){


      if(input == null){
        FullNameService.logger.log(Level.SEVERE, "config.properties file not found.");
      }
      props.load(input);


    }catch (IOException e){
      FullNameService.logger.log(Level.SEVERE, "config.properties file not found.");
    }


  }

  private void loadDatabase(){
    String dbUrl = props.getProperty("db.url");
    String dbUser = props.getProperty("db.user");
    String dbPassword = props.getProperty("db.password");

    try{
      connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }catch(SQLException e){
      FullNameService.logger.log(Level.SEVERE, "", e);
    }
  }

  public List<FullName> getFullNames() throws SQLException{
    loadDatabase();

    try(ResultSet result = connection.createStatement().executeQuery(SELECT_ALL);){
      while(result.next()){

        FullName fullName = new FullName(result.getString("firstname"), result.getString("lastname"));
        names.add(fullName);
      }
    }catch(SQLException e){
      e.printStackTrace();
    }finally {
      if(connection != null){
        connection.close();
      }

    }

    return names;


  }

  public void createName(FullName fullName){
    loadDatabase();
    try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO);){
      preparedStatement.setString(1, fullName.getFirstname());
      preparedStatement.setString(2, fullName.getLastname());
      try{
        preparedStatement.executeUpdate();
      }catch (SQLException e){
        FullNameService.logger.log(Level.SEVERE, "preparedStatement update execution failed", e);
      }
    }catch (SQLException e){
      FullNameService.logger.log(Level.SEVERE, "connection.prepareStatement(INSERT_INTO) failed or parameter settings.", e);

    }
  }


}
