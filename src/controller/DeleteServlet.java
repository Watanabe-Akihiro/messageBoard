package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.AdminService;
@WebServlet(urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		HttpSession session = request.getSession();
		String errorMessage = "指定されたURLは表示することができません";
		session.setAttribute("errorMessages", errorMessage);
		response.sendRedirect("./");
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		int deletedId = Integer.parseInt(request.getParameter("deletedId"));
		new AdminService().delete(deletedId);

		response.sendRedirect("admin");
	}
}
