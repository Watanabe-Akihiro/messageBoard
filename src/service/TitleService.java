package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branch;
import beans.Department;
import dao.TitleDao;

public class TitleService {

	public List<Branch> getBranches(){
		Connection connection = null;
		try{
			connection = getConnection();

			TitleDao titleDao = new TitleDao();
			List<Branch> ret = titleDao.getBranches(connection);

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

	public Branch getBranch(int id){
		Connection connection = null;
		try{
			connection = getConnection();

			TitleDao titleDao = new TitleDao();
			Branch branch = titleDao.getBranch(connection, id);

			commit(connection);
			return branch;
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

	public List<Department> getDepartments(){
		Connection connection = null;
		try{
			connection = getConnection();

			TitleDao titleDao = new TitleDao();
			List<Department> ret = titleDao.getDepartments(connection);

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

	public Department getDepartment(int id){
		Connection connection = null;
		try{
			connection = getConnection();

			TitleDao titleDao = new TitleDao();
			Department department = titleDao.getDepartment(connection, id);

			commit(connection);
			return department;
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
