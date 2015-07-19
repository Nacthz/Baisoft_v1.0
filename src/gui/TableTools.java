package gui;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableTools extends JPanel {
	boolean all = false;
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

		//Buttons
		JLabel JL_activos = new JLabel("Activos");
		JLabel JL_todos = new JLabel("Todos");
		
		JL_activos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(all){
					select(JL_activos);
					deselect(JL_todos);
					all=false;
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
		panell_SOUTH_WEST.add(JL_activos);

		JL_todos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!all){
					select(JL_todos);
					deselect(JL_activos);
					all=true;
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
		panell_SOUTH_WEST.add(JL_todos);
	}
	
	public void select(JComponent o){
		o.setBackground(new Color(180, 229, 106));
		o.setBorder(BorderFactory.createLineBorder(new Color(116, 178, 30)));
		o.setForeground(new Color(61, 93, 24));
	}
	
	public void deselect(JComponent o){
		o.setBackground(new Color(242, 242, 242));
		o.setBorder(BorderFactory.createLineBorder(new Color(213, 213, 213)));
		o.setForeground(new Color(68, 68, 68));
	}
}
