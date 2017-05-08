package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserPost implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int branchId;
	private int departmentId;
	private int userId;
	private String name;
	private String title;
	private String text;
	private String category;
	private Timestamp createdAt;


	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}


	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getCategory(){
		return category;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public void setCreatedAt(Timestamp createdAt){
		this.createdAt = createdAt;
	}

	public Timestamp getcreatedAt(){
		return createdAt;
	}
}
