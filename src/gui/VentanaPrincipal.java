package gui;

import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;

import domain.Usuario;
import threads.PantallaConSalvapantallas;
import threads.SalvaPantallas;

public class VentanaPrincipal extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private PanelTabla panelTabla;
	private PanelUserSelection panelUser;
	private CreacionUsuario panelCreacionUsuario;
	private FormularioIngresos panelNuevoOpe;
	private SalvaPantallas panelSalvaPantallas;

	private final String TITULO="Cartera Electronica";

	private Image iconoCartera;
	
	private String rutaResources="resources/";
	
	private CardLayout navegacion;
	
	private Timer timersalvapantallas;
	
	public Usuario actual;

	
	public VentanaPrincipal() {
	super();
	setSize(1200,750);
	setLocationRelativeTo(null);
	setTitle(getTITULO());
	
	navegacion=new CardLayout();
	setLayout(navegacion);
	

	
	añadirPaneles();
		
	iconoCartera=new ImageIcon("resources/images/CarteraIcono.png").getImage();
	setIconImage(iconoCartera);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	}
	
	
	private void añadirPaneles() {
		
		panelCreacionUsuario = new CreacionUsuario(navegacion);

		panelTabla=new PanelTabla(navegacion, this);
		
		panelUser = new PanelUserSelection(navegacion,this,panelTabla);
		
		panelNuevoOpe=new FormularioIngresos(navegacion,panelTabla);
		
		panelSalvaPantallas=new SalvaPantallas(navegacion);

		
		navegacion.addLayoutComponent(panelTabla, "pTabla");
		navegacion.addLayoutComponent(panelUser, "pUser");
		navegacion.addLayoutComponent(panelCreacionUsuario, "pCrea");
		navegacion.addLayoutComponent(panelNuevoOpe, "pNuevo");
		navegacion.addLayoutComponent(panelSalvaPantallas, "pSalva");


		add(panelTabla);
		add(panelUser);
		add(panelCreacionUsuario);
		add(panelNuevoOpe);
		add(panelSalvaPantallas);
		
		navegacion.show(getContentPane(), "pUser");
		
    	timersalvapantallas = new Timer(60000, e -> {
            navegacion.show(getContentPane(), "pSalva");
        });
    	
    	//actividad usuario
    	addMouseMotionListener(new UserActivityListener());
        addKeyListener(new UserActivityListener());
        
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
            navegacion.show(getContentPane(), "pSalva");
        });    
    	timersalvapantallas.start();
    }


	public String getTITULO() {
		return TITULO;
	}
	
	
}
