package beans;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private int branchId;
	private int departmentId;
	private String password;
	private String name;
	private int isActivated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public void setIsActivated(int isActivated){
		this.isActivated = isActivated;
	}

	public int getIsActivated(){
		return isActivated;
	}

}
