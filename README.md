# com.bitplan.simplegraph
Allows Wrapping Systems to make them available for Graph processing with [Apache TinkerPop / Gremlin](http://tinkerpop.apache.org/)

### Project
[![Build Status](https://travis-ci.org/BITPlan/com.bitplan.simplegraph.svg?branch=master)](https://travis-ci.org/BITPlan/com.bitplan.simplegraph)
[![Coverage Status](https://coveralls.io/repos/github/BITPlan/com.bitplan.simplegraph/badge.svg?branch=master)](https://coveralls.io/github/BITPlan/com.bitplan.simplegraph?branch=master)

# available modules / Systems wrapped
| Module | System wrapped | API exposed |
|---------------------------------------------------------------------- | ---------------- | ----------- |
|![Excel](http://wiki.bitplan.com/index.php/File:Microsoft_Excel_2013_logo.svg)[Excel](http://www.bitplan.com/index.php/SimpleGraph-Excel) |[Excel](https://en.wikipedia.org/wiki/Microsoft_Excel) |[Apache POI XSSF/HSSF](https://poi.apache.org/spreadsheet/quick-guide.html)
|![FileSystem](https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Folder.svg/100px-Folder.svg.png)[FileSystem](http://www.bitplan.com/index.php/SimpleGraph-FileSystem) |[FileSystem](https://en.wikipedia.org/wiki/File_system) |[java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)
|![HTML](HTML5 logo and wordmark.svg)[HTML](http://www.bitplan.com/index.php/SimpleGraph-HTML) |[HTML](https://en.wikipedia.org/wiki/HTML) |[HTML Cleaner](http://htmlcleaner.sourceforge.net/)
|![JSON](JSON vector logo.svg)[JSON](http://www.bitplan.com/index.php/SimpleGraph-JSON) |[JSON](https://en.wikipedia.org/wiki/JavaScript_Object_Notation) |[JSON](https://www.json.org/)
|![MapSystem](Map.png)[MapSystem](http://www.bitplan.com/index.php/SimpleGraph-MapSystem) |[MapSystem](https://en.wikipedia.org/wiki/Hash_table) |[java.api.Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)
|![MediaWiki](Mediawiki logo reworked.svg)[MediaWiki](http://www.bitplan.com/index.php/SimpleGraph-MediaWiki) |[MediaWiki](https://www.mediawiki.org/wiki/API:Main_page_MediaWiki) |[MediaWiki API](https://www.mediawiki.org/wiki/API:Main_page)
|![PDF](Pdf by mimooh.svg)[PDF](http://www.bitplan.com/index.php/SimpleGraph-PDF) |[PDF](https://de.wikipedia.org/wiki/Portable_Document_Format) |[Apache PDFBox](https://pdfbox.apache.org/)
|![PowerPoint](Microsoft PowerPoint 2013 logo.svg)[PowerPoint](http://www.bitplan.com/index.php/SimpleGraph-PowerPoint) |[PowerPoint](https://en.wikipedia.org/wiki/Microsoft_PowerPoint) |[Apache POI XSLF/HSLF](https://poi.apache.org/slideshow/xslf-cookbook.html)
|![SMW](SemanticMediaWiki Logo.png)[SMW](http://www.bitplan.com/index.php/SimpleGraph-SMW) |[SMW](https://en.wikipedia.org/wiki/SMW) |[SemanticMedia Wiki API](https://www.semantic-mediawiki.org/wiki/Help:API)
|![SNMP](Snmp.png)[SNMP](http://www.bitplan.com/index.php/SimpleGraph-SNMP) |[SNMP](https://en.wikipedia.org/wiki/Simple_Network_Management_Protocol) |[SNMP4J Simple Network Monitoring Protocol SNMP Java API](http://www.snmp4j.org/)
|![SQL](Database.svg)[SQL](http://www.bitplan.com/index.php/SimpleGraph-SQL) |[SQL](https://en.wikipedia.org/wiki/SQL) |[Java Database Connectivity (JDBC) API](https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html)
|![TripleStore](TripleStore-Icon.png)[TripleStore](http://www.bitplan.com/index.php/SimpleGraph-TripleStore) |[TripleStore](https://en.wikipedia.org/wiki/Triplestore) |[SiDIF-TripleStore](https://github.com/BITPlan/org.sidif.triplestore)
|![WikiData](Wikidata-logo-en.svg)[WikiData](http://www.bitplan.com/index.php/SimpleGraph-WikiData) |[WikiData](https://en.wikipedia.org/wiki/Wikidata) |[WikiData Toolkit](https://github.com/Wikidata/Wikidata-Toolkit)
|![Word](Microsoft Word 2013 logo.svg)[Word](http://www.bitplan.com/index.php/SimpleGraph-Word) |[Word](https://en.wikipedia.org/wiki/Word) |[Apache POI XWPF/HWPF](https://poi.apache.org/document/quick-guide-xwpf.html) 

# Links
* http://www.bitplan.com/index.php/SimpleGraph
* https://stackoverflow.com/questions/48213256/gremlin-interface-for-filesystem

# History
* 2018-01-11 initial showcase FileSystem
* 2018-01-14 adds Powerpoint and SiDIF-TripleStore access
* 2018-01-15 adds WikiData access
* 2018-02-19 adds Excel,HTML,JSON,MapSystem,MediaWiki,PDF,SMW,SQL and Word
