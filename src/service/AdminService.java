package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.UserDao;

public class AdminService {
	public List<User> getUsers(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getAllUsers(connection);

			commit(connection);
			return ret;
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

	public User getUser(int id){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUserFromId(connection, id);

			commit(connection);
			return user;
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

	public void isActive(int isActivated, int userId){
		Connection connection = null;
		try{
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.isActiveUser(connection, isActivated, userId);
			commit(connection);

		} finally{
			 close(connection);
		 }
	}

	public void delete(int deletedId){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, deletedId);

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
