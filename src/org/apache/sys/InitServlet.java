package org.apache.sys;

import org.apache.model.GuestbookDB;
import javax.servlet.ServletException;

public class InitServlet extends javax.servlet.http.HttpServlet {

	/**
	 * Init database
	 */
	public void init() throws ServletException {
		GuestbookDB db = GuestbookDB.getInstance();
		db.init(getServletContext().getRealPath("/WEB-INF"));
	}

}
