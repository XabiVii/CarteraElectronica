package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioIngresos extends  JPanel{
	
	public FormularioIngresos() {
		super();
		
		setSize(1200, 750);
		setLayout(null);
		setLocation(null);
		
		// Creamos los componentes
		
		// Empezamos con los Labels
		JLabel labelCantidad = new JLabel("Cantidad: ");
		JLabel labelFecha = new JLabel("Fecha de realización: ");
		JLabel labelDescripcion = new JLabel("Descripción: ");
		JLabel labelMetodoPago = new JLabel("Metodo de pago: ");
		JLabel labelTipoGasto = new JLabel("Tipo de pago: ");
		
		// Vamos con los datos que introduce el usuario:
		JTextField cantidad = new JTextField("Introduce aquí la cantidad", 15);
		JTextField fecha = new JTextField("DD/MM/XXXX", 15);
		JTextField descripcion = new JTextField("Descripción", 15);
		JComboBox metodoPago = new JComboBox<String>();
		JComboBox tipoPago = new JComboBox<String>();
		
		// Botón para registrar la información
		JButton realizar = new JButton();
		
		
	}
}