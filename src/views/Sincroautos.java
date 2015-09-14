package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import db.MySQLConnection;
import gui.Table;
import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Sincroautos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5503097647174550320L;
	private JComboBox<Item> JCB_employee , JCB_tipo;
	JEditorPane EP_trabajo, EP_valor;
	private int totalPrice = 0;
	private Table bigTable;
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
	private JPanel JP_NORT_WEST;
	private JPanel PanelTrabajos;
	private JLabel lblTrabajo;
	private JLabel lblNewLabel_9;
	private JPanel JP_NORT_CENTER;
	JRadioButton RB_pagado;
	
	
	public void createTable(){
		String[] title = { "Descripción", "Empleado", "Tipo", "Precio"};
		boolean search = false;
		boolean advanceIcons = false;
		boolean navigation = false;
		int limitedRows = 15;

		bigTable = new Table(this, "sincroautos", null, title, navigation, search, advanceIcons, limitedRows);
	}
	
	public Sincroautos() {

		setLayout(new BorderLayout(0, 0));

		createTable();
		add(bigTable, BorderLayout.CENTER);
		
		JPanel JP_NORT = new JPanel();
		add(JP_NORT, BorderLayout.NORTH);
		JP_NORT.setLayout(new BorderLayout(0, 0));
		JP_NORT.setBorder(new EmptyBorder(6, 6, 0, 6));
		
		JP_NORT_WEST = new JPanel();
		JP_NORT.add(JP_NORT_WEST, BorderLayout.WEST);
		JP_NORT_WEST.setMinimumSize(new Dimension(300, 135));
		JP_NORT_WEST.setPreferredSize(new Dimension(300, 135));
		JP_NORT_WEST.setLayout(new BorderLayout(0, 0));
		
		PanelTrabajos = new JPanel();
		JP_NORT_WEST.add(PanelTrabajos, BorderLayout.CENTER);
		PanelTrabajos.setLayout(null);
		
		lblTrabajo = new JLabel("Descripci\u00F3n:");
		lblTrabajo.setBounds(10, 68, 93, 14);
		PanelTrabajos.add(lblTrabajo);
		
		EP_trabajo = new JEditorPane();
		EP_trabajo.setBorder(new MatteBorder(1, 1, 1, 1, new Color(196,196,196)));
		EP_trabajo.setBounds(95, 68, 201, 40);
		PanelTrabajos.add(EP_trabajo);
		
		EP_valor = new JEditorPane();
		EP_valor.setBorder(new MatteBorder(1, 1, 1, 1, new Color(196,196,196)));
		EP_valor.setBounds(95, 114, 106, 20);
		PanelTrabajos.add(EP_valor);
		
		lblNewLabel_9 = new JLabel("Precio:");
		lblNewLabel_9.setBounds(10, 114, 46, 14);
		PanelTrabajos.add(lblNewLabel_9);
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Item Emplo = (Item) JCB_employee.getSelectedItem();
				Item Type = (Item) JCB_tipo.getSelectedItem();
				bigTable.addRow(
						new String[] {Emplo.getId(), EP_trabajo.getText(), Emplo.toString(), Type.toString(),  EP_valor.getText()}, ""+ UUID.randomUUID());
				EP_trabajo.setText("");
				EP_valor.setText("");
				calculate();	
			}
		});
		btnNewButton.setBounds(216, 114, 80, 20);
		PanelTrabajos.add(btnNewButton);
		
		JLabel lblEmpleado = new JLabel("Empleado:");
		lblEmpleado.setBounds(10, 45, 93, 14);
		PanelTrabajos.add(lblEmpleado);
		
		JCB_employee = new JComboBox<Item>();			
		JCB_employee.setBounds(95, 42, 201, 20);
		PanelTrabajos.add(JCB_employee);
		
		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setBounds(10, 20, 93, 14);
		PanelTrabajos.add(lblTipo);
		
		 JCB_tipo = new JComboBox<Item>();
		Item type1 = new Item("0", "Trabajo");
		Item type2 = new Item("1", "Scaneado");		
		JCB_tipo.addItem(type1);
		JCB_tipo.addItem(type2);	
		JCB_tipo.setBounds(95, 17, 201, 20);
		PanelTrabajos.add(JCB_tipo);

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
		JTF_num.setText(""+MySQLConnection.getLastBillI(1));
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
		
		JP_NORT_CENTER = new JPanel();
		JP_NORT.add(JP_NORT_CENTER, BorderLayout.CENTER);
		JP_NORT_CENTER.setLayout(null);
		
		JP_SOUTH = new JPanel();
		add(JP_SOUTH, BorderLayout.SOUTH);
		JP_SOUTH.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JB_bill = new JButton("Facturar");
		JB_bill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				doBill();
			}
		});
		
		JL_total = new JLabel("Total: 0");
		JP_SOUTH.add(JL_total);
		JL_total.setHorizontalAlignment(SwingConstants.CENTER);
		JL_total.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JP_SOUTH.add(JB_bill);
		
		RB_pagado = new JRadioButton("Pagado");
		RB_pagado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		JP_SOUTH.add(RB_pagado);
		
		ArrayList<String[]> temporal = MySQLConnection.getEmployees();
		
		for (String[] ms : temporal){
			Item abce = new Item(ms[0], ms[1]);
			JCB_employee.addItem(abce);
		}
	}
	
	public void doBill(){	
		
		System.out.println("Bill");
		
		String status = "Debiendo";
		if(RB_pagado.isSelected()){
			status = "Pagado";
		}
		
		boolean result = MySQLConnection.insertTransaction(JTF_num.getText(), "1", JTF_cc.getText(), JTF_plate.getText(), ""+totalPrice, JTF_date.getText(), JTF_client.getText(), JTF_phone.getText(),  bigTable.getItems(), status);

		if(result){
			String message = "¿Desea imprimir la factura?";
			String title = "Factura hecha correctamente";
			// display the JOptionPane showConfirmDialog
			int reply = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				EP_trabajo.setText("");
				EP_valor.setText("");
				JTF_cc.setText("0");
				JTF_client.setText("");
				JTF_phone.setText("0");
				JTF_plate.setText("");
				int nnum = Integer.parseInt(JTF_num.getText().trim()) + 1;
				JTF_num.setText("" + nnum);
				bigTable.removeItems();
			}
		}
	}
	
	public void calculate(){
		totalPrice = bigTable.getValue();
		JL_total.setText("Total: " + totalPrice);
	}
	
	public class Item {
	    private String id;
	    private String description;

	    public Item(String id, String description) {
	        this.id = id;
	        this.description = description;
	    }
	    public String getId() {
	        return id;
	    }

	    public String toString() {
	        return description;
	    }
	} 
}
