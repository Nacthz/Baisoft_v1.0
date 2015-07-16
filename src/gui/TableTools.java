package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class TableTools extends JPanel {

	private int height = 70;
	private static final long serialVersionUID = 2536822512129726654L;

	public TableTools() {
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(242, 242, 242));
		setPreferredSize(new Dimension(700, height));

		JPanel panel_NORTH = new JPanel();
		panel_NORTH.setOpaque(false);
		add(panel_NORTH, BorderLayout.NORTH);

		JPanel panel_SOUTH = new JPanel();
		panel_SOUTH.setOpaque(false);
		add(panel_SOUTH, BorderLayout.SOUTH);
		panel_SOUTH.setLayout(new BorderLayout(0, 0));

		JPanel panell_SOUTH_WEST = new JPanel();
		panell_SOUTH_WEST.setOpaque(false);
		panel_SOUTH.add(panell_SOUTH_WEST, BorderLayout.WEST);
		panell_SOUTH_WEST.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel JL_activos = new JLabel("Activos");
		JL_activos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_activos.setOpaque(true);
		JL_activos.setPreferredSize(new Dimension(64, 28));
		select(JL_activos);
		panell_SOUTH_WEST.add(JL_activos);
		
		JLabel JL_todos = new JLabel("Todos");
		JL_todos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_todos.setOpaque(true);
		JL_todos.setPreferredSize(new Dimension(64, 28));
		deselect(JL_todos);
		panell_SOUTH_WEST.add(JL_todos);
	}
	
	public void select(JComponent o){
		o.setBackground(new Color(115, 213, 213));
		o.setBorder(BorderFactory.createLineBorder(new Color(0, 171, 227)));
	}
	
	public void deselect(JComponent o){
		o.setBackground(Color.white);
		o.setBorder(BorderFactory.createLineBorder(new Color(187, 187, 187)));
	}
}
