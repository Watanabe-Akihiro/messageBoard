package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Comment;
import beans.User;
import service.CommentService;


@WebServlet(urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages) == true){
			User user = (User) session.getAttribute("loginUser");
			Comment comment = new Comment();

			comment.setText(request.getParameter("text"));
			comment.setUserId(user.getId());
			comment.setPostId(Integer.parseInt(request.getParameter("postId")));

			new CommentService().register(comment);
			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){

		String text = request.getParameter("text");

		if(text.length() == 0) {
			messages.add("コメントが入力されていません");
		}
		if(text.length() > 1000){
			messages.add("コメントは500字以下です");
		}
		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}
}
