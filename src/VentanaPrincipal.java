import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.Date;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JTable;
import javax.swing.JTextArea;

public class VentanaPrincipal implements ActionListener, TreeSelectionListener {
	//Variables primitivas
	private int altoPrograma = 700;
	private int anchoPrograma = 1000;
	private boolean itemSelec = false;
	private String imageResource;
	
	//Componentes Swing
	private JFrame frame;
	private JPanel panelOpciones, panelRuta, panelDatos, panelFiltro, panelFiltroOpcionesIzquierda, panelFiltroOpcionesDerecha, panelFiltroOpciones1, panelFiltroOpciones2, panelFiltroOpciones3, panelBusqueda;
	private JButton btnNuevo, btnAbrir, btnCopiar, btnEliminar, btnTestigos, btnVisados, btnCroquis, btnEmpresa, btnOpciones, btnAyuda, btnAcerca, btnSalir;
	private JTree arbol;
	private JScrollPane scrollPaneArbol;
	private GridBagConstraints c;
	private JTextArea pdTa;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, anchoPrograma, altoPrograma);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Border border = BorderFactory.createLineBorder(Color.black);
		
		panelOpciones = new JPanel();
		panelOpciones.setBounds(0, 0, 1008, 50);
		panelOpciones.setLayout(new GridLayout(0, 14, 0, 5));
		panelOpciones.setBackground(Color.WHITE);
		panelOpciones.setBorder(border);
		createPanelOpciones();

		panelRuta = new JPanel();
		panelRuta.setBounds(0, 50, 441, 272);
		panelRuta.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelRuta.setBackground(Color.WHITE);
		panelRuta.setBorder(border);
		createVentanaRuta();
		
		panelDatos = new JPanel();
		panelDatos.setBounds(0, 322, 441, 339);
		panelDatos.setLayout(new GridBagLayout());
		panelDatos.setBackground(Color.WHITE);
		panelDatos.setBorder(border);
		createPanelDatos();

		TitledBorder tb = BorderFactory.createTitledBorder(border, "Filtro de búsqueda");
		panelFiltro = new JPanel();
		panelFiltro.setBounds(441, 50, 567, 248);
		panelFiltro.setBackground(Color.WHITE);
		panelFiltro.setBorder(tb);
		panelFiltro.setLayout(null);
		createPanelFiltro();
		
		
		panelBusqueda = new JPanel();
		panelBusqueda.setBounds(440, 297, 544, 364);
		panelBusqueda.setBackground(Color.WHITE);
		panelBusqueda.setLayout(new BorderLayout());
		panelBusqueda.setBorder(border);
		createPanelBusqueda();
		
		frame.getContentPane().add(panelOpciones);
		frame.getContentPane().add(panelRuta);
		frame.getContentPane().add(panelDatos);
		frame.getContentPane().add(panelFiltro);
		frame.getContentPane().add(panelBusqueda);
		
	
		
	}
	private void createPanelOpciones() {
		btnNuevo = new JButton("Nuevo");
		panelOpciones.add(btnNuevo);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addActionListener(this);
		panelOpciones.add(btnAbrir);
		
		btnCopiar = new JButton("Copiar");
		panelOpciones.add(btnCopiar);
		
		btnEliminar = new JButton("Eliminar");
		panelOpciones.add(btnEliminar);

		JButton inv_1 = new JButton();
		inv_1.setVisible(false);
		panelOpciones.add(inv_1);
		
		btnTestigos = new JButton("Testigos");
		panelOpciones.add(btnTestigos);
		
		btnVisados = new JButton("Visados");
		panelOpciones.add(btnVisados);
		
		btnCroquis = new JButton("Croquis");
		panelOpciones.add(btnCroquis);
		
		btnEmpresa = new JButton("Empresa");
		panelOpciones.add(btnEmpresa);
		
		btnOpciones = new JButton("Opciones");
		panelOpciones.add(btnOpciones);
		
		JButton inv_2 = new JButton();
		inv_2.setVisible(false);
		panelOpciones.add(inv_2);
		
		btnAyuda = new JButton("Ayuda");
		panelOpciones.add(btnAyuda);
		
		btnAcerca = new JButton("Acerca");
		panelOpciones.add(btnAcerca);
		
		btnSalir = new JButton("Salir");
		panelOpciones.add(btnSalir);	
	}
	private void createVentanaRuta() {
		arbol = new JTree();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode pisos = new DefaultMutableTreeNode("Pisos");
		DefaultMutableTreeNode apartamentos = new DefaultMutableTreeNode("Apartamentos");
		DefaultMutableTreeNode casas = new DefaultMutableTreeNode("Casas");
		DefaultMutableTreeNode locales = new DefaultMutableTreeNode("Locales");
		DefaultMutableTreeNode oficinas= new DefaultMutableTreeNode("Oficinas");
		DefaultMutableTreeNode garajes = new DefaultMutableTreeNode("Garajes");
		DefaultMutableTreeNode trasteros = new DefaultMutableTreeNode("Trasteros");
		DefaultMutableTreeNode naves = new DefaultMutableTreeNode("Naves");
		DefaultMutableTreeNode solares = new DefaultMutableTreeNode("Solares");
		DefaultMutableTreeNode rusticas = new DefaultMutableTreeNode("Rusticas");
		DefaultTreeModel modelo = new DefaultTreeModel(root);
		modelo.insertNodeInto(pisos, root, 0);
		modelo.insertNodeInto(apartamentos, root, 1);
		modelo.insertNodeInto(casas, root, 2);
		modelo.insertNodeInto(locales, root, 3);
		modelo.insertNodeInto(oficinas, root, 4);
		modelo.insertNodeInto(garajes, root, 5);
		modelo.insertNodeInto(trasteros, root, 6);
		modelo.insertNodeInto(naves, root, 7);
		modelo.insertNodeInto(solares, root, 8);
		modelo.insertNodeInto(rusticas, root, 9);
		arbol = new JTree(modelo);
		arbol.setRootVisible(false);
        arbol.getSelectionModel().addTreeSelectionListener(this);
        scrollPaneArbol = new JScrollPane();
		scrollPaneArbol.setViewportView(arbol);
		panelRuta.add(scrollPaneArbol);
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		
	}
	private void createPanelDatos() {
		c = new GridBagConstraints();
		
		JLabel l = new JLabel("Solicitante");
		c = setConstraints(0, 0, 3, 1);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 100;
		panelDatos.add(l, c);
		
		JLabel l2 = new JLabel("Tipo");
		c = setConstraints(3, 0, 3, 1);
		panelDatos.add(l2, c);
		
		JLabel l3 = new JLabel("Tasador");
		c = setConstraints(0, 2, 3, 1);
		panelDatos.add(l3, c);

		JLabel l4 = new JLabel("Fecha");
		c = setConstraints(3, 2, 3, 1);
		panelDatos.add(l4, c);
		
		JLabel l5 = new JLabel("Pais");
		c = setConstraints(0, 4, 1, 1);
		panelDatos.add(l5, c);

		JLabel l6 = new JLabel("Provincia");
		c = setConstraints(1, 4, 5, 1);
		panelDatos.add(l6, c);
		
		JLabel l7 = new JLabel("Poblacion");
		c = setConstraints(0, 6, 5, 1);
		panelDatos.add(l7, c);

		JLabel l8 = new JLabel("C. Postal");
		c = setConstraints(5, 6, 1, 1);
		panelDatos.add(l8, c);
		
		JLabel l9 = new JLabel("Domicilio");
		c = setConstraints(0, 8, 6, 1);
		panelDatos.add(l9, c);

		JLabel l10 = new JLabel("Superficie útil");
		c = setConstraints(2, 10, 4, 1);
		panelDatos.add(l10, c);
		
		JLabel l11 = new JLabel("Sup. construida");
		c = setConstraints(2, 12, 4, 1);
		panelDatos.add(l11, c);

		JLabel l12 = new JLabel("Edad");
		c = setConstraints(2, 14, 3, 1);
		panelDatos.add(l12, c);
		
		JLabel l13 = new JLabel("Reformado");
		c = setConstraints(5, 14, 1, 1);
		panelDatos.add(l13, c);

		JLabel l14 = new JLabel("Valor de tasación");
		c = setConstraints(2, 16, 4, 1);
		panelDatos.add(l14, c);
		
		JTextField tb = new JTextField();
		c = setConstraints(0, 1, 3, 1);
		panelDatos.add(tb, c);

		JTextField tb2 = new JTextField();
		c = setConstraints(3, 1, 3, 1);
		panelDatos.add(tb2, c);
		
		JTextField tb3 = new JTextField();
		c = setConstraints(0, 3, 3, 1);
		panelDatos.add(tb3, c);

		JTextField tb4 = new JTextField();
		c = setConstraints(3, 3, 3, 1);
		panelDatos.add(tb4, c);
		
		JTextField tb5 = new JTextField();
		c = setConstraints(0, 5, 1, 1);
		panelDatos.add(tb5, c);

		JTextField tb6 = new JTextField();
		c = setConstraints(1, 5, 5, 1);
		panelDatos.add(tb6, c);
		
		JTextField tb7 = new JTextField();
		c = setConstraints(0, 7, 5, 1);
		panelDatos.add(tb7, c);

		JTextField tb8 = new JTextField();
		c = setConstraints(5, 7, 1, 1);
		panelDatos.add(tb8, c);
		
		JTextField tb9 = new JTextField();
		c = setConstraints(0, 9, 6, 1);
		panelDatos.add(tb9, c);

		JTextField tb10 = new JTextField();
		c = setConstraints(2, 11, 4, 1);
		panelDatos.add(tb10, c);
		
		JTextField tb11 = new JTextField();
		c = setConstraints(2, 13, 4, 1);
		panelDatos.add(tb11, c);

		JTextField tb12 = new JTextField();
		c = setConstraints(2, 15, 3, 1);
		panelDatos.add(tb12, c);
		
		JTextField tb13 = new JTextField();
		c = setConstraints(5, 15, 2, 1);
		panelDatos.add(tb13, c);

		JTextField tb14 = new JTextField();
		c = setConstraints(2, 17, 4, 1);
		panelDatos.add(tb14, c);
		
		c = setConstraints(0, 10, 2, 8);
		c.fill = GridBagConstraints.BOTH;
		if (!itemSelec) {
			pdTa = new JTextArea();
			pdTa.setBackground(Color.BLUE);
			panelDatos.add(pdTa, c);
		} else {
			try {
				URL url = this.getClass().getResource(imageResource);
				Image image = ImageIO.read(url);
				Imagen imagen = new Imagen(image);
				panelDatos.add(imagen, c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private GridBagConstraints setConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
		c.gridx = gridx;
		c.gridy = gridy;
		c.gridwidth = gridwidth;
		c.gridheight = gridheight;
		return c;
	}
	private void createPanelFiltro() {
		panelFiltroOpcionesIzquierda = new JPanel();
		panelFiltroOpcionesIzquierda.setBounds(5, 15, 464, 228);
		panelFiltroOpcionesIzquierda.setLayout(new GridLayout(3, 0, 0, 0));
		
		panelFiltroOpciones1 = new JPanel();
		panelFiltroOpciones1.setBackground(Color.WHITE);
		GridBagLayout gbl_panelFiltroOpciones1 = new GridBagLayout();
		gbl_panelFiltroOpciones1.columnWidths = new int[]{76, 126, 0, 0, 0, 0};
		gbl_panelFiltroOpciones1.rowHeights = new int[] {0, 0};
		gbl_panelFiltroOpciones1.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelFiltroOpciones1.rowWeights = new double[]{0.0, 0.0};
		panelFiltroOpciones1.setLayout(gbl_panelFiltroOpciones1);
		
		panelFiltroOpciones2 = new JPanel();
		panelFiltroOpciones2.setBackground(Color.WHITE);
		GridBagLayout gbl_panelFiltroOpciones2 = new GridBagLayout();
		gbl_panelFiltroOpciones2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panelFiltroOpciones2.rowHeights = new int[]{0, 0};
		gbl_panelFiltroOpciones2.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelFiltroOpciones2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelFiltroOpciones2.setLayout(gbl_panelFiltroOpciones2);
		
		//Panel Izquierdo Columna 1
		JLabel l1 = new JLabel("Tipo inmueble");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panelFiltroOpciones1.add(l1, gbc_lblNewLabel_2);
		
		JLabel l2 = new JLabel("Tasador");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 0;
		panelFiltroOpciones1.add(l2, gbc_label_5);
		
		JLabel l3 = new JLabel("Nº tasación");
		GridBagConstraints gbc_label_1_1 = new GridBagConstraints();
		gbc_label_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1_1.gridx = 2;
		gbc_label_1_1.gridy = 0;
		panelFiltroOpciones1.add(l3, gbc_label_1_1);
		
		JLabel l4 = new JLabel("Solicitante contiene...");
		GridBagConstraints gbc_label_2_1 = new GridBagConstraints();
		gbc_label_2_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_2_1.gridx = 3;
		gbc_label_2_1.gridy = 0;
		panelFiltroOpciones1.add(l4, gbc_label_2_1);
		
		JLabel l5 = new JLabel("NIF solicitante");
		GridBagConstraints gbc_label_3_1 = new GridBagConstraints();
		gbc_label_3_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_3_1.gridx = 4;
		gbc_label_3_1.gridy = 0;
		panelFiltroOpciones1.add(l5, gbc_label_3_1);
		
		JComboBox<String> cb = new JComboBox<String>();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 0;
		gbc_comboBox_2.gridy = 1;
		panelFiltroOpciones1.add(cb, gbc_comboBox_2);
		
		JTextField tf = new JTextField();
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.insets = new Insets(0, 0, 0, 5);
		gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 1;
		panelFiltroOpciones1.add(tf, gbc_textField_5);
		
		JTextField tf2 = new JTextField();
		GridBagConstraints gbc_textField_1_1 = new GridBagConstraints();
		gbc_textField_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1_1.gridx = 2;
		gbc_textField_1_1.gridy = 1;
		panelFiltroOpciones1.add(tf2, gbc_textField_1_1);
		
		JTextField tf3 = new JTextField();
		GridBagConstraints gbc_textField_2_1 = new GridBagConstraints();
		gbc_textField_2_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2_1.gridx = 3;
		gbc_textField_2_1.gridy = 1;
		panelFiltroOpciones1.add(tf3, gbc_textField_2_1);
		
		JTextField tf4 = new JTextField();
		GridBagConstraints gbc_textField_3_1 = new GridBagConstraints();
		gbc_textField_3_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3_1.gridx = 4;
		gbc_textField_3_1.gridy = 1;
		panelFiltroOpciones1.add(tf4, gbc_textField_3_1);
		
		//Panel Izquierdo Columna 2
		JLabel l21 = new JLabel("Dirección contiene");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelFiltroOpciones2.add(l21, gbc_lblNewLabel);
		
		JLabel l22 = new JLabel("Provincia");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panelFiltroOpciones2.add(l22, gbc_lblNewLabel_1);
		
		JLabel l23 = new JLabel("Población");
		GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints();
		gbc_lblNewLabel_21.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_21.gridx = 2;
		gbc_lblNewLabel_21.gridy = 0;
		panelFiltroOpciones2.add(l23, gbc_lblNewLabel_21);
		
		JLabel l24 = new JLabel("Zona");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 3;
		gbc_lblNewLabel_3.gridy = 0;
		panelFiltroOpciones2.add(l24, gbc_lblNewLabel_3);
		
		JLabel l25 = new JLabel("C. Postal");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 0;
		panelFiltroOpciones2.add(l25, gbc_lblNewLabel_4);
		
		JTextField tf21 = new JTextField();
		GridBagConstraints gbc_textField_51 = new GridBagConstraints();
		gbc_textField_51.insets = new Insets(0, 0, 0, 5);
		gbc_textField_51.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_51.gridx = 0;
		gbc_textField_51.gridy = 1;
		panelFiltroOpciones2.add(tf21, gbc_textField_51);
		
		JTextField tf22 = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.insets = new Insets(0, 0, 0, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 1;
		panelFiltroOpciones2.add(tf22, gbc_textField_6);
		
		JTextField tf23 = new JTextField();
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.insets = new Insets(0, 0, 0, 5);
		gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_7.gridx = 2;
		gbc_textField_7.gridy = 1;
		panelFiltroOpciones2.add(tf23, gbc_textField_7);
		
		JTextField tf24 = new JTextField();
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.insets = new Insets(0, 0, 0, 5);
		gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_8.gridx = 3;
		gbc_textField_8.gridy = 1;
		panelFiltroOpciones2.add(tf24, gbc_textField_8);
		
		JTextField tf25 = new JTextField();
		GridBagConstraints gbc_textField_9 = new GridBagConstraints();
		gbc_textField_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_9.gridx = 4;
		gbc_textField_9.gridy = 1;
		panelFiltroOpciones2.add(tf25, gbc_textField_9);		
		
		panelFiltroOpciones3 = new JPanel();
		panelFiltroOpciones3.setBackground(Color.WHITE);
		GridBagLayout gbl_panelFiltroOpciones3 = new GridBagLayout();
		gbl_panelFiltroOpciones3.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelFiltroOpciones3.rowHeights = new int[] {0, 0};
		gbl_panelFiltroOpciones3.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panelFiltroOpciones3.rowWeights = new double[]{0.0, 0.0};
		panelFiltroOpciones3.setLayout(gbl_panelFiltroOpciones3);
		
		JLabel l31 = new JLabel("Sup. desde");
		GridBagConstraints gbc_lblNewLabel1 = new GridBagConstraints();
		gbc_lblNewLabel1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel1.gridx = 0;
		gbc_lblNewLabel1.gridy = 0;
		panelFiltroOpciones3.add(l31, gbc_lblNewLabel1);
		
		JLabel l32 = new JLabel("Sup. hasta");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 1;
		gbc_lblNewLabel_11.gridy = 0;
		panelFiltroOpciones3.add(l32, gbc_lblNewLabel_11);
		
		JLabel l33 = new JLabel("Tipo superficie");
		GridBagConstraints gbc_lblNewLabel_211 = new GridBagConstraints();
		gbc_lblNewLabel_211.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_211.gridx = 2;
		gbc_lblNewLabel_211.gridy = 0;
		panelFiltroOpciones3.add(l33, gbc_lblNewLabel_211);
		
		JLabel l34 = new JLabel("Importe desde");
		GridBagConstraints gbc_lblNewLabel_31 = new GridBagConstraints();
		gbc_lblNewLabel_31.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_31.gridx = 3;
		gbc_lblNewLabel_31.gridy = 0;
		panelFiltroOpciones3.add(l34, gbc_lblNewLabel_31);
		
		JLabel l35 = new JLabel("Importe hasta");
		GridBagConstraints gbc_lblNewLabel_41 = new GridBagConstraints();
		gbc_lblNewLabel_41.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_41.gridx = 4;
		gbc_lblNewLabel_41.gridy = 0;
		panelFiltroOpciones3.add(l35, gbc_lblNewLabel_41);
		
		JLabel l36 = new JLabel("Fecha desde");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 5;
		gbc_lblNewLabel_5.gridy = 0;
		panelFiltroOpciones3.add(l36, gbc_lblNewLabel_5);
		
		JLabel l37 = new JLabel("Fecha hasta");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 6;
		gbc_lblNewLabel_6.gridy = 0;
		panelFiltroOpciones3.add(l37, gbc_lblNewLabel_6);
		
		JTextField tf31 = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelFiltroOpciones3.add(tf31, gbc_textField);
		
		JTextField tf32 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		panelFiltroOpciones3.add(tf32, gbc_textField_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		panelFiltroOpciones3.add(comboBox, gbc_comboBox);
		
		JTextField tf33 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 3;
		gbc_textField_2.gridy = 1;
		panelFiltroOpciones3.add(tf33, gbc_textField_2);
		
		JTextField tf34 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 1;
		panelFiltroOpciones3.add(tf34, gbc_textField_3);
		
		JXDatePicker dp = new JXDatePicker();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 0, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 5;
		gbc_textField_4.gridy = 1;
		panelFiltroOpciones3.add(dp, gbc_textField_4);
		
		JXDatePicker dp2 = new JXDatePicker();
		GridBagConstraints gbc_textField_511 = new GridBagConstraints();
		gbc_textField_511.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_511.gridx = 6;
		gbc_textField_511.gridy = 1;
		panelFiltroOpciones3.add(dp2, gbc_textField_511);
		panelFiltroOpcionesDerecha = new JPanel();
		panelFiltroOpcionesDerecha.setBounds(470, 11, 68, 232);

		panelFiltroOpcionesDerecha.setLayout(new GridLayout(2, 0, 0, 0));
		
		JButton btnLimpiar = new JButton("Limpiar");
		panelFiltroOpcionesDerecha.add(btnLimpiar);
		
		JButton btnBuscar = new JButton("Buscar");
		panelFiltroOpcionesDerecha.add(btnBuscar);
		
		panelFiltroOpcionesIzquierda.add(panelFiltroOpciones1);
		panelFiltroOpcionesIzquierda.add(panelFiltroOpciones2);
		panelFiltroOpcionesIzquierda.add(panelFiltroOpciones3);
		
		panelFiltro.add(panelFiltroOpcionesIzquierda);
		panelFiltro.add(panelFiltroOpcionesDerecha);
	}
	public void createPanelBusqueda() {
		String [] columnas = {"Tipo", "Número", "Solicitante", "Población", "Situación", "Fecha", "Valoración"};
		Object[][] data = {
				{"Casa", new Integer(3), "Daniel Sánchez Betancor", "Telde", "Calle Doctor Melian", new Date(System.currentTimeMillis()), new Double(25500.50)},
				{"Casa", new Integer(3), "Daniel Sánchez Betancor", "Telde", "Calle Doctor Melian", new Date(System.currentTimeMillis()), new Double(25500.50)},
				{"Casa", new Integer(3), "Daniel Sánchez Betancor", "Telde", "Calle Doctor Melian", new Date(System.currentTimeMillis()), new Double(25500.50)},
				{"Casa", new Integer(3), "Daniel Sánchez Betancor", "Telde", "Calle Doctor Melian", new Date(System.currentTimeMillis()), new Double(25500.50)},
				{"Casa", new Integer(3), "Daniel Sánchez Betancor", "Telde", "Calle Doctor Melian", new Date(System.currentTimeMillis()), new Double(25500.50)}
				};
		//Reemplazamos la clase de JModel para que no se puedan editar las celdas
				DefaultTableModel tableModel = new DefaultTableModel(data, columnas) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				};
		JTable table = new JTable();
		table.setModel(tableModel);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		JScrollPane jsp = new JScrollPane(table);
		panelBusqueda.add(jsp);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAbrir) {
			itemSelec = true;
			imageResource = "images/casa.jpg";
			panelDatos.revalidate();
		}
	}
	protected void paintComponent(Graphics og) {
        this.paintComponent(og);
       }
	
}
