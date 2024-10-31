package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FormularioIngresos extends  JFrame{
	
	public FormularioIngresos() {
		super();
		
		setSize(1200,750);
		setVisible(true);
		
		// Fondo y decoración
		setBackground(Color.white);
		
		JButton realizar = new JButton();

		
		// Spring Layout
		SpringLayout spring = new SpringLayout();
		
		// Componentes del Layout
		JTextField cantidad = new JTextField();
		JLabel label = new JLabel("Cantidad: ");
		
		spring.addLayoutComponent(cantidad, label);
		
		// Layout principal
		
		BorderLayout principal = new BorderLayout();
		// principal.addLayoutComponent(spring, BorderLayout.CENTER);
	}
}