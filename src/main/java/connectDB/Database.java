package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Database connectDB;
	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanVeTau";
	private String user;
	private String password;
	private Connection connection;

	/**
	 * Tạo một instance đến database với password được lấy từ biến môi trường
	 */
	public Database() {
		this.user = "sa";
		this.password = System.getenv("MSSQL_PASSWORD");
	}

	/**
	 * Tạo một instance đến database với user và password được truyền vào
	 * 
	 * @param user     người dùng
	 * @param password mật khẩu
	 */
	public Database(String user, String password) {
		this.user = user;
		this.password = password;
	}

	/**
	 * Get connection to the database<br/>
	 * Việc đống kết nối nên được thực hiện <strong>khi và chỉ khi</strong> kết thúc
	 * chương trình để đảm bảo tài nguyên
	 * 
	 * @return Connection to the database
	 * @throws SQLException if a database access error occurs
	 */
	public Connection getConnection() throws SQLException {
		return (connection == null || connection.isClosed())
				? connection = DriverManager.getConnection(url, user, password)
				: connection;
	}

	/**
	 * Đóng kết nối đến database<br/>
	 * Việc đống kết nối nên được thực hiện <strong>khi và chỉ khi</strong> kết thúc
	 * chương trình để đảm bảo tài nguyên
	 * 
	 * @throws SQLException if a database access error occurs
	 */
	public void disconnect() throws SQLException {
		if (connection != null)
			connection.close();
	}

	public static Database getInstance() {
		return connectDB == null ? connectDB = new Database() : connectDB;
	}

}