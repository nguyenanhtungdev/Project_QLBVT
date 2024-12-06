package vn.banvetau.QLBanVeTau;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import connectDB.Database;

public class ConnectDBTest {

	@Test
	public void doesPasswordConfiguredCorrectly() {
		String password = System.getenv("MSSQL_PASSWORD");

		assertFalse(() -> password == null);
	}

	@Test
	public void doesConnectionEstablishedSuccessfully() throws SQLException {
		Connection connection = Database.getInstance().getConnection();

		boolean connectionState = connection.isClosed();

		assertFalse(connectionState);
	}

}
