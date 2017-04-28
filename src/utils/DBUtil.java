package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SQLRuntimeException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost/messageboard";
	private static final String user = "root";
	private static final String password = "Lemon20050709";

	static {
		try{
			Class.forName(driver);
		} catch(ClassNotFoundException e){
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection(){
		try{
			Connection con = DriverManager.getConnection(url, user, password);

			con.setAutoCommit(false);

			return con;
		} catch(SQLException e){
			throw new SQLRuntimeException(e);
		}
	}


	public static void commit(Connection con){
		try{
			con.commit();
		} catch(SQLException e){
			throw new SQLRuntimeException(e);
		}
	}

	public static void rollback(Connection con){
		try{
			con.rollback();
		} catch(SQLException e){
			throw new SQLRuntimeException(e);
		}

	}
}
