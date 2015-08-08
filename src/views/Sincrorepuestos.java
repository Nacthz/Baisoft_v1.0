package views;

import javax.swing.JPanel;

import gui.Table;
import java.awt.BorderLayout;
import java.util.ArrayList;

public class Sincrorepuestos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2944687387171442917L;

	/**
	 * Create the panel.
	 */
	public Sincrorepuestos() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		String[] title = { "Descripcion", "Cantidad", "Precio", "Descuento" };
		ArrayList<String[]> data = new ArrayList<String[]>();
		boolean search = false;
		boolean advanceIcons = false;
		boolean navigation = false;
		int limitedRows = 5;
		add(new Table("sincrorepuestos", data, title, navigation, search, advanceIcons, limitedRows), BorderLayout.CENTER);

	}

}
