package beans;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int postId;
	private int userId;
	private String text;
	private Date createdAt;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getPostId(){
		return postId;
	}

	public void setPostId(int postId){
		this.postId = postId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public String getText(){
		return text;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getcreatedAt(){
		return createdAt;
	}
}
