package db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQLConnection {
	
	private static String url = "jdbc:mysql://localhost:3306/sincrodb";
	private static String username = "sincro";
	private static String password = "kif4g;3y";
	
	public static ArrayList<String[]> getInventory(boolean all) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();
			String sql = "";
			if (all)
				sql = "SELECT * FROM inventory_item;";
			else
				sql = "SELECT * FROM inventory_item WHERE inventory_item_status = 1";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				if (all) {
					String status = "eliminado";
					if (rs.getString("inventory_item_status").equals("1"))
						status = "activo";
					array.add(new String[] { rs.getString("inventory_item_id"),
							rs.getString("inventory_item_description"), status, rs.getString("inventory_item_price"),
							rs.getString("inventory_item_sale_price"), rs.getString("inventory_item_quantity") });
				} else
					array.add(new String[] { rs.getString("inventory_item_id"),
							rs.getString("inventory_item_description"), rs.getString("inventory_item_price"),
							rs.getString("inventory_item_sale_price"), rs.getString("inventory_item_quantity") });
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static void deleteItem(String sql) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}