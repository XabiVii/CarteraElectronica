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
	        for (int i = 1; i <= 31; i++) { //dias del 1 al 31, tendria que poner algo como que cuando eliges febrero solo se pueda 28
	            diasCB.addItem(String.valueOf(i));
	        }
	        
	        for (int i = 1945; i <= 2006; i++) { //años del 1945, una fecha limite que he querido poner pero se puede cambiar,
	            aniosCB.addItem(String.valueOf(i)); // hasta el 2006 que son el año que cumplen 18 este año
	        }
	        
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
	        
	        add(new JLabel("Fecha de nacimiento"));
	        JPanel fechaPanel = new JPanel(new GridLayout(1, 3, 5, 5));
	        fechaPanel.add(diasCB);
	        fechaPanel.add(mesCB);
	        fechaPanel.add(aniosCB);
	        add(fechaPanel);
	        
	        //botones
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
