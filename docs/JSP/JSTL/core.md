# JSTL - Java Server Page Standard Tag Library

## Introduction 

JSTL est l'acronyme de Java Server Page Standard Tag Library
C'est un ensemble de tags personnalisés développé sous la JSR 052
qui propose des fonctionnalités souvent rencontrées dans les JSP:
- Tag de structure (itération, conditionnement)
- Internationalisation
- Exécution des requêtes SQL
- Utilisation de documents XML

JSTL nécessite un conteneur d'applications web qui implémente l'API servlet 2.3
et l'API JSP 1.2.
L'implémentation de référence JSTL-RI de cette specification est développé 
par le projet TagLibs

## Install
JSTL allow to remove Java code from our JSP page
The library is divided into 5 modules:
- core ``c.tld`` : handle the display of variables, conditions, loop,
- format ``fmt.tld``: handle the data formatting, internationalisation of the site
- xml ``xml.tld`` : handle the manipulation of XML document
- functions `functions.tld`: handle strings
- sql `sql.tld`: allow to write SQL code inside your JSP File ( *** Wrong practice! *** )

If you do not use JSTL 1.0 tags then the "taglibs-standard-jstlel" JAR may be
omitted. If you do not use the XML library, then the Apache Xalan dependencies
may also be omitted.

### Install libraries using Maven 
Maven is used as dependency manager, add the dependencies to the POM
  ````xml
<dependencies>
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
</dependencies>
````

### Load JSTL libraries
In order to use a JSTL library in a JSP file, 
You must load JSTL library in JSP file.
There is 2 way to load a JSTL library:
- load JSTL library in JSP file directly
- configure the loading of JSTL libraries for all JSP files

#### Load JSTL library in JSP file
You must load JSTL library at the top of the JSP page which use it and indicate the prefix used for each library to call it.
The prefix must be the same as the short name for the library.
````
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Add  JSTL core library  --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

````

#### Load JSTL library for all JSP files
We could configure the loading of JSTL libraries for all JSP file
in ``web.xml`` file.

````xml
 <!-- 
  Add  Java Standard Tag libraries for all JSP files 
  by including a snippet of code in a JSP file at the top of each file 
 -->
  <jsp-config>
    <jsp-property-group>
      <url-pattern>*.jsp</url-pattern>
      <include-prelude>/WEB-INF/jsp/taglibs.jsp</include-prelude>
    </jsp-property-group>
  </jsp-config>
````

Create the snippet of code contains directives to include JSTL libraries

````
<%@ page pageEncoding="UTF-8" %>
<%-- Add  JSTL core library  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
````


## Usage
You can call any methods of a library by using the following notation:
- ``<prefix:method attribute1="value" attribute2="value" />``
- ``<prefix:method attribute1="value" attribute2="value" >...</prefix:method>``

````
<c:out value="Ma valeur" />
<c:forEach var="i" begin="0" end="5" step="1" >
  <p>
    Boucle using JSTL tag [forEach]
  </p>
</c:forEach>

````

