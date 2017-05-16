package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Branch;
import beans.Department;
import exception.SQLRuntimeException;

public class TitleDao {
	public List<Branch> getBranches(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM branches";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Branch> branches = toBranchesList(rs);


			return branches;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	public Branch getBranch(Connection connection, int id){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT DISTINCT branches.name, branches.id FROM branches INNER JOIN users ON branches.id = branch_id WHERE branches.id = ? ";
			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			List<Branch> branchList = toBranchesList(rs);

			if(branchList.isEmpty() == true){
				return null;
			} else if(branchList.size() >= 2){
				throw new IllegalStateException("branchList.size() >= 2");
			} else{
				return branchList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	private List<Branch> toBranchesList(ResultSet rs)
		throws SQLException {
		List<Branch> ret = new ArrayList<Branch>();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Branch branch = new Branch();
				branch.setId(id);
				branch.setName(name);

				ret.add(branch);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Department> getDepartments(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM departments";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<Department> departments = toDepartmentsList(rs);
			return departments;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}

	public Department getDepartment(Connection connection, int id){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT DISTINCT departments.name, departments.id FROM departments INNER JOIN users ON departments.id = department_id WHERE departments.id = ? ";
			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			List<Department> departmentList = toDepartmentsList(rs);

			if(departmentList.isEmpty() == true){
				return null;
			} else if(departmentList.size() >= 2){
				throw new IllegalStateException("departmentList.size() >= 2");
			} else{
				return departmentList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	private List<Department> toDepartmentsList(ResultSet rs)
		throws SQLException {
		List<Department> ret = new ArrayList<Department>();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Department department = new Department();
				department.setId(id);
				department.setName(name);

				ret.add(department);
			}
			return ret;
		} finally {
			close(rs);
		}
	}


}
