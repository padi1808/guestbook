package org.apache.controller;

import org.apache.model.GuestbookEntry;
import org.apache.model.DatabaseException;
import org.apache.model.GuestbookDB;

import com.opensymphony.xwork2.ActionSupport;



/**
 * Struts-Action zum Erstellen eines Gaestebuch-Eintrags
 */
public class CreateAction extends ActionSupport {
    
    private GuestbookEntry entryBean;

    public GuestbookEntry getEntryBean() {
    	return entryBean;
    }

    public void setEntryBean(GuestbookEntry entry) {
    	entryBean = entry;
    }
    
    // Validation of input.
    public void validate() {
        if (entryBean.getAuthor().length() == 0 ){	
		    addFieldError( "entryBean.author", "Autor muss ausgefüllt werden." );		
	    } else if (entryBean.getAuthor().length() < 3 ) {
		    addFieldError( "entryBean.author", "Autor muss mindestens 3 Zeichen lang sein." );		
	    }
        if (entryBean.getText().length() == 0){	
		    addFieldError( "entryBean.text", "Nachrichtentext muss ausgefüllt werden." );		
	    } else if (entryBean.getText().length() < 5) {
	        addFieldError( "entryBean.text", "Nachrichtentext muss mindestens 5 Zeichen lang sein." );	
	    }
    }
	
	@Override
    public String execute() throws Exception {    	    
    	try {
    			// ActionForm ist ein Guestbook-Entry (wurde in struts-config.xml so
    			// festgelegt), d.h. wir koennen direkt casten und haben schon
    			// das gewuenschte Eintrag-Objekt, ohne dass wir dafuer etwas machen
    			// muessen :-)
    			//GuestbookEntry newEntry = (GuestbookEntry) form;
    			// Eintrag in Datenbank abspeichern und danach gewuenschtes
    			// Forward-Ziel zurueckgeben.
    			GuestbookDB db = GuestbookDB.getInstance();
    			db.addEntry(getEntryBean());
    			return SUCCESS;

    	} catch (DatabaseException e) {
    			// Bei einem Datenbank-Fehler legen wir eine neue Action-Message
    			// mit einer Fehlermeldung an und speichern diese in den Errors
    			// dieser Struts-Action ab. Die Meldung kann mit dem Tag
    			// <html:errors> auf der JSP-Seite angezeigt werden.
    			
    			/*ActionMessage errorMsg = new ActionMessage(e.getMessage(), false);
    			ActionMessages errors = this.getErrors(request);
    			errors.add("databaseError", errorMsg);
    			this.saveErrors(request, errors);*/
    			return ERROR;
    	}
    }

}
