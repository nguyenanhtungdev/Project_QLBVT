package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private static Database instance;

	public static Database getInstance() {
		return instance == null ? instance = new Database() : instance;
	}

	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanVeTau";
	private String user;
	private String password;
	private Connection connection;

	public Database() {
		this.user = "sa";
		this.password = System.getenv("MSSQL_PASSWORD");
	}

	public Database(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public Connection getConnection() throws SQLException {
		return (connection == null || connection.isClosed())
				? connection = DriverManager.getConnection(url, user, password)
				: connection;
	}

	public void disconnect() throws SQLException {
		if (connection != null)
			connection.close();
	}

}