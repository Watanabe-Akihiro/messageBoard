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

	public List<UserPost> getUserPost(Connection connection, int limitNum){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts ");
			sql.append("ORDER BY created_at DESC limit " + limitNum);

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			List<UserPost> ret = toUserPostList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}


	private List<UserPost> toUserPostList(ResultSet rs)
		throws SQLException {
		List<UserPost> ret = new ArrayList<UserPost>();
		try{
			while(rs.next()){
				int branchId = rs.getInt("branch_id");
				int departmentId = rs.getInt("department_id");
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
