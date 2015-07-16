package gui;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.BoxLayout;

public class MenuButton extends JPanel {

	private static final long serialVersionUID = -6329369942185721101L;
	private boolean active = false;
	private JLabel JL_text, selected, selected_0, selected_2, selected_3,
			JL_logo = null;
	private JPanel content;
	private String icon_name = "";
	public String name = "";

	/**
	 * @wbp.parser.constructor
	 */

	public MenuButton(String icon_name, String text) {
		this.icon_name = icon_name;
		this.name = text;
		setBackground(new Color(46, 48, 51));
		setTrueSize(this, 127, 32);
		setLayout(new BorderLayout(0, 0));

		selected_0 = new JLabel();
		selected_0.setBackground(new Color(57, 59, 61));
		selected_0.setOpaque(true);
		setTrueSize(selected_0, 1, 32);
		selected_0.setVisible(false);
		add(selected_0, BorderLayout.WEST);

		selected = new JLabel();
		selected.setBackground(new Color(90, 71, 154));
		setTrueSize(selected, 4, 32);
		selected.setOpaque(true);
		selected.setVisible(false);
		add(selected, BorderLayout.WEST);

		selected_2 = new JLabel();
		selected_2.setBackground(new Color(57, 59, 61));
		selected_2.setOpaque(true);
		setTrueSize(selected_2, 127, 1);
		selected_2.setVisible(false);
		add(selected_2, BorderLayout.NORTH);

		selected_3 = new JLabel();
		selected_3.setBackground(new Color(57, 59, 61));
		selected_3.setOpaque(true);
		setTrueSize(selected_3, 127, 1);
		selected_3.setVisible(false);
		add(selected_3, BorderLayout.SOUTH);

		content = new JPanel();
		content.setBackground(new Color(46, 48, 51));
		add(content, BorderLayout.CENTER);
		content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));

		JL_logo = new JLabel(new ImageIcon("img/" + icon_name + "_2.png"));
		JL_logo.setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(0, 10, 0, 0)));
		content.add(JL_logo);

		JL_text = new JLabel(text);
		JL_text.setBorder(BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(0, 6, 0, 0)));
		JL_text.setFont(new Font("Arial", Font.PLAIN, 11));
		JL_text.setForeground(new Color(232, 232, 232));
		content.add(JL_text);

	}

	public void setTrueSize(Component o, int a, int b) {
		o.setSize(a, b);
		o.setMaximumSize(o.getSize());
		o.setMinimumSize(o.getSize());
		o.setPreferredSize(o.getSize());
	}

	public MenuButton(String icon_name) {
		setBackground(new Color(46, 48, 51));
		setLayout(new BorderLayout(0, 0));
		setTrueSize(this, 127, 42);

		JLabel logo = new JLabel(new ImageIcon("img/logo.png"));
		setTrueSize(logo, 127, 42);
		add(logo);
	}

	public void mouseClicked() {
		if (!active) {
			content.setBackground(new Color(41, 43, 46));
			content.setBorder(BorderFactory.createLineBorder(new Color(26, 27,
					29)));
			active = true;
			JL_logo.setIcon(new ImageIcon("img/" + icon_name + "_1.png"));
			selected.setVisible(true);
			selected_0.setVisible(false);
			selected_2.setVisible(true);
			selected_3.setVisible(true);
		}
	}

	public void mouseEntered() {
		content.setBackground(new Color(32, 34, 36));
		JL_logo.setIcon(new ImageIcon("img/" + icon_name + "_1.png"));
	}

	public void mouseExited() {
		if (!active) {
			content.setBackground(new Color(46, 48, 51));
			JL_logo.setIcon(new ImageIcon("img/" + icon_name + "_2.png"));
		} else
			content.setBackground(new Color(41, 43, 46));
	}

	public void undo() {
		content.setBackground(new Color(46, 48, 51));
		JL_logo.setIcon(new ImageIcon("img/" + icon_name + "_2.png"));
		content.setBorder(null);
		selected.setVisible(false);
		selected_0.setVisible(false);
		selected_2.setVisible(false);
		selected_3.setVisible(false);
		active = false;
	}
}
