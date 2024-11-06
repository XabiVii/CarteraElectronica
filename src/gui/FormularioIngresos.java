package gui;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioIngresos extends  JFrame{
	
		private CardLayout cardLayout;
		private GridLayout gridLayout;
		private JPanel jpanel;
		
	public FormularioIngresos() {
		
		super();
		
		setSize(1200, 750);
		
		cardLayout = new CardLayout();
		jpanel = new JPanel();
		gridLayout = new GridLayout();
		setLayout(null);
		setLocation(null);
		
		// Creamos los componentes
		
		// Empezamos con los Labels
		JLabel labelOpciones = new JLabel("Ingreso / Gasto");
		JLabel labelCantidad = new JLabel("Cantidad: ");
		JLabel labelFecha = new JLabel("Fecha de realización: ");
		JLabel labelDescripcion = new JLabel("Descripción: ");
		JLabel labelMetodoPago = new JLabel("Metodo de pago: ");
		JLabel labelTipoGasto = new JLabel("Tipo de pago: ");
		
		// Vamos con los datos que introduce el usuario:
		JComboBox opciones = new JComboBox<String>();
		JTextField cantidad = new JTextField("Introduce aquí la cantidad", 15);
		JTextField fecha = new JTextField("DD/MM/XXXX", 15);
		JTextField descripcion = new JTextField("Descripción", 15);
		JComboBox metodoPago = new JComboBox<String>();
		JComboBox tipoPago = new JComboBox<String>();
		
		// Creamos arrayList con las opciones: Ingreso y gasto
		ArrayList<String> ig = new ArrayList<String>();
		ig.add("Ingreso");
		ig.add("Gasto");
		
		// Creamos ArrayList con todos los métodos de pago
		ArrayList<String> metodos = new ArrayList<String>();
		metodos.add("Metálico");
		metodos.add("Domiciliación");
		metodos.add("Cheque");
		metodos.add("Bizum");
		metodos.add("Tarjeta");
		
		
		// Creamos un ArrayList con todos los tipos de pago disponibles		
		ArrayList<String> tiposPago = new ArrayList<String>();
		tiposPago.add("Ocio");
		tiposPago.add("Comida");
		tiposPago.add("Seguros");		
		
		
		// Botón para registrar la información
		JButton realizar = new JButton();
		
		
	}
}