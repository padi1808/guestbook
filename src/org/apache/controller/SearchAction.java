package org.apache.controller;

import org.apache.model.SearchForm;
import org.apache.model.GuestbookEntry;
import org.apache.model.DatabaseException;
import org.apache.model.GuestbookDB;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;

import java.util.ArrayList;
import java.util.List;

public class SearchAction extends ActionSupport {

    /*
        Getting form input here.
    */
    private SearchForm searchFormBean = new SearchForm();
    
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
			
			//addActionError("databaseError " + e.getMessage());

			return ERROR;	
		}
		
		//return SUCCESS;
	}
}
