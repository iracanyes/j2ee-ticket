<%--
  Description: 
  Date: 22-10-22  
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
  <title>send_file </title>
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
    <%@include file="/WEB-INF/jsp/_include/header.jsp" %>
    <main  class="d-flex flex-row flex-nowrap">
    <!-- section: Navigation -->
    <section id="sidebar">
      <%@ include file="../_include/sidebar-1.jsp"%>
    </section>
    <section id="main-content"  class="d-flex flex-column container">
      <h1>Upload File</h1>
      <article>
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
      </article>

    </section>

  </main>
  <%@include file="/WEB-INF/jsp/_include/footer.jsp"%>
</body>
</html>    