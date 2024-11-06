package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreacionUsuario extends JPanel{
	 private static final long serialVersionUID = 1L;
	    
	    private JTextField Inombre, Iapellido, Iid, Icorreo, Icontrasena;
	    private JButton aceptar, cancelar;
	    private Usuario usuario;
	    private JComboBox<String> diasCB, mesCB, aniosCB;
	   
	    public CreacionUsuario() {
	        setLayout(new GridLayout(7, 2, 10, 10));
	        
	        Inombre = new JTextField();
	        Iapellido = new JTextField();
	        Iid = new JTextField();
	        Icorreo = new JTextField();
	        Icontrasena = new JTextField();
	        
	        aceptar = new JButton("Aceptar");
	        cancelar = new JButton("Cancelar");
	        
	        //año, mes y dia para la barrita
	        diasCB = new JComboBox<>();
	        mesCB = new JComboBox<>(new String[] {
	                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
	                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
	            });
	        aniosCB = new JComboBox<>();
	        
	       //llenarlo con los dias y los meses
	        
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
	        add(aceptar);
	        add(cancelar);

	        //Aceptar
	        aceptar.addActionListener(e -> {
	           usuario = new Usuario(Inombre.getText(), Iapellido.getText(), 
	            		Icorreo.getText(), Icontrasena.getText(), Iid.getText()); //todo lo que conlleva un usuario
	        });
	    }

	    public JButton getCancelarButton() {
	        return cancelar;
	    }

	    public Usuario getUsuario() {
	        return usuario;
	    }
	    
	    
}
