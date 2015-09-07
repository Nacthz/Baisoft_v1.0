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

	public static ArrayList<String[]> getBillInfo2(String bill_id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT transaction_item.inventory_item_id, inventory_item.inventory_item_description, transaction_item.transaction_item_quantity, transaction_item.transaction_item_desc, transaction_item.transaction_item_price FROM sincrodb.transaction_item LEFT JOIN inventory_item on transaction_item.inventory_item_id = inventory_item.inventory_item_id WHERE transaction_number = "+bill_id+";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			int total = 0;
			
			while (rs.next()) {
				total += Integer.parseInt(rs.getString("transaction_item_price")) ;
				array.add(new String[] {
						rs.getString("inventory_item_id"),
						rs.getString("inventory_item_description"),
						"" + (Integer.parseInt(rs.getString("transaction_item_desc"))+Integer.parseInt(rs.getString("transaction_item_price"))),
						rs.getString("transaction_item_quantity"),
						rs.getString("transaction_item_desc"),
						rs.getString("transaction_item_price")});
			}

			array.add(new String[] {
						"",
						"",
						"",
						"",
						"",
						"" + total});
			
			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ArrayList<String[]> getBillInfo1(String bill_id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT * FROM sincrodb.transaction_service WHERE transaction_number =  "+bill_id+";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			int total = 0;
			
			while (rs.next()) {
				total += Integer.parseInt(rs.getString("transaction_service_price")) ;
				array.add(new String[] {
						rs.getString("transaction_service_id"),
						rs.getString("transaction_service_description"),
						rs.getString("transaction_service_price")});
			}
			array.add(new String[] {
					"",
					"",
					"" + total});
			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ArrayList<String[]> getEmployees() {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT * FROM sincrodb.employee;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				array.add(new String[] {
						rs.getString("employee_id"),
						rs.getString("employee_name")});
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ArrayList<String[]> getBillSincrorepuestos() {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT * FROM sincrodb.transaction WHERE company_nit = 2;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				array.add(new String[] {
						rs.getString("transaction_id"),
						rs.getString("customer_name"),
						rs.getString("customer_identification_number"),
						rs.getString("customer_phone"),
						rs.getString("transaction_number"), 
						rs.getString("transaction_date"),
						rs.getString("transaction_price")});
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ArrayList<String[]> getBillSincroautos() {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT * FROM sincrodb.transaction WHERE company_nit = 1;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				array.add(new String[] {
						rs.getString("transaction_id"),
						getEmployeeName(rs.getString("transaction_number")),
						rs.getString("customer_name"),
						rs.getString("vehicle_license_plate"),
						rs.getString("customer_identification_number"),
						rs.getString("customer_phone"),
						rs.getString("transaction_number"), 
						rs.getString("transaction_date"),
						rs.getString("transaction_price")});
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}

	public static String getEmployeeName(String bill_id){
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String result = null;

			String sql = "SELECT employee.employee_name FROM sincrodb.transaction_service LEFT JOIN employee on employee.employee_id = transaction_service.employee_id WHERE transaction_service.transaction_number = "+bill_id+" LIMIT 1;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				result = rs.getString("employee_name");
				break;
			}

			rs.close();
			connection.close();
			return result;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int getLastBillId(int companyId) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT `transaction_number` FROM `transaction` WHERE `company_nit` = '" + companyId
					+ "' ORDER BY `transaction_number` DESC LIMIT 1";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			int rt = 0;
			while (rs.next()) {
				String number = rs.getString("transaction_number");
				rt = Integer.parseInt(number) + 1;
			}

			rs.close();
			connection.close();
			return rt;
		} catch (SQLException e) {
			return 0;
		}
	}

	public static void insertTransaction(String tra_num, String com_id, String cus_id, String vei_lic_pla,
			String tra_pri, String tra_dat, String cus_nam, String cus_pho, ArrayList<String[]> items) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `transaction` (`transaction_number`,`company_nit`,`customer_identification_number`,`vehicle_license_plate`,`transaction_price`,`transaction_date`,`customer_name`, `customer_phone`) VALUES ('"
					+ tra_num + "', '" + com_id + "','" + cus_id + "','" + vei_lic_pla + "','" + tra_pri + "','"
					+ tra_dat + "','" + cus_nam + "','" + cus_pho + "');";
			statement.executeUpdate(sql);
			for (String[] s : items) {
				sql = "INSERT INTO `transaction_item` (`transaction_number`,`inventory_item_id`,`transaction_item_quantity`,`transaction_item_desc`,`transaction_item_price`) VALUES ('"
						+ tra_num + "','" + s[0] + "','" + s[1] + "','" + s[2] + "','" + s[3] + "');";
				statement.executeUpdate(sql);
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void insertTransaction(String tra_num, String com_id, String cus_id, String vei_lic_pla,
			String tra_pri, String tra_dat, String cus_nam, String cus_pho, String tra_emp, ArrayList<String[]> items) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO `transaction` (`transaction_number`,`company_nit`,`customer_identification_number`,`vehicle_license_plate`,`transaction_price`,`transaction_date`,`customer_name`, `customer_phone`) VALUES ('"
					+ tra_num + "', '" + com_id + "','" + cus_id + "','" + vei_lic_pla + "','" + tra_pri + "','"
					+ tra_dat + "','" + cus_nam + "','" + cus_pho + "');";
			statement.executeUpdate(sql);
			for (String[] s : items) {
				sql = "INSERT INTO `sincrodb`.`transaction_service` (`transaction_number`,`transaction_service_description`,`transaction_service_price`,`employee_id`) VALUES ('"
						+ tra_num + "','" + s[1] + "','" + s[2] + "','" + tra_emp +  "');";
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