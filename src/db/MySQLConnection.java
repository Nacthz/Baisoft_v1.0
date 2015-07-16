package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
	public static java.sql.Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/sincrodb";
		String username = "sincroadmin";
		String password = "3sC]Es8w4&3JqHd";
		try (Connection connection = DriverManager.getConnection(url, username,
				password)) {
			return connection;
		} catch (SQLException e) {
			return null;
		}
	}

	public static boolean check() throws SQLException {
		Connection connection;
		if ((connection = getConnection()) != null) {
			connection.close();
			return true; // Connection established to the database
		}
		return false; // Connection failed
	}
}