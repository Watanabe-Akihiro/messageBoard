package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserComment implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String branchId;
	private String departmentId;
	private int userId;
	private int postId;
	private String name;
	private String text;
	private Timestamp createdAt;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}


	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}


	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getPostId(){
		return postId;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setCreatedAt(Timestamp createdAt){
		this.createdAt = createdAt;
	}

	public Timestamp getcreatedAt(){
		return createdAt;
	}
}

