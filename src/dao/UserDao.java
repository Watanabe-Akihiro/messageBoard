package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.SQLRuntimeException;


public class UserDao {
	public void insert(Connection connection, User user){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append("id");
			sql.append(", login_id");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", is_activated");
			sql.append(") VALUES (");
			sql.append("null");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, user.getLoginId());
			ps.setInt(2, user.getBranchId());
			ps.setInt(3, user.getDepartmentId());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getName());
			ps.setInt(6, user.getIsActivated());
			ps.executeUpdate();

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally{
				close(ps);
			}
	}

	public void isActiveUser(Connection connection, int isActivated, int userId){
		PreparedStatement ps = null;
		try{
			String sql = "UPDATE users SET is_activated = ? WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, isActivated);
			ps.setInt(2, userId);

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
			String sql = "DELETE FROM users WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1,  deletedId);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	public List<User> getAllUsers(Connection connection){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM users";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<User> userList = toUserList(rs);

			return userList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	public User getUserFromId(Connection connection, int id){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM users WHERE id = ? ";
			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			List<User> userList = toUserList(rs);

			if(userList.isEmpty() == true){
				return null;
			} else if(userList.size() >= 2){
				throw new IllegalStateException("userList.size() >= 2");
			} else{
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	public User getUser(Connection connection, String loginId, String password){
		PreparedStatement ps = null;
		try{
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
			ps = connection.prepareStatement(sql);

			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			List<User> userList = toUserList(rs);

			if(userList.isEmpty() == true){
				return null;
			} else if(userList.size() >= 2){
				throw new IllegalStateException("userList.size() >= 2");
			} else{
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	public void update(Connection connection, User user){
		PreparedStatement ps = null;

		try{
			StringBuilder sql = new StringBuilder();

			sql.append("UPDATE users SET");
			sql.append(" login_id = ?");
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");
			sql.append(", password = ?");
			sql.append(", name = ?");
			sql.append(" WHERE id = ?");
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setInt(2, user.getBranchId());
			ps.setInt(3, user.getDepartmentId());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getName());
			ps.setInt(6, user.getId());
			ps.executeUpdate();

			System.out.println(ps.toString());
		}  catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException{
		List<User> ret = new ArrayList<User>();
		try{

			while(rs.next()){
				int id = rs.getInt("id");
				int branchId = rs.getInt("branch_id");
				int isActivated = rs.getInt("is_activated");
				int departmentId = rs.getInt("department_id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");

				User user = new User();
				user.setId(id);
				user.setBranchId(branchId);
				user.setDepartmentId(departmentId);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setIsActivated(isActivated);

				ret.add(user);

			}
			return ret;
		} finally{
			close(rs);
		}
	}
}
