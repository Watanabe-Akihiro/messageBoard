package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Post;
import beans.UserPost;
import dao.PostDao;
import dao.UserPostDao;

public class PostService {

	public void register(Post post){
		Connection connection = null;
		try{
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.insert(connection, post);

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

	private static final int limitNum = 1000;

	public List<UserPost> getPost(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostDao userPostDao = new UserPostDao();
			List<UserPost> ret = userPostDao.getUserPost(connection, limitNum);

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

	public void delete(int deletedId){
		Connection connection = null;
		try{
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.delete(connection, deletedId);

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