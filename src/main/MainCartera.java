package main;

import javax.swing.SwingUtilities;

import gui.VentanaPrincipal;
import persistencia.GestorBD;

public class MainCartera {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorBD gestorBD = new GestorBD();
		gestorBD.crearTablas();
		SwingUtilities.invokeLater(() -> new VentanaPrincipal());


	}

}
