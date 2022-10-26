# JSTL Format
Provides methods for formatting data
See full documentation:  [JSTL Format](https://jakarta.ee/specifications/tags/1.2/tagdocs/c/tld-summary.html)

|Tag Library    | Information   |
| ------------- | ------------- |
| Display Name	| JSTL fmt      |
| Version	      | 1.2           |
| Short Name	  | fmt           |
| URI	          | http://java.sun.com/jsp/jstl/fmt |


## Tag Summary
| Function          | Description                     |
| ----------------- | --------------------------------|
| requestEncoding	  | Sets the request character encoding |
| setLocale	        | Stores the given locale in the locale configuration variable |
| timeZone	        | Specifies the time zone for any time formatting or parsing actions nested in its body | 
| setTimeZone	      | Stores the given time zone in the time zone configuration variable |
| bundle	          | Loads a resource bundle to be used by its tag body |
| setBundle	        | Loads a resource bundle and stores it in the named scoped variable or the bundle configuration variable |
| message	          | Maps key to localized message and performs parametric replacement |
| param	            | Supplies an argument for parametric replacement to a containing tag |
| formatNumber	    | Formats a numeric value as a number, currency, or percentage |
| parseNumber	      | Parses the string representation of a number, currency, or percentage |
| formatDate	      | Formats a date and/or time using the supplied styles and pattern |
| parseDate	        | Parses the string representation of a date and/or time |


### JSTL format - requestEncoding