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
import service.UserService;

@WebServlet(urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();

		User user = new User();

		if(isValid(request, messages) == true){
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

			new UserService().register(user);

			response.sendRedirect("admin");
		} else{
			user.setLoginId(request.getParameter("loginId"));
			user.setName(request.getParameter("name"));
			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

			request.setAttribute("signupUser", user);

			session.setAttribute("errorMassages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String name = request.getParameter("name");

		if(!password.equals(passwordConfirmation)){
			messages.add("パスワードが一致しません");
		}
		if(password.length() >= 255 || password.length() <= 6 || !password.matches("[ -~｡-ﾟ]+$")){
			messages.add("不正なパスワードです");
		}

		if(loginId.length() >=20 || loginId.length() <= 6 || !loginId.matches("[0-9a-zA-Z_]+$")){
			messages.add("不正なログインIDです");
		}

		if(name.length() >= 10){
			messages.add("名前は10字以下です");
		}
		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}


	}
}