### JSTL Core
See full documentation:  [JSTL Core](https://jakarta.ee/specifications/tags/1.2/tagdocs/c/tld-summary.html)

| Display Name	| JSTL core |
| ------------- | --------- |
| Version	      | 1.2       |
| Short Name	  | c         |
| URI	          | http://java.sun.com/jsp/jstl/core |

#### Tag Summary

| Function  | Description                                                               |
| --------- | -------------------------------------------------------------------------- |
| catch	    | Catches any Throwable that occurs in its body and optionally exposes it.  |
| choose    | Simple conditional tag that establishes a context for mutually exclusive conditional operations, marked by and  |
| if        | Simple conditional tag, which evalutes its body if the supplied condition is true and optionally exposes a Boolean scripting variable representing the evaluation of this condition |
| import    | Retrieves an absolute or relative URL and exposes its contents to either the page, a String in 'var', or a Reader in 'varReader'.  |
| forEach   | The basic iteration tag, accepting many different collection types and supporting subsetting and other functionality |
| forTokens | Iterates over tokens, separated by the supplied delimeters            |
| out       | Like <%= ... >, but for expressions.                                  |
| otherwise | Subtag of that follows tags and runs only if all of the prior conditions evaluated to 'false' |
| param     | Adds a parameter to a containing 'import' tag's URL.                  |
| redirect  | Redirects to a new URL.                                               |
| remove    | Removes a scoped variable (from a particular scope, if specified).    |
| set       | Sets the result of an expression evaluation in a 'scope'              |
| url       | Creates a URL with optional query parameters.                         |
| when      | Subtag of that includes its body if its condition evalutes to 'true'  |


#### JSTL Core - set
``<c:set>`` allow to set variables which will be accessible depending on the scope.

> Recall  
> JSTL define different scope for variables :
>  - page : the variable is available in the page which define it
>  - request : the variable is available during lifecycle of the request 
>     (if request is forwarded, to another jsp file, the variable will be available)
>  - session : the variable is available for the session  
>  - application : the variable is available in all the application
>
> Expression language provides variables to access data from each scope
>   - pageScope
>   - requestScope
>   - sessionScope
>   - applicationScope
> 
> Expression provides also other variables 
>   - param : access specific parameter of the request
>   - paramValues : access parameter of the request as collection
>   - header : provide the header of the request
>   - headerValues : provides headers of the request as a collection
>   - initParam : initialization parameters
>   - cookie : provides cookies
>   - pageContext : Object ``PageContext``

Here, we define a variable which scope is the JSP page

````
<c:set var="variableName" value="Value of variable" scope="page"/>

<%-- The 2 statements below will returns value of the variable if they are in the same JSP page --%>
<c:out value="${variableName}" />
<c:out value="${pageScope.variableName}" />
````

##### Modify Java Beans object using set
You can also update a Java Bean object using ``<c:set>``.
We use the following notation:

````
<c:set target="${ author }" property="firstname" value="Johnny" />
````

#### JSTL Core - remove
``<c:remove>`` allow to remove variable defined in all scope of the application

the method define 2 attributes:
- var : name of the variable to remove
- scope : scope of the variable to remove

````jsp
<c:remove var="variableName" scope="page" />
<c:remove var="variableName" scope="request" />
<c:remove var="variableName" scope="session" />
<c:remove var="variableName" scope="application " />

````

#### JSTL Core - out

``<c:out>`` allow also to display the content of XML and HTML tag from the document.
It use the dot notation ``.`` to access sub properties of XML tag. 
It prevent XSS vulnerability by escaping XML tag. 
So that,  You can display users input with security;

3 attributes are defined for the method
- value : set the data value to display
- default : default value if the variable isn't set. 
- escapeXml : Escaping XML special characters. Default : true
````
<c:out value="${variableName}" default="Default value"/>

<%-- If you want XML special characters to be processed. You have to disable escaping XML. --%>
<c:out value="${variableName}" default="Default value" escapeXml="false" />
````

> Recall  
> Expression language provides variables to access data from each scope
>   - pageScope
>   - requestScope
>   - sessionScope
>   - application


````
<c:out value="${pageScope.variableName}" default="Default value" escapeXml="false" />
<c:out value="${requestScope.variableName}" default="Default value" escapeXml="false" />
<c:out value="${sessionScope.variableName}" default="Default value" escapeXml="false" />
<c:out value="${application.variableName}" default="Default value" escapeXml="false" />

````

#### JSTL Core - if
Condition allow to display data depending on certain circonstances.

The method provides 3 attributes:
- test : set the conditional expression to be evaluated.
- var : allow to store the result of the evaluated condition in a variable
- scope : set the scope of the variable. Default value : page

````xml
<c:if test="${ condition }" var="varCondition"   >
  Content displayed if condition is true
</c:if>
````

#### JSTL Core - choose
``<c:choose>`` allow to make multiple choice condition, like switch statement.
It's used in conjonction with ``<c:when>`` which evaluate a test and display something if the expression tested is true
Like ``case`` in a ``switch`` statement.
It's also used in conjonction with ``<c:otherwise>``which display data if all other test are evaluated to false. 
Like ``default`` statement in ``switch`` statement.

````xml
<c:choose>
  <c:when test="${condition1}">
    Display this value if condition1 is true
  </c:when>
  <c:when test="${condition2}">
    Display this value if condition2 is true
  </c:when>
  <c:otherwise>
    Default value to display if all other condition are false!
  </c:otherwise>
</c:choose>
````

#### JSTL Core - forEach
``<c:forEach>`` let you create loops for displaying data.

Multiple attributes are availaable:
- items : Iterable object to loop over
- begin : index of starting point. Default : 0
- end : index of end point. Default : array.length - 1
- step : iteration step. Default : 1
- var : Name of the variable which store the current item
- varStatus : Name of the variable which exposes the loop status

```varStatus``` allow to access status of the loop:
- count : index from 1
- index : index from 0
- current : current element in the loop
- first : true if the current element is the first in the list
- last : true if the current element is the last of the list


````xml
<c:forEach var="i" begin="0" end="10" step="1">
  <%-- Display data using  --%>
  <p>Message ${i} : ?</p>
</c:forEach>
````

##### forEach & Iterable 
You can use ``forEach`` with all iterable objects

````xml
<c:forEach items="${ titles }" var="title" begin="1" step="1" varStatus="status">
  <p>
    Title N° ${ status.count } : ${ title }
  </p>
</c:forEach>
````

#### JSTL Core - forTokens
``<c:forTokens>`` let you iterate on String object 
Attributes defined by the method are:
- **var** (*String*) : Name of the variable which will store part of the String 
- **items** : ValueExpression which can be evaluate to String object composed of tokens separated by specific separator.  
- **delims** (*String*): List of delimiters.
- **begin** : Iteration begin at the token located at the specified index
- **end** : Iteration ends at the token located at the specified index
- **step** : Iteration step 
- **varStatus** : Name of the exported scoped variable for the status of the iteration.
  - **index** : index of the current item in the collection
  - **count** : number of the iteration already made. Same as index but starting from 1.
  - **first** : true if it's the first iteration
  - **last** : true if it's the last iteration

````xml
<c:forTokens var="piece" items="${ author.website }" delims="/">
  <p>
    <c:out value="${piece}" />
  </p>  
</c:forTokens>
````

````xml
<c:forEach begin="1" end="12" var="i" step="3" varStatus="vs">
  index = <c:out value="${vs.index}"/> : 
  count = <c:out value="${vs.count}"/> : 
  value = <c:out value="${i}"/>
  <c:if test="${vs.first}">
     : Premier element
  </c:if>
  <c:if test="${vs.last}">
     : Dernier element
  </c:if>
  <br>
</c:forEach>
````

#### JSTL Core - url & param
``<c:url>`` let you build URL with parameters. 
The method accepts absolute or relative path. 
It also prevent XSS vulnerability by escaping special characters in parameter.
Attributes defined for the method are:
- **var** : Name of exported scoped variable
- **scope** : Scope of the exported variable
- **value** : URL to be processed 
- **context** : Name of the context when specifying a relative path that belongs to a foreign context

``<c:param>`` let you define parameter of the requested URL
It build the request parameter. Ex: ``?paramName1=paramValue&param2=value``
The attributes defined for the method are:
- **name** : Name of the parameter
- **value** : Value of the parameter 

Here, we want to build the uri ``webapp/jstl?title=core&trackingId=1234``
as JSTL core url method will create an relative path.
The scope of the URL created is request. 
The variable ``jstlCore`` will exist during the lifecycle of the request
of the JSP file.
````xml
<c:url value="/jstl" context="webapp" var="jstlCore" scope="request" >
  <c:param name="title" value="core"/>
  <c:param name = "trackingId" value = "1234"/>
</c:url>
<c:import url = "${jstlCore}"/>
````

#### JSTL Core - redirect
``<c:redirect>`` let you redirect to another resource using absolute or relative path
Attributes defined for the method are: 
- **url** : URL of the resource to redirect to
- **context** : Name of the context when redirecting to a relative URL resource that belongs to a foreign context.

Here we redirect to another resource  of the application.

````xml
<c:redirect url="/jstl?title=core" context="webapp"/>
````

#### JSTL Core - import

``<c:import>`` allow to import data using absolute or relative path.
Relative path allow to import fil
Attributes defined for the method are:
- url : URL to retrieve data
- context: if relative path, set context. Default: current application
- charEncoding : set the character encoding
- var : Name of the variable which will store the imported data
- scope : set the scope of the variable defined. Default : page
- varReader : Set the name of the alternative variable which exposes ``java.io.Reader``



````xml
<c:import url = "/jstl" context="webapi" charEncoding="UTF-8" var="webapiJstl" scope="session"/>
````
You can also build an URL for importing resources using ``<c:url>`` and ``<c:param>

````xml
<c:url  value="/jstl" context="webapp" var="myUrl" scope="request" >
  <c:param name="lib" value="core" />
</c:url>

<c:import url="${myUrl}"/>
````


#### JSTL core - catch
``<c:catch>`` let you catch error from JSP page and store it in a variable.
Attributes defined for the method are:
- **var** : Name of variable which stores the exception thrown

````xml
<c:catch var="erreur">
  ${ 5 / 0 }
</c:catch>
<c:if test="not empty erreur">
  <p>
    Message : <c:out value="${erreur.message}" />
  </p>
  <p>
    Type : <c:out value="${erreur.type}" default="erreur.type isn't defined!"/>
  </p>
</c:if>
````




