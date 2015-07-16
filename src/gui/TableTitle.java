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
	private static final long serialVersionUID = -7812049790984436067L;

	public TableTitle(String v1, String v2, String v3) {
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
		
		JPanel panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));
		
		JLabel JL_1 = new JLabel(v1);
		JL_1.setOpaque(false);
		JL_1.setPreferredSize(new Dimension(440, height));
		panel_CENTER.add(JL_1, BorderLayout.WEST);
		
		JPanel panel_CENTER_CENTE = new JPanel();
		panel_CENTER_CENTE.setBorder(new EmptyBorder(0, 0, 0, 50));
		panel_CENTER_CENTE.setOpaque(false);
		panel_CENTER.add(panel_CENTER_CENTE, BorderLayout.CENTER);
		panel_CENTER_CENTE.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel JL_2 = new JLabel(v2);
		JL_2.setHorizontalAlignment(SwingConstants.CENTER);
		JL_2.setOpaque(false);
		panel_CENTER_CENTE.add(JL_2);

		JLabel JL_3 = new JLabel(v3);
		JL_3.setHorizontalAlignment(SwingConstants.CENTER);
		JL_3.setOpaque(false);
		panel_CENTER_CENTE.add(JL_3 );
		
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
	}
}

















