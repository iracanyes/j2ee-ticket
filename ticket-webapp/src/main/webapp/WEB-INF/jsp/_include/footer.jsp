<%--
  Created by IntelliJ IDEA.
  User: iracanyes
  Date: 06-10-22
  Time: 09:31
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<footer class="footer">
  <div class="container">
    <p>
      ${application.name} - version ${project.version}
      &copy; <c:if test="${!empty author}"><a href="${author.website}">${!empty author ? String.join(" ", author.firstname, author.lastname) : ""}</c:if> @ <a href="${organization.url}">${organization.name}</a>
    </p>
  </div>
</footer>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
