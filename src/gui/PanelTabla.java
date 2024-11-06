package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private CardLayout navegacion;
	private JTable tabla;
	private DefaultTableModel modeloDatos;
	private JScrollPane scrollPaneTabla;
	
	private JButton introducirNuevo;
	private JButton mediaGasto;
	private JButton mediaIngreso;

	
	public PanelTabla(CardLayout cardLayout) {
		setLayout(new BorderLayout());
		navegacion=cardLayout;
		
		
		
		Vector<String> cabecera = new Vector<String>(Arrays.asList("FECHA", "IMPORTE", "TIPO", "OPERACION","BALANCE"));

		modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);

		tabla=new JTable(modeloDatos);
		
		tabla.getTableHeader().setReorderingAllowed(false);

		
		scrollPaneTabla = new JScrollPane(tabla);
		scrollPaneTabla.setBorder(new TitledBorder("Semana1"));
		scrollPaneTabla.setPreferredSize(new Dimension(100,100));
		
		
		
		
		JPanel x= new JPanel();
		GridLayout gridLayout=new GridLayout(1,3);
		gridLayout.setHgap(50);
		gridLayout.setVgap(100);
		x.setLayout(gridLayout);
		x.setSize(1000,150);
		x.setBackground(Color.black);
		x.setPreferredSize(new Dimension(100,50));
		
		
		introducirNuevo=new JButton("Nuevo registro");
		mediaGasto=new JButton("Media de gastos");
		mediaIngreso=new JButton("Media de ingresos");
		
		introducirNuevo.addActionListener(e -> cardLayout.show(getParent(), "pNuevo"));
		
		x.add(introducirNuevo);
		x.add(mediaGasto);
		x.add(mediaIngreso);

		add(scrollPaneTabla,BorderLayout.CENTER);
		add(x,BorderLayout.SOUTH);

		
		setSize(1200,750);
		setBackground(Color.BLACK);
		
		
	}

}
