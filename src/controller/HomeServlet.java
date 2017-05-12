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

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String start;
		String startParameter = request.getParameter("start");

		String end;
		String endParameter = request.getParameter("end");
		;
		if(startParameter == null){
			//start = "2000-1-1";
			start = new PostService().getOldestDate();
		}else if (startParameter.isEmpty()){
			//start = "2000-1-1";
			start = new PostService().getOldestDate();
		} else {
			start = startParameter;
		}

		if(endParameter == null){
			end = sdf.format(date).toString();
		}else if (endParameter.isEmpty()){
			end = sdf.format(date).toString();
		} else {
			end = endParameter;
		}


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



	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
	}


}

