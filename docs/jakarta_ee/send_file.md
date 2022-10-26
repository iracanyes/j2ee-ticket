# Jakarta EE - Send file

## Create Form

First, we create a JSP page with a form for sending file. 
There is 2 key element to specify in the form.
1. the file encoding type used with the form.
  ``<form method="post" action="send_file" enctype="multipart/form-data" />``
2. The input file tag
   ``<input type="file" id="fileInput" name="myFileInput" />``

````xml
<form method="post" action="send_file" enctype="multipart/form-data" class="col-6">
  <fieldset>
    <legend>File form</legend>
    <div class="input-group mb-3">
      <label for="descriptionInput" class="input-group-text p-2">Description du fichier</label>
      <input type="text" id="descriptionInput" name="description" class="form-control"/>
    </div>
    <div class="input-group mb-3">
      <label for="fileInput" class="input-group-text">Fichier à envoyer</label>
      <input type="file" id="fileInput" name="myFileInput" class="form-control"/>
    </div>
    <div class="btn-group">
      <button type="submit" class="btn btn-success">Send file</button>
      <button class="btn btn-danger">Cancel</button>
    </div>
  </fieldset>
  <c:if test="${ !empty myFileInput }">
    <fieldset>
      <legend>Resultat</legend>
      <div class="alert alert-info">
        The file : <c:out value=" ${ myFileInput }"/> <br>
        <c:out value="${ description }" />
      </div>
    </fieldset>
  </c:if>

</form>
````

## Create servlet
Create a controller which handle the request, call the business logic necessary to treat the request
and finally dispatch request and response object to JSP file that will build the response page returned 
````java
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

    // Call the business logic which for file upload
    FileHandler fileHandler = new FileHandler();
    fileHandler.uploadFile(request);

    // Dispatch to JSP file which handle to response page returned
    this.getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/page/send_file.jsp")
            .forward(request, response);
  }

}
````

### Configure servlet
Configure the servlet mapping by connecting request for URI ``/send_file`` to servlet that handle it
In the web configuration file ```webapp/WEB-INF/web.xml```
````xml
<!-- ================================ Page Send file form ======================== -->
  <servlet>
    <servlet-name>SendFile</servlet-name>
    <servlet-class>com.iracanyes.demo.j2ee.ticket.webapp.servlets.FileForm</servlet-class>
    <load-on-startup>2</load-on-startup>
    <!-- Configuration for uploading files -->
    <multipart-config>
      <!-- Location to store  temporary files. 
      Here we use Windows path  
      On server, you would use Unix path:  /c/Projets/cours/frameworks/java/j2eee/ticket/dist/tmp_files/
      -->
      <location>C:\Projets\cours\frameworks\java\j2ee\ticket\dist\tmp_files\</location>
      <!-- Size of uploaded files -->
      <max-file-size>10485760</max-file-size> <!-- 10 Mo -->
      <!-- Size of request  -->
      <max-request-size>52428800</max-request-size> <!-- 5 x 10 Mo -->
      <!-- Size after which an uploaded file is written to disk -->
      <file-size-threshold>1048576</file-size-threshold> <!-- 1 Mo -->
    </multipart-config>
  </servlet>
  <servlet-mapping>
    <servlet-name>SendFile</servlet-name>
    <url-pattern>/send_file</url-pattern>
  </servlet-mapping>
  <!-- ================================ End Page Send file form ======================== -->
````
## Business Logic
Business logic handle service consumption (Database, API, etc.).
Here, we create Bean object which will contains our logic for file upload
It consume a service ``ÙploadFile`` responsible of uploading the temporary file to disk 
````java
package com.iracanyes.demo.j2ee.ticket.forms;

import com.iracanyes.demo.j2ee.ticket.UploadFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;


import java.io.IOException;

public class FileHandler {

  /*
  * Simulate a call to a service consumer (database) looking for an existing user.
  * Here we check that the password is equal to (login value +"123")
  * */
  public void uploadFile(HttpServletRequest request) throws ServletException, IOException {
    Part part = request.getPart("myFileInput");
    // The filename is the full path to the file
    String filename = getFilename(part);

    if(filename != null && !filename.isEmpty()){
      String fieldName = part.getName();
      
      // The filename is the full path to the file 
      filename = filename.substring(filename.lastIndexOf('/') + 1)
              .substring(filename.lastIndexOf("\\") + 1);

      UploadFile.writeFileToDisk(part, filename);

      request.setAttribute(fieldName, filename);

    }

  }

  private static String getFilename(Part part){
    for(String contentDisposition : part.getHeader("content-disposition").split(";")){

      if(contentDisposition.trim().startsWith("filename")){
        return contentDisposition.substring(
                contentDisposition.indexOf("=") + 1)
                .trim().replace("\"", "");
      }
    }

    return null;
  }
  
}
````

> Recall 
> The form request with encoding type ``multipart/form-data`` send its data in the format below : 
>  ````
>  -----------------------------143583359941450267124008729983
>  Content-Disposition: form-data; name="description"
>
>  Liste de jeux PC rÃ©cents au format PNG
>  -----------------------------143583359941450267124008729983
>  Content-Disposition: form-data; name="myFileInput"; filename="Jeux PC rÃ©cents.PNG"
>  Content-Type: image/png
>  ...
> ````
>
> 
## Service - UploadFile
Here, we create a service that handle writing files to disk.
````java
package com.iracanyes.demo.j2ee.ticket;

import java.io.*;
import jakarta.servlet.http.Part;

public class UploadFile {
  /* buffer_size used to transfer the temporary file to uploaded files directory */
  public static final int BUFFER_SIZE = 10240;
  public static final String FILES_PATH = "C:\\Projets\\cours\\frameworks\\java\\j2ee\\ticket\\dist\\uploaded_files\\";

  // Prevent object creation by hiding implicit default public constructor
  private UploadFile(){

  }

  public static void writeFileToDisk(Part part, String filename) throws IOException {

    try(
        BufferedInputStream bis = new BufferedInputStream(part.getInputStream(), BUFFER_SIZE);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(FILES_PATH + filename)), BUFFER_SIZE);
    ){
      byte[] buffer = new byte[BUFFER_SIZE];
      int length;

      while((length = bis.read(buffer)) > 0){
        bos.write(buffer, 0, length);
      }


    }
  }
}

````