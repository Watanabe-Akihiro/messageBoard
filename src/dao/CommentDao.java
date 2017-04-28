package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {
	public void insert(Connection connection, Comment comment){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("id");
			sql.append(", post_id");
			sql.append(", user_id");
			sql.append(", text");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append("null");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(2, comment.getPostId());
			ps.setInt(1, comment.getUserId());
			ps.setString(3, comment.getText());

			ps.executeUpdate();

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally{
				close(ps);
			}
	}


}
