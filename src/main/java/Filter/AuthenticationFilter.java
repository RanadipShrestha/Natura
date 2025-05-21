package Filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Update the URL pattern to include all protected pages including admin dashboard
@WebFilter(urlPatterns = { "/pages/Dashboard.jsp", "/adminDashboard", "/pages/adminDashboard" })
public class AuthenticationFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Initialization code if required (for example: reading init parameters)
		System.out.println("AuthenticationFilter initialized");
	}

	@Override
	public void destroy() {
		// Cleanup code if required
		System.out.println("AuthenticationFilter destroyed");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession userSession = httpRequest.getSession(false);
		    
		String requestUri = httpRequest.getRequestURI();
		String appContextPath = httpRequest.getContextPath();

		// Check if the user is already authenticated
		boolean isAuthenticated = (userSession != null && userSession.getAttribute("user_id") != null);
		
		// Check if user is admin (if authenticated)
		boolean isAdmin = false;
		if (isAuthenticated) {
		    Boolean isAdminUser = (Boolean) userSession.getAttribute("is_admin");
		    isAdmin = (isAdminUser != null && isAdminUser);
		}

		// Check if it's the authentication pages
		boolean isLoginPage = requestUri.endsWith("login.jsp") || requestUri.endsWith("/Login");
		boolean isRegistrationPage = requestUri.endsWith("register.jsp") || requestUri.endsWith("/register");

		// Check if it's the admin dashboard
		boolean isAdminDashboard = requestUri.contains("Dashboard.jsp") || 
		                          requestUri.endsWith("adminDashboard") || 
		                          requestUri.contains("adminDashboard");
		
		boolean isCartPage = requestUri.contains("cart.jsp");
		boolean isUserDashboard = requestUri.contains("userDashboard.jsp");

		// Handle admin dashboard access - only admins can access
		if (isAdminDashboard) {
		    if (isAuthenticated) {
		        if (isAdmin) {
		            // Admin user accessing admin dashboard - allow
		            chain.doFilter(request, response);
		        } else {
		            // Non-admin user trying to access admin dashboard - redirect to regular dashboard
		            httpResponse.sendRedirect(appContextPath + "/Index");
		        }
		    } else {
		        // Unauthenticated user trying to access admin dashboard - redirect to login
		        httpResponse.sendRedirect(appContextPath + "/Login");
		    }
		    return;
		}

		if (isAuthenticated && (isLoginPage || isRegistrationPage)) {
		    // If authenticated and trying to access auth pages, redirect to appropriate dashboard
		    if (isAdmin) {
		        httpResponse.sendRedirect(appContextPath + "/adminDashboard");
		    } else {
		        httpResponse.sendRedirect(appContextPath + "/Index");
		    }
		    return;
		}

		// Handling authentication requirements for other protected pages
		if (isCartPage || isUserDashboard) {
		    // These pages require authentication
		    if (isAuthenticated) {
		        chain.doFilter(request, response);
		    } else {
		        httpResponse.sendRedirect(appContextPath + "/Login");
		    }
		} else {
		    // Publicly accessible pages
		    chain.doFilter(request, response);
		}
	}
}