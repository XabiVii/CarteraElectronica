package threads;

import javax.swing.*;

import gui.VentanaPrincipal;

import java.awt.CardLayout;
import java.awt.event.*;

public class PantallaConSalvapantallas {
	private Timer timersalvapantallas;
    private CardLayout navegacion;
    private JPanel panelAnterior=null;
    private VentanaPrincipal ventanaPrincipal;
    
    public PantallaConSalvapantallas(VentanaPrincipal ventanaPrincipal ,CardLayout navegacion) {
    	this.ventanaPrincipal = ventanaPrincipal;
    	this.navegacion=navegacion;
    	//2 minutos de incactividad para que se inice
    	timersalvapantallas = new Timer(60000, e -> {
            navegacion.show(ventanaPrincipal.getContentPane(), "pSalva");
        });
    	
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
    	timersalvapantallas = new Timer(60000, e -> {
            navegacion.show(ventanaPrincipal.getContentPane(), "pSalva");
        });    
    	System.out.println("holaa");
    	timersalvapantallas.start();
    }
    
}
