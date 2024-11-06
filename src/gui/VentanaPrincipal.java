package gui;

import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private PanelTabla panelTabla;
	private PanelUserSelection panelUser;
	private CreacionUsuario panelCreacionUsuario;
	
	private final String TITULO="Cartera Electronica";

	private Image iconoCartera;
	
	private String rutaResources="resources/";
	
	private CardLayout navegacion;
	

	
	public VentanaPrincipal() {
	super();
	
	setSize(1200,750);
	setLocationRelativeTo(null);
	setTitle(getTITULO());
	setResizable(false);
	
	navegacion=new CardLayout();
	setLayout(navegacion);
	

	
	añadirPaneles();
	
	
	
	iconoCartera=new ImageIcon("resources/images/CarteraIcono.png").getImage();
	setIconImage(iconoCartera);
	setVisible(true);
	}
	
	
	private void añadirPaneles() {
		panelUser = new PanelUserSelection(navegacion);
		
		panelCreacionUsuario = new CreacionUsuario(navegacion);

		
		panelTabla=new PanelTabla();
		
		navegacion.addLayoutComponent(panelTabla, "pTabla");
		navegacion.addLayoutComponent(panelUser, "pUser");
		navegacion.addLayoutComponent(panelCreacionUsuario, "pCrea");

		add(panelTabla);
		add(panelUser);
		add(panelCreacionUsuario);


		navegacion.show(getContentPane(), "pUser");
	}


	public String getTITULO() {
		return TITULO;
	}
	
	
}
