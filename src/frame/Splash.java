package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;

public class Splash extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -676931233728672119L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
					frame.setVisible(true);
					frame.Load(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Splash() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		contentPane.setLayout(null);
		JLabel background = new JLabel(new ImageIcon("img/splash.PNG"));
		background.setBounds(0, 0, 747, 436);
		contentPane.add(background);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(background.getSize());
		setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);
	}

	public void Load(final JFrame main) {
		final Main baisoft = new Main();
		Timer delay = new Timer(3000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main.dispose();
				baisoft.setVisible(true);
			}
		});
		delay.start();
	}
}
