package org.apache.controller;

import org.apache.model.SearchForm;
import org.apache.model.GuestbookEntry;
import org.apache.model.DatabaseException;
import org.apache.model.GuestbookDB;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

/*import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.validator.DynaValidatorForm;*/

public class SearchAction extends ActionSupport {

    /*
        Getting form input here.
    */
    private SearchForm searchFormBean;
    
    public SearchForm getSearchFormBean() {
        return searchFormBean;
    }
    
    public void setSearchFormBean(SearchForm search) {
        searchFormBean = search;
    }
    
    
    /*
        Setting form output in this block below.
    */
	private static ArrayList<GuestbookEntry> searchResult = new ArrayList<GuestbookEntry>();
	
	public ArrayList<GuestbookEntry> getSearchResult() {
	    return searchResult;
	}
	
	public void setSearchResult(ArrayList<GuestbookEntry> results) {
	    searchResult = results;
	}
    


	@Override
	public String execute() throws Exception {
	    
	    // Wandle in Kleinbuchstaben um, fuer Case-Insensitive Suche
		String searchText = searchFormBean.getSearchText().toLowerCase();
		String author = searchFormBean.getAuthor().toLowerCase();
	    
	    // Mindests ein Parameter wurde ausgefuellt, wir fuehren
		// also die Suche aus.
		// Zuerst holen wir eine Instanz der Gaestebuch DB-Schnittstelle
		GuestbookDB db = GuestbookDB.getInstance();
	    
	    try {

			// Hole alle Eintraege im Gaestebuch
			List<GuestbookEntry> allEntries = db.getAllEntries();
			
			// Gehe alle Eintraege durch und filtere die heraus,
			// die die Suchanfrage erfuellen
			for (GuestbookEntry entry : allEntries) {
				if (entry.getText().toLowerCase().contains(searchText)
						&& entry.getAuthor().toLowerCase().contains(author)) {
					// Treffer gefunden - Text und Author passen
					// Wir fuegen den Eintrag ins Ergebnis ein
					searchResult.add(entry);
				}
			}
			
			return SUCCESS;	
			
		} catch (DatabaseException e) {
			// Bei einem Datenbank-Fehler legen wir eine neue Action-Message
			// mit einer Fehlermeldung an und speichern diese in den Errors
			// dieser Struts-Action ab. Die Meldung kann mit dem Tag 
			// <html:errors> auf der JSP-Seite angezeigt werden.
			addActionError("databaseError " + e.getMessage());

			return ERROR;	
		}
	}
	
	
/*	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DynaValidatorForm searchForm = (DynaValidatorForm) form;
		
		// Wandle in Kleinbuchstaben um, fuer Case-Insensitive Suche
		String searchText = searchForm.getString("text").toLowerCase();
		String author = searchForm.getString("author").toLowerCase();

		// Mindests ein Parameter wurde ausgefuellt, wir fuehren
		// also die Suche aus.
		// Zuerst holen wir eine Instanz der Gaestebuch DB-Schnittstelle
		GuestbookDB db = GuestbookDB.getInstance();
		try {

			// Liste fuer die Suchergebnisse, die wir spaeter an JSP
			// per Request-Attribut weitergeben werden
			ArrayList<GuestbookEntry> searchResult = 
				new ArrayList<GuestbookEntry>();

			// Hole alle Eintraege im Gaestebuch
			List<GuestbookEntry> allEntries = db.getAllEntries();
			
			// Gehe alle Eintraege durch und filtere die heraus,
			// die die Suchanfrage erfuellen
			for (GuestbookEntry entry : allEntries) {
				if (entry.getText().toLowerCase().contains(searchText)
						&& entry.getAuthor().toLowerCase().contains(author)) {
					// Treffer gefunden - Text und Author passen
					// Wir fuegen den Eintrag ins Ergebnis ein
					searchResult.add(entry);
				}
			}
			
			// Die Ergebnisliste haengen wir als Attribut in den Request
			// damit die JSP-Seite Zugriff auf die Liste bekommen kann.
			request.setAttribute("result", searchResult);
			
			// Forward-Target fuer Erfolgsfall zurueckgeben
			return mapping.findForward("success");
			
		} catch (DatabaseException e) {
			// Bei einem Datenbank-Fehler legen wir eine neue Action-Message
			// mit einer Fehlermeldung an und speichern diese in den Errors
			// dieser Struts-Action ab. Die Meldung kann mit dem Tag 
			// <html:errors> auf der JSP-Seite angezeigt werden.
			ActionMessage errorMsg = new ActionMessage(e.getMessage(), false);
			ActionMessages errors = this.getErrors(request);
			errors.add("databaseError", errorMsg);
			this.saveErrors(request, errors);
			return mapping.findForward("failure");
		}
		
		
	}
/*
	/**
	 * Hilfsmethode zum Auslesen von String-Parametern aus dem Request Gibt
	 * einen Request-Parameter als String zurueck. Wenn der Parameter im Request
	 * nicht enthalten war, wird ein Default-Wert zurueckgegeben. Leerzeichen am
	 * Anfang und Ende werden getrimmt.
	 * 
	 * @param name
	 *            Der Name des Request-Parameters
	 * @param defaultValue
	 *            Der Default-Wert fuer den Fehler-Fall
	 * @param request
	 *            Der HTTP-Request
	 * @return Der Wert des Parameters
	 */
    // protected String getParameterAsString(String name, String defaultValue,
    //      HttpServletRequest request) {
    //  String value = request.getParameter(name);
    //  if (value == null) {
    //      value = defaultValue;
    //  }
    //  value = value.trim();
    //  return value;
    // }

}
