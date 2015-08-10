package views;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import db.MySQLConnection;
import gui.Table;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sincrorepuestos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2944687387171442917L;

	private int totalPrice = 0;
	private Table smallTable, bigTable;
	ArrayList<String[]> data, dataNames;
	private JPanel JP_NORT_EAST;
	private JPanel JP_NORT_EAST_NORTH;
	private JLabel lblNewLabel;
	private JTextField JTF_date;
	private JLabel lblNewLabel_1;
	private JTextField JTF_num;
	private JPanel JP_NORT_EAST_CENTER;
	private JTextField JTF_client;
	private JTextField JTF_cc;
	private JTextField JTF_phone;
	private JPanel JP_NORTH_CENTER;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField JTF_mount;
	private JTextField JTF_discount;
	private JPanel JP_SOUTH;
	private JLabel JL_total;
	private JButton JB_bill;

	/**
	 * Create the panel.
	 */
	public Sincrorepuestos() {
		setLayout(new BorderLayout(0, 0));

		String[] title = { "Descripcion", "Cantidad", "Precio unitario", "Total", "Descuento" };
		data = new ArrayList<String[]>();
		dataNames = new ArrayList<String[]>();
		boolean search = false;
		boolean advanceIcons = false;
		boolean navigation = false;
		int limitedRows = 15;

		boolean all = false;
		data = MySQLConnection.getInventory(all);

		for (String[] sm : data) {
			dataNames.add(new String[] { sm[0], sm[1], sm[3], sm[4] });
		}

		bigTable = new Table(this, "sincrorepuestos", null, title, navigation, search, advanceIcons, limitedRows);
		add(bigTable, BorderLayout.CENTER);

		smallTable = new Table(this, "sincrorepuestos_search", dataNames, null, true, true, advanceIcons, 0);

		JPanel JP_NORT = new JPanel();
		add(JP_NORT, BorderLayout.NORTH);
		JP_NORT.setLayout(new BorderLayout(0, 0));
		JP_NORT.setBorder(new EmptyBorder(6, 6, 0, 6));

		smallTable.setPreferredSize(new Dimension(400, 138));
		smallTable.setBorder(BorderFactory.createLineBorder(new Color(196, 196, 196)));
		JP_NORT.add(smallTable, BorderLayout.WEST);

		JP_NORT_EAST = new JPanel();
		JP_NORT.add(JP_NORT_EAST, BorderLayout.EAST);
		JP_NORT_EAST.setLayout(new BorderLayout(0, 0));

		JP_NORT_EAST_NORTH = new JPanel();
		JP_NORT_EAST.add(JP_NORT_EAST_NORTH, BorderLayout.NORTH);
		JP_NORT_EAST_NORTH.setLayout(new FlowLayout(FlowLayout.LEFT, 6, 0));

		lblNewLabel = new JLabel("Fecha:");
		JP_NORT_EAST_NORTH.add(lblNewLabel);

		JTF_date = new JTextField();
		JTF_date.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| (c == '/'))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JTF_date.setOpaque(false);
		JTF_date.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		JP_NORT_EAST_NORTH.add(JTF_date);
		JTF_date.setColumns(10);

		lblNewLabel_1 = new JLabel("No. factura");
		JP_NORT_EAST_NORTH.add(lblNewLabel_1);

		JTF_num = new JTextField();
		JTF_num.setText(""+MySQLConnection.getLastBillId(2));
		JTF_num.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JTF_num.setOpaque(false);
		JTF_num.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		JP_NORT_EAST_NORTH.add(JTF_num);
		JTF_num.setColumns(10);

		Calendar c1 = Calendar.getInstance();
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(c1.get(Calendar.YEAR));

		JTF_date.setText(dia + "/" + mes + "/" + annio);

		JP_NORT_EAST_CENTER = new JPanel();
		JP_NORT_EAST_CENTER.setBorder(BorderFactory.createLineBorder(new Color(196, 196, 196)));
		JP_NORT_EAST.add(JP_NORT_EAST_CENTER, BorderLayout.CENTER);
		JP_NORT_EAST_CENTER.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Cliente");
		lblNewLabel_5.setBounds(10, 23, 46, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Cedula");
		lblNewLabel_6.setBounds(10, 48, 46, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Telefono");
		lblNewLabel_7.setBounds(10, 73, 53, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_7);

		JTF_client = new JTextField();
		JTF_client.setBounds(73, 20, 200, 20);
		JP_NORT_EAST_CENTER.add(JTF_client);
		JTF_client.setColumns(30);

		JTF_cc = new JTextField();
		JTF_cc.setText("0");
		JTF_cc.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JTF_cc.setBounds(73, 45, 86, 20);
		JP_NORT_EAST_CENTER.add(JTF_cc);
		JTF_cc.setColumns(10);

		JTF_phone = new JTextField();
		JTF_phone.setText("0");
		JTF_phone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JTF_phone.setBounds(73, 70, 86, 20);
		JP_NORT_EAST_CENTER.add(JTF_phone);
		JTF_phone.setColumns(10);

		JP_NORTH_CENTER = new JPanel();
		JP_NORT.add(JP_NORTH_CENTER, BorderLayout.CENTER);
		JP_NORTH_CENTER.setLayout(null);

		lblNewLabel_2 = new JLabel(" Cantidad");
		lblNewLabel_2.setBounds(0, 94, 70, 14);
		JP_NORTH_CENTER.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel(" Descuento");
		lblNewLabel_3.setBounds(0, 119, 70, 14);
		JP_NORTH_CENTER.add(lblNewLabel_3);

		JTF_mount = new JTextField();
		JTF_mount.setText("1");
		JTF_mount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JTF_mount.setBounds(80, 94, 86, 20);
		JP_NORTH_CENTER.add(JTF_mount);
		JTF_mount.setColumns(10);

		JTF_discount = new JTextField();
		JTF_discount.setText("0");
		JTF_discount.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
				}
			}
		});
		JTF_discount.setBounds(80, 119, 86, 20);
		JP_NORTH_CENTER.add(JTF_discount);
		JTF_discount.setColumns(10);
		
		JP_SOUTH = new JPanel();
		add(JP_SOUTH, BorderLayout.SOUTH);
		
		JL_total = new JLabel("Total: 0");
		JP_SOUTH.add(JL_total);
		
		JB_bill = new JButton("Facturar");
		JB_bill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				doBill();
			}
		});
		JP_SOUTH.add(JB_bill);

	}
	
	public void doBill(){		
		MySQLConnection.insertTransaction(JTF_num.getText(), "2", JTF_cc.getText(), "", ""+totalPrice, JTF_date.getText(), JTF_client.getText(), JTF_phone.getText(), bigTable.getItems());
	}
	
	public void calculate(){
		totalPrice = bigTable.getValue();
		JL_total.setText("Total: " + totalPrice);
	}

	public void addRow(String id) {
		for (String[] s : data) {
			if (s[0].equals(id)) {
				int total = Integer.parseInt(s[3]) * Integer.parseInt(JTF_mount.getText()) - Integer.parseInt(JTF_discount.getText());
				bigTable.addRow(
						new String[] { s[0], s[1], JTF_mount.getText(), s[3], "" + total, JTF_discount.getText() });
				break;
			}
		}
		calculate();
	}
}
