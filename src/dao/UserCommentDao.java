package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import beans.UserComment;
import exception.SQLRuntimeException;

public class UserCommentDao {
	public List<UserComment> getUserComment(Connection connection){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_comments ");
			sql.append("ORDER BY created_at ASC");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			List<UserComment> ret = toUserCommentList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}


	private List<UserComment> toUserCommentList(ResultSet rs)
		throws SQLException {
		List<UserComment> ret = new ArrayList<UserComment>();
		try{
			while(rs.next()){
				String branchId = rs.getString("branch_id");
				String departmentId = rs.getString("department_id");
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				int postId = rs.getInt("post_id");
				String name = rs.getString("name");
				String text = rs.getString("text");
				Timestamp createdAt = rs.getTimestamp("created_at");

				UserComment comment = new UserComment();
				comment.setBranchId(branchId);
				comment.setDepartmentId(departmentId);
				comment.setId(id);
				comment.setUserId(userId);
				comment.setPostId(postId);
				comment.setName(name);
				comment.setText(text);
				comment.setCreatedAt(createdAt);

				ret.add(comment);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
