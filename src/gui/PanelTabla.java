package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import domain.Operacion;
import domain.Usuario;
import persistencia.GestorBD;

public class PanelTabla extends JPanel {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private CardLayout navegacion;
	private JTable tabla;
	private DefaultTableModel modeloDatos;
	private JScrollPane scrollPaneTabla;

	private JButton introducirNuevo;
	private JButton mediaGasto;
	private JButton mediaIngreso;
	private GestorBD gestorBD;
	private static List<Operacion> operaciones;
	private Vector<String> cabecera = new Vector<String>(Arrays.asList("FECHA", "IMPORTE", "TIPO", "OPERACION", "BALANCE"));

	Usuario actual;

	public PanelTabla(CardLayout cardLayout,VentanaPrincipal principal) {
		gestorBD=new GestorBD();
		setLayout(new BorderLayout());
		navegacion = cardLayout;
		actual=principal.actual;
		initTabla();
		
		operaciones=new ArrayList<>();
		int indice_Balance = 0;
		
		for (Operacion ope: operaciones) {
			modeloDatos.addRow(new Object[] { ope.getFecha(), ope.getCantidad(), ope.getTipoPago(), ope.getTipoOperacion(), gestorBD.getBalance2().get(indice_Balance)});
			indice_Balance++;
		}
		
		

		scrollPaneTabla = new JScrollPane(tabla);
		scrollPaneTabla.setBorder(new TitledBorder("Semana1"));
		scrollPaneTabla.setPreferredSize(new Dimension(100, 100));

		JPanel x = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 3);
		gridLayout.setHgap(50);
		gridLayout.setVgap(100);
		x.setLayout(gridLayout);
		x.setSize(1000, 150);
		scrollPaneTabla.setBackground(new Color(90,90,90));
		x.setPreferredSize(new Dimension(100, 50));

		introducirNuevo = new JButton("Nuevo registro");
		mediaGasto = new JButton("Media de gastos");
		mediaIngreso = new JButton("Media de ingresos");

		new JOptionPane();
		mediaGasto.addActionListener(e -> JOptionPane.showMessageDialog(this, "La media de los gastos es: " + gestorBD.getMediaGastos()));
		
		new JOptionPane();
		mediaIngreso.addActionListener(e -> JOptionPane.showMessageDialog(this, "La media de Ingreso es " + gestorBD.getMediaIngresos()));
		
		introducirNuevo.addActionListener(e -> cardLayout.show(getParent(), "pNuevo"));

		x.add(introducirNuevo);
		x.add(mediaGasto);
		x.add(mediaIngreso);
		x.setBackground(new Color(90,90,90));

		add(scrollPaneTabla, BorderLayout.CENTER);
		add(x, BorderLayout.SOUTH);

		setSize(1200, 750);

	}
	
	public void actualizarOpe() {
		operaciones=gestorBD.getOperaciones(actual.getId());
		System.out.println(operaciones);
		for (int i = 0; i < modeloDatos.getRowCount(); i++) {
			modeloDatos.removeRow(i);
		}
		int indice_Balance = 0;
		for (Operacion ope: operaciones) {
			modeloDatos.addRow(new Object[] { ope.getFecha(), ope.getCantidad(), ope.getTipoPago(), ope.getTipoOperacion(), gestorBD.getBalance2().get(indice_Balance)});
			indice_Balance++;
		}
	}
	

	private void initTabla() {

		modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);
		
		tabla = new JTable(modeloDatos);
		tabla.getTableHeader().setReorderingAllowed(false);

		TableCellRenderer tableCell = new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				
				Color c;


				if (row % 2 == 0) {
					if (hasFocus) {
						c = table.getSelectionBackground();
					} else {
						c = new Color(250, 249, 249);
						if (column==3 && value.toString().equals("INGRESO")) {
							c=Color.GREEN;
						}else if(column==3 && value.toString().equals("GASTO")) {
							c=Color.RED;					
						}
					}
					JLabel x = new JLabel(value.toString());
					if (value instanceof Integer || column==0) {
						x.setHorizontalAlignment(0);
					} else {
						x.setHorizontalAlignment(2);
					}
					x.setOpaque(true);
					x.setBackground(c);
					return x;
				} else {
					if (hasFocus) {
						c = table.getSelectionBackground();
					} else {
						c = new Color(190, 227, 219);
						if (column==3 && value.toString().equals("INGRESO")) {
							c=Color.GREEN;
						}else if(column==3 && value.toString().equals("GASTO")) {
							c=Color.RED;					
						}
					}
						JLabel x = new JLabel(value.toString());

						if (value instanceof Integer || column==0) {
							x.setHorizontalAlignment(0);
						} else {
							x.setHorizontalAlignment(2);
						}
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					
				}

			}
				
		};
		tabla.setRowHeight(26);
		tabla.setDefaultRenderer(Object.class, tableCell);
		tabla.getTableHeader().setDefaultRenderer(new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				JLabel x = new JLabel(value.toString());
				x.setHorizontalAlignment(0);

			return x;}
		});
		// Se modifica el modelo de selección de la tabla para que se pueda selecciona
		// únicamente una fila
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Se define el comportamiento el evento de selección de una fila de la tabla
		tabla.getSelectionModel().addListSelectionListener(e ->	{System.out.println("aaa");});

	}

}
