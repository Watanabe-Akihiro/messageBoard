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

	public List<String> getCategories(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostDao userPostDao = new UserPostDao();
			List<String> ret = userPostDao.getCategories(connection);

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


	public List<UserPost> getPost(String category, String start, String end){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostDao userPostDao = new UserPostDao();
			List<UserPost> ret = userPostDao.getUserPost(connection, category, start, end);

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


	/*public List<UserPost> getPostByCategory(String category){
		Connection connection = null;
		try{
			connection = getConnection();

			UserPostDao userPostDao = new UserPostDao();
			List<UserPost> ret = userPostDao.getPostByCategory(connection, category);

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
	}*/

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