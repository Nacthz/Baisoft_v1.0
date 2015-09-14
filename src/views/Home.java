package views;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import db.MySQLConnection;
import frame.Info;
import gui.Table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;

public class Home extends JPanel {

	private static final long serialVersionUID = -6135967367365120766L;
	private boolean all = false;
	
	/**
	 * Create the panel.
	 */
	public Home() {
		setLayout(new BorderLayout(0, 0));
		Calendar c1 = Calendar.getInstance();
		
		c1.add(Calendar.MONTH, -6);
		
		String dia = Integer.toString(c1.get(Calendar.DATE));
		String mes = Integer.toString(c1.get(Calendar.MONTH) + 1);
		String annio = Integer.toString(c1.get(Calendar.YEAR));

		String numf = annio;

		if (mes.length() == 1)
			numf += "0" + mes;
		else
			numf += mes;

		if (dia.length() == 1)
			numf +="0" + dia;
		else
			numf += dia;

		 MySQLConnection.getVehiclePerDate(numf, "");
		String[] title = new String[] {"Cliente", "Telefono", "Placa", "Fecha"};	
		JPanel Table1 = new Table(this, "home", MySQLConnection.getVehiclePerDate(numf, ""), title, true, false, false, 0);
		Table1.setPreferredSize(new Dimension(550, 138));
		add(Table1, BorderLayout.WEST);
		
		
		JPanel JP_CENTER = new JPanel();
		JP_CENTER.setBackground(Color.white);
		add(JP_CENTER, BorderLayout.CENTER);
		JP_CENTER.setLayout(new BorderLayout(0, 0));
		
		JPanel JP_EAST_NORTH = new JPanel();
		JP_EAST_NORTH.setOpaque(false);
		JP_EAST_NORTH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(12, 180, 204)));
		JP_EAST_NORTH.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		JP_CENTER.add(JP_EAST_NORTH, BorderLayout.NORTH);
		
		// Buttons
		JLabel JL_sincroautos = new JLabel("Activos");
		JL_sincroautos.setBackground(Color.white);
		JLabel JL_sincrorepuestos = new JLabel("Inactivos");
		JL_sincrorepuestos.setBackground(Color.white);

		JL_sincroautos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (all) {
					select(JL_sincroautos);
					deselect(JL_sincrorepuestos);
					all = false;
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
		JL_sincroautos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_sincroautos.setOpaque(true);
		JL_sincroautos.setPreferredSize(new Dimension(90, 28));
		select(JL_sincroautos);
		JP_EAST_NORTH.add(JL_sincroautos);

		JL_sincrorepuestos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!all) {
					select(JL_sincrorepuestos);
					deselect(JL_sincroautos);
					all = true;			
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
		JL_sincrorepuestos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_sincrorepuestos.setOpaque(true);
		JL_sincrorepuestos.setPreferredSize(new Dimension(115, 28));
		deselect(JL_sincrorepuestos);
		JP_EAST_NORTH.add(JL_sincrorepuestos);
	}
	
	public void view(String lin_pla){
		ArrayList<String[]> result = MySQLConnection.getVehicleInfos(lin_pla);
		
		final Info inf = new Info("Historia para: " + lin_pla, result, "home");
		inf.setVisible(true);
	}
	
	public void select(JComponent o) {
		o.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 180, 204)));
		o.setForeground(new Color(2, 34, 34));
	}

	public void deselect(JComponent o) {
		o.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		o.setForeground(new Color(68, 68, 68));
	}

}
