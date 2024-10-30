package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private PanelTabla panelTabla;
	
	private final String TITULO="Cartera Electronica";

	private Image iconoCartera;
	
	private String rutaResources="resources/";
	
	public VentanaPrincipal() {
	super();
	
	setSize(1200,750);
	setLocationRelativeTo(null);
	setTitle(getTITULO());
	setResizable(false);
	setLayout(null);
	
	iconoCartera=new ImageIcon("resources/images/CarteraIcono.png").getImage();
	setIconImage(iconoCartera);
	
	añadirPaneles();
	
	setVisible(true);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void añadirPaneles() {
		panelTabla=new PanelTabla();
		
		
		
		
		add(panelTabla);
	}


	public String getTITULO() {
		return TITULO;
	}
	
	
}
