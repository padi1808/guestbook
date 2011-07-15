<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" 
                      "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
 
<%-- Direktiven fuer Page-Optionen und verwendete Tag-Libs --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="de">

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
         
            <div class="searchResults">
              
      
               <div class="searchHit">
                   <div class="searchHit_author">
                        <%-- Wenn eMail gesetzt ist, den Namen als Link
                             anklickbar machen mit Email-Adresse als 
                             Linkziel--%> 
                               
                             <s:if test="entryBean.email.length() == 0">
                               ${entryBean.author}
                             </s:if>
                             <s:else><a href="mailto:${entryBean.email}">${entryBean.author}</a></s:else>
                   </div>
                   <div class="searchHit_date">
                     
                       <%--Formatiere Datum zur besseren Anzeige --%>
                       <s:date name="entryBean.date" format="dd.MM.yyyy HH:mm" />
                   </div>
                   <div class="searchHit_text">
                       ${entryBean.text}
                   </div>
               </div>
   
            </div>
            
            <br/>
            <a href="<s:url action='displayall'/>">Zur Anzeige des Gästebuchs</a> 
            
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