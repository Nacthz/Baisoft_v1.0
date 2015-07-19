package views;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JPanel;

import gui.Table;

import java.awt.BorderLayout;

public class Inventory extends JPanel {

	private static final long serialVersionUID = 1721860207718285215L;

	public Inventory() {
		setLayout(new BorderLayout(0, 0));

		JPanel JP_NORTH = new JPanel();
		JP_NORTH.setBackground(new Color(242, 0, 242));
		add(JP_NORTH, BorderLayout.NORTH);

		String[] title = {"Descripción", "Precio de compra", "Precio de venta", "Cantidad"};
		ArrayList<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"1","Alfombra","12856","19075","42"});
		data.add(new String[] {"2","Alternador","10626","11568","90"});
		data.add(new String[] {"3","Amortiguador delantero derecho","25381","32510","25"});
		data.add(new String[] {"4","Amortiguador delantero izquierdo","58984","66752","95"});
		data.add(new String[] {"5","Amortiguador trasero derecho","17442","61481","114"});
		data.add(new String[] {"6","Amortiguador trasero izquierdo","60627","70770","108"});
		data.add(new String[] {"7","Arrancador","10700","10785","22"});
		data.add(new String[] {"8","Articulacion trasera derecha","36487","47642","96"});
		data.add(new String[] {"9","Articulacion trasera izquierda","19821","38054","99"});
		data.add(new String[] {"10","Asiento central","9856","11729","17"});
		data.add(new String[] {"11","Asiento trasero","11967","70684","51"});
		data.add(new String[] {"12","Asiento trasero","47533","54938","45"});
		data.add(new String[] {"13","Bobina de ignicion","9324","13633","71"});
		data.add(new String[] {"14","Bomba de agua","9242","15125","44"});
		data.add(new String[] {"15","Bomba de combustible","10753","39527","92"});
		data.add(new String[] {"16","Bomba de inyeccion","30303","67607","71"});
		data.add(new String[] {"17","Bomba de suspension neumatica","58143","72153","112"});
		data.add(new String[] {"18","Bomba de traccion","23028","33211","120"});
		data.add(new String[] {"19","Bomba de vacio","22006","40093","11"});
		data.add(new String[] {"20","Bomba neumatica","20389","23668","42"});
		data.add(new String[] {"21","Brazo inferior delantero derecho","8022","15454","52"});
		data.add(new String[] {"22","Brazo inferior delantero izquierdo","17690","66929","94"});
		data.add(new String[] {"23","Brazo inferior derecho posterior","14555","15526","55"});
		data.add(new String[] {"24","Brazo inferior trasero izquierdo","29599","32684","64"});
		data.add(new String[] {"25","Brazo superior delantero derecho","19663","23991","20"});
		data.add(new String[] {"26","Brazo superior trasero derecho","27952","52044","37"});
		data.add(new String[] {"27","Brazo superior trasero izquierdo","23151","65500","117"});
		data.add(new String[] {"28","Caja de Transferencia","19203","60553","48"});
		data.add(new String[] {"29","Calibrador delantero derecho","13985","23056","62"});
		data.add(new String[] {"30","Calibrador delantero izquierdo","16524","35314","74"});
		data.add(new String[] {"31","Calibrador trasero derecho","15714","55437","80"});
		data.add(new String[] {"32","Calibrador trasero izquierdo","15285","23119","71"});
		data.add(new String[] {"33","Carburador","8716","13209","107"});
		data.add(new String[] {"34","Carter de aceite","60924","70314","97"});
		data.add(new String[] {"35","Catalizador","13705","70628","60"});
		data.add(new String[] {"36","Cinturon de seguridad trasero","29175","68046","19"});
		data.add(new String[] {"37","Compresor del Aire acondicionado","8867","72786","38"});
		data.add(new String[] {"38","Condensador","48161","78626","32"});
		data.add(new String[] {"39","Conjunto de Aire acondicionado","8696","10047","112"});
		data.add(new String[] {"40","Conjunto de la culata de cilindro","43895","68704","70"});
		data.add(new String[] {"41","Conjunto de marco de puerta","38381","47613","89"});
		data.add(new String[] {"42","Conjunto del filtro de aire","9871","35842","110"});
		data.add(new String[] {"43","Conjunto del resorte","57430","60203","118"});
		data.add(new String[] {"44","Conjunto del resorte delantero","9455","15310","41"});
		data.add(new String[] {"45","Conjunto del resorte trasero","40365","75453","96"});
		data.add(new String[] {"46","Conjunto del silenciador","12375","33064","96"});
		data.add(new String[] {"47","Convertidor de par","35988","79979","26"});
		data.add(new String[] {"48","Cubierta de la culata de cilindro","22613","68920","102"});
		data.add(new String[] {"49","Cubierta del embrague","8408","9493","114"});
		data.add(new String[] {"50","Cuerpo del acelerador","10408","61732","82"});
		data.add(new String[] {"51","Distribuidor","19031","65239","52"});
		data.add(new String[] {"52","Eje impulsor delantero derecho","17719","37561","41"});
		data.add(new String[] {"53","Eje impulsor delantero izquierdo","31507","41395","68"});
		data.add(new String[] {"54","Eje motriz delantero","12096","26021","30"});
		data.add(new String[] {"55","Eje motriz trasero","12051","29796","120"});
		data.add(new String[] {"56","Eje trasero derecho","18334","67646","24"});
		data.add(new String[] {"57","Eje trasero izquierdo","50899","75017","31"});
		data.add(new String[] {"58","Enfriador de aceite","12414","34974","90"});
		data.add(new String[] {"59","Engranaje del diferencial trasero","32035","79195","63"});
		data.add(new String[] {"60","Estabilizador delantero","17540","39576","110"});
		data.add(new String[] {"61","Estabilizador trasero","15777","25496","59"});
		data.add(new String[] {"62","Interenfriador","8763","21721","68"});
		data.add(new String[] {"63","Medidor central de combustible","20995","54096","106"});
		data.add(new String[] {"64","Medidor de flujo de aire","28428","43599","16"});
		data.add(new String[] {"65","Miembro central del motor","28703","45246","120"});
		data.add(new String[] {"66","Miembro de la suspension trasera","38148","75990","86"});
		data.add(new String[] {"67","Montante trasero derecho","50268","65220","109"});
		data.add(new String[] {"68","Montante trasero izquierdo","13360","26688","26"});
		data.add(new String[] {"69","Motor Conjunto","17161","47418","65"});
		data.add(new String[] {"70","Multiple de entrada","12629","14467","72"});
		data.add(new String[] {"71","Multiple de escape","26218","73580","71"});
		data.add(new String[] {"72","Otra bomba","31483","74061","13"});
		data.add(new String[] {"73","Otro sistema motor E/G M/T","20116","31180","76"});
		data.add(new String[] {"74","Palanca de cambios automatica","28759","66916","115"});
		data.add(new String[] {"75","Polea del ciguenal","22997","58687","110"});
		data.add(new String[] {"76","Radiador","11991","13763","87"});
		data.add(new String[] {"77","Resorte de hojas delantero","13361","25796","40"});
		data.add(new String[] {"78","Resorte de hojas trasero","15206","79406","71"});
		data.add(new String[] {"79","Resorte delantero derecho","11157","19161","41"});
		data.add(new String[] {"80","Resorte delantero izquierdo","15625","33039","93"});
		data.add(new String[] {"81","Resorte helicoidal trasero","16332","61050","68"});
		data.add(new String[] {"82","Resorte trasero derecho","41160","41364","13"});
		data.add(new String[] {"83","Resorte trasero izquierdo","28394","40087","114"});
		data.add(new String[] {"84","Rotor de disco delantero","66933","78895","118"});
		data.add(new String[] {"85","Rotor de disco trasero","48957","53762","64"});
		data.add(new String[] {"86","Sensor de O2","12049","26732","14"});
		data.add(new String[] {"87","Silenciador central del escape","11740","38872","115"});
		data.add(new String[] {"88","Silenciador trasero","16718","32831","97"});
		data.add(new String[] {"89","Supercargador","26211","32689","37"});
		data.add(new String[] {"90","Suspensión delantera derecha","9454","77698","61"});
		data.add(new String[] {"91","Suspensión delantera izquierda","26509","70264","47"});
		data.add(new String[] {"92","Tambor delantero","18629","31083","55"});
		data.add(new String[] {"93","Tambor trasero","22652","25439","111"});
		data.add(new String[] {"94","Tanque de combustible","13661","50894","102"});
		data.add(new String[] {"95","Tanque de liquidos","26574","46042","70"});
		data.add(new String[] {"96","Tanque de reserva del radiador","10624","24470","112"});
		data.add(new String[] {"97","Tanque de suspension neumatica","8802","11117","116"});
		data.add(new String[] {"98","Transmisión (Caja de cambio)","11862","29641","81"});
		data.add(new String[] {"99","Tubo de escape delantero","34747","71362","57"});
		data.add(new String[] {"100","Turbocargador","34942","44690","20"});
		data.add(new String[] {"101","Varilla tensora delantera derecha","12923","21706","22"});
		data.add(new String[] {"102","Varilla tensora delantera izquierda","35164","49451","108"});
		data.add(new String[] {"103","Ventilador de acoplamiento","30694","51157","114"});
		data.add(new String[] {"104","Ventilador del radiador","66988","75704","104"});
		data.add(new String[] {"105","Ventilador electrico","8200","14599","103"});
		data.add(new String[] {"106","Viga del eje delantero","11703","18607","51"});
		data.add(new String[] {"107","Viga del eje trasero","9836","30470","72"});
		data.add(new String[] {"108","Volante","37781","50977","102"});

		String[] complements = {"tools", "navigation", "inventory"};
		int cant = 19;
		JPanel JP_CENTER = new Table(cant, data, title, complements);
		add(JP_CENTER, BorderLayout.CENTER);
	}

	public void setTrueSize(Component o, int a, int b) {
		o.setSize(a, b);
		o.setMinimumSize(o.getSize());
		o.setMinimumSize(o.getSize());
		o.setPreferredSize(o.getSize());
	}
}
