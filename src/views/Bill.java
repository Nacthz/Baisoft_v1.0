package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import db.MySQLConnection;
import gui.Table;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Bill extends JPanel {
	private JPanel table;
	private static final long serialVersionUID = 1721860207718285215L;

	public Bill() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		JPanel JP_NORTH = new JPanel();
		JP_NORTH.setOpaque(false);
		JP_NORTH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(12, 180, 204)));
		add(JP_NORTH, BorderLayout.NORTH);
		JP_NORTH.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		table = new JPanel();
		table.setOpaque(false);
		add(table, BorderLayout.CENTER);
		table.setLayout(new GridLayout(1, 0, 0, 0));
		
		table.add(sincroautos());
		
		// Buttons
		JLabel JL_sincroautos = new JLabel("Sincroautos");
		JL_sincroautos.setBackground(Color.white);
		JLabel JL_sincrorepuestos = new JLabel("Sincrorepuestos");
		JL_sincrorepuestos.setBackground(Color.white);
		JLabel JL_empleados = new JLabel("Empleados");
		JL_empleados.setBackground(Color.white);

		JL_sincroautos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					select(JL_sincroautos);
					deselect(JL_sincrorepuestos);
					deselect(JL_empleados);
					table.add(sincroautos());
					table.revalidate();
					table.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JL_sincroautos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_sincroautos.setOpaque(true);
		JL_sincroautos.setPreferredSize(new Dimension(90, 28));
		select(JL_sincroautos);
		JP_NORTH.add(JL_sincroautos);

		JL_sincrorepuestos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					select(JL_sincrorepuestos);
					deselect(JL_sincroautos);
					deselect(JL_empleados);
					table.add(sincrorepuestos());
					table.revalidate();
					table.repaint();					
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JL_sincrorepuestos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_sincrorepuestos.setOpaque(true);
		JL_sincrorepuestos.setPreferredSize(new Dimension(115, 28));
		deselect(JL_sincrorepuestos);
		JP_NORTH.add(JL_sincrorepuestos);
		
		JL_empleados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
					select(JL_empleados);
					deselect(JL_sincroautos);
					deselect(JL_sincrorepuestos);
					table.add(empleados());
					table.revalidate();
					table.repaint();					
			}

			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JL_empleados.setHorizontalAlignment(SwingConstants.CENTER);
		JL_empleados.setOpaque(true);
		JL_empleados.setPreferredSize(new Dimension(80, 28));
		deselect(JL_empleados);
		JP_NORTH.add(JL_empleados);
	}

	public void select(JComponent o) {
		o.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 180, 204)));
		o.setForeground(new Color(2, 34, 34));
	}

	public void deselect(JComponent o) {
		o.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		o.setForeground(new Color(68, 68, 68));
	}

	public void setTrueSize(Component o, int a, int b) {
		o.setSize(a, b);
		o.setMinimumSize(o.getSize());
		o.setMinimumSize(o.getSize());
		o.setPreferredSize(o.getSize());
	}
	
	public Table sincrorepuestos(){
		table.removeAll();
		String[] title = {"Cliente", "Cedula", "Celular", "Factura", "Fecha", "Estado", "Valor"};
		ArrayList<String[]> data = MySQLConnection.getBillSincrorepuestos();

		boolean search = true;
		boolean advanceIcons = true;
		boolean navigation = true;
		int limitedRows = 0;
		return new Table(this, "Bill_2", data, title, navigation, search, advanceIcons, limitedRows);
	}
	
	public Table sincroautos(){
		table.removeAll();
		String[] title = { "Cliente", "Placa", "Cedula", "Celular", "Factura", "Fecha", "Estado", "Valor"};
		ArrayList<String[]> data = MySQLConnection.getBillSincroautos();


		boolean search = true;
		boolean advanceIcons = true;
		boolean navigation = true;
		int limitedRows = 0;
		return new Table(this, "Bill_1", data, title, navigation, search, advanceIcons, limitedRows);
	}
	
	
	public Table empleados(){
		table.removeAll();
		String[] title = { "Empleado", "Descripción", "Factura", "Fecha", "Tipo", "Valor"};
		ArrayList<String[]> data = MySQLConnection.getEmployeesBill();

		boolean search = true;
		boolean advanceIcons = false;
		boolean navigation = true;
		int limitedRows = 0;
		return new Table(this, "Employee_bill", data, title, navigation, search, advanceIcons, limitedRows);
	}
}