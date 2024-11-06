package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class PanelTabla extends JPanel {

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
		navegacion = cardLayout;

		initTabla();
		
		modeloDatos.addRow(new Object[] { "11/11", "700", "Ocio", "Gasto","1000" });

		scrollPaneTabla = new JScrollPane(tabla);
		scrollPaneTabla.setBorder(new TitledBorder("Semana1"));
		scrollPaneTabla.setPreferredSize(new Dimension(100, 100));

		JPanel x = new JPanel();
		GridLayout gridLayout = new GridLayout(1, 3);
		gridLayout.setHgap(50);
		gridLayout.setVgap(100);
		x.setLayout(gridLayout);
		x.setSize(1000, 150);
		x.setBackground(Color.LIGHT_GRAY);
		x.setPreferredSize(new Dimension(100, 50));

		introducirNuevo = new JButton("Nuevo registro");
		mediaGasto = new JButton("Media de gastos");
		mediaIngreso = new JButton("Media de ingresos");

		introducirNuevo.addActionListener(e -> cardLayout.show(getParent(), "pNuevo"));

		x.add(introducirNuevo);
		x.add(mediaGasto);
		x.add(mediaIngreso);

		add(scrollPaneTabla, BorderLayout.CENTER);
		add(x, BorderLayout.SOUTH);

		setSize(1200, 750);
		setBackground(Color.BLACK);

	}

	private void initTabla() {
		Vector<String> cabecera = new Vector<String>(Arrays.asList("FECHA", "IMPORTE", "TIPO", "OPERACION", "BALANCE"));

		modeloDatos = new DefaultTableModel(new Vector<Vector<Object>>(), cabecera);

		tabla = new JTable(modeloDatos);

		tabla.getTableHeader().setReorderingAllowed(false);

		TableCellRenderer tableCell = new TableCellRenderer() {

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {

				if (row % 2 == 0) {
					Color c;
					if (hasFocus) {
						c = table.getSelectionBackground();
					} else {
						c = new Color(250, 249, 249);
					}
					if (value.toString() == "MARVEL") {
						JLabel x = new JLabel("a");
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					} else if (value.toString() == "DC") {
						JLabel x = new JLabel("a");
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					} else {
						JLabel x = new JLabel(value.toString());
						if (value instanceof Integer) {
							x.setHorizontalAlignment(0);
						} else {
							x.setHorizontalAlignment(2);
						}
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					}

				} else {
					Color c;
					if (hasFocus) {
						c = table.getSelectionBackground();
					} else {
						c = new Color(190, 227, 219);
					}
					if (value.toString() == "MARVEL") {
						JLabel x = new JLabel("");
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					} else if (value.toString() == "DC") {
						JLabel x = new JLabel("a");
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					} else {
						JLabel x = new JLabel(value.toString());

						if (value instanceof Integer) {
							x.setHorizontalAlignment(0);
						} else {
							x.setHorizontalAlignment(2);
						}
						x.setOpaque(true);
						x.setBackground(c);
						return x;
					}
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
/*
				if (table.getValueAt(1, column) instanceof String) {
					x.setHorizontalAlignment(2);

				} else {
					x.setHorizontalAlignment(0);

				}
				return x;
			}*/return x;}
		});
		// Se modifica el modelo de selección de la tabla para que se pueda selecciona
		// únicamente una fila
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Se define el comportamiento el evento de selección de una fila de la tabla
		tabla.getSelectionModel().addListSelectionListener(e ->	{System.out.println("aaa");});

	}

}
