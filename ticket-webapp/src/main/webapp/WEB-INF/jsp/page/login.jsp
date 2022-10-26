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
    <title>Login</title>
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
  <body id="login-form">
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
          <form method="post" action="login">
            <fieldset>
              <legend>Connection</legend>
              <div class="form-group">
                <label for="login">Login</label>
                <input type="text" id="login" name="login"/>
              </div>
              <div class="form-group">
                <label for="passwordLabel">Password</label>
                <input type="password" id="passwordLabel" name="password"/>
              </div>
              <div class="button-group">
                <button type="submit" class="btn btn-success">Connect</button>
                <button class="btn btn-danger">Cancel</button>
              </div>
            </fieldset>
            <c:if test="${ !empty form }">
              <fieldset>
                <legend>Resultat</legend>
                <div class="alert alert-info">
                  <c:out value="${form.result}" />
                </div>
              </fieldset>
            </c:if>

          </form>
        </article>

      </section>

    </main>
    <%@include file="/WEB-INF/jsp/_include/footer.jsp"%>
  </body>
</html>    