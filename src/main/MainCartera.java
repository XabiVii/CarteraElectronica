package main;

import javax.swing.SwingUtilities;

import gui.VentanaPrincipal;
import persistencia.GestorBD;
import recursividad.CreacionRecursiva;

public class MainCartera {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorBD gestorBD = new GestorBD();
		gestorBD.crearTablas();
		SwingUtilities.invokeLater(() -> new VentanaPrincipal());

		CreacionRecursiva x= new CreacionRecursiva();
		x.copiaSeguridadResources();
	}

}
