package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TableRow extends JPanel {

	private int height = 25;
	private boolean edit = false;
	private static final long serialVersionUID = 6359330881452231410L;
	private JLabel id;
	private JTextField description, inicialPrice, price, quantity;

	public TableRow(int v1, String v2, int v3, int v4, int v5) {
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

		JCheckBox check = new JCheckBox();
		check.addMouseListener(mouseEvent());
		check.setOpaque(false);
		panel_WEST.add(check, BorderLayout.WEST);

		id = new JLabel("" + v1);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setPreferredSize(new Dimension(35, height));
		panel_WEST.add(id, BorderLayout.CENTER);

		JLabel line_SOUTH = new JLabel();
		line_SOUTH.setPreferredSize(new Dimension(Integer.MAX_VALUE, 1));
		line_SOUTH.setBackground(new Color(220, 220, 220));
		line_SOUTH.setOpaque(true);
		add(line_SOUTH, BorderLayout.SOUTH);

		JPanel panel_CENTER = new JPanel();
		panel_CENTER.setOpaque(false);
		add(panel_CENTER, BorderLayout.CENTER);
		panel_CENTER.setLayout(new BorderLayout(0, 0));

		description = new JTextField();
		description.addMouseListener(mouseEvent());
		description.setBorder(null);
		description.setEditable(false);
		description.setOpaque(false);
		description.setPreferredSize(new Dimension(360, height));
		panel_CENTER.add(description, BorderLayout.WEST);

		JPanel panel_CENTER_CENTE = new JPanel();
		panel_CENTER_CENTE.setOpaque(false);
		panel_CENTER.add(panel_CENTER_CENTE, BorderLayout.CENTER);
		panel_CENTER_CENTE.setLayout(new GridLayout(1, 0, 0, 0));

		inicialPrice = new JTextField();
		inicialPrice.setHorizontalAlignment(SwingConstants.CENTER);
		inicialPrice.addMouseListener(mouseEvent());
		inicialPrice.setOpaque(false);
		inicialPrice.setBorder(null);
		inicialPrice.setEditable(false);
		panel_CENTER_CENTE.add(inicialPrice);
		
		price = new JTextField();
		price.setHorizontalAlignment(SwingConstants.CENTER);
		price.addMouseListener(mouseEvent());
		price.setOpaque(false);
		price.setBorder(null);
		price.setEditable(false);
		panel_CENTER_CENTE.add(price);
		
		quantity = new JTextField();
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.addMouseListener(mouseEvent());
		quantity.setOpaque(false);
		quantity.setBorder(null);
		quantity.setEditable(false);
		panel_CENTER_CENTE.add(quantity);

		JPanel panel_EAST = new JPanel();
		panel_EAST.setBorder(new EmptyBorder(0, 0, 0, 10));
		panel_EAST.setOpaque(false);
		panel_EAST.setPreferredSize(new Dimension(50, height));
		add(panel_EAST, BorderLayout.EAST);
		panel_EAST.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel JL_edit = new JLabel(new ImageIcon("img/pencil.png"));
		JL_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				edit = !edit;
				if (edit)
					setBackground(new Color(180, 229, 106));
				else
					setBackground(new Color(255, 255, 255));
				description.setEditable(edit);
				price.setEditable(edit);
				quantity.setEditable(edit);
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
		panel_EAST.add(JL_edit);

		JLabel JL_delete = new JLabel(new ImageIcon("img/trash.png"));
		JL_delete.setOpaque(false);
		panel_EAST.add(JL_delete);

		description.setText(v2);
		inicialPrice.setText("" + v3);
		price.setText("" + v4);
		quantity.setText("" + v5);
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

}