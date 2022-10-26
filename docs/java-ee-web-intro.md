# Java EE

## Introduction

### MVC Design pattern - Model View Controller

Java EE n'exige pas de structure pour son code tant qu'il est fonctionnel. Le design pattern MVC est utilisé pour
structurer le code d'une application Java EE. Les Controller gère le traitement des requêtes entrant par routage, en
faisant appel aux modèles et aux vues prédefinis si nécessaire pour générer la réponse appropriée. Ex: routage, appels
des services, vues Le modèle contient les informations structurées de nos données. Il permet une communication
structurée avec les bases de données, API, etc. Ex: DB, external API, services, etc. La vue définit une page HTML ou une
réponse retourner par le controller Ex: Homepage,

#### MVC et Java EE

Avec Java EE, chaque élément du design pattern a son nom propre.
- Controller est géré par des servlets
- Modèles sont en général géré par les Java Beans Objects
- Vues sont gérées par des pages JSP.

### Java EE frameworks

Il existe de nombreux frameworks Java EE

- JSF
- Struts    
- Spring (framework web)
    - Permet de gérer les transactions, l'injection des dépendances, et la programmation orienté aspect
- Hibernate (ORM - Object Relational Mapping)
    - Implémente les normes (ou spécifications) JPA (Java Persistence API). Les normes Permet aux outils développés de suivre la même logique de fonctionnement
    - Permet de faciliter le développement de la couche persistance des données.
    - Permet de représenter une base de donnée sous forme d'objets java en utilisant les modèles (Java Bean Objects).

## Servlets - Controller
Le controller est géré par des servlets.
Les servlets sont de simples classes Java qui implémente
des méthodes qui ont pour object de recevoir l'information d'une requête HTTP
et de produire une réponse HTTP (souvent une page HTML):
- doGet
- doPost
- doPut (update d'un objet complet, détruit l'objet préexistant)
- doDelete (delete d'une ressource)
- doPatch (update d'un élément d'un objet)
- doOptions (pre-flight request accéder aux contraintes d'accès à une ressource )
- doHead (accéder aux meta données d'une ressource ou d'un fichier)
- doTrace (méthode réservé au debugging, vérifier si une requête est reçu et une réponse renvoyé)
- doConnect (permet d'utiliser un proxy HTTP comme un tunnel TCP par exemple)

### Servlets creation
Servlets are in the presentation layer of an application. 
All servlets of the app will be stored in directory ```webapp/src/main/java/package/name/servlets```
They handle the reception of HTTP request, call business logic, dispatch to JSP which provides the vue to return  
In a multi-tiers project, You can create a servlets in the directory ``ticket-webapp/src/main/java``

> Recall :
> J2ee is not allow to use the brand ``Java`` since Oracle owned the brand 2017.
> J2ee changed all package with name "javax" to "jakarta" 
````java
package com.iracanyes.demo.j2ee.ticket.webapp.servlets;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class Home extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // HttpServletResponse content type is text/html
    response.setContentType("text/html");
    // UTF-8 gére les accents
    response.setCharacterEncoding("UTF-8");

    // Dispatch to home.jsp for handling the vue to return in response
    // forward the request object which contains the params, and attributes
    // and response object on which the build html page will be attached  
    this.getServletContext()
            .getRequestDispatcher("index.jsp")
            .forward(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO: 1. Create form

  }

}
````
## JSP page
JSP page handle the creation of the vue to return.  
JSP provides many ways to integrate data from different sources (request, Beans objects, service provider) to the HTML document:
- Java code insertion (==Not recommended!==)
- Expression Language
- JSTL - Java Standard Tag Library

### Create a JSP page 

Here is a sample code of a basic JSP page.
The page is stored in the directory ``WEB-INF/jsp/page/my_page.jsp``

the page use some JSP directives:
- ```<%@ page />``` :   
  allow to set attribute for the document like programming language used
- ```<%@ include file="[path-to-file]" />``` :   
  allow to insert another JSP page. 
  Use a relative path from the document itself ``../path/to/file.ext`` 
  or an absolute path from the directory of the web content directory ``webapp``

````jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${application.name} - A propos</title>
    <link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
        crossorigin="anonymous"
    />
    <link href="../../style/custom.css" rel="stylesheet" />
  </head>
  <body>
    <!-- includes file: header.jsp -->
    <%@ include file="../_include/header.jsp" %>
    
    <div class="container">
      <ul>
        <li>Application: ${application.name}</li>
        <li>Version: ${project.version}</li>
        <li>Date du build: ${maven.build.timestamp}</li>
    
      </ul>
    </div>
    
    <!-- includes file: footer.jsp -->
    <%@ include file="../_include/footer.jsp" %>
  </body>
</html>
````

### 


### JSTL 
> Recall:  
> As a good practice, NEVER implements java code inside a JSP file

JSTL allow to remove Java code from our JSP page
The library is divided into 5 modules:
- core ``c.tld`` : handle the display of variables, conditions, loop, 
- format ``fmt.tld``: handle the data formatting, internationalisation of the site
- xml ``xml.tld`` : handle the manipulation of XML document
- functions `functions.tld`: handle strings
- sql `sql.tld`: allow to write SQL code inside your JSP File ( *** Wrong practice! *** )

#### Download JSTL libraries
We indicate to JVM to load the JSTL library for this specific page.

There is 2 ways to load JSTL libraries inside JSP page:
- download all jar package for JSTL on page [JSTL download](https://tomcat.apache.org/download-taglibs.cgi)  
  and put all jar packages in directory ```/src/main/webapp/WEB-INF/lib/```
  - taglibs-standard-spec-1.2.5.jar
  - taglibs-standard-impl-1.2.5.jar
  - taglibs-standard-jstlel-1.2.5.jar
  - serializer-2.7.1.jar

If you do not use JSTL 1.0 tags then the "taglibs-standard-jstlel" JAR may be
omitted. If you do not use the XML library, then the Apache Xalan dependencies
may also be omitted. At the time of writing Xalan dependencies contains vulnerabilities.

- if Maven is used as dependency manager,
  add the dependencies to the POM
  ````xml
   <dependency>
      <groupId>org.glassfish.web</groupId>
      <artifactId>jakarta.servlet.jsp.jstl</artifactId>
      <version>3.0.0</version>
    </dependency>
    <dependency>
      <groupId>jakarta.servlet.jsp.jstl</groupId>
      <artifactId>jakarta.servlet.jsp.jstl-api</artifactId>
      <version>3.0.0</version>
    </dependency>
   <dependency>
      <groupId>org.apache.taglibs</groupId>
      <artifactId>taglibs-standard-spec</artifactId>
      <version>1.2.5</version>
    </dependency>
    <dependency>
      <groupId>org.apache.taglibs</groupId>
      <artifactId>taglibs-standard-impl</artifactId>
      <version>1.2.5</version>
    </dependency>
    <dependency>
      <groupId>org.apache.taglibs</groupId>
      <artifactId>taglibs-standard-jstlel</artifactId>
      <version>1.2.5</version>
    </dependency>
    <dependency>
      <groupId>xalan</groupId>
      <artifactId>serializer</artifactId>
      <version>2.7.2</version>
    </dependency>
  ````
#### Load JSTL libraries into JSP files

Next, we load each JSTL core library and indicate to use the corresponding prefix defined in documentation to call it
````
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Add  JSTL core library  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

````