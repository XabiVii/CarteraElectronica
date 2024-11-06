package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelUserSelection extends JPanel{
	private static final long serialVersionUID = 1L;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private CreacionUsuario panelCreacionUsuario;

    public PanelUserSelection() {
    	
    	setLayout(new BorderLayout()); //el contenedor
        JButton registro = new JButton("Inicio Sesión");
        registro.setFont(new Font("Arial", Font.BOLD, 14));
        registro.setBackground(new Color(30, 144, 255));
        registro.setForeground(Color.WHITE);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        
        //crear el panel de selección de usuario (Primer panel)
        JPanel panelSeleccionUsuario = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelSeleccionUsuario.setBackground(new Color(224, 255, 255));
        
        //cargar la imagen para el botón
        ImageIcon nuevoUsuarioIcon = new ImageIcon("imagenes/Webp.net-resizeimage.png"); 
        JButton nuevoUsuarioFotoPrueba = new JButton(nuevoUsuarioIcon); //crea el botón con la imagen
        nuevoUsuarioFotoPrueba.setPreferredSize(new Dimension(100, 100)); //tamaño del boton que tiene la imagen
        nuevoUsuarioFotoPrueba.setBackground(new Color(245, 245, 245)); //sin borde visible para mas elegancia (ni se nota)
        nuevoUsuarioFotoPrueba.setBorderPainted(false);
        
        
        panelSeleccionUsuario.add(registro);
        panelSeleccionUsuario.add(nuevoUsuarioFotoPrueba);
        
        //crear el panel de añadir texto
        panelCreacionUsuario = new CreacionUsuario(); //segundo panel
        panelCreacionUsuario.setBackground(Color.WHITE);
        
        //al CardLayout le añado los dos panales
        cardPanel.add(panelSeleccionUsuario, "SeleccionUsuario"); //primer panel
        cardPanel.add(panelCreacionUsuario, "CreacionUsuario"); //segundo panel

        //Nuevo Usuario
        nuevoUsuarioFotoPrueba.addActionListener(e -> {
            cardLayout.show(cardPanel, "CreacionUsuario"); 
        });
        
        //Cancelar
        panelCreacionUsuario.getCancelarButton().addActionListener(e -> {
            cardLayout.show(cardPanel, "SeleccionUsuario"); //vuelve pa atras
        });
    }
}
