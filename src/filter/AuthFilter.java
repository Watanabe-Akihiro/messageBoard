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
@WebFilter(description = "アクセス権限フィルター", filterName = "authFilter", urlPatterns = {"/admin", "/edit", "/signup"})
public class AuthFilter implements Filter{
	 public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {

	try{
		HttpSession session = ((HttpServletRequest)request).getSession();
		//String target = ((HttpServletRequest)request).getServletPath();

		User user = (User) session.getAttribute("loginUser");
		if(user != null){
			if((user.getBranchId()).equals("1") && (user.getDepartmentId()).equals("1")){
				chain.doFilter(request, response);
			} else{
				String message = "指定されたURLへのアクセス権限がありません";
				session.setAttribute("errorMessages", message);
				((HttpServletResponse)response).sendRedirect("./");
				return;
			}
		} else {
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
