package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

public class TableRow extends JPanel {

	private int height = 25;
	private boolean edit, first = true;
	private static final long serialVersionUID = 6359330881452231410L;
	private JLabel id;
	private JCheckBox check;
	private JPanel panel_CENTER_CENTE, panel_CENTER;
	private ArrayList<JTextComponent> info = new ArrayList<JTextComponent>();

	public TableRow(String[] data) {
		addMouseListener(mouseEvent());
		setLayout(new BorderLayout(0, 0));
		setBackground(new Color(255, 255, 255));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, height));
		setMinimumSize(new Dimension(700, height));

		JPanel panel_WEST = new JPanel();
		panel_WEST.setOpaque(false);
		panel_WEST.setBorder(new EmptyBorder(0, 10, 0, 0));
		add(panel_WEST, BorderLayout.WEST);
		panel_WEST.setLayout(new BorderLayout(0, 0));

		check = new JCheckBox();
		check.addMouseListener(mouseEvent());
		check.setOpaque(false);
		panel_WEST.add(check, BorderLayout.WEST);

		id = new JLabel(data[0]);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setPreferredSize(new Dimension(35, height));
		panel_WEST.add(id, BorderLayout.CENTER);

		JLabel line_SOUTH = new JLabel();
		line_SOUTH.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line_SOUTH.setBackground(new Color(220, 220, 220));
		line_SOUTH.setOpaque(true);
		add(line_SOUTH, BorderLayout.SOUTH);

		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));

		panel_CENTER_CENTE = new JPanel();
		panel_CENTER_CENTE.setOpaque(false);
		panel_CENTER.add(panel_CENTER_CENTE, BorderLayout.CENTER);
		panel_CENTER_CENTE.setLayout(new GridLayout(1, 0, 0, 0));
		
		id.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JPanel panel_EAST = new JPanel();
		panel_EAST.setBorder(new EmptyBorder(0, 0, 0, 10));
		panel_EAST.setOpaque(false);
		panel_EAST.setPreferredSize(new Dimension(50, height));
		add(panel_EAST, BorderLayout.EAST);
		panel_EAST.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel JL_delete = new JLabel(new ImageIcon("img/trash.png"));
		JL_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				doDelete();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				if (!edit)
					setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (!edit)
					setBackground(new Color(255, 255, 255));
			}
		});
		JL_delete.setOpaque(false);
		
		JLabel JL_edit = new JLabel(new ImageIcon("img/pencil.png"));
		JL_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				edit = !edit;
				
				if (edit){
					for(JTextComponent ob : info){
						ob.setFont(new Font("Tahoma", Font.PLAIN, 16));
					}
					JL_edit.setIcon(new ImageIcon("img/tick.png"));
					JL_delete.setIcon(new ImageIcon("img/cross.png"));
					
				}else{
					for(JTextComponent ob : info){
						ob.setFont(new Font("Tahoma", Font.PLAIN, 12));
					}
					JL_edit.setIcon(new ImageIcon("img/pencil.png"));
					JL_delete.setIcon(new ImageIcon("img/trash.png"));
				}
				for(JTextComponent ob : info){
					ob.setEditable(edit);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				if (!edit)
					setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				if (!edit)
					setBackground(new Color(255, 255, 255));
			}
		});
		
		for(int i = 1; i < data.length; i++){
			addCamp(data[i]);
		}
		
		panel_EAST.add(JL_edit);
		panel_EAST.add(JL_delete);
	}

	public void addCamp(String name){
		JTextField JTF = new JTextField(name);
		JTF.addMouseListener(mouseEvent());
		JTF.setOpaque(false);
		JTF.setBorder(null);
		JTF.setEditable(false);
		JTF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		info.add(JTF);
		
		if(first){
			JTF.setPreferredSize(new Dimension(360, height));
			panel_CENTER.add(JTF, BorderLayout.WEST);
			first = false;
			return;
		}
		
		JTF.setHorizontalAlignment(SwingConstants.CENTER);
		panel_CENTER_CENTE.add(JTF);
	}
	
	public MouseAdapter mouseEvent() {
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (!edit)
					setBackground(new Color(230, 230, 230));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!edit)
					setBackground(new Color(255, 255, 255));
			}
		};
	}
	
	public void setSelected(boolean status){
		check.setSelected(status);
	}
	
	public void doDelete(){
		System.out.println("Eliminado");
	}
	
	public boolean isSelected(){
		return check.isSelected();
	}
}