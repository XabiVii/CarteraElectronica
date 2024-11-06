package gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.IngresoGasto;
import domain.MetodosPago;
import domain.TiposPago;

public class FormularioIngresos extends  JPanel{
	
		private GridLayout gridLayout;
		private CardLayout cardLayout;
		
	public FormularioIngresos(CardLayout cardLayout) {
		super();
		
		this.cardLayout=cardLayout;
		
		setSize(1200, 750);
		
		gridLayout = new GridLayout(7,2);
		setLayout(gridLayout);
		gridLayout.setHgap(50);
		gridLayout.setVgap(50);

		
		// Creamos los componentes
		
		// Empezamos con los Labels
		
		JLabel labelOpciones = new JLabel("Tipo de operación");
		JLabel labelCantidad = new JLabel("Cantidad: ");
		JLabel labelFecha = new JLabel("Fecha de realización: ");
		JLabel labelDescripcion = new JLabel("Descripción: ");
		JLabel labelMetodoPago = new JLabel("Metodo de pago: ");
		JLabel labelTipoPago = new JLabel("Tipo de pago: ");
		labelOpciones.setHorizontalAlignment(0);
		labelCantidad.setHorizontalAlignment(0);
		labelFecha.setHorizontalAlignment(0);
		labelDescripcion.setHorizontalAlignment(0);
		labelMetodoPago.setHorizontalAlignment(0);
		labelTipoPago.setHorizontalAlignment(0);

		// Vamos con los datos que introduce el usuario:
		
		JComboBox opciones = new JComboBox<>(IngresoGasto.values());
		JTextField cantidad = new JTextField("Introduce aquí la cantidad", 15);
		JTextField fecha = new JTextField("DD/MM/XXXX", 15);
		JTextField descripcion = new JTextField("Descripción", 15);
		JComboBox metodoPago = new JComboBox<>(MetodosPago.values());
		JComboBox tipoPago = new JComboBox<>(TiposPago.values());


		
		Dimension mx=new Dimension(50,50);
		opciones.setPreferredSize(mx);
		
		// Botón para registrar la información
		
		JButton confirmar=new JButton("Confirmar");
		JButton cancelar=new JButton("Cancelar");
		
		cancelar.addActionListener(e -> cardLayout.show(getParent(), "pTabla"));
		
		add(labelOpciones);
		add(opciones);
		add(labelCantidad);
		add(cantidad);
		add(labelFecha);
		add(fecha);
		add(labelDescripcion);
		add(descripcion);
		add(labelMetodoPago);
		add(metodoPago);
		add(labelTipoPago);
		add(tipoPago);
		add(confirmar);
		add(cancelar);

	}
}