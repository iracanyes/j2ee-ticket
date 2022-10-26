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
    <main  class="d-flex flex-row flex-nowrap">
      <!-- section: Navigation -->
      <section id="sidebar">
        <%@ include file="../_include/sidebar-1.jsp"%>
      </section>
      <section id="main-content"  class="d-flex flex-column">
        <h1>Author</h1>
        <article>
          <form method="post" action="forms" class="d-flex flex-column">
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
                  <input type="text" id="lastname" name="firstname"  class="form-control"/>
                </div>
              </fieldset>
            </fieldset>
            <div class="button-group mt-2">
              <button type="submit" class="btn btn-success">Envoyer</button>
              <button class="btn btn-danger">Annuler</button>
            </div>
          </form>
        </article>
        <article>
          <h3>Names</h3>
          <ul>
            <c:forEach var="author" items="${ authors }">
              <li>
                Firstname : ${ author.firstname } <br>
                Lastname : ${ author.lastname } <br>
              </li>
            </c:forEach>
          </ul>
        </article>
      </section>
      <%@include file="/WEB-INF/jsp/_include/footer.jsp" %>
    </main>
  </body>
</html>    