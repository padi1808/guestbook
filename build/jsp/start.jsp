<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
                      "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
 
<%-- Direktiven fuer Page-Optionen und verwendete Tag-Libs --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">

	<!-- HTML head -->
	<head>
		<title>Suche im Gästebuch</title>
		<!-- Embed cascading stylesheet file -->
		<link rel="stylesheet" type="text/css" href="css/guestbook.css"/>
	</head>

	<!-- HTML body -->
	<body>
	 
		<!-- Guestbook header is just a first-level headline -->
		<h1 class="guestbook_header">my guestbook</h1>
		
		      <!-- Guestbook body with contains the actual page content -->
        <div class="guestbook_body">
            
            <!-- Subheader and explanation text for this page -->
            <h2>Willkommen bei unserem Gästebuch</h2>
            <p class="welcomeText">
                Folgende Funktionen stehen ihnen zur Verfügung:
            </p>
            <ul class="functionList">
                <li><a href="<s:url action='searchInput'/>">Suche im Gästebuch</a></li>
                <li><a href="<s:url action='displayall'/>">Alle Einträge ansehen</a></li>
                <li><a href="<s:url action='createInput'/>">Neuen Eintrag erstellen</a></li>
            </ul>
            
        </div> <!-- End of guestbook body -->
      
		
		<!-- W3C symbols for standard compliance -->
		<div class="validation_icons">
			<a href="http://validator.w3.org/check?uri=referer">
				<img class="w3icon" src="images/valid-xhtml11.png" 
				     alt="Valid XHTML 1.1"/>
			</a>
			<a href="http://jigsaw.w3.org/css-validator/">
				<img class="w3icon" src="images/valid-css.png"
		             alt="Valid CSS"/>
			</a>
		</div>
	</body>
</html>
