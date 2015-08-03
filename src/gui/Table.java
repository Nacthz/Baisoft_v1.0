package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class Table extends JPanel {

	private boolean navigation;
	private int index, first = 0;
	private int page, maxPage = 1;
	private int max;
	private int searchTable = 1;
	private int actual = 0;
	private JLabel cantInfo;
	private JPanel panel_CENTER;
	private static final long serialVersionUID = -2108154469027694188L;
	private ArrayList<TableRow> rows = new ArrayList<TableRow>();
	private ArrayList<String[]> originalData = new ArrayList<String[]>();
	private ArrayList<String[]> actualData = new ArrayList<String[]>();
	private Table actualTable = this;
	private JTextField search;

	public boolean isFocusable() {
		return true;
	}

	public void rv() {
		this.revalidate();
		this.repaint();
	}
	
	public void searchOn(int id){
		searchTable = id;
	}
	
	public void search(String match){
		ArrayList<String[]> ArrayData = new ArrayList<String[]>();
		match = match.toLowerCase();
		for(int i = 0; i < originalData.size(); i++){
			if (originalData.get(i)[searchTable].toLowerCase().contains(match)){
				ArrayData.add(originalData.get(i));
			}
		}
		
		if(ArrayData.size()>0){
			actualData.clear();
			actualData = ArrayData;
			fillData(1, actualData);
			double a = actualData.size();
			double b = max;
			double c = first;
			maxPage = (int) Math.ceil(a / b);
			page = (int) Math.ceil(c / b);
			cantInfo.setText(page + " de " + maxPage);
			rv();			
		}else{
			actualData.clear();
			panel_CENTER.removeAll();
			maxPage = 0;
			page = 0;
			cantInfo.setText(page + " de " + maxPage);
			rv();
		}
		

	}

	@SuppressWarnings("unchecked")
	public Table(ArrayList<String[]> data, String[] title, String[] complements) {        
		this.setBackground(Color.white);
		this.originalData = (ArrayList<String[]>) data.clone();
		this.actualData = (ArrayList<String[]>) data.clone();
		setLayout(new BorderLayout(0, 0));

		JPanel panel_NORTH = new JPanel();
		panel_NORTH.setLayout(new BorderLayout(0, 0));
		add(panel_NORTH, BorderLayout.NORTH);
		panel_NORTH.add(new TableTitle(title, this), BorderLayout.CENTER);

		panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BoxLayout(panel_CENTER, BoxLayout.Y_AXIS));

		JPanel panel_search = new JPanel();
		panel_search.setLayout(new BorderLayout(0, 0));

		JLabel img1 = new JLabel(new ImageIcon("img/search_west.png"));
		JLabel img2 = new JLabel(new ImageIcon("img/search_east.png"));

		search = new JTextField();
		search.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent e) {
				if (search.getText().equals("")) {
					actualData.clear();
					actualData = (ArrayList<String[]>) originalData.clone();
					fillData(1, actualData);
					if (navigation) {
						double a = actualData.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						fillData(page, actualData);
						cantInfo.setText(page + " de " + maxPage);
					}
					rv();
				} else {
					search(search.getText());
				}
			}
			public void insertUpdate(DocumentEvent e) {
				search(search.getText());
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		search.setBackground(new Color(250, 250, 250));
		search.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(204, 204, 204)));
		search.setColumns(20);

		panel_search.add(search, BorderLayout.CENTER);
		panel_search.add(img1, BorderLayout.WEST);
		panel_search.add(img2, BorderLayout.EAST);

		JPanel panel_NORTH_NORTH = new JPanel();
		panel_NORTH_NORTH.setBorder(new EmptyBorder(3, 5, 3, 5));
		panel_NORTH.add(panel_NORTH_NORTH, BorderLayout.NORTH);
		panel_NORTH_NORTH.setLayout(new BorderLayout(0, 0));
		panel_NORTH_NORTH.add(panel_search, BorderLayout.WEST);

		max = panel_CENTER.getHeight() / 25;
		for (String s : complements) {
			if (s.equals("navigation"))
				addbuttons();
			if (s.equals("basic"))
				fillData(1, originalData);
		}

		panel_CENTER.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {
				int newCant = panel_CENTER.getHeight() / 25;
				if (max < newCant) {
					if (actual < actualData.size()) {
						TableRow tr = new TableRow(actualData.get(actual), actual, actualTable);
						actual++;
						rows.add(tr);
						panel_CENTER.add(tr);
					}
					max = newCant;
					if (navigation) {
						double a = actualData.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);

						fillData(page, actualData);
					}
					return;
				}
				if (index > newCant) {
					index--;
					actual--;
					panel_CENTER.remove(index);
					rows.remove(index);
					max = newCant;
					if (navigation) {
						double a = actualData.size();
						double b = max;
						double c = first;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						cantInfo.setText(page + " de " + maxPage);

						fillData(page, actualData);
					}
					return;
				}

				if (max != newCant) {
					max = newCant;
					if (navigation) {
						double a = actualData.size();
						double b = max;
						double c = actual;
						maxPage = (int) Math.ceil(a / b);
						page = (int) Math.ceil(c / b);
						fillData(page, actualData);
						cantInfo.setText(page + " de " + maxPage);
					}
				}
			}
		});
	}

	public void setSelected(boolean status) {
		for (TableRow TR : rows) {
			TR.setSelected(status);
		}
	}

	public void deleteRow(int toDelete) {
		actualData.remove(toDelete);
		if (navigation) {
			double a = actualData.size();
			double b = max;
			double c = actual;
			maxPage = (int) Math.ceil(a / b);
			page = (int) Math.ceil(c / b);
			fillData(page, actualData);
			cantInfo.setText(page + " de " + maxPage);
		}
		this.revalidate();
	}

	public void fillData(int nPage, ArrayList<String[]> ArrayData) {
		rows.clear();
		panel_CENTER.removeAll();
		page = nPage;
		first = actual = index = (page - 1) * max;
		first++;
		for (int i = (page - 1) * max; i < ((page - 1) * max) + max && i < ArrayData.size(); i++) {
			TableRow tr = new TableRow(ArrayData.get(i), i, actualTable);
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
					fillData(page, actualData);
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
					fillData(page, actualData);
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
					fillData(page, actualData);
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
					fillData(page, actualData);
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
