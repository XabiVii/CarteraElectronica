package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FormularioIngresos extends  JFrame{
	
	public FormularioIngresos() {
		super();
		
		setBackground(Color.WHITE);
		
		JFrame formulario = new JFrame();
		formulario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = formulario.getContentPane();
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout(layout);
		
		JButton realizar = new JButton();
		JTextField cantidad = new JTextField("Introduce aquí la cantidad", 15);
		JLabel label = new JLabel("Cantidad: ");
		contentPane.add(label);
		contentPane.add(cantidad);
		
		layout.putConstraint(SpringLayout.WEST, label, 5, SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, label, 5, SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, cantidad,
                5,
                SpringLayout.EAST, label);
		layout.putConstraint(SpringLayout.NORTH, cantidad,
                5,
                SpringLayout.NORTH, contentPane);
		
		formulario.pack();
		formulario.setSize(1200,750);
		formulario.setVisible(true);
	}
}