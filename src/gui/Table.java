package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import db.MySQLConnection;
import views.Sincroautos;
import views.Sincrorepuestos;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class Table extends JPanel {

	private static final long serialVersionUID = -2108154469027694188L;

	private boolean navigation, icons = false;
	public String father;
	private int page, max, index, first = 0, actual = 0, maxPage = 1, searchTable = 1, cant, Acant;
	private JLabel cantInfo;
	private JPanel panel_CENTER, jFather;
	private Table actualTable = this;
	private JTextField JTF_Search;
	private ArrayList<TableRow> rows = new ArrayList<TableRow>();
	private ArrayList<String[]> originalData = new ArrayList<String[]>();
	private ArrayList<String[]> actualData = new ArrayList<String[]>();

	public boolean isFocusable() {
		return true;
	}

	public void removeItems(){
		originalData.clear();
		actualData.clear();
		panel_CENTER.removeAll();
		rv();
	}
	
	public int getValue(){
		int sum = 0;
		Component[] components = panel_CENTER.getComponents();
		for(Component  tr : components){
			TableRow a = (TableRow) tr;
			sum += a.getValue();
		}
		return sum;
	}
	
	public ArrayList<String[]> getItems(){
		ArrayList<String[]> rt = new ArrayList<String[]>();
		Component[] components = panel_CENTER.getComponents();
		for(Component  tr : components){
			TableRow a = (TableRow) tr;
			rt.add(a.getDescription());
		}
		return rt;
	}
	
	public void rv() {
		this.revalidate();
		this.repaint();
	}

	public void searchOn(int id) {
		searchTable = id;
	}

	public void addRow(String[] d) {
		if (Acant > 0) {
			TableRow tr = new TableRow(jFather, d, actual, actualTable, icons);
			actualData.add(d);
			panel_CENTER.add(tr);
			rv();
			Acant--;
		}
	}
	
	public void addRow(String[] d, String rid) {
		if (Acant > 0) {
			if (father.equals("sincrorepuestos")) {
				String g[] = new String[] {d[0], d[1], d[2], d[3], d[4], d[5], d[6], rid};
				TableRow tr = new TableRow(jFather, d, actual, actualTable, icons, rid);
				actualData.add(g);
				panel_CENTER.add(tr);
				rv();
				Acant--;
			}
			if (father.equals("sincroautos")) {
				String g[] = new String[] {d[0], d[1], d[2], rid};
				TableRow tr = new TableRow(jFather, d, actual, actualTable, icons, rid);
				actualData.add(g);
				panel_CENTER.add(tr);
				rv();
				Acant--;
			}
		}
	}

	public void search(String match) {
		ArrayList<String[]> ArrayData = new ArrayList<String[]>();
		match = match.toLowerCase();
		for (int i = 0; i < originalData.size(); i++) {
			if (originalData.get(i)[searchTable].toLowerCase().contains(match)) {
				ArrayData.add(originalData.get(i));
			}
		}
		if (navigation)
			if (ArrayData.size() > 0) {
				actualData.clear();
				actualData = ArrayData;
				double a = actualData.size();
				double b = max;
				maxPage = (int) Math.ceil(a / b);
				page = 1;
				cantInfo.setText(page + " de " + maxPage);
				fillData(page, actualData);
				rv();
			} else {
				actualData.clear();
				panel_CENTER.removeAll();
				maxPage = 0;
				page = 0;
				cantInfo.setText(page + " de " + maxPage);
				rv();
			}
		else if (ArrayData.size() > 0) {
			actualData.clear();
			actualData = ArrayData;
			fillData(-1, actualData);
			rv();
		} else {
			actualData.clear();
			panel_CENTER.removeAll();
			rv();
		}

	}

	@SuppressWarnings("unchecked")
	public Table(JPanel jFather, String father, ArrayList<String[]> data, String[] title, boolean navigation,
			boolean search, boolean icons, int cant) {
		this.jFather = jFather;
		this.father = father;
		this.cant = cant;
		this.Acant = cant;
		this.icons = icons;
		this.navigation = navigation;
		if (data != null) {
			this.originalData = (ArrayList<String[]>) data.clone();
			this.actualData = (ArrayList<String[]>) data.clone();
		}
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		JPanel panel_NORTH = new JPanel();
		panel_NORTH.setLayout(new BorderLayout(0, 0));
		add(panel_NORTH, BorderLayout.NORTH);

		if (title != null)
			panel_NORTH.add(new TableTitle(title, actualTable, icons, search), BorderLayout.CENTER);

		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BoxLayout(panel_CENTER, BoxLayout.Y_AXIS));

		JPanel panel_search = new JPanel();
		panel_search.setLayout(new BorderLayout(0, 0));

		JLabel img1 = new JLabel(new ImageIcon("img/search_west.png"));
		JLabel img2 = new JLabel(new ImageIcon("img/search_east.png"));

		JPanel panel_NORTH_NORTH = new JPanel();
		panel_NORTH_NORTH.setBorder(new EmptyBorder(3, 5, 3, 5));
		panel_NORTH.add(panel_NORTH_NORTH, BorderLayout.NORTH);
		panel_NORTH_NORTH.setLayout(new BorderLayout(0, 0));

		if (search) {
			JTF_Search = new JTextField();
			JTF_Search.getDocument().addDocumentListener(new DocumentListener() {
				public void removeUpdate(DocumentEvent e) {
					if (JTF_Search.getText().equals("")) {
						if (navigation) {
							actualData.clear();
							actualData = (ArrayList<String[]>) originalData.clone();
							double a = actualData.size();
							double b = max;
							maxPage = (int) Math.ceil(a / b);
							actual = 1;
							cantInfo.setText(actual + " de " + maxPage);
							fillData(actual, actualData);

						} else {
							actualData.clear();
							panel_CENTER.removeAll();
						}
						rv();
					} else {
						search(JTF_Search.getText());
					}
				}

				public void insertUpdate(DocumentEvent e) {
					search(JTF_Search.getText());
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
				}
			});
			JTF_Search.setBackground(new Color(250, 250, 250));
			JTF_Search.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(204, 204, 204)));
			JTF_Search.setColumns(20);

			panel_search.add(JTF_Search, BorderLayout.CENTER);
			panel_search.add(img1, BorderLayout.WEST);
			panel_search.add(img2, BorderLayout.EAST);
			panel_NORTH_NORTH.add(panel_search, BorderLayout.WEST);
		}

		max = panel_CENTER.getHeight() / 25;

		if (navigation) {
			addbuttons();
			fillData(1, originalData);
		}

		panel_CENTER.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				if (navigation) {
					int newCant = panel_CENTER.getHeight() / 25;
					if (max < newCant) {
						if (actual < actualData.size()) {
							TableRow tr = new TableRow(jFather, actualData.get(actual), actual, actualTable, icons);
							actual++;
							rows.add(tr);
							panel_CENTER.add(tr);
						}
						max = newCant;
						double a = actualData.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);
						fillData(page, actualData);
						return;
					}
					if (index > newCant) {
						index--;
						actual--;
						panel_CENTER.remove(index);
						rows.remove(index);
						max = newCant;
						double a = actualData.size();
						double b = max;
						double c = first;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);
						fillData(page, actualData);
						return;
					}

					if (max != newCant) {
						max = newCant;
						double a = actualData.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);
						fillData(page, actualData);
					}
				}
			}
		});
	}

	public void setSelected(boolean status) {
		for (TableRow TR : rows) {
			TR.setSelected(status);
		}
	}

	public void enableRow(int toEnable, String itemID) {
		actualData.get(toEnable)[2] = "activo";
		reload();
		String sql;

		sql = "UPDATE `inventory_item` SET `inventory_item_status` = '1' WHERE `inventory_item_id` = '" + itemID + "'";
		MySQLConnection.deleteItem(sql);
	}

	public void reload() {
		if (navigation) {
			double a = actualData.size();
			double b = max;
			double c = actual;
			maxPage = (int) Math.ceil(a / b);
			page = (int) Math.ceil(c / b);
			fillData(page, actualData);
			cantInfo.setText(page + " de " + maxPage);
		}
		this.revalidate();
	}

	public void updateRow(int toWork, String[] updateData) {
		String sql;
		if (father.equals("inventory")) {
			sql = "UPDATE `inventory_item` SET `inventory_item_description` = '" + updateData[1]
					+ "', `inventory_item_quantity` = '" + updateData[4] + "', `inventory_item_sale_price` = '"
					+ updateData[3] + "', `inventory_item_price` = '" + updateData[2]
					+ "' WHERE `inventory_item_id` = '" + updateData[0] + "';";
			MySQLConnection.deleteItem(sql);

			int s = 0;
			for (String a : updateData) {
				actualData.get(toWork)[s] = a;
				s++;
			}
			reload();
			return;
		}

		if (father.equals("inventory_all")) {
			sql = "UPDATE `inventory_item` SET `inventory_item_description` = '" + updateData[1]
					+ "', `inventory_item_quantity` = '" + updateData[5] + "', `inventory_item_sale_price` = '"
					+ updateData[4] + "', `inventory_item_price` = '" + updateData[3]
					+ "' WHERE `inventory_item_id` = '" + updateData[0] + "';";
			MySQLConnection.deleteItem(sql);

			int s = 0;
			for (String a : updateData) {
				if (s != 2)
					actualData.get(toWork)[s] = a;
				s++;
			}
			reload();
			return;
		}
		
		if (father.equals("sincrorepuestos")) {
			Sincrorepuestos sr = (Sincrorepuestos) jFather;
			sr.calculate();
		}
	}

	public void deleteRow(TableRow trd, String uid) {
		if (father.equals("sincrorepuestos")) {
			int i = 0;
			for (String[] sm : actualData) {
				if (sm[7].equals(uid)) {
					actualData.remove(i);
					break;
				}
				i++;
			}
			Acant++;
			panel_CENTER.remove(trd);
			rv();
			Sincrorepuestos sr = (Sincrorepuestos) jFather;
			sr.calculate();
		}
		
		if (father.equals("sincroautos")) {
			int i = 0;
			for (String[] sm : actualData) {
				if (sm[3].equals(uid)) {
					actualData.remove(i);
					break;
				}
				i++;
			}
			Acant++;
			panel_CENTER.remove(trd);
			rv();
			Sincroautos sa = (Sincroautos) jFather;
			sa.calculate();
		}
	}
	
	public void deleteRow(int toWork, String itemID) {
		String sql;
		if (father.equals("inventory")) {
			sql = "UPDATE `inventory_item` SET `inventory_item_status` = '0' WHERE `inventory_item_id` = '" + itemID
					+ "'";
			MySQLConnection.deleteItem(sql);
			actualData.remove(toWork);
			reload();
			return;
		}

		if (father.equals("inventory_all")) {
			sql = "UPDATE `inventory_item` SET `inventory_item_status` = '0' WHERE `inventory_item_id` = '" + itemID
					+ "'";
			MySQLConnection.deleteItem(sql);
			actualData.get(toWork)[2] = "eliminado";
			reload();
			return;
		}
	}

	public void fillData(int nPage, ArrayList<String[]> ArrayData) {
		if (ArrayData.size() > 0) {
			if (nPage != -1) {
				rows.clear();
				panel_CENTER.removeAll();
				page = nPage;
				first = actual = index = (page - 1) * max;
				first++;
				for (int i = (page - 1) * max; i < ((page - 1) * max) + max && i < ArrayData.size(); i++) {
					TableRow tr = new TableRow(jFather, ArrayData.get(i), i, actualTable, icons);
					rows.add(tr);
					panel_CENTER.add(tr);
				}
				actual += rows.size();
				index = actual - index;
				panel_CENTER.revalidate();
				panel_CENTER.repaint();
			} else {
				rows.clear();
				panel_CENTER.removeAll();
				int i = 0;
				for (String[] sm : ArrayData) {
					TableRow tr = new TableRow(jFather, sm, i, actualTable, icons);
					rows.add(tr);
					panel_CENTER.add(tr);
					i++;
					if (i == cant) {
						panel_CENTER.revalidate();
						panel_CENTER.repaint();
						break;
					}
				}
				panel_CENTER.revalidate();
				panel_CENTER.repaint();
			}
		}
	}

	public void addbuttons() {
		JPanel panel_SOUTH = new JPanel();
		panel_SOUTH.setBackground(Color.white);
		panel_SOUTH.setOpaque(true);
		panel_SOUTH.setBorder(new EmptyBorder(0, 0, 2, 0));
		add(panel_SOUTH, BorderLayout.SOUTH);

		panel_SOUTH.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		JLabel first = new JLabel(new ImageIcon("img/first.png"));
		JLabel prev = new JLabel(new ImageIcon("img/prev.png"));

		cantInfo = new JLabel();
		cantInfo.setBorder(new EmptyBorder(0, 6, 0, 6));
		cantInfo.setHorizontalAlignment(SwingConstants.CENTER);
		navigation = true;

		JLabel next = new JLabel(new ImageIcon("img/next.png"));
		JLabel last = new JLabel(new ImageIcon("img/last.png"));

		prev.addMouseListener(getEventLabel());
		prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page > 1) {
					page--;
					fillData(page, actualData);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}
			}
		});

		first.addMouseListener(getEventLabel());
		first.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page != 1) {
					page = 1;
					fillData(page, actualData);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}
			}
		});

		last.addMouseListener(getEventLabel());
		last.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page != maxPage) {
					page = maxPage;
					fillData(page, actualData);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}

			}
		});

		next.addMouseListener(getEventLabel());
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page < maxPage) {
					page++;
					fillData(page, actualData);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}
			}
		});
		panel_SOUTH.add(first);
		panel_SOUTH.add(prev);
		panel_SOUTH.add(cantInfo);
		panel_SOUTH.add(next);
		panel_SOUTH.add(last);
	}

	public MouseAdapter getEventLabel() {
		MouseAdapter MA = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};
		return MA;
	}
}
