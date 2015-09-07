package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import db.MySQLConnection;
import gui.Table;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sincroautos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5503097647174550320L;
	private JLabel JLB_employee;
	private int totalPrice = 0;
	private Table smallTable, bigTable;
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
	private JPanel JP_SOUTH;
	private JLabel JL_total;
	private JButton JB_bill;
	private JTextField JTF_plate;
	private JLabel lblNewLabel_8;
	private JPanel JP_WEST;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel lblTrabajo;
	private JLabel lblNewLabel_9;
	private String employee_id; 
	
	public Sincroautos() {

		setLayout(new BorderLayout(0, 0));

		String[] title = { "Trabajo", "Precio"};
		boolean search = false;
		boolean advanceIcons = false;
		boolean navigation = false;
		int limitedRows = 15;

		bigTable = new Table(this, "sincroautos", null, title, navigation, search, advanceIcons, limitedRows);
		add(bigTable, BorderLayout.CENTER);

		smallTable = new Table(this, "sincroautos_employee", MySQLConnection.getEmployees(), null, true, false, advanceIcons, 0);
		
		JPanel JP_NORT = new JPanel();
		add(JP_NORT, BorderLayout.NORTH);
		JP_NORT.setLayout(new BorderLayout(0, 0));
		JP_NORT.setBorder(new EmptyBorder(6, 6, 0, 6));

		smallTable.setPreferredSize(new Dimension(400, 138));
		smallTable.setBorder(BorderFactory.createLineBorder(new Color(196, 196, 196)));
		JP_NORT.add(smallTable, BorderLayout.WEST);
		
		lblNewLabel_8 = new JLabel("Empleado");
		lblNewLabel_8.setBorder(BorderFactory.createEmptyBorder(5,10,5,10));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.LEFT);
		smallTable.add(lblNewLabel_8, BorderLayout.NORTH);

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
		JTF_num.setText(""+MySQLConnection.getLastBillId(1));
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
		lblNewLabel_5.setBounds(10, 39, 46, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Cedula");
		lblNewLabel_6.setBounds(10, 64, 46, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Telefono");
		lblNewLabel_7.setBounds(10, 89, 53, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_7);
		
		JLabel lblNewLabel_4 = new JLabel("Placa");
		lblNewLabel_4.setBounds(10, 14, 46, 14);
		JP_NORT_EAST_CENTER.add(lblNewLabel_4);

		JTF_client = new JTextField();
		JTF_client.setBounds(73, 36, 200, 20);
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
		JTF_cc.setBounds(73, 61, 86, 20);
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
		JTF_phone.setBounds(73, 86, 86, 20);
		JP_NORT_EAST_CENTER.add(JTF_phone);
		JTF_phone.setColumns(10);
		
		JTF_plate = new JTextField();
		JTF_plate.setBounds(73, 11, 86, 20);
		JP_NORT_EAST_CENTER.add(JTF_plate);
		JTF_plate.setColumns(10);
		
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
		
		JP_WEST = new JPanel();
		JP_WEST.setMinimumSize(new Dimension(300, 100));
		JP_WEST.setPreferredSize(new Dimension(300, 100));
		add(JP_WEST, BorderLayout.WEST);
		JP_WEST.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel.setMinimumSize(new Dimension(300, 6));
		panel.setPreferredSize(new Dimension(300, 6));
		JP_WEST.add(panel, BorderLayout.NORTH);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(0, 0, 0, 1, new Color(196,196,196)));
		JP_WEST.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		lblTrabajo = new JLabel("Trabajo");
		lblTrabajo.setBounds(10, 44, 46, 14);
		panel_1.add(lblTrabajo);
		
		JEditorPane EP_trabajo = new JEditorPane();
		EP_trabajo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(196,196,196)));
		EP_trabajo.setBounds(10, 62, 280, 40);
		panel_1.add(EP_trabajo);
		
		JEditorPane EP_valor = new JEditorPane();
		EP_valor.setBorder(new MatteBorder(1, 1, 1, 1, new Color(196,196,196)));
		EP_valor.setBounds(55, 109, 106, 20);
		panel_1.add(EP_valor);
		
		lblNewLabel_9 = new JLabel("Precio:");
		lblNewLabel_9.setBounds(10, 112, 46, 14);
		panel_1.add(lblNewLabel_9);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bigTable.addRow(
						new String[] {"", EP_trabajo.getText(), EP_valor.getText()}, ""+ UUID.randomUUID());
				EP_trabajo.setText("");
				EP_valor.setText("");
				calculate();
			}
		});
		btnNewButton.setBounds(171, 109, 80, 20);
		panel_1.add(btnNewButton);
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setBounds(10, 11, 69, 14);
		panel_1.add(lblEmpleado);
		
		JLB_employee = new JLabel("");
		JLB_employee.setBounds(74, 11, 216, 14);
		panel_1.add(JLB_employee);
		

	}
	
	public void selectEmployee(String id, String name){
		employee_id = id;
		JLB_employee.setText(name);
	}
	
	public void doBill(){	
		MySQLConnection.insertTransaction(JTF_num.getText(), "1", JTF_cc.getText(), JTF_plate.getText(), ""+totalPrice, JTF_date.getText(), JTF_client.getText(), JTF_phone.getText(), employee_id,  bigTable.getItems());
	}
	
	public void calculate(){
		totalPrice = bigTable.getValue();
		JL_total.setText("Total: " + totalPrice);
	}
}
