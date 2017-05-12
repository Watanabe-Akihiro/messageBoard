package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserPost;
import exception.SQLRuntimeException;

public class UserPostDao {

	public List<UserPost> getUserPost(Connection connection, String category, String start, String end){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts ");
			sql.append("WHERE '"+ start + "' <= created_at AND '" + end + "' >= created_at ");
			if(category == null || category.isEmpty()){
			} else{
				sql.append("AND category = ? ");
			}
			sql.append("ORDER BY created_at DESC");

			ps = connection.prepareStatement(sql.toString());
			if(category == null || category.isEmpty()){
			}else{
				ps.setString(1, category);
			}

			ResultSet rs = ps.executeQuery();

			List<UserPost> ret = toUserPostList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	/*public List<UserPost> getPostByCategory(Connection connection, String category){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts ");
			if(category != null){
			sql.append("WHERE category = ?");
			}
			ps = connection.prepareStatement(sql.toString());
			if(category != null){
			ps.setString(1, category);
			}
			ResultSet rs = ps.executeQuery();

			List<UserPost> ret = toUserPostList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}*/

	public List<String> getCategories(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT category FROM users_posts GROUP BY category";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<String> categories = toCategoriesList(rs);
			//System.out.println(categories);
			return categories;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	public  String getOldestDate(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT MIN(created_at) FROM users_posts";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			String oldestDate = null;
			while(rs.next()){
				Timestamp createdAt = rs.getTimestamp("MIN(created_at)");
				oldestDate = createdAt.toString();
				//oldestDate.substring(0,10);
				System.out.println(oldestDate);
			}
			return oldestDate;
		}  catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	private List<String> toCategoriesList(ResultSet rs)
		throws SQLException {
		List<String> ret = new ArrayList<String>();
		try{
			while(rs.next()){
				String category = rs.getString("category");
				ret.add(category);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	private List<UserPost> toUserPostList(ResultSet rs)
		throws SQLException {
		List<UserPost> ret = new ArrayList<UserPost>();
		try{
			while(rs.next()){
				String branchId = rs.getString("branch_id");
				String departmentId = rs.getString("department_id");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String name = rs.getString("name");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp createdAt = rs.getTimestamp("created_at");

				UserPost post = new UserPost();
				post.setBranchId(branchId);
				post.setDepartmentId(departmentId);
				post.setId(id);
				post.setUserId(userId);
				post.setName(name);
				post.setTitle(title);
				post.setText(text);
				post.setCategory(category);
				post.setCreatedAt(createdAt);

				ret.add(post);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
