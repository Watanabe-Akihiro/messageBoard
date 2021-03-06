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

import org.apache.commons.lang.StringUtils;

import beans.Post;
import beans.User;
import service.PostService;


@WebServlet(urlPatterns = {"/newPost"})
public class NewPostServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		List<String> categories = new PostService().getCategories();
		session.setAttribute("selectCategories", categories);
		request.getRequestDispatcher("newpost.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		User user = (User) session.getAttribute("loginUser");
		Post post = new Post();
		if(isValid(request, messages) == true){

			post.setTitle(request.getParameter("title"));
			post.setText(request.getParameter("text"));
			if(!request.getParameter("newCategory").isEmpty()){
			post.setCategory(request.getParameter("newCategory"));
			}else {
				post.setCategory(request.getParameter("category"));
			}
			post.setUserId(user.getId());



			new PostService().register(post);
			response.sendRedirect("./");

		} else {
			post.setText(request.getParameter("text"));
			post.setTitle(request.getParameter("title"));
			request.setAttribute("leftText", post.getText());
			request.setAttribute("leftTitle", post.getTitle());
			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("newpost.jsp").forward(request, response);
			//response.sendRedirect("newPost");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");
		String newCategory = request.getParameter("newCategory");
		if(StringUtils.isBlank(text)) {
			messages.add("本文が入力されていません");
		}
		if(StringUtils.isBlank(title)){
			messages.add("件名が入力されていません");
		}
		if(StringUtils.isBlank(category) && StringUtils.isBlank(newCategory)){
			messages.add("カテゴリーが入力されていません");
		}
		if(title.length() >50){
			messages.add("件名は50字以下です");
		}
		if(text.length() > 1000){
			messages.add("本文は1000字以下です");
		}
		if(category.length() > 10 || newCategory.length() > 10){
			messages.add("カテゴリは10字以下です");
		}
		if((!category.isEmpty() && !newCategory.isEmpty()) && !category.equals(newCategory) ){
			messages.add("複数のカテゴリは選択できません");
		}

		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}

}
