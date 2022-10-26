<%--
  User: iracanyes
  Date: 06-10-22
--%>
<%--
  First JSP directive
  <%@ page %> is a the page directive which allow to set attribute for the document
  Here is some of the action available :
    - contentType : set content type of the document
    - language : programming language used inside
    - import : import package
  See docs for attributes: https://docs.oracle.com/cd/E17802_01/j2ee/j2ee/1.4/docs/tutorial-update2/doc/JSPIntro7.html
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%--Import library  --%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.LocalTime" %>

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
    <style>
      <%@ include file="/WEB-INF/resources/css/styles.css"%>
      <%@ include file="/WEB-INF/resources/css/custom.css"%>
    </style>

  </head>
  <body>
    <!-- includes file: header.jsp -->
    <%--
      <%@ include %>
      include directive allow to include CSS, HTML,JSP file
      In a JSP include directive the path can be relative to the including page or absolute
      (then it has to start with a / and belongs to the webapplication root directory).
    --%>

    <%@ include file="/WEB-INF/jsp/_include/header.jsp" %>
    <main class="d-flex flex-row flex-nowrap">
      <!-- section: Navigation -->
      <section id="sidebar">
        <%@ include file="../_include/sidebar-1.jsp"%>
      </section>
      <!-- section: Main content -->
      <section id="main-content" class="d-flex">
        <section id="jsp-pages" class="d-flex flex-column">
          <h3>JSP pages</h3>
          <article class="d-flex flex-column">
            <h4>Wrong practice</h4>
            <blockquote class="blockquote alert alert-danger">Never add Java code directly in JSP file</blockquote>
            <p>
              <%
                // Une balise JSP permet d'exécuter du code java au sein de la page
                String myString = (String) request.getAttribute("myString");
                out.println(myString);

              %>
            </p>
            <p>
              <%
                LocalDateTime ld = LocalDateTime.now();
                // Data from Get request parameter
                String name2 = (String) request.getAttribute("name_request");

                if(ld.toLocalTime().isAfter(LocalTime.of(18, 0, 0))){
                  out.println("Bonsoir " + (name2 != null ? name2 : ""));
                }else{
                  out.println("Bonjour " + (name2 != null ? name2 : ""));
                }
              %>
            </p>
          </article>
          <article class="d-flex  flex-column">
            <div  class="d-flex">
              <h4>Access POM properties</h4>
              <p>
                JSP file allow to access request.attribute elements using
              </p>
              <article class="d-flex">
                <ul>
                  <li>Application: ${application.name}</li>
                  <li>Version: ${project.version}</li>
                  <li>Date du build: ${maven.build.timestamp}</li>
                </ul>
              </article>
            </div>

            <div class="d-flex">
              <h3>Expression language</h3>
              <p>
                JSP file allow to access request.attributes elements using Expression language notation <code>\${}</code>
              </p>
              <p>
                <%--
                  Using Expression language
                  You can access request.attribute using the name of attribute in request object
                 --%>
                Name received from parameter : ${ !empty name_request ? name_request : ""}
              </p>
              <p>
                MyString : ${myString}
              </p>
              <p>
                Name 2  : ${ names[2] }
              </p>
              <p>
                Request parameter [name] : ${name_request}
              </p>
              <p>
                Using Java beans objects in request.attributes : Auteur <br>
                Firstname: ${ author.firstname } <br>
                Lastname: ${ author.lastname } <br>
                IsActive: ${ author.active } <br>
              </p>
            </div>

          </article>

          <section id="jstl">
            <h4>JSTL - Java Standard Tag Library</h4>
            <p>
              Allow to withdraw java code from JSP files by using methods provided by those libraries
            </p>
            <section id="jstl-core">
              <h5>JSTL Core</h5>

            </section>

          </section>

          <article>


          </article>

        </section>

      </section>
    </main>


    <!-- includes file: footer.jsp -->
    <%@ include file="../_include/footer.jsp" %>
  </body>
</html>
