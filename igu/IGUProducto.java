/**
 * Clase que implementa una interfaz para el modulo de productos.
 * @author Frida Hernadez
 * @version 1.0
 */

package ProyectoOpalo.igu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class IGUProducto extends JFrame{

	public static final String ICONOS = "/iconos/"; //ruta para la carpeta de imagenes
 	private JButton btModificar, btEliminar, btAgregar, btFlechaDer, btFlechaDobleDer, btFlechaDobleIzq, btFlechaIzq;
	//private JComboBox<String> combo1;

	private JTextField camposTexto[] = {
		new JTextField(),
		new JTextField(),
		new JTextField(),
		new JTextField(),
	};

	private JTextField camposTextoExistencias[] = {
		new JTextField(),
		new JTextField(),
		new JTextField(),
	};

	private JLabel etiquetas[] = {
		new JLabel("Codigo"),
		new JLabel("Nombre"),
		new JLabel("Descripci\u00F3n"),
		new JLabel("Precio"),
	};

	private JLabel etiquetasExistencias[] = {
		new JLabel("Actual"),
		new JLabel("M\u00EDnima"),
		new JLabel("M\u00E1xima"),
	};

	public IGUProducto(){
/*
		super("Producto");

		add(getIGUProducto());

		setSize(700, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
*/
	}

	public JPanel getIGUProducto(){

		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());

		panel.add(getPanelBuscar(), BorderLayout.NORTH);
		panel.add(getPanelInventario(), BorderLayout.CENTER);
		panel.add(getPanelDatosProducto(), BorderLayout.WEST);

		return panel;

	}

	public JPanel getPanelBuscar(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setLayout(new FlowLayout());

		JLabel titulo = new JLabel("Producto");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		panel.add(titulo);

		JLabel buscar = new JLabel("Buscar");
		panel.add(buscar);

		JTextField campoBuscar = new JTextField();
		campoBuscar.setText("Codigo/Nombre");
		campoBuscar.setPreferredSize(new Dimension(200,25));
		panel.add(campoBuscar);

		JButton btBuscar = new JButton(new ImageIcon(ICONOS + "lupa.png"));
		btBuscar.setPreferredSize(new Dimension(32,32));

		btBuscar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

            //    btBuscarActionPerformed(evt);

            }
        });

		panel.add(btBuscar);


		return panel;

	}

	public JPanel getPanelInventario(){

		JPanel panel = new JPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Inventario"));

		//creacion de la tabla
		JTable tabla = new JTable();
		JScrollPane jScroll = new JScrollPane(tabla);

		String [] nombre = {
                "Codigo", "Nombre", "Cantidad"
            };

		tabla.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            }, nombre
            
        ));

        jScroll.setViewportView(tabla);

		panel.add(jScroll, BorderLayout.CENTER);

		btFlechaDer = new JButton(new ImageIcon(ICONOS + "flechaDer.png"));
		btFlechaDer.setToolTipText("Siguiente");		
		btFlechaDobleDer = new JButton(new ImageIcon(ICONOS + "flechaDobleDer.png"));
		btFlechaDobleDer.setToolTipText("Fin");		
		btFlechaDobleIzq = new JButton(new ImageIcon(ICONOS + "flechaDobleIzq.png"));
		btFlechaDobleIzq.setToolTipText("Inicio");
		btFlechaIzq = new JButton(new ImageIcon(ICONOS + "flechaIzq.png"));
		btFlechaIzq.setToolTipText("Anterior");
		

		panel.add(btFlechaDobleIzq, BorderLayout.SOUTH);
		panel.add(btFlechaIzq, BorderLayout.SOUTH);
		panel.add(btFlechaDer, BorderLayout.SOUTH);
		panel.add(btFlechaDobleDer, BorderLayout.SOUTH);


		return panel;

	}
	
	public JPanel getPanelDatosProducto(){

		JPanel panelLayoutGeneral = new JPanel();

		panelLayoutGeneral.setLayout(new GridLayout(3, 1, 50, 10));

		panelLayoutGeneral.add(getPanelDatos());
		panelLayoutGeneral.add(getPanelExistencias());
		panelLayoutGeneral.add(getPanelBotones());
		

		panelLayoutGeneral.setBorder(BorderFactory.createTitledBorder("Datos producto"));


		return panelLayoutGeneral;

	}

	public JPanel getPanelDatos(){

		JPanel panel = new JPanel();

		panel.setLayout(new GridLayout(8, 1));

		for (int i = 0; i < camposTexto.length; i++){

			panel.add(etiquetas[i]);
			panel.add(camposTexto[i]);

		}

		return panel;

	}

	public JPanel getPanelExistencias(){

		JPanel panelExistencias = new JPanel();

		panelExistencias.setLayout(new GridLayout(2, 3, 10, 0));

		for (int eContador = 0; eContador < etiquetasExistencias.length; eContador++){

			panelExistencias.add(etiquetasExistencias[eContador]);
			
		}

		for (int eContador = 0; eContador < camposTextoExistencias.length; eContador++){

			panelExistencias.add(camposTextoExistencias[eContador]);

		}

		panelExistencias.setBorder(BorderFactory.createTitledBorder("Existencias"));

		return panelExistencias;

	}
	

	public JPanel getPanelBotones(){

		JPanel panelBotones = new JPanel();

		panelBotones.setLayout(new FlowLayout());

		btAgregar = new JButton(new ImageIcon(ICONOS + "agregar.png"));
		btAgregar.setToolTipText("Agregar");

		btEliminar = new JButton(new ImageIcon(ICONOS + "eliminar.png"));
		btEliminar.setToolTipText("Eliminar");

		btModificar = new JButton(new ImageIcon(ICONOS + "modificar.png"));
		btModificar.setToolTipText("Modificar");

		panelBotones.add(btAgregar);
		panelBotones.add(btEliminar);
		panelBotones.add(btModificar);

		return panelBotones;
	}

	public void limpiar(){


	}

	public static void main(String[] args) {

		IGUProducto ventana = new IGUProducto();
		
	}
	
}