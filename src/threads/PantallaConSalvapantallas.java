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
    	ventanaPrincipal.addMouseMotionListener(new UserActivityListener());
        ventanaPrincipal.addKeyListener(new UserActivityListener());
        
        timersalvapantallas.start();
    }
    
	private class UserActivityListener extends KeyAdapter implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent e) {
        	reseteotimer(); //se reinicia 
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	reseteotimer(); // Reiniciar el temporizador al presionar una tecla
        }

        //metodos sin nada, vacíos necesarios para implementar MouseMotionListener
        @Override
        public void mouseDragged(MouseEvent e) {}
    }
    private void reseteotimer() {
        if (timersalvapantallas.isRunning()) {
            timersalvapantallas.stop(); //detiene el timer
        }
        timersalvapantallas.start(); //reinicia el timer
    }
    
}
