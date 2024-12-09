package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.IngresoGasto;
import domain.MetodosPago;
import domain.Operacion;
import domain.TiposPago;
import persistencia.GestorBD;

public class FormularioIngresos extends  JPanel{
	
		private GridLayout gridLayout;
		private CardLayout cardLayout;
		
	public FormularioIngresos(CardLayout cardLayout) {
		super();
		
		// Hacemos algunos ajustes en el JPanel
		this.cardLayout=cardLayout;
		
		setSize(1200, 750);
			// Creamos el layout
		gridLayout = new GridLayout(7,2);
		setLayout(gridLayout);
		gridLayout.setHgap(50);
		gridLayout.setVgap(50);

		
		// Creamos los componentes del layout
		
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
		JTextField cantidad = new JTextField();
		JTextField fecha = new JTextField();
		JTextField descripcion = new JTextField();
		JComboBox metodoPago = new JComboBox<>(MetodosPago.values());
		JComboBox tipoPago = new JComboBox<>(TiposPago.values());

		Dimension mx = new Dimension(50,50);
		opciones.setPreferredSize(mx);
		
		// Botón para registrar la información
		
		JButton confirmar = new JButton("Confirmar");
		JButton cancelar = new JButton("Cancelar");
		
			// Les ponemos colores a los botones
		cancelar.setBackground(Color.red);
		confirmar.setBackground(Color.GREEN);
		
			// Añadimos el actionListener
		cancelar.addActionListener(e -> cardLayout.show(getParent(), "pTabla"));
		
			// Creamos el actionListener para el botón confirmar
		
		confirmar.addActionListener(e -> {
			try {
				// Recopilamos los datos
				String tipo = String.valueOf(opciones.getSelectedItem());
				Integer cant = Integer.parseInt((String) cantidad.getText());
				String fch = fecha.getText();
				String desc = descripcion.getText();
				String metPag = String.valueOf(metodoPago.getSelectedItem());
				String tipoPag = String.valueOf(tipoPago.getSelectedItem());
				
				Operacion op = new Operacion(tipo, cant, fch, desc, metPag, tipoPag);
				
				// Añadir a base de datos
				
				
				JOptionPane.showMessageDialog(this, "Operación creada con éxito");
				
			} catch (Exception ex){
				JOptionPane.showMessageDialog(this, "Error al crear la operación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
				
				
				
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