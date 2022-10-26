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
    <main  class="d-flex flex-row flex-nowrap">
      <!-- section: Navigation -->
      <section id="sidebar">
        <%@ include file="../_include/sidebar-1.jsp"%>
      </section>
      <section id="main-content"  class="d-flex flex-column">
        <h1>Forms</h1>
        <c:if test="${ !empty sessionScope.login }" >
          <article>
            <div class="alert alert-info">
              <c:out value="Hello, ${sessionScope.login}" />
            </div>
          </article>
        </c:if>
        <article>
          <%@ include file="../_include/form.jsp"%>
        </article>
        <article>
          <h3>Data received from form</h3>
          <p>
            You can access data from request in JSP file but it's not recommended for security reason to manipulate data without sanitizing it <br>
            Firstname : ${ param.firstname } <br>
            Lastname : ${ param.lastname } <br>
          </p>
          <p>
            Here we access data from form retrieved by servlet and set in attributes of the request passed to JSP file.
            <br>
            Firstname : <c:out value="${firstname}" /> <br>
            Lastname : <c:out value="${lastname}" /> <br>
          </p>
          <p>

          </p>
        </article>
      </section>
      <%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
    </main>
  </body>
</html>    