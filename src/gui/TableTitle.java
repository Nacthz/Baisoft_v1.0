package gui;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TableTitle extends JPanel {
	private int height = 30;
	private boolean actual = true;
	private JLabel actualSelect = null;
	private static final long serialVersionUID = -7812049790984436067L;
	JPanel panel_CENTER_CENTE, panel_CENTER;

	public TableTitle(String[] title, Table father) {
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
		check.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(check.isSelected()){
					father.setSelected(true);
				}else{
					father.setSelected(false);
				}
			}
		});
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
		
		setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(12, 180, 204)));
		
		int j = 0;
		for(String s_title : title){
			j++;
			addTitle(j, s_title, father);
		}
	}
	
	public void addTitle(int id, String name, Table father){
		JLabel JL = new JLabel(name);
		JL.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(actualSelect != JL){
					father.searchOn(id);
					deselect(actualSelect);
					actualSelect = JL;
					select(JL);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JL.setOpaque(false);
		
		if(actual){
			JL.setPreferredSize(new Dimension(230, height));
			select(JL);
			actualSelect = JL;
			panel_CENTER.add(JL, BorderLayout.WEST);
			actual=false;
			return;
		}		
		JL.setHorizontalAlignment(SwingConstants.CENTER);
		panel_CENTER_CENTE.add(JL);
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

















