package views;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

import gui.Table;

import java.awt.BorderLayout;

public class Inventory extends JPanel {

	private static final long serialVersionUID = 1721860207718285215L;

	public Inventory() {
		setLayout(new BorderLayout(0, 0));

		JPanel JP_NORTH = new JPanel();
		JP_NORTH.setBackground(new Color(242, 0, 242));
		add(JP_NORTH, BorderLayout.NORTH);

		JPanel JP_CENTER = new Table(19);
		add(JP_CENTER, BorderLayout.CENTER);
	}

	public void setTrueSize(Component o, int a, int b) {
		o.setSize(a, b);
		o.setMinimumSize(o.getSize());
		o.setMinimumSize(o.getSize());
		o.setPreferredSize(o.getSize());
	}
}
