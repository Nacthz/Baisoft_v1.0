package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.Table;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Bill extends JPanel {
	private JPanel table;
	private boolean all = false;
	private static final long serialVersionUID = 1721860207718285215L;

	public Bill() {
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.white);

		JPanel JP_NORTH = new JPanel();
		JP_NORTH.setOpaque(false);
		JP_NORTH.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(12, 180, 204)));
		add(JP_NORTH, BorderLayout.NORTH);
		JP_NORTH.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		table = new JPanel();
		table.setOpaque(false);
		add(table, BorderLayout.CENTER);
		table.setLayout(new GridLayout(1, 0, 0, 0));
		
		table.add(sincroautos());
		
		// Buttons
		JLabel JL_sincroautos = new JLabel("Sincroautos");
		JL_sincroautos.setBackground(Color.white);
		JLabel JL_sincrorepuestos = new JLabel("Sincrorepuestos");
		JL_sincrorepuestos.setBackground(Color.white);

		JL_sincroautos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (all) {
					select(JL_sincroautos);
					deselect(JL_sincrorepuestos);
					all = false;
					table.add(sincroautos());
					table.revalidate();
					table.repaint();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JL_sincroautos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_sincroautos.setOpaque(true);
		JL_sincroautos.setPreferredSize(new Dimension(90, 28));
		select(JL_sincroautos);
		JP_NORTH.add(JL_sincroautos);

		JL_sincrorepuestos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (!all) {
					select(JL_sincrorepuestos);
					deselect(JL_sincroautos);
					all = true;
					table.add(sincrorepuestos());
					table.revalidate();
					table.repaint();					
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		});
		JL_sincrorepuestos.setHorizontalAlignment(SwingConstants.CENTER);
		JL_sincrorepuestos.setOpaque(true);
		JL_sincrorepuestos.setPreferredSize(new Dimension(115, 28));
		deselect(JL_sincrorepuestos);
		JP_NORTH.add(JL_sincrorepuestos);
	}

	public void select(JComponent o) {
		o.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(12, 180, 204)));
		o.setForeground(new Color(2, 34, 34));
	}

	public void deselect(JComponent o) {
		o.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.white));
		o.setForeground(new Color(68, 68, 68));
	}

	public void setTrueSize(Component o, int a, int b) {
		o.setSize(a, b);
		o.setMinimumSize(o.getSize());
		o.setMinimumSize(o.getSize());
		o.setPreferredSize(o.getSize());
	}
	
	public Table sincrorepuestos(){
		table.removeAll();
		String[] title = { "Cliente", "fecha", "Valor" };
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "1", "ACEVEDO JHONG, DANIEL", "15/12/2015", "446946" });
		data.add(new String[] { "2", "AGURTO RONDOY, MIGUELVICENTE", "7/12/2015", "33821" });
		data.add(new String[] { "3", "ALCALÁ NEGRÓN, CHRISTIAN NELSON", "2/12/2015", "449501" });
		data.add(new String[] { "4", "ALMORA HERNANDEZ, RAUL EDUARDO", "29/11/2015", "15243" });
		data.add(new String[] { "5", "ALOSILLA VELAZCO VERA, JORGE ", "29/11/2015", "348378" });
		data.add(new String[] { "6", "ALVA CAMPOS, VICTOR", "19/11/2015", "125431" });
		data.add(new String[] { "7", "AREVALO LOPEZ, JAVIER", "19/11/2015", "355755" });
		data.add(new String[] { "8", "ARIAS HERNANDEZ, ROSARIO", "13/11/2015", "472734" });
		data.add(new String[] { "9", "ARROYO RAMÍREZ, EFRAÍN", "7/11/2015", "418984" });
		data.add(new String[] { "10", "ALOCEN BARRERA, MARCO TULIO", "3/11/2015", "394630" });
		data.add(new String[] { "11", "BAIOCCHI URETA, CESAR", "19/10/2015", "157096" });
		data.add(new String[] { "12", "BAYLÓN ROJAS, ISELA FLOR", "14/10/2015", "293855" });
		data.add(new String[] { "13", "BEDOYA CASTILLO, LEONCIA", "6/10/2015", "161503" });
		data.add(new String[] { "14", "BEDREGAL CANALES, LUZ MARINA", "28/09/2015", "159132" });
		data.add(new String[] { "15", "BEJAR TORRES, RAMIRO ALBERTO", "12/09/2015", "311490" });
		data.add(new String[] { "16", "BENAVIDES ESPEJO, JAVIER", "10/09/2015", "154433" });
		data.add(new String[] { "17", "BOZA SOLIS, NELSON", "4/09/2015", "160403" });
		data.add(new String[] { "18", "CALLE BETANCOURT, CIELITO MERCEDES", "27/08/2015", "56940" });
		data.add(new String[] { "19", "CARAZA VILLEGAS, ISABEL FLORISA", "8/08/2015", "59408" });
		data.add(new String[] { "20", "CARRERA ABANTO, GIZELLA", "19/07/2015", "204174" });
		data.add(new String[] { "21", "CARRILLO SEGURA, ESTALINS", "14/07/2015", "245159" });
		data.add(new String[] { "22", "CARRIÓN NEIRA, JORGE AUGUSTO", "9/07/2015", "109471" });
		data.add(new String[] { "23", "CASAPIA VALDIVIA, GUILLERMO ", "3/07/2015", "141398" });
		data.add(new String[] { "24", "CHANCOS MENDOZA, ZARITA", "2/07/2015", "283539" });
		data.add(new String[] { "25", "CHIRINOS LACOTERA, CARLOS", "29/06/2015", "473899" });
		data.add(new String[] { "26", "CORES MORENO, DORIS", "1/06/2015", "109388" });
		data.add(new String[] { "27", "CORTEZ LOZANO, MARIBEL CORINA", "24/05/2015", "389016" });
		data.add(new String[] { "28", "CRISPIN QUISPE, ANGEL", "22/05/2015", "393056" });
		data.add(new String[] { "29", "DE LOAYZA CONTERNO, ANTONIO ", "12/05/2015", "414830" });
		data.add(new String[] { "30", "DIAZ SALINAS, ANA MARIA", "29/04/2015", "97111" });
		data.add(new String[] { "31", "DUEÑAS ARISTISABAL, ANTONIO ", "27/04/2015", "60413" });
		data.add(new String[] { "32", "ESPINOZA ARANA, YULIANA", "25/04/2015", "167326" });
		data.add(new String[] { "33", "FERNANDEZ GUZMAN, CARLOS ENRIQUE", "24/04/2015", "341007" });
		data.add(new String[] { "34", "FERNANDEZ MATTA, ESTHER AURORA", "21/04/2015", "101360" });
		data.add(new String[] { "35", "FERRO SALAS, OLGA", "19/04/2015", "316976" });
		data.add(new String[] { "36", "FLORES ROMERO, EDWIN", "18/04/2015", "110255" });
		data.add(new String[] { "37", "GAMARRA ASTETE, ROBERTO", "6/04/2015", "231002" });
		data.add(new String[] { "38", "GAMIO LOZANO, GLORIA", "3/04/2015", "329347" });
		data.add(new String[] { "39", "GARCÍA PERALTA, MIRIAM", "20/03/2015", "71476" });
		data.add(new String[] { "40", "GONZALES DEL VALLE MAGUIÑO, ARTURO", "14/03/2015", "328343" });
		data.add(new String[] { "41", "GONZALES HUILCA, MARLENE VICTORIA", "7/03/2015", "371255" });
		data.add(new String[] { "42", "GONZALES MEDINA, ELSA PATRICIA", "5/03/2015", "316500" });
		data.add(new String[] { "43", "GUTIERREZ VELEZ, JAVIER", "1/03/2015", "378764" });
		data.add(new String[] { "44", "GUZMAN CHINAG, ELENA ROSAVELT", "24/02/2015", "160665" });
		data.add(new String[] { "45", "GUZMAN QUISPE, CLARA", "18/02/2015", "263157" });
		data.add(new String[] { "46", "HERRERA CARBAJAL, MILAGROS SUSAN ", "11/02/2015", "489512" });
		data.add(new String[] { "47", "HORRUITINER MARTINEZ, GUILLERMO", "10/02/2015", "173278" });
		data.add(new String[] { "48", "HUAMANI FLORES, LOURDES", "1/02/2015", "42566" });
		data.add(new String[] { "49", "HUAPAYA RAYGADA, LUIS ARMANDO", "29/01/2015", "312594" });
		data.add(new String[] { "50", "HUARCAYA QUISPE, MARCOS", "27/01/2015", "244621" });
		data.add(new String[] { "51", "HUAYTAN SAUÑE, WALTER DAVID", "13/01/2015", "237260" });
		data.add(new String[] { "52", "LA ROSA FABIAN, ELBA MERCEDES ", "7/01/2015", "493377" });
		data.add(new String[] { "53", "LANDA GINOCCHIO, PEDRO GUILLERMO", "5/01/2015", "185716" });
		data.add(new String[] { "54", "LLAJA TAFUR, ROBERTO JULIAN", "4/01/2015", "438920" });
		data.add(new String[] { "55", "LLENPEN NUÑEZ, ORFELINA", "3/01/2015", "168214" });

		boolean search = true;
		boolean advanceIcons = true;
		boolean navigation = true;
		int limitedRows = 0;
		return new Table("Bill_2", data, title, navigation, search, advanceIcons, limitedRows);
	}
	
	public Table sincroautos(){
		table.removeAll();
		String[] title = { "Encargado", "Placa", "Fecha", "Valor" };
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[] { "1", "GUTIERREZ VELEZ, JAVIER", "AAA04", "15/12/2015", "410640" });
		data.add(new String[] { "2", "GUTIERREZ VELEZ, JAVIER", "AAA73", "7/12/2015", "390701" });
		data.add(new String[] { "3", "GUTIERREZ VELEZ, JAVIER", "AAB089", "2/12/2015", "283671" });
		data.add(new String[] { "4", "HERRERA CARBAJAL, MILAGROS SUSAN ", "AAB242", "29/11/2015", "457139" });
		data.add(new String[] { "5", "HORRUITINER MARTINEZ, GUILLERMO", "AAB321", "29/11/2015", "198491" });
		data.add(new String[] { "6", "HUAMANI FLORES, LOURDES", "ABT44A2", "19/11/2015", "124686" });
		data.add(new String[] { "7", "HUAMANI FLORES, LOURDES", "ABT50", "19/11/2015", "277030" });
		data.add(new String[] { "8", "HUAMANI FLORES, LOURDES", "ABT815", "13/11/2015", "410291" });
		data.add(new String[] { "9", "GUZMAN QUISPE, CLARA", "ABU602", "7/11/2015", "196254" });
		data.add(new String[] { "10", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ABV03", "3/11/2015", "358437" });
		data.add(new String[] { "11", "HORRUITINER MARTINEZ, GUILLERMO", "ABV62", "19/10/2015", "450179" });
		data.add(new String[] { "12", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ABW08", "14/10/2015", "258678" });
		data.add(new String[] { "13", "HORRUITINER MARTINEZ, GUILLERMO", "ABW308", "6/10/2015", "309084" });
		data.add(new String[] { "14", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ABX32A", "28/09/2015", "317772" });
		data.add(new String[] { "15", "GUZMAN QUISPE, CLARA", "ABX43C", "12/09/2015", "377969" });
		data.add(new String[] { "16", "GUZMAN QUISPE, CLARA", "ABX47A", "10/09/2015", "97202" });
		data.add(new String[] { "17", "GUZMAN QUISPE, CLARA", "ABZ18A", "4/09/2015", "23882" });
		data.add(new String[] { "18", "GUZMAN QUISPE, CLARA", "ACA55", "27/08/2015", "134196" });
		data.add(new String[] { "19", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACB770", "8/08/2015", "152804" });
		data.add(new String[] { "20", "HORRUITINER MARTINEZ, GUILLERMO", "ACB913", "19/07/2015", "275336" });
		data.add(new String[] { "21", "GUTIERREZ VELEZ, JAVIER", "ACC344", "14/07/2015", "479777" });
		data.add(new String[] { "22", "GUTIERREZ VELEZ, JAVIER", "ACC38", "9/07/2015", "357334" });
		data.add(new String[] { "23", "GUTIERREZ VELEZ, JAVIER", "ACC433", "3/07/2015", "179385" });
		data.add(new String[] { "24", "GUZMAN QUISPE, CLARA", "ACC461", "2/07/2015", "450805" });
		data.add(new String[] { "25", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACC58", "29/06/2015", "290978" });
		data.add(new String[] { "26", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACC631", "1/06/2015", "238793" });
		data.add(new String[] { "27", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACC745", "24/05/2015", "254411" });
		data.add(new String[] { "28", "HORRUITINER MARTINEZ, GUILLERMO", "ACC783", "22/05/2015", "289224" });
		data.add(new String[] { "29", "HORRUITINER MARTINEZ, GUILLERMO", "ACC918", "12/05/2015", "424879" });
		data.add(new String[] { "30", "GUTIERREZ VELEZ, JAVIER", "ACC982", "29/04/2015", "242496" });
		data.add(new String[] { "31", "GUTIERREZ VELEZ, JAVIER", "ACD098", "27/04/2015", "258677" });
		data.add(new String[] { "32", "GUTIERREZ VELEZ, JAVIER", "ACD629", "25/04/2015", "78567" });
		data.add(new String[] { "33", "GUZMAN QUISPE, CLARA", "ACD658", "24/04/2015", "345927" });
		data.add(new String[] { "34", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACE050", "21/04/2015", "497534" });
		data.add(new String[] { "35", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACE10", "19/04/2015", "219136" });
		data.add(new String[] { "36", "HERRERA CARBAJAL, MILAGROS SUSAN ", "ACE227", "18/04/2015", "112301" });
		data.add(new String[] { "37", "HORRUITINER MARTINEZ, GUILLERMO", "ACE441", "6/04/2015", "197842" });
		data.add(new String[] { "38", "HORRUITINER MARTINEZ, GUILLERMO", "ACE672", "3/04/2015", "459951" });
		data.add(new String[] { "39", "GUTIERREZ VELEZ, JAVIER", "ACE737", "20/03/2015", "52305" });
		data.add(new String[] { "40", "GUTIERREZ VELEZ, JAVIER", "AAA04", "14/03/2015", "410265" });
		data.add(new String[] { "41", "GUTIERREZ VELEZ, JAVIER", "AAA73", "7/03/2015", "256458" });
		data.add(new String[] { "42", "HUAMANI FLORES, LOURDES", "AAB089", "5/03/2015", "50933" });
		data.add(new String[] { "43", "GUZMAN QUISPE, CLARA", "AAB242", "1/03/2015", "42763" });
		data.add(new String[] { "44", "HERRERA CARBAJAL, MILAGROS SUSAN ", "AAB321", "24/02/2015", "14313" });
		data.add(new String[] { "45", "HORRUITINER MARTINEZ, GUILLERMO", "AAB618", "18/02/2015", "493932" });
		data.add(new String[] { "46", "HERRERA CARBAJAL, MILAGROS SUSAN ", "AAB638", "11/02/2015", "266738" });
		data.add(new String[] { "47", "HORRUITINER MARTINEZ, GUILLERMO", "AAB649", "10/02/2015", "303630" });
		data.add(new String[] { "48", "GUTIERREZ VELEZ, JAVIER", "AAB901", "1/02/2015", "409846" });
		data.add(new String[] { "49", "HERRERA CARBAJAL, MILAGROS SUSAN ", "AAC35", "29/01/2015", "205658" });
		data.add(new String[] { "50", "HORRUITINER MARTINEZ, GUILLERMO", "AAC813", "27/01/2015", "404203" });
		data.add(new String[] { "51", "HUAMANI FLORES, LOURDES", "AAC892", "13/01/2015", "344792" });
		data.add(new String[] { "52", "GUTIERREZ VELEZ, JAVIER", "AAD45", "7/01/2015", "471683" });
		data.add(new String[] { "53", "HERRERA CARBAJAL, MILAGROS SUSAN ", "AAD682", "5/01/2015", "207551" });
		data.add(new String[] { "54", "HORRUITINER MARTINEZ, GUILLERMO", "AAD82", "4/01/2015", "190429" });
		data.add(new String[] { "55", "HUAMANI FLORES, LOURDES", "AAE31", "3/01/2015", "338351" });

		boolean search = true;
		boolean advanceIcons = true;
		boolean navigation = true;
		int limitedRows = 0;
		return new Table("Bill_1", data, title, navigation, search, advanceIcons, limitedRows);
	}
}