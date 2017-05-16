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

import beans.Branch;
import beans.Department;
import beans.User;
import service.AdminService;
import service.TitleService;
import service.UserService;

@WebServlet(urlPatterns = { "/edit" })
public class EditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if(isValidURL(request, messages) == true){
			int id = Integer.parseInt(request.getParameter("userId"));
			User editUser = new AdminService().getUser(id);
			request.setAttribute("editUser", editUser);
			List<Branch> branches = new TitleService().getBranches();
			List<Department> departments = new TitleService().getDepartments();
			Branch branch = new TitleService().getBranch(Integer.parseInt(editUser.getBranchId()));
			Department department = new TitleService().getDepartment(Integer.parseInt(editUser.getDepartmentId()));
			session.setAttribute("branches", branches);
			session.setAttribute("departments", departments);
			request.setAttribute("editBranch", branch);
			request.setAttribute("editDepartment", department);

			request.getRequestDispatcher("edit.jsp").forward(request, response);
			} else{
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("admin");
			}
		}



	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User user = new User();

		if(isValid(request, messages) == true){
			user.setId(Integer.parseInt(request.getParameter("userId")));
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(request.getParameter("branchId"));
			user.setDepartmentId(request.getParameter("departmentId"));

			new UserService().update(user);

			String validationMessage = (String)user.getName() + "のユーザー情報は正常に更新されました";
			session.setAttribute("validationMessage", validationMessage);
			response.sendRedirect("admin");

		} else {
			user.setLoginId(request.getParameter("loginId"));
			user.setName(request.getParameter("name"));
			user.setBranchId(request.getParameter("branchId"));
			user.setDepartmentId(request.getParameter("departmentId"));

			request.setAttribute("user", user);

			session.setAttribute("errorMessages", messages);
			response.sendRedirect("admin");
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

		if(loginId.length() >=20 ||  loginId.length() < 6 || !loginId.matches("[0-9a-zA-Z_]+$")){
			messages.add("不正なログインIDです");
		}
		if(password.length() >= 255 ||  (password.length() < 6 && password.length() > 0)){
			messages.add("不正なパスワードです");
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

	private boolean isValidURL(HttpServletRequest request, List<String> messages){

		if(StringUtils.isEmpty(request.getParameter("userId")) || !request.getParameter("userId").matches("\\d{1,9}")){
			messages.add("不正なユーザーIDです");
		} else{
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user = new AdminService().getUser(userId);
			if(user == null){

				messages.add("ユーザーが存在しません");
			}

		}


		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}

	/*public boolean isNumber(String num) {
	    try {
	    	Integer.parseInt(num);
	        return true;
	        } catch (NumberFormatException e) {
	        	return false;
	        }
	    }*/
	}