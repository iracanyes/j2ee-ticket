<%--
  Description: 
  Date: 10-10-22  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<nav class="d-flex flex-column flex-shrink-0 p-3 text-bg-dark" style="width: 280px;">
  <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
    <svg class="bi pe-none me-2" width="40" height="32"><use xlink:href="#bootstrap"/></svg>
    <span class="fs-4">Menu - ${application.name}</span>
  </a>
  <hr>
  <ul class="nav nav-pills flex-column mb-auto">
    <li class="nav-item">
      <a href="${pageContext.request.contextPath}/" class="nav-link active" aria-current="page">
        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/" /></svg>
        Home
      </a>
    </li>
    <li>
      <a href="${pageContext.request.contextPath}/about" class="nav-link text-white">
        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/about"/></svg>
        About
      </a>
    </li>
    <li>
      <a href="${pageContext.request.contextPath}/test" class="nav-link text-white">
        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/test" /></svg>
        Test
      </a>
    </li>
    <li>
        <a href="<c:url context="${pageContext.request.contextPath}" value="/jstl" />" class="nav-link text-white">
        <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/jstl"/></svg>
        JSTL - Java Standard Tag Library
      </a>
    </li>
    <li>
      <div class="dropdown">
        <button typeof="button" class="btn btn-dark" data-bs-toggle="dropdown" aria-expanded="false">
          <svg class="bi pe-none me-2" width="16" height="16"></svg>
          Forms
        </button>
        <ul class="dropdown-menu">
          <li>
            <button class="dropdown-item">
              <a href="<c:url context="${pageContext.request.contextPath}" value="/forms/samples" />" class="nav-link text-dark">
                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/forms/samples"/></svg>
                samples
              </a>
            </button>
          </li>
          <li>
            <button class="dropdown-item">
              <a href="<c:url context="${pageContext.request.contextPath}" value="/forms/login" />" class="nav-link text-dark">
                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/forms/login"/></svg>
                Login
              </a>
            </button>
          </li>
          <li>
            <button class="dropdown-item">
              <a href="<c:url context="${pageContext.request.contextPath}" value="/forms/send_file" />" class="nav-link text-dark">
                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/forms/send_file"/></svg>
                Send file
              </a>
            </button>
          </li>
          <li>
            <button class="dropdown-item">
              <a href="<c:url context="${pageContext.request.contextPath}" value="/forms/create_fullname" />" class="nav-link text-dark">
                <svg class="bi pe-none me-2" width="16" height="16"><use xlink:href="/forms/create_fullname"/></svg>
                Create Fullname
              </a>
            </button>
          </li>
        </ul>
      </div>
    </li>
  </ul>

  <hr>
  <div class="dropdown">
    <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
      <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
      <strong>mdo</strong>
    </a>
    <ul class="dropdown-menu dropdown-menu-dark text-small shadow">
      <li><a class="dropdown-item" href="#">New project...</a></li>
      <li><a class="dropdown-item" href="#">Settings</a></li>
      <li><a class="dropdown-item" href="#">Profile</a></li>
      <li><hr class="dropdown-divider"></li>
      <li><a class="dropdown-item" href="#">Sign out</a></li>
    </ul>
  </div>
</nav>