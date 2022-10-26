package com.iracanyes.demo.j2ee.ticket.webapp.servlets;

//import jakarta.servlet.*;
//import jakarta.servlet.http.*;
import com.iracanyes.demo.j2ee.ticket.Author;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
//import java.io.*;
import java.io.PrintWriter;
import java.io.IOException;

@WebServlet(name="Test", value="/Test")
public class Test extends HttpServlet{
  public Test(){
    super();
  }

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException{

    // Create a basic response text wrote in a HTML document
    // basicResponse(response);

    // Create a basic HTML document using PrintWriter
    //writeHtmlDocument(response);

    // Dispatch request to JSP file which handle HTML document creation for the response
    // and attach to it
    //createResponseFromJsp(request, response);

    // Dispatch request to JSP file and passing data using HttpServletRequest object
    useJspWithData(request, response);

    // Dispatch request to JSP file and using passed request parameter to generate response
    useJspWithRequestParameter(request, response);

    useJspWithJavaBeansObjects(request, response);

  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response) throws ServletException, IOException{
    // TODO
  }

  public void setResponseMetadata(HttpServletResponse response){
    // HttpServletResponse content type is text/html
    response.setContentType("text/html");
    // UTF-8 gére les accents
    response.setCharacterEncoding("UTF-8");
  }

  /*
  * Create a basic response text wrapped in a HTML document
  */
  public void basicResponse(HttpServletResponse response) throws ServletException, IOException{
    // Set response metadata for HTML document with accents for french language
    this.setResponseMetadata(response);

    // PrintWriter permet de définir l'objet retournée
    PrintWriter out = response.getWriter();
    // Ecrire une ligne dans le document retourné
    out.println("Hello from the Test servlet ");
  }

  public void dispatchToJspFile(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    /*
     * Le répertoire WEB-INF est masqué aux visiteurs du site
     * Pour renvoyer une page JSP, on utilisera le contexte du servlet,
     * qui contient une méthode getRequestDispatcher() qui permet
     * d'indiquer quel fichier JSP sera chargé de former la réponse à la requête.
     * On passe ensuite les objets request et response avec la méthode forward(HttpServletRequest, HttpServletResponse)
     * */
    // Reponse retournant une page JSP dans le répertoire webapp/WEB-INF/
    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/test.jsp")
            .forward(request, response);
  }

  public void writeHtmlDocument(HttpServletResponse response) throws ServletException, IOException{
    // Set response metadata for HTML document with accents for french language
    this.setResponseMetadata(response);


    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset=\"utf-8\" />");
    out.println("<title>Test</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<p>Bonjour !</p>");
    out.println("</body>");
    out.println("</html>");
  }

  public void createResponseFromJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    // Set response metadata for HTML document with accents for french language
    this.setResponseMetadata(response);

    /*
     * Le répertoire WEB-INF est masqué aux visiteurs du site
     * Pour renvoyer une page JSP, on utilisera le contexte du servlet,
     * qui contient une méthode getRequestDispatcher() qui permet
     * d'indiquer quel fichier JSP sera chargé de former la réponse à la requête.
     * On passe ensuite les objets request et response avec la méthode forward(HttpServletRequest, HttpServletResponse)
     * */
    // Reponse retournant une page JSP dans le répertoire webapp/WEB-INF/
    this.dispatchToJspFile(request, response );
  }

  public void useJspWithData(HttpServletRequest request, HttpServletResponse response){
    // Set response metadata for HTML document with accents for french language
    this.setResponseMetadata(response);

    String stringData = "String passed to JSP file";
    // We can pass data to JSP files by setting a new attribute to the request object HttpServletRequest
    request.setAttribute("myString", stringData);
  }

  public void useJspWithRequestParameter(
          HttpServletRequest request,
          HttpServletResponse response
  ){
    // Set response metadata for HTML document with accents for french language
    this.setResponseMetadata(response);

    // Access request parameters using request.getParameter()
    String param = request.getParameter("name");

    // TODO: Sanitize and  Validate input

    // Use input
    if(param != null && !param.isEmpty()){
      request.setAttribute("name_request", param);
    }

    String[] names = {"John", "Jane", "Jack","Jean"};
    request.setAttribute("names", names);

  }

  public void useJspWithJavaBeansObjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    Author author = new Author("Jack", "Random","https://jack-random.fr", true);
    request.setAttribute("author", author);

    this.dispatchToJspFile( request, response);
  }



}