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

import beans.Branch;
import beans.Department;
import beans.User;
import service.TitleService;
import service.UserService;

@WebServlet(urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();

		List<Branch> branches = new TitleService().getBranches();
		List<Department> departments = new TitleService().getDepartments();

		session.setAttribute("branches", branches);
		session.setAttribute("departments", departments);

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
			user.setBranchId(request.getParameter("branchId"));
			user.setDepartmentId(request.getParameter("departmentId"));

			new UserService().register(user);

			response.sendRedirect("admin");
		} else{
			user.setLoginId(request.getParameter("loginId"));
			user.setName(request.getParameter("name"));
			user.setBranchId(request.getParameter("branchId"));
			user.setDepartmentId(request.getParameter("departmentId"));

			request.setAttribute("signupUser", user);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages){
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String passwordConfirmation = request.getParameter("passwordConfirmation");
		String name = request.getParameter("name");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));

		if(!password.equals(passwordConfirmation)){
			messages.add("パスワードが一致しません");
		}
		if(password.length() >= 255 || password.length() < 6 || !password.matches("[ -~｡-ﾟ]+$")){
			messages.add("不正なパスワードです");
		}

		if(loginId.length() >=20 || loginId.length() < 6 || !loginId.matches("[0-9a-zA-Z_]+$")){
			messages.add("不正なログインIDです");
		}

		if(name.length() == 0){
			messages.add("名前を入力してください");
		}

		if(name.length() >= 10){
			messages.add("名前は10字以下です");
		}

		if(branchId == 1 && departmentId > 2){
			messages.add("存在しない部署です");
		}
		if(branchId != 1 && departmentId <= 2){
			messages.add("存在しない部署です");
		}
		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}


	}
}
