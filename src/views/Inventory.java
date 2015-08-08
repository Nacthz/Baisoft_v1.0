package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

public class Inventory extends JPanel {

	private boolean all = false;
	private JPanel JP_CENTER;
	private static final long serialVersionUID = 1721860207718285215L;

	public Inventory() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		JPanel JP_NORTH = new JPanel();
		JP_NORTH.setOpaque(false);
		JP_NORTH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(12, 180, 204)));
		add(JP_NORTH, BorderLayout.NORTH);
		JP_NORTH.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		JP_CENTER = new JPanel();
		JP_CENTER.setLayout(new GridLayout(1, 0, 0, 0));
		add(JP_CENTER, BorderLayout.CENTER);

		// Crear Tabla
		putInventory();
		// Fin Crear Tabla

		// Buttons
		JLabel JL_activos = new JLabel("Activos");
		JL_activos.setBackground(Color.white);
		JLabel JL_todos = new JLabel("Todos");
		JL_todos.setBackground(Color.white);

		JL_activos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (all) {
					select(JL_activos);
					deselect(JL_todos);
					all = false;

					putInventory();
				}
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
		JL_activos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_activos.setOpaque(true);
		JL_activos.setPreferredSize(new Dimension(64, 28));
		select(JL_activos);
		JP_NORTH.add(JL_activos);

		JL_todos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!all) {
					select(JL_todos);
					deselect(JL_activos);
					all = true;

					putInventoryAll();
				}
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
		JL_todos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_todos.setOpaque(true);
		JL_todos.setPreferredSize(new Dimension(64, 28));
		deselect(JL_todos);
		JP_NORTH.add(JL_todos);

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

	public void putInventory() {
		boolean all = false;
		boolean search = true;
		boolean advanceIcons = false;
		boolean navigation = true;
		int limitedRows = 0;
		String[] title = { "Descripción", "Precio de compra", "Precio de venta", "Cantidad" };
		ArrayList<String[]> data = MySQLConnection.getInventory(all);
		JPanel table = new Table("inventory", data, title, navigation, search, advanceIcons, limitedRows);
		JP_CENTER.removeAll();
		JP_CENTER.add(table, BorderLayout.CENTER);
		JP_CENTER.revalidate();
		JP_CENTER.repaint();
	}
	
	public void putInventoryAll() {
		boolean all = true;		
		boolean search = true;
		boolean advanceIcons = false;
		boolean navigation = true;
		int limitedRows = 0;
		String[] title = { "Descripción", "Estado", "Precio de compra", "Precio de venta", "Cantidad" };
		ArrayList<String[]> data = MySQLConnection.getInventory(all);
		JPanel table = new Table("inventory_all", data, title, navigation, search, advanceIcons, limitedRows);
		JP_CENTER.removeAll();
		JP_CENTER.add(table, BorderLayout.CENTER);
		JP_CENTER.revalidate();
		JP_CENTER.repaint();
	}
	
	public void deleteItem(int id){
		
	}
}
