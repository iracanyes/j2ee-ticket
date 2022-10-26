/*
* Description :
* This Bean object handle login connection to the app.
* It simulates the result returned from
* Database request for existing user.
*
* */
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

    String filename = getFilename(part);

    if(filename != null && !filename.isEmpty()){
      String fieldName = part.getName();

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
