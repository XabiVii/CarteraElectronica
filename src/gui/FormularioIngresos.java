package gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormularioIngresos extends  JPanel{
	
	public FormularioIngresos() {
		super();
		
		setSize(1200,750);
		setLayout(null);
		setVisible(true);
		setLocation(null);
		
		// Fondo y decoración
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton realizar = new JButton();
		JTextField cantidad = new JTextField();
		
		// Spring Layout
	}
}