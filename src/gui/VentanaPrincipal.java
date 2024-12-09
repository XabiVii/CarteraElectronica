package gui;

import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import threads.PantallaConSalvapantallas;

public class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private PanelTabla panelTabla;
	private PanelUserSelection panelUser;
	private CreacionUsuario panelCreacionUsuario;
	private FormularioIngresos panelNuevoOpe;

	private final String TITULO="Cartera Electronica";

	private Image iconoCartera;
	
	private String rutaResources="resources/";
	
	private CardLayout navegacion;
	

	
	public VentanaPrincipal() {
	super();
	
	setSize(1200,750);
	setLocationRelativeTo(null);
	setTitle(getTITULO());
	
	navegacion=new CardLayout();
	setLayout(navegacion);
	

	
	añadirPaneles();
	
	new PantallaConSalvapantallas(this);
	
	iconoCartera=new ImageIcon("resources/images/CarteraIcono.png").getImage();
	setIconImage(iconoCartera);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}
	
	
	private void añadirPaneles() {
		panelUser = new PanelUserSelection(navegacion);
		
		panelCreacionUsuario = new CreacionUsuario(navegacion);

		panelTabla=new PanelTabla(navegacion);
		
		panelNuevoOpe=new FormularioIngresos(navegacion);
		
		navegacion.addLayoutComponent(panelTabla, "pTabla");
		navegacion.addLayoutComponent(panelUser, "pUser");
		navegacion.addLayoutComponent(panelCreacionUsuario, "pCrea");
		navegacion.addLayoutComponent(panelNuevoOpe, "pNuevo");


		add(panelTabla);
		add(panelUser);
		add(panelCreacionUsuario);
		add(panelNuevoOpe);


		navegacion.show(getContentPane(), "pUser");
	}


	public String getTITULO() {
		return TITULO;
	}
	
	
}
