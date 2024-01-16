import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class CustomerLogoutController contains
 * the servlet controller functionality for processing
 * customer's request to log out.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CustomerLogoutController extends HttpServlet {
    
    public void doPost (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// Get session
    	HttpSession session = request.getSession();
    	
    	// End session
		session.invalidate();
		
		response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
    
}
