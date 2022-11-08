package com.iracanyes.demo.j2ee.ticket.dao;

import com.iracanyes.demo.j2ee.ticket.model.bean.intro.FullName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FullNameMysqlDaoImpl implements FullNameDao {
  private MysqlDaoFactory mysqlDaoFactory;
  private static final Logger LOGGER = Logger.getLogger(FullNameMysqlDaoImpl.class.getName());
  private static final String INSERT_INTO = "INSERT INTO javaee.names(firstname, lastname) VALUES(?, ?)";
  private static final String SELECT_ALL = "SELECT firstname, lastname FROM javaee.names";
  FullNameMysqlDaoImpl(MysqlDaoFactory mysqlDaoFactory){
    this.mysqlDaoFactory = mysqlDaoFactory;
  }

  @Override
  public void create(FullName fullname) throws DaoException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try{
      connection = mysqlDaoFactory.getConnection();
      preparedStatement = connection.prepareStatement(INSERT_INTO);

      preparedStatement.setString(1, fullname.getFirstname());
      preparedStatement.setString(2, fullname.getLastname());

      preparedStatement.executeUpdate();

      // Commit transaction with the db
      connection.commit();
    }catch(SQLException e){
      if(connection != null){
        // rollback transaction with the DB
        try{
          connection.rollback();
        }catch (SQLException exception){
          throw new DaoException("Error while rolling back transaction with databases.");
        }
      }
      LOGGER.log(Level.SEVERE, "Error while executing prepared statement create FullName.", e);
      throw new DaoException("Error while executing prepared statement create FullName.");
    }finally{
      if(connection != null){
        // rollback transaction with the DB
        try{
          connection.close();
        }catch (SQLException exception){
          LOGGER.log(Level.SEVERE, "Error while closing connection with databases.", exception);
          throw new DaoException("Error while closing connection with databases.");
        }
      }
    }
  }

  @Override
  public List<FullName> findAll() throws DaoException{
    List<FullName> fullNames = new ArrayList<>();
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    try{
      connection = mysqlDaoFactory.getConnection();
      statement = connection.createStatement();
      resultSet = statement.executeQuery(SELECT_ALL);

      while(resultSet.next()){
        FullName fullName = new FullName(resultSet.getString("firstname"), resultSet.getString("lastname"));
        fullNames.add(fullName);
      }

    }catch(SQLException e){
      LOGGER.log(Level.SEVERE, "Error while executing statement", e);
      throw new DaoException("Error while executing statement");
    }finally{
      try{
        if(connection != null){ connection.close();}
        if(statement != null){ statement.close();}
        if(resultSet != null){ resultSet.close();}
      }catch (SQLException e){
        LOGGER.log(Level.SEVERE, "Error while closing connection, statement and resultSet", e);
        throw new DaoException("Error while closing connection, statement and resultSet");
      }

    }

    return fullNames;
  }
}
