package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import beans.UserComment;
import beans.UserPost;
import service.CommentService;
import service.PostService;

@WebServlet(urlPatterns = {"/index.jsp"})
public class HomeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		HttpSession session = request.getSession();

		List<String> categories = new PostService().getCategories();
		request.setAttribute("categories", categories);
		String category = request.getParameter("category");
		request.setAttribute("selectedCategory", category);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String start;
		String startParameter = request.getParameter("start");

		String end;
		String endParameter = request.getParameter("end");

		String oldestDate;
		if(new PostService().getOldestDate() == null){
			oldestDate = "2000-01-01";
		}else{
			oldestDate = new PostService().getOldestDate();
		}

		if(StringUtils.isEmpty(startParameter)){
			start = oldestDate;
		} else if(!startParameter.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")){
			start = oldestDate;
		}else{
			start = startParameter;
		}


		if(StringUtils.isEmpty(endParameter)){
			end = sdf.format(date).toString();
		} else if(!endParameter.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")){
			end = sdf.format(date).toString();
		}else{
			end = endParameter;
		}

		String trimmedEnd = end.substring(0, 10);
		String trimmedStart = start.substring(0, 10);
		request.setAttribute("end", trimmedEnd);
		request.setAttribute("start", trimmedStart);

		List<UserPost> posts = new PostService().getPost(category, start, end);
		request.setAttribute("posts", posts);

		List<UserComment> comments = new CommentService().getComment();
		request.setAttribute("comments", comments);

		if(session.getAttribute("loginUser") == null ){
		response.sendRedirect("login");
		}else{
		request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}


}

