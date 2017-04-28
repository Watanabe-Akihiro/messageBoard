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

import beans.User;
import service.AdminService;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{

		HttpSession session = request.getSession();

		List<User> allUsers = new AdminService().getUsers();

		if(allUsers != null){
			request.setAttribute("allUsers", allUsers);
		} else {
			List<String> messages = new ArrayList<String>();
			messages.add("ユーザーが存在しません");
			session.setAttribute("errorMessages", messages);
		}

		request.getRequestDispatcher("admin.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		int isActivated = Integer.parseInt(request.getParameter("isActivated"));
		int userId = Integer.parseInt(request.getParameter("userId"));

		new AdminService().isActive(isActivated, userId);

		response.sendRedirect("admin");
	}


}
