package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;
@WebServlet(urlPatterns = "/deletePost")
public class DeletePostServlet extends HttpServlet{
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		int deletedId = Integer.parseInt(request.getParameter("deletedId"));
		new PostService().delete(deletedId);

		response.sendRedirect("./");
	}
}
