package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Post;
import exception.SQLRuntimeException;

public class PostDao {
	public void insert(Connection connection, Post post){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append("id");
			sql.append(", user_id");
			sql.append(", title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append("null");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, post.getUserId());
			ps.setString(2, post.getTitle());
			ps.setString(3, post.getText());
			ps.setString(4, post.getCategory());

			ps.executeUpdate();

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally{
				close(ps);
			}
	}

	public void delete(Connection connection, int deletedId){
		PreparedStatement ps = null;
		try{
			String sql = "DELETE FROM posts WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1,  deletedId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}
}
