package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.MenuButton;
import views.Inventory;

import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class Main extends JFrame {

	private static final long serialVersionUID = 2292455670620751734L;
	private JPanel contentPane;
	private JPanel menu_panel = new JPanel();
	private MenuButton actual_button = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(1000, 650);
		setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 255));
		contentPane.setLayout(new BorderLayout(0, 0));

		// Frame components
		contentPane.add(getMenuItem(), BorderLayout.WEST);
		contentPane.add(getPanel(), BorderLayout.CENTER);
		menu_panel.setLayout(new GridLayout(1, 0, 0, 0));

		setContentPane(contentPane);
	}

	public void loadJPanel(String name) {
		menu_panel.removeAll();

		if (name.equals("Inventario")) {
			menu_panel.add(new Inventory());
			menu_panel.repaint();
		}
	}

	public JPanel getMenuItem() {
		JPanel menu_item = new JPanel();
		menu_item.setBackground(new Color(46, 48, 51));
		menu_item.setLayout(new BoxLayout(menu_item, BoxLayout.Y_AXIS));

		// Main menu buttons
		menu_item.add(newLogo("logo"));
		actual_button = newButton("stock_up", "Inicio");
		actual_button.mouseClicked();
		menu_item.add(actual_button);
		menu_item.add(newButton("paper", "Sincroautos"));
		menu_item.add(newButton("paper", "Sincrorepuestos"));
		menu_item.add(newButton("shopping_cart", "Ventas"));
		menu_item.add(newButton("notepad", "Inventario"));
		return menu_item;
	}

	public MenuButton newButton(String icon_name, String text) {
		final MenuButton n = new MenuButton(icon_name, text);
		n.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (n != actual_button) {
					n.mouseClicked();
					actual_button.undo();
					actual_button = n;
					loadJPanel(n.name);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				n.mouseEntered();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				n.mouseExited();
			}
		});
		return n;
	}

	public MenuButton newLogo(String icon_name) {
		MenuButton n = new MenuButton(icon_name);
		return n;
	}

	public JPanel getPanel() {
		menu_panel.setBackground(new Color(255, 255, 255));
		return menu_panel;
	}
}
