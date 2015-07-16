package gui;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class Table extends JPanel {

	private static final long serialVersionUID = -2108154469027694188L;

	public Table(int n) {
		this.setBackground(Color.white);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_NORTH = new JPanel();
		panel_NORTH.setLayout(new BorderLayout(0, 0));
		add(panel_NORTH, BorderLayout.NORTH);
		panel_NORTH.add(new TableTools(), BorderLayout.NORTH);
		panel_NORTH.add(new TableTitle("Descripción", "Precio", "Cantidad"), BorderLayout.CENTER);

		JPanel panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BoxLayout(panel_CENTER, BoxLayout.Y_AXIS));

		for (int i = 0; i < n; i++) {
			TableRow tr = new TableRow(i, "producto productoproductoproductoproducto" + i,
					(int) (Math.random() * (150000 - 5000) + 1) + 5000,
					(int) (Math.random() * (200 - 10) + 1) + 10);
			panel_CENTER.add(tr);
		}

		JPanel panel_SOUTH = new JPanel();
		panel_SOUTH.setOpaque(false);
		add(panel_SOUTH, BorderLayout.SOUTH);
	}
}
