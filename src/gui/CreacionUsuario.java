package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Usuario;

public class CreacionUsuario extends JPanel{
	 private static final long serialVersionUID = 1L;
	    
	    private JTextField Inombre, Iapellido, Iid, Icorreo, Icontrasena;
	    private JButton aceptar, cancelar;
	    private Usuario usuario;
	    private JComboBox<String> diasCB, mesCB, aniosCB;
	    private CardLayout cardLayout;
	    private JLabel correoErrorLabel;

	    public CreacionUsuario(CardLayout cardLayout) {
	    	this.cardLayout=cardLayout;
	        setLayout(new GridLayout(8, 2, 10, 10));
	        
	        // Creamos los campos que necesitamos saber para la creación del usuario
	        Inombre = new JTextField();
	        Iapellido = new JTextField();
	        Iid = new JTextField();
	        Icorreo = new JTextField();
	        Icontrasena = new JTextField();
	        
	        // Creamos lo botones
	        aceptar = new JButton("Aceptar");
	        cancelar = new JButton("Cancelar");
	        
	        // Modificamos el fondo de nuestros botones
	        aceptar.setBackground(new Color(60, 179, 113)); // Verde
	        aceptar.setForeground(Color.WHITE);
	        cancelar.setBackground(new Color(220, 20, 60)); // Rojo
	        cancelar.setForeground(Color.WHITE);
	        
	        aceptar.setBackground(new Color(60, 179, 113)); 
	        aceptar.setForeground(Color.WHITE);
	        cancelar.setBackground(new Color(220, 20, 60)); 
	        cancelar.setForeground(Color.WHITE);
	        
	        
	        //año, mes y dia para la barrita
	        diasCB = new JComboBox<>();
	        mesCB = new JComboBox<>(new String[] {
	                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
	                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
	            });
	        aniosCB = new JComboBox<>();
	        
	       //llenarlo con los dias y los meses
	        for (int i = 1; i <= 31; i++) { //dias del 1 al 31, tendria que poner algo como que cuando eliges febrero solo se pueda 28
	            diasCB.addItem(String.valueOf(i));
	        }
	        
	        for (int i = 1945; i <= 2006; i++) { //años del 1945, una fecha limite que he querido poner pero se puede cambiar,
	            aniosCB.addItem(String.valueOf(i)); // hasta el 2006 que son el año que cumplen 18 este año
	        }
	        
	        // Creamos todos los JLabels
	        add(new JLabel("Nombre"));
	        add(Inombre);
	        add(new JLabel("Apellido"));
	        add(Iapellido);
	        add(new JLabel("Contraseña"));
	        add(Icontrasena);
	        add(new JLabel("DNI"));
	        add(Iid);
	        add(new JLabel("Email"));
	        add(Icorreo);
	        
	        correoErrorLabel = new JLabel("");
	        correoErrorLabel.setForeground(Color.RED);
	        add(correoErrorLabel);
	        
	        Icorreo.addFocusListener(new FocusListener() {
				
				@Override
				public void focusLost(FocusEvent e) {
					String email = Icorreo.getText();
					if (email.contains("@")) {
						 correoErrorLabel.setText(""); 
					}else {
						correoErrorLabel.setText("El email debe contener '@'");
					}
					
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
	        
	        
	        add(new JLabel("Fecha de nacimiento"));
	        JPanel fechaPanel = new JPanel(new GridLayout(1, 3, 5, 5));
	        fechaPanel.add(diasCB);
	        fechaPanel.add(mesCB);
	        fechaPanel.add(aniosCB);
	        add(fechaPanel);
	        
	        // Creamos los botones botones
	        add(aceptar);
	        add(cancelar);
	        
	        getCancelarButton().addActionListener(e -> {
	            cardLayout.show(getParent(), "pUser"); //vuelve pa atras
	        });
	        
	       
	        getAceptarButton().addActionListener(e -> {
	        	cardLayout.show(getParent(), "pUser"); // vuelve pa atras
	        });


	    }
	    
	    public JButton getAceptarButton() {
	    	return aceptar;
	    }
	    public JButton getCancelarButton() {
	        return cancelar;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }
	    
	    
}
