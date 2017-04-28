package beans;

import java.io.Serializable;
import java.sql.Date;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private String title;
	private String text;
	private String category;
	private Date createdAt;


	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
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

	public void setCreatedAt(Date createdAt){
		this.createdAt = createdAt;
	}

	public Date getcreatedAt(){
		return createdAt;
	}
}
