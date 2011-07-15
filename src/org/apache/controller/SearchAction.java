package org.apache.controller;

import org.apache.model.SearchForm;
import org.apache.model.GuestbookEntry;
import org.apache.model.DatabaseException;
import org.apache.model.GuestbookDB;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;

public class SearchAction extends ActionSupport {

    //Storing search input here.
    private SearchForm searchFormBean = new SearchForm();
    
    public SearchForm getSearchFormBean() {
        return searchFormBean;
    }
    
    public void setSearchFormBean(SearchForm search) {
        searchFormBean = search;
    }
    
    
    //Storing search results.
	private static ArrayList<GuestbookEntry> searchResultBean = null;
	
	public ArrayList<GuestbookEntry> getSearchResultBean() {
	    return searchResultBean;
	}
    

	@Override
	public String execute() throws Exception {
	   	searchResultBean = new ArrayList<GuestbookEntry>();
	   	
		String searchText = searchFormBean.getSearchText().toLowerCase();
		String author = searchFormBean.getAuthor().toLowerCase();
		
		GuestbookDB db = GuestbookDB.getInstance();
	    
	    try {
			List<GuestbookEntry> allEntries = db.getAllEntries();
			
			for (GuestbookEntry entry : allEntries) {
				if (entry.getText().toLowerCase().contains(searchText)
						&& entry.getAuthor().toLowerCase().contains(author)) {
					searchResultBean.add(entry);
				}
			}
		    return SUCCESS;	
			
		} catch (DatabaseException e) {
		    
		    //Added actionErrors will be displayed on error page.
			addActionError(e.getMessage());
			return ERROR;	
		}
		
	}
}
