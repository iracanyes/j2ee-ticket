# DAO - Data Access Object

## DAO Pattern 

The DAO design pattern can be used to provide a separation between 
the low-level data accessing operations and the high-level business services

![Before DAO pattern and After](/../images/dao_before_after.webp)

### DAO Layer

In between the database and the business layer, there is a layer called the DAO layer. 
The DAO layer is mainly used to perform the Create-Retrieve-Update-Delete (CRUD) operation. The DAO layer is responsible for creating, obtaining, updating, or deleting records in the database table. To perform this CRUD operation, DAO uses a low-level API, such as the JDBC API or the Hibernate API. 
This DAO layer will have a method for performing the CRUD operation.
![DAO Layer](/../images/service_dao_impl.png)

### DAO pattern Implementation
The DAO pattern provides a pattern to persist our model in different type of data store (Databases, File system, In-memory, etc.).

Each data store is represented by a ``DaoFactory`` class which provides a way to interact with it.
``DaoFactory`` class provides methods to generate DAO objects 
which will performs those operations on the data store on which DaoFactory class refers to.
Those DAO objects implements interfaces that represent the type of interactions
made for a specified type of our model in the data store on which the **DaoFactory class** 
refers to.

Here are the types used to represents a DAO pattern
- DAO Factory class: represents each type of data store connected to the application and how to interact with it
- DAO Operation Interface : represents the type of operations performed on a specific data store for a specific type of our model
- DAO Operation Implementation class : implementations of those operations made on a specific data store for a specific type of our model.


![DAO Layer](/../images/dao_design-pattern_java.gif)

In the example above, the model defined a class ``Contact`` which inherits from ``Person`` class 
and is related to ``Address`` class and ``Company`` class of our model.
````java 
class Contact extends Person{
  private Address address;
  private Company company;
  ...
}

class Person{
  ...
}

class Address{
  ...
}

class Company{
  ...
}
````
Each class of our model has its own DAO object which defines the operations performed for a specific data store.
- AddressDao
- PersonDao
- CompanyDao
- ContactDao

Here the data store is a **hsqldb** database.  
``ContactDaoFactory`` class defined a method to interact with the data store.
It also provide a method to generate DAO implementation class objects 
capable of interacting with the data store and performing operations.

**Client services** class from business layer and other classes from presentation layer
can use ``ContactDaoFactory`` class to generate **DAO objects** 
which interact with the HSQLDB database to provide data and perform operations on those data.

## DAO pattern Implementation Example

### Model
In our model, we define a class ``FullName``. 

````java
package com.iracanyes.demo.j2ee.ticket.model;

/**
 * FullName class
 *
 */
public class FullName
{
  private long id;
  private String firstname;
  private String lastname;

  public FullName(String firstname, String lastname){
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  @Override
  public String toString() {
    return "FullName{" +
            "firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }
}

````

### DAO Factory 
We create a **DAO Factory class** which provides the following methods:
1. a method to get an instance of the ```MysqlDaoFactory``` class
2. a method to connect with a MySQL databse 
3. a method to generate DAO operation implementation object which contains an instance of ``DaoFactory`` class

````java
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
    // Create the connection to database
    Connection connection = DriverManager.getConnection(url, username, password);
    
    // Disable autocommit operations, to enable transactions
    connection.setAutoCommit(false);
    return connection; 
  }

  public FullNameDao getFullNameDao(){
    // Returns the FullNameDoaImpl and pass as argument the DaoFactory which allow to connect to db
    return new FullNameDaoImpl(this);
  }

}

````

### DAO Operation interface
We create an DAO Interface named ```FullNameDao``` that defines the type of operations 
performed for the type ```FullName``` class of our model, independently of the data source used to store data

````java
package com.iracanyes.demo.j2ee.ticket.dao;

import com.iracanyes.demo.j2ee.ticket.model.FullName;

import java.util.List;

public interface FullNameMysqlDao {
  void create(FullName fullname) throws DaoException;
  List<FullName> findAll() throws DaoException;
}

````

### DAO Operations class implementation
The class that implements those DAO interface will define the type of data store it interact with.  

We create a class ``FullNameDaoMysqlImpl`` which implements the methods 
to interact with the data source *MySQL database*.
Each class which implements DAO interface will also have an instance of a **DAO Factory** class to interact with a specific data store.
The class defines a property ``mysqlDaoFactory`` which will contains an instance of ``MysqlDaoFactory`` class

The class also implements all methods of the interface ``FullNameDao``
which defines the type of operations performed on the type ``FullName`` of the model.

````java
package com.iracanyes.demo.j2ee.ticket.dao;

