<%--
  Created by IntelliJ IDEA.
  User: iracanyes
  Date: 06-10-22
--%>
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
    <style rel="stylesheet">
      <%@ include file="/WEB-INF/resources/css/styles.css" %>
    </style>
  </head>
  <body>
    <!-- includes file: header.jsp -->
    <%@ include file="/WEB-INF/jsp/_include/header.jsp" %>

    <div class="container">
      <ul>
        <li>Application: ${application.name}</li>
        <li>Version: ${project.version}</li>
        <li>Date du build: ${maven.build.timestamp}</li>

      </ul>
    </div>

    <!-- includes file: footer.jsp -->
    <%@ include file="/WEB-INF/jsp/_include/footer.jsp" %>
  </body>
</html>
