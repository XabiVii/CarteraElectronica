package gui;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FormularioIngresos extends  JPanel{
	
	public FormularioIngresos() {
		super();
		
		JFrame formulario = new JFrame();
		formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = formulario.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		JButton realizar = new JButton();
		
		
		
		JTextField cantidad = new JTextField("Introduce aquí la cantidad", 15);
		JLabel labelCantidad = new JLabel("Cantidad: ");
		contentPane.add(labelCantidad);
		contentPane.add(cantidad);
		
		layout.putConstraint(SpringLayout.WEST, labelCantidad, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, labelCantidad, 5, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, cantidad,
                5,
                SpringLayout.EAST, labelCantidad);
		layout.putConstraint(SpringLayout.NORTH, cantidad,
                5,
                SpringLayout.NORTH, contentPane);
		
		formulario.pack();
		formulario.setSize(1200,750);
		formulario.setVisible(true);
	}
}