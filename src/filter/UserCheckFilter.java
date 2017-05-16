package filter;

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

import beans.User;
import service.AdminService;
@WebFilter(filterName = "userCheckFilter", urlPatterns = { "/*" })
public class UserCheckFilter implements Filter{
	 public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {
		 HttpSession session = ((HttpServletRequest)request).getSession();
			//String target = ((HttpServletRequest)request).getServletPath();
			//String thisURI = ((HttpServletRequest)request).getRequestURI();
			User user = (User) session.getAttribute("loginUser");


	try{
		if(user != null){
			User checkedUser = new AdminService().getUser(user.getId());

			if(checkedUser == null){
			String message = "アカウントが存在しません";
			session.setAttribute("errorMessages", message);
			session.removeAttribute("loginUser");
			((HttpServletResponse)response).sendRedirect("login");
			return;
			} else if(checkedUser.getIsActivated() == 1){
				String message = "アカウントが停止されています";
				session.setAttribute("errorMessages", message);
				session.removeAttribute("loginUser");
				((HttpServletResponse)response).sendRedirect("login");
				return;
			} else{
				chain.doFilter(request, response);
			}
		} else{
			chain.doFilter(request, response);
		}


	 } catch (ServletException se){
	    }catch (IOException e){
	    }
	}


    public void init(FilterConfig arg0) throws ServletException {

    }


	 public void destroy() {

	 }


}
