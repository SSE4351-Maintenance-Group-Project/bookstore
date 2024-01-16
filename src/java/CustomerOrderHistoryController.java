import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import store.OrderSet;
import store.Customer;
import store.Store;

/**
 * Class CustomerOrderHistoryController contains
 * the servlet controller functionality for processing
 * customer's request to view order history.
 * 
 * @author Sergey Kim
 * @version 1.0
 *
 */
public class CustomerOrderHistoryController extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Get current customer to show order history
        Customer customer = (Customer) session.getAttribute("customer");
        OrderSet orders = new OrderSet();
        
        try {
            Store store = new Store();
            orders = store.getOrderHistory(customer.getEmail());

            // Get the intended redirect URL from the request parameter
            String redirectUrl = request.getParameter("redirectUrl");

            // Validate the redirect URL to prevent unvalidated redirects
            if (isValidRedirectUrl(redirectUrl)) {
                // Set orders attribute to provide order history.
                request.getSession().setAttribute("orders", orders);

                // Redirect to the validated URL
                response.sendRedirect(redirectUrl);
            } else {
                // Log or handle invalid redirect URL
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (Exception e) {
            // Log or handle the exception
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    /**
     * Validate the redirect URL.
     * You can customize this method based on your application's requirements.
     * In this example, a simple check is performed to ensure the URL starts with "/".
     *
     * @param redirectUrl The URL to validate.
     * @return True if the URL is valid, false otherwise.
     */
    private boolean isValidRedirectUrl(String redirectUrl) {
        return redirectUrl != null && redirectUrl.startsWith("/");
    }
}
