package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
		
		private BorderLayout borderLayout;
		private GridLayout gridLayout;
		private CardLayout cardLayout;
		
	public FormularioIngresos(CardLayout cardLayout,PanelTabla principal) {
		super();
		
		// Hacemos algunos ajustes en el JPanel
		this.cardLayout=cardLayout;
		
		setSize(1200, 750);
			// Creamos el layout
		borderLayout = new BorderLayout();
		gridLayout = new GridLayout(7,2);
		
		JPanel formulario = new JPanel(gridLayout);
		setLayout(borderLayout);
		
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
		
		JComboBox<IngresoGasto> opciones = new JComboBox<IngresoGasto>(IngresoGasto.values());
		JTextField cantidad = new JTextField();
		
		// Para fechas 
		
		JComboBox<String> dias = new JComboBox<String>();
		
		for (int i = 1; i <= 31; i++) {
			dias.addItem(String.valueOf(i));
		}
		
		JComboBox<String> meses = new JComboBox<String>(new String[] {
				"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
		});
		
		JComboBox<String> anos = new JComboBox<String>();
		
		for (int i = 2025; i >= 2000; i--) {
			anos.addItem(String.valueOf(i));
		}
		
		JPanel fechaPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		fechaPanel.add(anos);
		fechaPanel.add(meses);
		fechaPanel.add(dias);
		//
		
		JTextField descripcion = new JTextField();
		JComboBox<MetodosPago> metodoPago = new JComboBox<MetodosPago>(MetodosPago.values());
		JComboBox<TiposPago> tipoPago = new JComboBox<TiposPago>(TiposPago.values());

		Dimension mx = new Dimension(50,50);
		opciones.setPreferredSize(mx);
		
		// Botón para registrar la información
		
		JButton confirmar = new JButton("Confirmar");
		JButton cancelar = new JButton("Cancelar");
		
			// Les ponemos colores a los botones
		cancelar.setBackground(Color.red);
		confirmar.setBackground(Color.GREEN);
		
			// Añadimos el actionListener
		cancelar.addActionListener(e ->{
			cardLayout.show(getParent(), "pTabla");
		});
		
			// Creamos el actionListener para el botón confirmar
		
		confirmar.addActionListener(e -> {
			try {
				// Recopilamos los datos
				String tipo = String.valueOf(opciones.getSelectedItem());
				Integer cant = Integer.parseInt((String) cantidad.getText());
				String fch = String.valueOf(anos.getSelectedItem()) + " - " + String.valueOf(meses.getSelectedItem()) + " - " + String.valueOf(dias.getSelectedItem());
				String desc = descripcion.getText();
				String metPag = String.valueOf(metodoPago.getSelectedItem());
				String tipoPag = String.valueOf(tipoPago.getSelectedItem());
				
				Operacion op = new Operacion(tipo, cant, fch, desc, metPag, tipoPag);
				
				// Añadir a base de datos
				GestorBD gestorBD= new GestorBD();
				gestorBD.insertarOperacion(op);
				
				JOptionPane.showMessageDialog(this, "Operación creada con éxito");
				
				principal.actualizarOpe();
				cardLayout.show(getParent(), "pTabla");
			} catch (Exception ex){
				JOptionPane.showMessageDialog(this, "Error al crear la operación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
								
		formulario.add(labelOpciones);
		formulario.add(opciones);
		formulario.add(labelCantidad);
		formulario.add(cantidad);
		formulario.add(labelFecha);
		formulario.add(fechaPanel);
		formulario.add(labelDescripcion);
		formulario.add(descripcion);
		formulario.add(labelMetodoPago);
		formulario.add(metodoPago);
		formulario.add(labelTipoPago);
		formulario.add(tipoPago);
		formulario.add(confirmar);
		formulario.add(cancelar);
		
		add(formulario, BorderLayout.CENTER);
		
		// Panel de arriba
		
		JPanel norte = new JPanel();
		JLabel titulo = new JLabel("Formulario INGRESO / GASTO");
		titulo.setForeground(Color.lightGray);
		titulo.setSize(500, 50);
		norte.add(titulo);
		norte.setBackground(Color.DARK_GRAY);
		
		add(norte, BorderLayout.NORTH);
		
		// Panel de abajo
		
		JPanel sur = new JPanel();
		// Ahí poner un thread de tiempo restante: que al llegar a 0 se salga al panel tabla
		add(sur, BorderLayout.SOUTH);
	}
}