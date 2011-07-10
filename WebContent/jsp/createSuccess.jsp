<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
                      "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
 
<%-- Direktiven fuer Page-Optionen und verwendete Tag-Libs --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"   %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">

    <%-- Struts-Tags mitteillen, dass sie sich als XHTML rendern sollen --%>
    <html:xhtml/> 

    <!-- HTML head -->
    <head>
        <title>Eintrag erstellt</title>
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
            <h2>Danke für ihren Eintrag</h2>

            <%-- Ausgabe des gerade erstellten Eintrags. Der Zugriff auf das
                 Bean erfolgt einfach mit dem Namen des Struts-Formbeans. --%>            
            <div class="searchResults">
               <div class="searchHit">
                   <div class="searchHit_author">
                        <%-- Wenn eMail gesetzt ist, den Namen als Link
                             anklickbar machen mit Email-Adresse als 
                             Linkziel--%> 
                        <c:choose>
                            <c:when test="${createForm.email != '' 
                                        && createForm.email != null}">
                                <a href="mailto:${createForm.email}">
                                    ${createForm.author}
                                </a>
                            </c:when>
                            <c:otherwise>
                               ${createForm.author}
                            </c:otherwise>
                        </c:choose>
                   </div>
                   <div class="searchHit_date">
                       <%--Formatiere Datum zur besseren Anzeige --%>
                       <fmt:formatDate value="${createForm.date}" 
                                       pattern="dd.MM.yyyy HH:mm"/>
                   </div>
                   <div class="searchHit_text">
                       ${createForm.text}
                   </div>
               </div>    
            </div>
            
            <br/>
            <html:link page="/DisplayAll.do">
                Zur Anzeige des Gästebuchs
            </html:link>
            
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