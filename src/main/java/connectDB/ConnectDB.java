package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanVeTau";
	private final String user = "sa";
	private String password;

	public Connection connection;
	public static ConnectDB connectDB;

	public static ConnectDB getInstance() {
		if (connectDB == null) {
			connectDB = new ConnectDB();
		}

		return connectDB;
	}

	public ConnectDB() {
		password = System.getenv("MSSQL_PASSWORD");
	}

	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, user, password);
		}

		return connection;
	}

	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}