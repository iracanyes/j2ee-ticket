<%--
  Description: 
  Date: 17-10-22  
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%-- Import package 
<%@ page import="java.time.LocalDateTime" %>
--%>
<%--
- JSTL Core - Import tag
- Path is relative web content directory "webapp"
--%>


<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Java Standard Tag Library </title>
  <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
      crossorigin="anonymous"
  />
  <style>
    <c:import url="/WEB-INF/resources/css/custom.css"/>
  </style>
</head>
<body>
  <!-- JSTL Core - Import tag   -->
  <c:import var="myHeader" url="/WEB-INF/jsp/_include/header.jsp"/>
  <c:out value="${myHeader}"  escapeXml="false"/>

  <main  class="d-flex flex-row flex-nowrap">
    <!-- section: Navigation -->
    <section id="sidebar">
      <%@ include file="/WEB-INF/jsp/_include/sidebar-1.jsp"%>
    </section>
    <!-- section: Main content -->
    <section id="main-content" class="d-flex flex-column">
      <h1>JSTL - Java Standard Tag Library</h1>
      <section id="jstl-core">
        <h2>JSTL Core</h2>
        <article id="core-import">
          <h3>Import - &lt;c:import&gt;</h3>
          <blockquote>
            <p>
              Depending on the IDE used to create the app, the root web content directory could be named:
            </p>
            <ul>
              <li>webcontent : app created using Eclipse IDE</li>
              <li>webapp : app created using Jetbrains Ultimate IDEA</li>
            </ul>
          </blockquote>
          <article id="jstl-import">
            <h3>Import - &lt;c:import&gt;</h3>
            <p>
              <code>&lt;c:import&gt;</code> tag allow to import document or data using an url. <br>
              If you use the relative path, it must be relative to the root web content directory. <br>
            </p>
            <pre>
              &lt;c:import url="/WEB-INF/resources/css/custom.css" scope="page"/&gt;
            </pre>
            <pre class="result">
              <c:import url="/WEB-INF/resources/css/custom.css"/>
            </pre>
          </article>

        </article>
        <article>
          <h3>Set a variable - &lt;c:set /&gt;</h3>
          <p>
            Set a variable
          </p>
          <pre>
            &lt;c:set var="variableName" value="Set the value of the variable" />
            &lt;c:out value="&#36;{variableName}" /&gt;

            &lt;c:set var="myVariable" scope="page">Valeur de ma variable&lt;/c:set>
            &lt;c:out value="&#36;{myVariable}" /&gt;

          </pre>
          <pre class="result">
            <c:set var="variableName" value="Set the value of the variable" />
            <c:out value="${variableName}" />
            <%-- Scope page : available in the JSP page --%>
            <c:set var="myVariable" scope="page">Valeur de ma variable</c:set>
            <c:out value="${myVariable}" />

          </pre>
        </article>
        <article>
          <h3>Display a value - &lt;c:out /&gt;</h3>
          <p>
            <code>&lt;c:out /&gt;</code> allow to display a value set in the JSP file, data from request.attribute,
            and also content of unique XML tag (ex: head, header, body, main, footer,etc.).
            It prevents XSS vulnerabilities by escaping all XML special characters, so you can display input from users with security.
          </p>
          <table class="table table-bordered" style="text-align:center;">
            <caption>Attributes of Core tag out function &lt;c:out&gt;</caption>
            <tbody><tr>
              <th style="text-align:center;">Attribute</th>
              <th style="text-align:center;">Description</th>
              <th style="text-align:center;">Required</th>
              <th style="text-align:center;">Default</th>
            </tr>
            <tr>
              <td>url</td>
              <td>URL to retrieve and import into the page</td>
              <td style="vertical-align:middle;">Yes</td>
              <td style="vertical-align:middle;">None</td>
            </tr>
            <tr>
              <td style="vertical-align:middle;">context</td>
              <td>/ followed by the name of a local web application</td>
              <td style="vertical-align:middle;">No</td>
              <td style="vertical-align:middle;">Current application</td>
            </tr>
            <tr>
              <td style="vertical-align:middle;">charEncoding</td>
              <td>Character set to use for imported data</td>
              <td style="vertical-align:middle;">No</td>
              <td style="vertical-align:middle;">ISO-8859-1</td>
            </tr>
            <tr>
              <td style="vertical-align:middle;">var</td>
              <td>Name of the variable to store imported text</td>
              <td style="vertical-align:middle;">No</td>
              <td style="vertical-align:middle;">Print to page</td>
            </tr>
            <tr>
              <td style="vertical-align:middle;">scope</td>
              <td>Scope of the variable used to store imported text. (value: page, request, session, application)</td>
              <td style="vertical-align:middle;">No</td>
              <td style="vertical-align:middle;">Page</td>
            </tr>
            <tr>
              <td style="vertical-align:middle;">varReader</td>
              <td>Name of an alternate variable to expose java.io.Reader</td>
              <td style="vertical-align:middle;">No</td>
              <td style="vertical-align:middle;">None</td>
            </tr>
            </tbody>
          </table>
          <section>
            <pre>
              &lt;!-- Explicit value set to display --&gt;
              &lt;c:out value = "Explicit value set" /&gt;
            </pre>
            <pre class="result">
              <c:out value="Explicit value set to display" />
            </pre>
            <p>
              In the example below, we set a default value if the variable doesn't exist
            </p>
            <pre>
              &lt;!-- Variable value to display --&gt;
              &lt;c:out value = "&#36;{variableName}" default="default value if variable doesn't exist"/&gt;

              &lt;c:out value="&#36;{myVar2}">
                Default content if variable doesn't exist
              &lt;/c:out>
            </pre>
            <pre class="result">
              <c:set var="myVar" value="Value of the variable myVar" />
              myVar: <c:out value="${myVar}" default="default value if variable doesn't exist"/>

              <c:out value="${myVar2}">
                Default content if variable doesn't exist
              </c:out>
            </pre>
          </section>
          <section>
            <h4>Access JSP default variables </h4>
            <p>
              <code>&ltc:out&gt;</code> allow also to display scoped variables and access default variables of Expression language. <br>
              It use the dot notation <code>.</code> to access sub properties of XML tag
            </p>
            <blockquote>
              <p>
                EL provides default variables for accessing different part of the application
              </p>
              <ul>
                <li><strong>pageScope</strong> : let you access variable defined in the page scope </li>
                <li><strong>requestScope</strong> : let you access variable defined in the request scope</li>
                <li><strong>sessionScope</strong> : let you access variable defined in the page scope
                <li><strong>applicationScope</strong> : let you access variable defined in the page scope
                <li><strong>param</strong> : Access parameter of the request
                <li><strong>paramValues</strong> : parameters of the request as list
                <li><strong>header</strong> : Access header of the request
                <li><strong>headerValues</strong> : headers of the request as a collection
                <li><strong>initParam</strong> : initialization parameters
                <li><strong>cookie</strong> : Access cookies
                <li><strong>pageContext</strong> : Access page context variables
              </ul>
            </blockquote>
            <div>
              <h5>Example 1 : Display the footer as text or processed</h5>
              <p>
                Here we output the content of the HTML tag <code>&lt;footer&gt;</code>
              </p>
              <pre>
                &lt;c:import var="myFooter" url="/WEB-INF/jsp/_include/footer.jsp"/>
                &lt;%-- By default XML and HTML tags are not processed, so you can display users input of XML/HTML type with security --%>
                &lt;c:out value="&#36;{footer}" />
                &lt;%-- Here we want the XML and HTML tags to be processed. Be aware of XSS vulnerability when disabling escaping XML special characters --%>
                &lt;c:out value="&#36;{footer}"  escapeXml="false"/>
              </pre>
              <p>
                Result:
              </p>
              <pre>
                <c:import var="myFooter" url="/WEB-INF/jsp/_include/footer.jsp"/>
                <c:out value="${myFooter}" />
              </pre>
              <c:out value="${myFooter}" escapeXml="false"/>
            </div>
            <div>
              <h5>Example 2 : Display the attribute <em>host</em> of the request's header</h5>
              <p>
                Here we display the title of the page starting.
              </p>
              <pre>
                &lt;c:out value="&#36;{header.host}" />
              </pre>
              <p>
                Result:
              </p>
              <pre class="result">
                Header host : <c:out value="${header.host}" />
              </pre>
            </div>
          </section>
        </article>
        <article>
          <h3>Conditional display - &lt;c:if&gt;</h3>
          <p>
            Condition allow to display data depending on certain circonstances. <br>
            The method provides 3 attributes:
          </p>
          <ul>
            <li>test : set the conditional expression to be evaluated.</li>
            <li>var : allow to store the result of the evaluated condition in a variable</li>
            <li>
              scope : set the scope of the variable. Default value : page
            </li>
          </ul>
          <pre>

            &lt;c:if test="&#36;{ 50 > 30 }" var="varCondition" scope="request">
              Content displayed if condition is true
            &lt;/c:if>

          </pre>
          <pre class="result">
            <c:if test="${ 50 > 30 }" var="varCondition" scope="request" >
              Content displayed if condition is true
            </c:if>
          </pre>

        </article>
        <article>
          <h3>Multi choice condition - choose, when, otherwise</h3>
          <p>
            <code>&lt;c:choose&gt;</code> allow to make multiple choice condition, like switch statement. <br>
            It's used in conjonction with <code>&lt;c:when&gt;</code> which evaluate a test and display something if the expression tested is true
            Like <code>case</code> in a <code>switch</code> statement.
          </p>
          <p>
              It's also used in conjonction with <code>&lt;c:otherwise&gt;</code> which display data if all other test are evaluated to false.
              Like <code>default</code> statement in <code>switch</code> statement.
          </p>
          <pre>
            &lt;c:choose>
              &lt;c:when test="&#36;{  50 < 20 }">
                <p>

                </p>
              &lt;/c:when>
              &lt;c:when test="&#36;{ 50 > 20   }">
                Display this value if condition2 is true
              &lt;/c:when>
              &lt;c:otherwise>
                Default value to display if all other condition are false!
              &lt;/c:otherwise>
            &lt;/c:choose>
          &lt;/pre>
          <pre class="result">
            <c:choose>
              <c:when test="${ 50 < 20 }">
                <p>
                  50 is less than 20
                </p>
              </c:when>
              <c:when test="${ 50 > 20 }">
                <p>
                  50 is greater than 20
                </p>
              </c:when>
              <c:otherwise>
                Default value to display if all other condition are false!
              </c:otherwise>
            </c:choose>
          </pre>
        </article>
        <article>
          <h3>Loops - &lt;c:forEach&gt;</h3>
          <p>
            Core library
          </p>
          <pre>
            &lt;c:forEach var="i" begin="0" end="10" step="1">
            &lt;div class="alert alert-info">
            Boucle JSTL &lt;c:forEach&gt;
            &lt;/div>
            &lt;br>
            &lt;/c:forEach>
          </pre>

          <div class="result">
            <c:forEach items="${ titles }" var="title" begin="1" step="1" varStatus="status">
              <p>
                Title N° ${ status.count } : ${ title }
              </p>
              <p>
                Title #<c:out value="${status.count}"/> : <c:out value="${title}" />
              </p>
            </c:forEach>
          </div>

          <pre class="result">

          </pre>
        </article>
      </section>
    </section>
  </main>

  <!-- JSTL Core - Import tag   -->
  <c:import var="myFooter" url="/WEB-INF/jsp/_include/footer.jsp"/>
  <%--
    JSTL escape XML tags by default to allow displaying XML tags.
    In order to use XML tags in your document,
    you need to disable escapeXML attributes using "escapeXml"
   --%>

  <c:out value="${ myFooter }" escapeXml="false"/>
</body>
</html>    