# Expression language - EL

Expression language provides an easy to access data 
needed for displaying JSP files.
It use expression to refer to Java Beans objects.

The syntax is ``${expression}``

It simplify the syntaxe of Java expression

````jsp
<%= session.getAttribute("attributeName").getPropertyName() %>

<%-- In expresison language --%>
${sessionScope.attributeName.getPropertyName()}
````

EL provides default variables for accessing different part of the application
- **pageScope** : let you access variable defined in the page scope
- **requestScope** : let you access variable defined in the request scope
- **sessionScope** : let you access variable defined in the page scope
- **applicationScope** : let you access variable defined in the page scope
- **param** : Access parameter of the request
- **paramValues** : parameters of the request as list
- **header** : Access header of the request
- **headerValues** : headers of the request as a collection
- **initParam** : initialization parameters
- **cookie** : Access cookies
-**pageContext** : Access page context variables

EL provides operators to use in expression to be evaluated

| Operateur 	 | Rôle 	                                            | Exemple                  |
|---|---------------------------------------------------|--------------------------|
| . 	 | Obtenir une propriété d'un objet 	                | ${param.nom}             |
| [] 	 | Obtenir une propriété par son nom ou son indice 	 | ${param[" nom "]} <br/> ${row[1]} |
| Empty  	 | Teste si un objet est null ou vide si c'est une chaîne de caractère. Renvoie un booléen 	| ${empty param.nom} |
| == <br> eq 	 | teste l'égalité de deux objets | 	 
| != <br> ne 	 | teste l'inégalité de deux objets |	 
| < <br> lt | 	test strictement inférieur |
| > <br> gt 	 | test strictement supérieur 	 |
| <= <br> le | 	test inférieur ou égal  |
| >= <br> ge 	 | test supérieur ou égal |
| + 	 | Addition| 
| - 	 | Soustraction|
| * 	 | Multiplication |	 
| / <br> div 	 | Division |	 
 | % <br> mod 	 |Modulo |	 
| && <br> and | And  | 	  	 
 |   || <br> or  | Or |
| !  | not 	Négation d'une valeur |