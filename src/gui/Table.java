package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table extends JPanel {

	private boolean navigation = false;
	private int page = 1;
	private int max, heigth = 490;
	private int actual = 0;
	private JLabel cantInfo;
	private static final long serialVersionUID = -2108154469027694188L;
	private ArrayList<TableRow> rows = new ArrayList<TableRow>();

	public boolean isFocusable() {
		return true;
	}

	public Table(int n, ArrayList<String[]> data, String[] title, String[] complements) {
		this.setBackground(Color.white);
		setLayout(new BorderLayout(0, 0));

		JPanel panel_NORTH = new JPanel();
		panel_NORTH.setLayout(new BorderLayout(0, 0));
		add(panel_NORTH, BorderLayout.NORTH);
		panel_NORTH.add(new TableTools(), BorderLayout.NORTH);
		panel_NORTH.add(new TableTitle(title), BorderLayout.CENTER);

		JPanel panel_CENTER = new JPanel();
		panel_CENTER.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				int newCant = panel_CENTER.getHeight() / 25;
				if (max < newCant && actual < data.size()) {
					max = newCant;
					TableRow tr = new TableRow(data.get(actual));
					actual++;
					rows.add(tr);
					panel_CENTER.add(tr);
					
					if(navigation){
						cantInfo.setText(page + " de " + Math.round(data.size()/max));
					}
				} else if (actual > newCant) {
					actual--;
					panel_CENTER.remove(rows.get(actual));
					rows.remove(actual);
					max = newCant;
					if(navigation){
						cantInfo.setText(page + " de " + Math.round(data.size()/max));
					}
				}
				
				
			}
		});
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BoxLayout(panel_CENTER, BoxLayout.Y_AXIS));

		max = heigth / 25;

		for (int i = 0; i < max && i < data.size(); i++) {
			TableRow tr = new TableRow(data.get(i));
			rows.add(tr);
			panel_CENTER.add(tr);
			actual++;
		}
		
		for(String s : complements){
			if(s.equals("navigation")){
				addbuttons();
				cantInfo.setText(page + " de " + Math.round(data.size()/max));
			}
		}
	}
	
	public void addbuttons(){
		JPanel panel_SOUTH = new JPanel();
		panel_SOUTH.setBackground(Color.white);
		panel_SOUTH.setOpaque(true);
		panel_SOUTH.setBorder(new EmptyBorder(0, 0, 2, 0));
		add(panel_SOUTH, BorderLayout.SOUTH);
		
		panel_SOUTH.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));	
		JLabel first = new JLabel(new ImageIcon("img/first.png"));
		JLabel prev = new JLabel(new ImageIcon("img/prev.png"));
		
		cantInfo = new JLabel();
		cantInfo.setBorder(new EmptyBorder(0, 6, 0, 6));
		cantInfo.setHorizontalAlignment(SwingConstants.CENTER);
		navigation = true;
		
		JLabel next = new JLabel(new ImageIcon("img/next.png"));
		JLabel last = new JLabel(new ImageIcon("img/last.png"));
		
		prev.addMouseListener(getEventLabel());
		first.addMouseListener(getEventLabel());
		last.addMouseListener(getEventLabel());
		next.addMouseListener(getEventLabel());
	
		panel_SOUTH.add(prev);
		panel_SOUTH.add(first);
		panel_SOUTH.add(cantInfo);
		panel_SOUTH.add(last);
		panel_SOUTH.add(next);		
	}
	
	public MouseAdapter getEventLabel(){
		MouseAdapter MA = new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};
		return MA;
	}
}