import com.iracanyes.demo.j2ee.ticket.model.FullName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FullNameMysqlDaoImpl implements FullNameDao {
  private MysqlDaoFactory mysqlDaoFactory;
  private Logger logger = Logger.getLogger(FullNameMysqlDaoImpl.class.getName());
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
      // Commit our operation 
      connection.commit();
    }catch(SQLException e){
      logger.log(Level.SEVERE, "Error while executing prepared statement.", e);
      // Throw new Exception to hide sensitive data from outside
      throw new DaoException("Error while executing prepared statement.");
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
      // Log the SQL Exception
      logger.log(Level.SEVERE, "Error while executing statement", e);
      // Throw new Exception to hide sensitive data from outside
      throw new DaoException("Error while executing statement");
    }finally{
      try{
        if(connection != null){ connection.close();}
        if(statement != null){ statement.close();}
        if(resultSet != null){ resultSet.close();}
      }catch (SQLException e){
        // Log the SQL Exception
        logger.log(Level.SEVERE, "Error while closing connection, statement and resultSet", e);
        // Throw new Exception to hide sensitive data from outside
        throw new DaoException("Error while closing connection, statement and resultSet");
      }

    }

    return fullNames;
  }
}
````

### Dao Usage

#### Inside Servlet
Here, we gonna use the DAO implementation on the presentation layer, 
inside our servlet class ``NameForm``.
You can also use DAO implementation inside business layer of the application

````java
package com.iracanyes.demo.j2ee.ticket.webapp.servlets;


import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.iracanyes.demo.j2ee.ticket.FullNameService;
import com.iracanyes.demo.j2ee.ticket.dao.MysqlDaoFactory;
import com.iracanyes.demo.j2ee.ticket.dao.FullNamelDao;
import com.iracanyes.demo.j2ee.ticket.model.FullName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "name", value = "/hello-servlet")
public class NameForm extends HttpServlet {
  private static final Logger logger = Logger.getLogger(NameForm.class.getName());

  private FullNamelDao fullNamelDao;

  @Override
  public void init() throws ServletException{
    MysqlDaoFactory mysqlDaoFactory = MysqlDaoFactory.getInstance();
    this.fullNamelDao = mysqlDaoFactory.getFullNameDao();
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
    response.setContentType("text/html");

    // Uncomment to use service to retrive data from database
    // getFullNamesUsingService(request);


    // Best pratice is to use DAO - Data Access Object
    getFullNamesUsingDAO(request);

    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/fullname_form.jsp")
            .forward(request, response);

  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    // Uncomment to use service to communicate with database
    // createFullnameObjectUsingService(request);

    // Best pratice is to use DAO - Data Access Object
    createFullnameObjectUsingDAO(request);

    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/fullname_form.jsp")
            .forward(request, response);
  }
  /*------- Using a service to provide data ---------- */

  public void getFullNamesUsingService(HttpServletRequest request){
    try{
      FullNameService fullNameService = new FullNameService();
      request.setAttribute("fullnames", fullNameService.getFullNames());
    }catch (SQLException|IOException e) {
      logger.log(Level.SEVERE, "fullNameService.getFullNames() error", e);
    }
  }

  public void createFullnameObjectUsingService(HttpServletRequest request){
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");

    FullName fullName = new FullName(firstname, lastname);

    try{
      // Create a new FullName object in DB
      FullNameService fullNameService = new FullNameService();
      fullNameService.createName(fullName);
      // Retrieve all FullName object in DB
      request.setAttribute("fullnames", fullNameService.getFullNames());
    }catch (SQLException|IOException e) {
      logger.log(Level.SEVERE, "fullNameService.getFullNames() error", e);
    }
  }

  /*------- Using a DOA to provide data ---------- */
  public void getFullNamesUsingDAO(HttpServletRequest request){
    try{
      request.setAttribute("fullnames", this.fullNamelDao.findAll());
    }catch(DaoException e){
      request.setAttribute("error", e.getMessage());
    }
    
  }

  public void createFullnameObjectUsingDAO(HttpServletRequest request){
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");

    try{
      // Create a new FullName object in DB
      this.fullNamelDao.create( new FullName(firstname, lastname));
      // Retrieve all FullName object in DB
      request.setAttribute("fullnames", this.fullNamelDao.findAll());
    }catch (DaoException e){
      request.setAttribute("error", e.getMessage());
    }
  }

}
````

##### JSP page
Here we create a JSP page for the form to add a fullname and to display all fullname in data store.

````jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Description: 
  Date: 20-10-22  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%-- Import package 
<%@ page import="java.time.LocalDateTime" %>
--%>

