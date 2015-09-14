package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import gui.Table;

public class Info extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info frame = new Info("", null, "");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Info(String bill_id, ArrayList<String[]> data, String type) {
		this.setTitle("Factura " + bill_id);		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(800, 500);
		setLocation(d.width / 2 - this.getSize().width / 2, d.height / 2 - this.getSize().height / 2);
		
		String[] title = null;
		
		if(type.equals("Bill_2")){
			title = new String[] {"Producto", "Neto", "Cantidad", "Descuento", "Total"};		
		}
		
		if(type.equals("Bill_1")){
			title = new String[] {"Descripción", "Empleado", "Tipo", "Precio"};		
		}
		
		if(type.equals("home")){
			title = new String[] {"Cliente", "Telefono", "Placa", "Fecha", "Precio"};		
		}

		boolean search = false;
		boolean advanceIcons = false;
		boolean navigation = true;
		int limitedRows = 0;
		
		contentPane = new Table(null, "Info", data, title, navigation, search, advanceIcons, limitedRows);
		setContentPane(contentPane);
	}
	
}