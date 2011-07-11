import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import org.apache.model.GuestbookDB;

public final class ApplicationListener implements ServletContextListener {

    
    private ServletContext context = null;

                
    public void contextInitialized(ServletContextEvent event) {
        /*this.context = event.getServletContext();
    	GuestbookDB db = GuestbookDB.getInstance();
		db.init(context.getRealPath("/WEB-INF"));
		*/
    }
    
    public void contextDestroyed(ServletContextEvent event) {
    }
}