<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>forms </title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
            crossorigin="anonymous"
    />
    <style>
      <%@ include file="/WEB-INF/resources/css/styles.css"%>
      <%@ include file="/WEB-INF/resources/css/custom.css"%>
    </style>
  </head>
  <body id="forms">
    <%@include file="/WEB-INF/jsp/_include/header.jsp" %>
    <main  class="d-flex flex-nowrap">
      <!-- section: Navigation -->
      <section id="sidebar">
        <%@ include file="../_include/sidebar-1.jsp"%>
      </section>
      <section id="main-content"  class="d-flex flex-column">
        <h1>Full name form</h1>
        <article>
          <form method="post" action="create_names" class="d-flex flex-column">
            <fieldset>
              <legend>User</legend>
              <fieldset>
                <legend>Names</legend>
                <div class="input-group mb-3">
                  <label for="firstname">Firstname : </label>
                  <input type="text" id="firstname" name="firstname"  class="form-control" />
                </div>
                <div class="input-group">
                  <label for="lastname">Lastname : </label>
                  <input type="text" id="lastname" name="lastname"  class="form-control"/>
                </div>
              </fieldset>
            </fieldset>
            <div class="button-group mt-2">
              <button type="submit" class="btn btn-success">Envoyer</button>
              <button class="btn btn-danger">Annuler</button>
            </div>
          </form>
        </article>
        <c:if test="${!empty fullnames }">
          <article>
            <h3>Names</h3>
            <ul>
              <c:forEach var="fullname" items="${ fullnames  }">
                <li>
                  Firstname : ${ fullname.firstname } <br>
                  Lastname : ${ fullname.lastname } <br>
                </li>
              </c:forEach>
            </ul>
          </article>
        </c:if>
      </section>
      <%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
    </main>
  </body>
</html>    
````

And the last we configure the route ``/create_names`` and its handler
the servlets ``com.iracanyes.demo.j2ee.ticket.webapp.servlets.NameForm`` in the ``web.xml``

````xml
<servlet>
  <servlet-name>NameForm</servlet-name>
  <servlet-class>com.iracanyes.demo.j2ee.ticket.webapp.servlets.NameForm</servlet-class>
  <load-on-startup>2</load-on-startup>
</servlet>
<servlet-mapping>
  <servlet-name>NameForm</servlet-name>
  <url-pattern>/forms/create_names</url-pattern>
</servlet-mapping>
````

#### Exceptions Handler
In the example used, we must create 2 exception classes ``DaoException`` and ``ModelException``

``ModelException`` instances will be thrown if a rule of our model is broken.

````java
package com.iracanyes.demo.j2ee.ticket.model;

public class ModelException extends Exception{
  public ModelException(String message){
    super(message);
  }
}
````
``DaoException`` class will hide SQLException thrown in our DAO implementation classes from the caller context
We gonna catch SQLException thrown and log them and after rethrow a DaoException.
````java
package com.iracanyes.demo.j2ee.ticket.dao;

public class DaoException extends Exception{
  public DaoException(String message){
    super(message);
  }
}
````

You can see a sample of usage of ``DaoException`` class in the ``FullNameMysqlDaoImpl``.


#### Database transactions
Database transactions allow to perform multiple operations on a database and commit those changes
only if all operations are resolved without issue.
if one of the operation throw an error we can rollback the other operations already accomplished.

By default, each successful operation on the database is automatically committed.
To enable transaction with msyql-connector-j, we need to disable ``AutoCommit``
In the class ```MysqlDaoFactory```, when we create the connection we must disable autocommit.
````java
class MysqlDaoFactory{
  // ...
  
  public Connection getConnection() throws SQLException{
    // Create the connection to database
    Connection connection = DriverManager.getConnection(url, username, password);

    // Disable autocommit operations, to enable transactions
    connection.setAutoCommit(false);
    return connection;
  }
  
  // ...
}

````

Now, all operations that involves a change in the state of our database , 
will be committed manually.

````java
import java.sql.SQLException;

class FullNameDaoImpl {
  @Override
  public void create(FullName fullname) throws DaoException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = mysqlDaoFactory.getConnection();
      preparedStatement = connection.prepareStatement(INSERT_INTO);

      preparedStatement.setString(1, fullname.getFirstname());
      preparedStatement.setString(2, fullname.getLastname());

      // Execute the prepared statement
      preparedStatement.executeUpdate();

      // Here we commit the changes 
      connection.commit();
    } catch (SQLException e) {
      if (connection != null) {
        try {
          // Here we cancel all modification already accomplished
          connection.rollback();
        } catch (SQLException e){
          logger.log(Level.SEVERE, "Error while executing connection rollback.", e);
          // Throw new Exception to hide sensitive data from outside
          throw new DaoException("Error while executing connection rollback.");
        }
      }
      logger.log(Level.SEVERE, "Error while executing prepared statement.", e);
      // Throw new Exception to hide sensitive data from outside
      throw new DaoException("Error while executing prepared statement.");
    } finally {
      if (connection != null) {
        try {
          // Here we close the connection
          connection.close();
        } catch (SQLException exception){
          logger.log(Level.SEVERE, "Error while executing connection close.", exception);
          // Throw new Exception to hide sensitive data from outside
          throw new DaoException("Error while executing connection close.");
        }
      }
    }
  }
}
````