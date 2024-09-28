package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	public static ConnectDB connectDB = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return connectDB;
	}
	//Khoi tao ket noi
	public void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanVeTau";
		String user = "sa";
		String pass = "12345";
		con = DriverManager.getConnection(url,user,pass);
	}
	//Goi ket noi
	public static Connection getConnection() {
		return con;
	}
	//Ngat ket noi
	public void disconnect() {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
