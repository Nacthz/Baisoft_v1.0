package gui;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class TableTitle extends JPanel {
	private int height = 30;
	private boolean actual = true;
	private static final long serialVersionUID = -7812049790984436067L;
	JPanel panel_CENTER_CENTE, panel_CENTER;

	public TableTitle(String[] title) {
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(248, 248, 248));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		setMinimumSize(new Dimension(700, height));

		JPanel panel_WEST = new JPanel();
		panel_WEST.setBorder(new EmptyBorder(0, 10, 0, 0));
		panel_WEST.setPreferredSize(new Dimension(66, height));
		panel_WEST.setOpaque(false);
		add(panel_WEST, BorderLayout.WEST);
		panel_WEST.setLayout(new BorderLayout(0, 0));

		JCheckBox check = new JCheckBox();
		check.setOpaque(false);
		panel_WEST.add(check, BorderLayout.WEST);
		
		JLabel id = new JLabel("ID");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setPreferredSize(new Dimension(35, height));
		panel_WEST.add(id, BorderLayout.CENTER);
		
		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));
		
		panel_CENTER_CENTE = new JPanel();
		panel_CENTER_CENTE.setBorder(new EmptyBorder(0, 0, 0, 50));
		panel_CENTER_CENTE.setOpaque(false);
		panel_CENTER.add(panel_CENTER_CENTE, BorderLayout.CENTER);
		panel_CENTER_CENTE.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel line_NORTH = new JLabel();
		line_NORTH.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line_NORTH.setBackground(new Color(196, 196, 196));
		line_NORTH.setOpaque(true);
		add(line_NORTH, BorderLayout.NORTH);
		
		JLabel line_SOUTH = new JLabel();
		line_SOUTH.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line_SOUTH.setBackground(new Color(196, 196, 196));
		line_SOUTH.setOpaque(true);
		add(line_SOUTH, BorderLayout.SOUTH);
		
		for(String s_title : title){
			addTitle(s_title);
		}
	}
	
	public void addTitle(String name){
		JLabel JL = new JLabel(name);
		JL.setOpaque(false);
		
		if(actual){
			JL.setPreferredSize(new Dimension(360, height));
			panel_CENTER.add(JL, BorderLayout.WEST);
			actual=false;
			return;
		}		
		JL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_CENTER_CENTE.add(JL);
	}
}

















