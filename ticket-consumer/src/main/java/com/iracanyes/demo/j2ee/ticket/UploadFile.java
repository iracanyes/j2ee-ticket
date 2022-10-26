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
