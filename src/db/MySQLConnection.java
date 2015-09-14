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

			String sql = "SELECT transaction_item.inventory_item_id, inventory_item.inventory_item_description, transaction_item.transaction_item_quantity, transaction_item.transaction_item_desc, transaction_item.transaction_item_price FROM sincrodb.transaction_item LEFT JOIN inventory_item on transaction_item.inventory_item_id = inventory_item.inventory_item_id WHERE transaction_number = "
					+ bill_id + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			int total = 0;

			while (rs.next()) {
				total += Integer.parseInt(rs.getString("transaction_item_price"));
				array.add(new String[] { rs.getString("inventory_item_id"), rs.getString("inventory_item_description"),
						"" + (Integer.parseInt(rs.getString("transaction_item_desc"))
								+ Integer.parseInt(rs.getString("transaction_item_price"))),
						rs.getString("transaction_item_quantity"), rs.getString("transaction_item_desc"),
						rs.getString("transaction_item_price") });
			}

			array.add(new String[] { "", "", "", "", "", "" + total });

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

			String sql = "SELECT * FROM sincrodb.transaction_service LEFT JOIN sincrodb.employee on employee.employee_id = transaction_service.employee_id WHERE transaction_number =   "
					+ bill_id + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			int total = 0;

			while (rs.next()) {
				total += Integer.parseInt(rs.getString("transaction_service_price"));
				array.add(new String[] { rs.getString("transaction_service_id"),
						rs.getString("transaction_service_description"), rs.getString("employee_name"),
						rs.getString("transaction_service_type"), rs.getString("transaction_service_price") });
			}
			array.add(new String[] { "", "", "", "", "" + total });
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
				array.add(new String[] { rs.getString("employee_id"), rs.getString("employee_name") });
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

			String sql = "SELECT * FROM sincrodb.transaction WHERE company_nit = 2  ORDER BY transaction_id DESC;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				array.add(new String[] { rs.getString("transaction_id"), rs.getString("customer_name"),
						rs.getString("customer_identification_number"), rs.getString("customer_phone"),
						rs.getString("transaction_number"), rs.getString("transaction_date"),
						rs.getString("transaction_status"), rs.getString("transaction_price") });
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}

	public static String getEmployeesBillInfo(String bill_id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String result = "";

			String sql = "SELECT transaction_date FROM sincrodb.transaction WHERE transaction_number = " + bill_id
					+ " AND company_nit = 1;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				result = rs.getString("transaction_date");
			}

			rs.close();
			connection.close();
			return result;
		} catch (SQLException e) {
			return null;
		}
	}

	public static ArrayList<String[]> getEmployeesBill() {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT * FROM sincrodb.transaction_service LEFT JOIN sincrodb.employee on employee.employee_id = transaction_service.employee_id ORDER BY transaction_service_id DESC ";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				array.add(new String[] { rs.getString("transaction_service_id"), rs.getString("employee_name"),
						rs.getString("transaction_service_description"), rs.getString("transaction_number"),
						getEmployeesBillInfo(rs.getString("transaction_number")),
						rs.getString("transaction_service_type"), rs.getString("transaction_service_price") });
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

			String sql = "SELECT * FROM sincrodb.transaction WHERE company_nit = 1 ORDER BY transaction_id DESC;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				array.add(new String[] { rs.getString("transaction_id"), rs.getString("customer_name"),
						rs.getString("vehicle_license_plate"), rs.getString("customer_identification_number"),
						rs.getString("customer_phone"), rs.getString("transaction_number"),
						rs.getString("transaction_date"), rs.getString("transaction_status"),
						rs.getString("transaction_price") });
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}

	public static String getEmployeeName(String bill_id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String result = null;

			String sql = "SELECT employee.employee_name FROM sincrodb.transaction_service LEFT JOIN employee on employee.employee_id = transaction_service.employee_id WHERE transaction_service.transaction_number = "
					+ bill_id + " LIMIT 1;";
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

	public static int getLastBillI(int companyId) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT `transaction_number` FROM `transaction` WHERE `company_nit` = '" + companyId
					+ "' ORDER BY `transaction_id` DESC LIMIT 1";
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

	public static boolean ifExistsTransaction(String tra_num, String com_id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM sincrodb.transaction WHERE company_nit = " + com_id
					+ " AND transaction_number = " + tra_num + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				rs.close();
				connection.close();
				return true;
			}
			rs.close();
			connection.close();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	public static String[] getOwner(String lin_pla) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT customer_name, customer_phone FROM sincrodb.transaction WHERE vehicle_license_plate = '"
					+ lin_pla + "' AND company_nit = 1 ORDER BY transaction_id DESC LIMIT 1;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String result[] = new String[2];
				result[0] = rs.getString("customer_name");
				result[1] = rs.getString("customer_phone");
				rs.close();
				connection.close();
				return result;
			}
			rs.close();
			connection.close();
			return null;
		} catch (SQLException e) {
			return null;
		}
	}

	public static ArrayList<String[]> getVehicleInfos(String lin_pla) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT transaction_id, customer_name, customer_phone, vehicle_license_plate, transaction_date, transaction_price FROM sincrodb.transaction WHERE vehicle_license_plate = '"
					+ lin_pla + "' ORDER BY transaction_id DESC;";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				array.add(new String[] { rs.getString("transaction_id"), rs.getString("customer_name"),
						rs.getString("customer_phone"), rs.getString("vehicle_license_plate"),
						rs.getString("transaction_date"), rs.getString("transaction_price") });
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}

	public static ArrayList<String[]> getVehiclePerDate(String date, String type) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			ArrayList<String[]> array = new ArrayList<String[]>();

			String sql = "SELECT vehicle_id, vehicle_license_plate, vehicle_last_date FROM sincrodb.vehicle WHERE ";
			Statement statement = connection.createStatement();

			if (!date.equals("")) {
				sql += "vehicle_last_date_num <= " + date
						+ " AND vehicle_status = 1 ORDER BY vehicle_last_date_num ASC; ";
			} else {
				sql += "vehicle_status = " + type + " ORDER BY vehicle_last_date_num ASC; ";
			}

			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String[] test = getOwner(rs.getString("vehicle_license_plate"));

				array.add(new String[] { rs.getString("vehicle_id"), test[0], test[1],
						rs.getString("vehicle_license_plate"), rs.getString("vehicle_last_date") });
			}

			rs.close();
			connection.close();
			return array;
		} catch (SQLException e) {
			return null;
		}
	}

	public static void getVehicles(String tra_num, String com_id, String cus_id, String vei_lic_pla, String tra_pri,
			String tra_dat, String cus_nam, String cus_pho, ArrayList<String[]> items) {

		if (ifExistsTransaction(tra_num, com_id) == false) {
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
			}
		}
	}

	public static boolean insertTransaction(String tra_num, String com_id, String cus_id, String vei_lic_pla,
			String tra_pri, String tra_dat, String cus_nam, String cus_pho, ArrayList<String[]> items, String tra_sta) {

		if (com_id.equals("1")) {

			if (ifExistsTransaction(tra_num, com_id) == false) {
				try (Connection connection = DriverManager.getConnection(url, username, password)) {
					Statement statement = connection.createStatement();
					String sql = "INSERT INTO `transaction` (`transaction_number`,`company_nit`,`customer_identification_number`,`vehicle_license_plate`,`transaction_price`,`transaction_date`,`customer_name`, `customer_phone`, `transaction_status`) VALUES ('"
							+ tra_num + "', '" + com_id + "','" + cus_id + "','" + vei_lic_pla + "','" + tra_pri + "','"
							+ tra_dat + "','" + cus_nam + "','" + cus_pho + "','" + tra_sta + "');";
					statement.executeUpdate(sql);
					for (String[] s : items) {
						sql = "INSERT INTO `sincrodb`.`transaction_service` (`transaction_number`,`transaction_service_description`,`transaction_service_price`,`transaction_service_type`,`employee_id`) VALUES ('"
								+ tra_num + "','" + s[1] + "','" + s[4] + "','" + s[3] + "','" + s[0] + "');";
						statement.executeUpdate(sql);
					}
					if (!vei_lic_pla.equals("")) {
						String test[] = tra_dat.split("/");

						String numf = test[2];

						if (test[1].length() == 1)
							numf += "0" + test[1];
						else
							numf += test[1];

						if (test[0].length() == 1)
							numf += "0" + test[0];
						else
							numf += test[0];

						sql = "INSERT INTO vehicle (vehicle_license_plate, vehicle_last_date, vehicle_last_date_num, vehicle_status) VALUES('"
								+ vei_lic_pla + "', '" + tra_dat + "','" + numf + "','" + 1
								+ "') ON DUPLICATE KEY UPDATE `vehicle_last_date`='" + tra_dat
								+ "', `vehicle_last_date_num`='" + numf + "', `vehicle_status`='" + 1 + "';";
						statement.executeUpdate(sql);
					}
					statement.close();
					connection.close();
					return true;
				} catch (SQLException e) {
					return false;
				}
			}
		} else if (com_id.equals("2")) {

			if (ifExistsTransaction(tra_num, com_id) == false) {
				try (Connection connection = DriverManager.getConnection(url, username, password)) {
					Statement statement = connection.createStatement();
					String sql = "INSERT INTO `transaction` (`transaction_number`,`company_nit`,`customer_identification_number`,`vehicle_license_plate`,`transaction_price`,`transaction_date`,`customer_name`, `customer_phone`, `transaction_status`) VALUES ('"
							+ tra_num + "', '" + com_id + "','" + cus_id + "','" + vei_lic_pla + "','" + tra_pri + "','"
							+ tra_dat + "','" + cus_nam + "','" + cus_pho + "','" + tra_sta + "');";
					statement.executeUpdate(sql);
					for (String[] s : items) {
						sql = "INSERT INTO `transaction_item` (`transaction_number`,`inventory_item_id`,`transaction_item_quantity`,`transaction_item_desc`,`transaction_item_price`) VALUES ('"
								+ tra_num + "','" + s[0] + "','" + s[1] + "','" + s[2] + "','" + s[3] + "');";
						statement.executeUpdate(sql);
						takeItems(s[0], s[1]);
					}
					statement.close();
					connection.close();
					return true;
				} catch (SQLException e) {
					return false;
				}
			}
		}
		return false;
	}

	public static void takeItems(String ite_id, String ite_can) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT inventory_item_quantity FROM sincrodb.inventory_item WHERE inventory_item_id = "
					+ ite_id + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int dif = Integer.parseInt(rs.getString("inventory_item_quantity")) - Integer.parseInt(ite_can);
				if (dif > 0) {
					sql = "UPDATE `sincrodb`.`inventory_item` SET `inventory_item_quantity` = "+dif+" WHERE `inventory_item_id` = "+ite_id+" ";
				}else{
					sql = "UPDATE `sincrodb`.`inventory_item` SET `inventory_item_quantity` = "+0+" WHERE `inventory_item_id` = "+ite_id+" ";
				}
			}			
			statement.executeUpdate(sql);			
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
		}
	}

	public static String getStatus(String id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String status = "";

			String sql = "SELECT transaction_status FROM sincrodb.transaction WHERE transaction_id = " + id + ";";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				status = rs.getString("transaction_status");
			}

			rs.close();
			connection.close();
			return status;
		} catch (SQLException e) {
			return null;
		}
	}

	public static String changeStatus(String id) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();

			String sql = "";
			String result = "";

			if (getStatus(id).equals("Debiendo")) {
				sql = "UPDATE `sincrodb`.`transaction` SET `transaction_status` = 'Pagado' WHERE `transaction_id` = "
						+ id + ";";
				result = "Pagado";
			} else {
				sql = "UPDATE `sincrodb`.`transaction` SET `transaction_status` = 'Debiendo' WHERE `transaction_id` = "
						+ id + ";";
				result = "Debiendo";
			}

			statement.executeUpdate(sql);
			statement.close();
			connection.close();
			return result;
		} catch (SQLException e) {
		}
		return "";
	}

	public static void deleteItem(String sql) {
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e) {
		}
	}
}