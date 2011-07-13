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
            <h2>Suchergebnis</h2>
            
            <div class="searchResults">  
              
              <s:if test="searchResultBean.size < 1">
                  <span>
                    Ihre Suche hat leider keinen Treffer ergeben.
                        Versuchen Sie andere Suchbegriffe.<br/>
                        <br/>
                      </span>
              </s:if>
              
              <s:iterator value="searchResultBean" var="result">
                       
			            <div class="searchHit">
			                <div class="searchHit_author">
			                    <s:if test="%{email != null}">
                            <a href="mailto:${email}">${author}</a>
                          </s:if>
                          <s:else>${author}</s:else>
			                </div>
			                <div class="searchHit_date">
			                  <s:date name="date" format="dd.MM.yyyy HH:mm" />
			                </div>
			                <div class="searchHit_text">${text}</div>
			            </div>    
              </s:iterator>
            </div>
          <br/>
          <a href="<s:url action='start'/>">Zurück zur Startseite</a>    
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