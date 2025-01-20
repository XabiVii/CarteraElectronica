package gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.Usuario;
import persistencia.GestorBD;


public class CreacionUsuario extends JPanel{
	 private static final long serialVersionUID = 1L;
	    
	    private JTextField Inombre, Iapellido, Iid, Icorreo, Icontrasena;
	    private JButton aceptar, cancelar;
	    private Usuario usuario;
	    private JComboBox<String> diasCB, mesCB, aniosCB;
	    @SuppressWarnings("unused")
		private CardLayout cardLayout;
	    private JLabel correoErrorLabel;
	    private static int countIDUser;

	    public CreacionUsuario(CardLayout cardLayout) {
	    	this.cardLayout=cardLayout;
	        setLayout(new GridLayout(7, 2, 10, 10));
	        
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
	        add(new JLabel("Nombre", JLabel.CENTER));
	        add(Inombre);
	        add(new JLabel("Apellido", JLabel.CENTER));
	        add(Iapellido);
	        add(new JLabel("Contraseña", JLabel.CENTER));
	        add(Icontrasena);
	        add(new JLabel("DNI", JLabel.CENTER));
	        add(Iid);
	        add(new JLabel("Email", JLabel.CENTER));
	        add(Icorreo);
	        
	        correoErrorLabel = new JLabel("");
	        correoErrorLabel.setForeground(Color.RED);
	        //add(correoErrorLabel);// Este era tu error
	        
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
	        
	        
	        add(new JLabel("Fecha de nacimiento", JLabel.CENTER));
	        
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

	        aceptar.addActionListener(e -> {
	            try {
	                String nombre = Inombre.getText();
	                String apellido = Iapellido.getText();
	                String correo = Icorreo.getText();
	                String contrasena = Icontrasena.getText();
	                int dia = Integer.parseInt((String) diasCB.getSelectedItem());
	                int mes = mesCB.getSelectedIndex() + 1; //los índices en los JComboBox comienzan en 0 pero con sumando 1 hago que empiecen por enero
	                int anio = Integer.parseInt((String) aniosCB.getSelectedItem());
	                LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia); //junto todo, como en R lo de lubridate
	                
	                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
	                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }

	                //Crear un nuevo usuario
	                
	                Usuario nuevoUsuario = new Usuario(nombre, apellido, correo, contrasena,countIDUser, fechaNacimiento);
	                countIDUser++;
	                System.out.println(nuevoUsuario.getId());
	                //Guardar en la base de datos
	                GestorBD gestorBD = new GestorBD();
	                gestorBD.insertarUsuario(nuevoUsuario);

	                JOptionPane.showMessageDialog(this, "Usuario creado con éxito");
	                
	                //limpiar
	                Inombre.setText("");
	                Iapellido.setText("");
	                Icorreo.setText("");
	                Icontrasena.setText("");
	                diasCB.setSelectedIndex(0);  
	                mesCB.setSelectedIndex(0);  
	                aniosCB.setSelectedIndex(0); 
	            
	            } catch (Exception ex) {
	                JOptionPane.showMessageDialog(this, "Error al crear el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	            }
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
