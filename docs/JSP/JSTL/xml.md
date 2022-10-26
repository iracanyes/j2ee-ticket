### JSTL XML
Provides methods for manipulating XML documents
The attribute ``select`` of all tags of the library used the **XPath** specification for writing values.

`````xpath
$personList/persons/person[@id=2]   
`````
> Recall :  
> XPath is a component of XSLT standards and is a W3C recommandation..
> XPath stands for XML Path Language
> It use URL like syntax to identify and navigate through XML documents
> XPath contains over 200 functions to work with XML document
> XPath use path expression to select nodes or node-sets in an XML document 
> 
See Documentation for more info: 
- [JSTL XML](https://jakarta.ee/specifications/tags/1.2/tagdocs/x/tld-summary.html)
- [JSTL samples](https://www.jmdoudoux.fr/java/dej/chap-jstl.htm)
- 
| Tag Library   | Information |
| ------------- | ----------- |
| Display Name	| JSTL XML    |
| Version	      | 1.2         |
| Short Name	  | x                               |
| URI	          | http://java.sun.com/jsp/jstl/xml |


##### Tag Summary

| Return type        | Description                                                   |
| ------------------ | ------------------------------------------------------------ |
| choose	           | Simple conditional tag that establishes a context for mutually exclusive conditional operations, marked by and |
| out	               |  Like <%= ... >, but for XPath expressions. |
| if	               | XML conditional tag, which evalutes its body if the supplied XPath expression evalutes to 'true' as a boolean |
| forEach	           | XML iteration tag. |
| otherwise	         | Subtag of that follows tags and runs only if all of the prior conditions evaluated to 'false' |
| param	             | Adds a parameter to a containing 'transform' tag's Transformer  |
| parse	             | Parses XML content from 'source' attribute or 'body'  |
| set	               | Saves the result of an XPath expression evaluation in a 'scope'  |
| transform	         | Conducts a transformation given a source XML document and an XSLT stylesheet  |
| when	             | Subtag of that includes its body if its expression evalutes to 'true'  |


### XML Sample Document

````xml
<personnes>
  <personne id="1">
    <nom>nom1</nom>
    <prenom>prenom1</prenom>
  </personne>
  <personne id="2">
    <nom>nom2</nom>
    <prenom>prenom2</prenom>
  </personne>
  <personne id="3">
    <nom>nom3</nom>
    <prenom>prenom3</prenom>
  </personne>
</personnes>  
````