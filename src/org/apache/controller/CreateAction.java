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
		    addFieldError( "entryBean.author", "Autor muss ausgef&uuml;llt werden." );		
	    } else if (entryBean.getAuthor().length() < 3 ) {
		    addFieldError( "entryBean.author", "Autor muss mindestens 3 Zeichen lang sein." );		
	    }
        if (entryBean.getText().length() == 0){	
		    addFieldError( "entryBean.text", "Nachrichtentext muss ausgef&uuml;llt werden." );		
	    } else if (entryBean.getText().length() < 5) {
	        addFieldError( "entryBean.text", "Nachrichtentext muss mindestens 5 Zeichen lang sein." );	
	    }
    }
	
	@Override
    public String execute() throws Exception {    	    
    	try {
    	    
            //ATTENTION: guestbook entries containing new line characters will corrupt the db file.
            //This was probably intended behaviour to easily check the exception handling, but should be fixed
            //for a productive use of this code.
    	    
    			GuestbookDB db = GuestbookDB.getInstance();
    			db.addEntry(getEntryBean());
    			return SUCCESS;

    	} catch (DatabaseException e) {
    	        addActionError(e.getMessage());
    			return ERROR;
    	}
    }

}
