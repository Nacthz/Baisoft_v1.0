package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Table extends JPanel {

	private boolean navigation = false;
	private int index = 0, first = 0;
	private int page = 1, maxPage = 1;
	private int max, heigth = 490;
	private int actual = 0;
	private JLabel cantInfo;
	private JPanel panel_CENTER;
	private static final long serialVersionUID = -2108154469027694188L;
	private ArrayList<TableRow> rows = new ArrayList<TableRow>();
	private ArrayList<String[]> data;

	public boolean isFocusable() {
		return true;
	}

	public Table(int n, ArrayList<String[]> data, String[] title, String[] complements) {
		this.setBackground(Color.white);
		this.data = data;
		setLayout(new BorderLayout(0, 0));

		JPanel panel_NORTH = new JPanel();
		panel_NORTH.setLayout(new BorderLayout(0, 0));
		add(panel_NORTH, BorderLayout.NORTH);
		panel_NORTH.add(new TableTools(), BorderLayout.NORTH);
		panel_NORTH.add(new TableTitle(title, this), BorderLayout.CENTER);

		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BoxLayout(panel_CENTER, BoxLayout.Y_AXIS));

		max = heigth / 25;

		fillData(1);

		for (String s : complements) {
			if (s.equals("navigation")) {
				addbuttons();
				cantInfo.setText(page + " de " + Math.round(data.size() / max));
			}
		}

		double a = data.size();
		double b = max;
		maxPage = (int) Math.ceil(a / b);

		panel_CENTER.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				int newCant = panel_CENTER.getHeight() / 25;
				if (max < newCant) {
					if(actual < data.size()){
					TableRow tr = new TableRow(data.get(actual));
					actual++;
					rows.add(tr);
					panel_CENTER.add(tr);
					}
					max = newCant;
					if (navigation) {
						double a = data.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);
						
						fillData(page);
					}
					return;
				}
				if (index > newCant) {
					index--;
					actual--;
					panel_CENTER.remove(rows.get(index));
					rows.remove(index);
					max = newCant;
					if (navigation) {
						double a = data.size();
						double b = max;
						double c = first;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);
						
						fillData(page);
					}
					return;
				}
				
				if(max != newCant){
					max = newCant;
					if (navigation) {
						double a = data.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						fillData(page);
					}					
				}
				cantInfo.setText(page + " de " + maxPage);
			}
		});
	}

	public void setSelected(boolean status){
		for(TableRow TR : rows){
			TR.setSelected(status);
		}
	}
	
	public void fillData(int nPage) {
		rows.clear();
		panel_CENTER.removeAll();
		page = nPage;
		first = actual = index = (page - 1) * max;
		first++;
		for (int i = (page - 1) * max; i < ((page - 1) * max) + max && i < data.size(); i++) {
			TableRow tr = new TableRow(data.get(i));
			rows.add(tr);
			panel_CENTER.add(tr);
		}
		actual += rows.size();
		index = actual - index;
		panel_CENTER.repaint();
	}

	public void addbuttons() {
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
		prev.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page > 1) {
					page--;
					fillData(page);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}
			}
		});

		first.addMouseListener(getEventLabel());
		first.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page != 1) {
					page = 1;
					fillData(page);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}
			}
		});

		last.addMouseListener(getEventLabel());
		last.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page != maxPage) {
					page = maxPage;
					fillData(page);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}

			}
		});

		next.addMouseListener(getEventLabel());
		next.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (page < maxPage) {
					page++;
					fillData(page);
					panel_CENTER.revalidate();
					cantInfo.setText(page + " de " + maxPage);
				}
			}
		});
		panel_SOUTH.add(first);
		panel_SOUTH.add(prev);
		panel_SOUTH.add(cantInfo);
		panel_SOUTH.add(next);
		panel_SOUTH.add(last);
	}

	public MouseAdapter getEventLabel() {
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
