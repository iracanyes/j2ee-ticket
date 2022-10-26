package com.iracanyes.demo.j2ee.ticket.webapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.iracanyes.demo.j2ee.ticket.forms.FileHandler;

import java.io.IOException;

@WebServlet(name = "FileForm", value = "/file_form")
public class FileForm extends HttpServlet {


  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    this.getServletContext()
        .getRequestDispatcher("/WEB-INF/jsp/page/send_file.jsp")
        .forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");

    // Retrieve form input
    String description = request.getParameter("description");
    request.setAttribute("description", description);

    // Let Business logic handle file upload
    FileHandler fileHandler = new FileHandler();
    fileHandler.uploadFile(request);

    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/send_file.jsp")
            .forward(request, response);
  }

}