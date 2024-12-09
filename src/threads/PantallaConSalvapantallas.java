package threads;

import javax.swing.*;

import gui.VentanaPrincipal;

import java.awt.event.*;

public class PantallaConSalvapantallas {
	private Timer timersalvapantallas;
    private VentanaPrincipal ventanaPrincipal;
    
    public PantallaConSalvapantallas(VentanaPrincipal ventanaPrincipal) {
    	this.ventanaPrincipal = ventanaPrincipal;
    	//2 minutos de incactividad para que se inice
    	timersalvapantallas = new Timer(120000, e -> {
            ventanaPrincipal.setVisible(false); //ocultar la ventana principal
            SalvaPantallas salvapantallas = new SalvaPantallas();
            salvapantallas.setVisible(true);
        });
    	timersalvapantallas.setRepeats(false); //se ejecuta solo una vez hasta que se reinicie asi no hay 400 salva pantallas
    	
    	//actividad usuario
    	//addMouseMotionListener(new UserActivityListener());
        //addKeyListener(new UserActivityListener());
        
        timersalvapantallas.start();
    }
    
}
