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
	
	public static int getLastBillId(int companyId){
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT `transaction_number` FROM `transaction` WHERE `company_nit` = '"+companyId+"' ORDER BY `transaction_number` DESC LIMIT 1";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int rt = 0;
			while (rs.next()) {
					rt = Integer.parseInt(rs.getString("transaction_number")) + 1;
			}

			rs.close();
			connection.close();
			return rt;
		} catch (SQLException e) {
			return 0;
		}
	}
	
	
	
	public static void insertTransaction(String tra_num, String com_id, String cus_id, String vei_lic_pla, String tra_pri, String tra_dat, String cus_nam, String cus_pho, ArrayList<String[]> items){
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `transaction` (`transaction_number`,`company_nit`,`customer_identification_number`,`vehicle_license_plate`,`transaction_price`,`transaction_date`,`customer_name`, `customer_phone`) VALUES ('"+tra_num+"', '"+com_id+"','"+cus_id+"','"+vei_lic_pla+"','"+tra_pri+"','"+tra_dat+"','"+cus_nam+"','"+cus_pho+"');";
			statement.executeUpdate(sql);
			for(String[] s : items){
				sql = "INSERT INTO `transaction_item` (`transaction_number`,`inventory_item_id`,`transaction_item_quantity`,`transaction_item_price`) VALUES ('"+tra_num+"','"+s[0]+"','"+s[1]+"','"+s[2]+"');";			
				statement.executeUpdate(sql);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
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