package com.iracanyes.demo.j2ee.ticket.webapp.servlets;


import java.io.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.iracanyes.demo.j2ee.ticket.FullNameService;
import com.iracanyes.demo.j2ee.ticket.dao.DaoException;
import com.iracanyes.demo.j2ee.ticket.dao.MysqlDaoFactory;
import com.iracanyes.demo.j2ee.ticket.dao.FullNameDao;
import com.iracanyes.demo.j2ee.ticket.model.bean.intro.FullName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "name", value = "/hello-servlet")
public class NameForm extends HttpServlet {
  private static final Logger logger = Logger.getLogger(NameForm.class.getName());

  private FullNameDao fullNameDao;

  @Override
  public void init() throws ServletException{
    MysqlDaoFactory mysqlDaoFactory = MysqlDaoFactory.getInstance();
    this.fullNameDao = mysqlDaoFactory.getFullNameDao();
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

    getFullNamesUsingDAO(request);

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
      request.setAttribute("fullnames", this.fullNameDao.findAll());
    }catch(DaoException e){
      request.setAttribute("error", e.getMessage());
    }
  }

  public void createFullnameObjectUsingDAO(HttpServletRequest request){
    String firstname = request.getParameter("firstname");
    String lastname = request.getParameter("lastname");

    try{
      // Create a new FullName object in DB
      this.fullNameDao.create( new FullName(firstname, lastname));
      // Retrieve all FullName object in DB
      request.setAttribute("fullnames", this.fullNameDao.findAll());
    }catch(DaoException e){
      request.setAttribute("error", e.getMessage());
    }
  }

}