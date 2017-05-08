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

import beans.Post;
import beans.User;
import service.PostService;


@WebServlet(urlPatterns = {"/newPost"})
public class NewPostServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("newpost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();
		List<String> categories = new PostService().getCategories();
		request.setAttribute("selectCategories", categories);
		if(isValid(request, messages) == true){
			User user = (User) session.getAttribute("loginUser");
			Post post = new Post();


			post.setTitle(request.getParameter("title"));
			post.setText(request.getParameter("text"));
			post.setCategory(request.getParameter("category"));
			post.setUserId(user.getId());



			new PostService().register(post);
			response.sendRedirect("./");

		} else {
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newpost.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		if(text.length() == 0 || text.length() == 0 || category.length() == 0 ) {
			messages.add("必須項目が入力されていません");
		}
		if(title.length() >50){
			messages.add("件名は50字以下です");
		}
		if(text.length() > 1000){
			messages.add("本文は1000字以下です");
		}
		if(category.length() > 10){
			messages.add("カテゴリーは10字以下です");
		}
		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}

}
