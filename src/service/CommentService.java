package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Comment;
import beans.UserComment;
import dao.CommentDao;
import dao.UserCommentDao;

public class CommentService {
	public void register(Comment comment){
		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

			commit(connection);
		 } catch(RuntimeException e){
			 rollback(connection);
			 throw e;
		 } catch(Error e){
			 throw e;
		 } finally{
			 close(connection);
		 }
		}

	public List<UserComment> getComment(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserCommentDao userCommentDao = new UserCommentDao();
			List<UserComment> ret = userCommentDao.getUserComment(connection);

			commit(connection);
			return ret;
		} catch(RuntimeException e){
			 rollback(connection);
			 throw e;
		 } catch(Error e){
			 throw e;
		 } finally{
			 close(connection);
		 }
	}

	public void deleteComment(int deletedId){
		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.deleteComment(connection, deletedId);

			commit(connection);
		} catch(RuntimeException e){
			 rollback(connection);
			 throw e;
		} catch(Error e){
			 rollback(connection);
			 throw e;
		} finally{
			 close(connection);
		}
	}

}
