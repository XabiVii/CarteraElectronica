package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelUserSelection extends JPanel{
	
	private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public PanelUserSelection(CardLayout navegacion) {
    	
    	setSize(1200,750);
    	setLayout(new FlowLayout(1, 30, 275)); //el contenedor
        JButton registro = new JButton("Inicio Sesión");
        registro.setFont(new Font("Arial", Font.BOLD, 14));
        
        this.setBackground(new Color(90,90,90));
        
        cardLayout = navegacion;
        cardPanel = new JPanel(cardLayout);
        
        //crear el panel de selección de usuario (Primer panel)
        //cargar la imagen para el botón
        ImageIcon nuevoUsuarioIcon = new ImageIcon("resources/images/Webp.net-resizeimage.png"); 
        JButton nuevoUsuarioFotoPrueba = new JButton(nuevoUsuarioIcon); //crea el botón con la imagen
        nuevoUsuarioFotoPrueba.setPreferredSize(new Dimension(100, 100)); //tamaño del boton que tiene la imagen
        nuevoUsuarioFotoPrueba.setBackground(new Color(245, 245, 245)); //sin borde visible para mas elegancia (ni se nota)
        nuevoUsuarioFotoPrueba.setBorderPainted(false);
        
        
        add(registro,BorderLayout.CENTER);
        add(nuevoUsuarioFotoPrueba,BorderLayout.CENTER);
        
        //crear el panel de añadir texto

        
        //al CardLayout le añado los dos panales
        cardPanel.add(this, "SeleccionUsuario"); //primer panel

        //Nuevo Usuario
        nuevoUsuarioFotoPrueba.addActionListener(e -> {
            cardLayout.show(getParent(), "pCrea"); 
        });
        
        //Cancelar
       /* panelCreacionUsuario.getCancelarButton().addActionListener(e -> {
            cardLayout.show(cardPanel, "SeleccionUsuario"); //vuelve pa atras
        });*/
        
        
        
        registro.addActionListener(e -> {
            cardLayout.show(getParent(), "pTabla"); //vuelve pa atras
        	/*
            String username = JOptionPane.showInputDialog(null, "username:");
            String password = JOptionPane.showInputDialog(null, "password:");
            if (username!=null && password!=null && username.equals("usuario") && password.equals("contraseña")) {
                JOptionPane.showMessageDialog(null, "Bienvenido");//esto es para probar de cara a lo proximo
            } else {
                JOptionPane.showMessageDialog(null, "Invalido");
            }*/
        });
        
    }
    
}
