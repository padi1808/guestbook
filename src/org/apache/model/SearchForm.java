package org.apache.model;

//import org.apache.struts.validator.ValidatorForm;

public class SearchForm /*extends ValidatorForm*/ {

	private String searchText;
	private String author;
	
	
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
		System.out.println("Set searchText to '" + searchText + "'");
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
		System.out.println("Set author to '" + author + "'");
	}
	
}
