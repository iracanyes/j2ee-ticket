### JSTL Functions
Provides methods to manipulate strings
See full documentation [JSTL Functions](https://jakarta.ee/specifications/tags/1.2/tagdocs/fn/tld-summary.html)

| Tag Library | Information |
| ------------  | -------------- |
| Display Name	| JSTL functions |
| Version	      | 1.2            |
| Short Name	  | fn |
| URI	          | http://java.sun.com/jsp/jstl/functions |


#### Function Summary

| Return type         | Function & argument type                                            | Description                                                   |
| ------------------- |---------------------------------------------------------------------| ------------------------------------------------------------ |
| boolean	            | contains( java.lang.String, java.lang.String)	                      | Tests if an input string contains the specified substring.    |                                   
| boolean	            | containsIgnoreCase( java.lang.String, java.lang.String)	            | Tests if an input string contains the specified substring in a case insensitive way.                                           |
| boolean	            | endsWith( java.lang.String, java.lang.String)	                      | Tests if an input string ends with the specified suffix.                                                                       |
| java.lang.String	  | escapeXml( java.lang.String)	                                       | Escapes characters that could be interpreted as XML markup.                                                                   |
| int	                | indexOf( java.lang.String, java.lang.String)	                       | Returns the index withing a string of the first occurrence of a specified substring.                                          |
| java.lang.String	  | join( java.lang.String[], java.lang.String)	                        | Joins all elements of an array into a string.                                                                                  |
| int	                | length( java.lang.Object)	                                          | Returns the number of items in a collection, or the number of characters in a string.                                          |
| java.lang.String	  | replace( java.lang.String, java.lang.String, java.lang.String)	     | Returns a string resulting from replacing in an input string all occurrences of a "before" string into an "after" substring.  |
| java.lang.String[]	| split( java.lang.String, java.lang.String)	                         | Splits a string into an array of substrings.                                                                                  |
| boolean	            | startsWith( java.lang.String, java.lang.String)	                    | Tests if an input string starts with the specified prefix.                                                                     |
| java.lang.String	  | substring( java.lang.String, int, int)	                             | Returns a subset of a string.                                                                                                 |
| java.lang.String	  | substringAfter( java.lang.String, java.lang.String)	                | Returns a subset of a string following a specific substring.                                                                   |
| java.lang.String	  | substringBefore( java.lang.String, java.lang.String)	               | Returns a subset of a string before a specific substring.                                                                     |
| java.lang.String	  | toLowerCase( java.lang.String)	                                     | Converts all of the characters of a string to lower case.                                                                     |
| java.lang.String	  | toUpperCase( java.lang.String)	                                     | Converts all of the characters of a string to upper case.                                                                     |
| java.lang.String	  | trim( java.lang.String)	                                            | Removes white spaces from both ends of a string.                                                                               |