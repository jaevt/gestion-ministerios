package gui;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import principal.Gobierno;

import java.awt.Color;

public class Resultado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panel;
	private DefaultTableModel modelo;
	private JTable tabla;
	private int ID;
	private String[] datos;
	private JLabel campo1= new JLabel(" "),campo2= new JLabel(" "),campo3= new JLabel(" "),campo4= new JLabel(" "),campo5= new JLabel(" ");

	/**
	 * Recibe una matriz de tipo Object con informaci�n la cual mostrara
	 * en un JTable
	 * @param buscar
	 */
	public Resultado(Object[][] buscar, Gobierno gobierno) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 756, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 325, 541);
		contentPane.add(scrollPane);
		String[] tituloColumns = {"1","2","3","4"};
		
		modelo = new DefaultTableModel(buscar,tituloColumns);
		tabla = new JTable(modelo) ;
		tabla.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evt)
			{
				int col = tabla.getSelectedColumn();
				int row = tabla.getSelectedRow();
				System.out.println("("+col+","+row +") "+tabla.getModel().getValueAt(row, col));
				
				if(col==0){
                	String xw=(String) tabla.getModel().getValueAt(row, col);
                	ID = Integer.parseInt(xw);
                	if(ID%100!=0){
                		int index= gobierno.Buscar(ID);
            			int index2=gobierno.getMinisterios().get(index).Buscar(ID);
            			int index3=gobierno.getMinisterios().get(index).getDepartamentos().get(index2).Buscar(ID);
            			datos=gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getFuncionarios().get(index3).Datos();
                	}else{
                		if(ID%10000!=0){
                			int index= gobierno.Buscar(ID);
                			int index2=gobierno.getMinisterios().get(index).Buscar(ID);
                			datos=gobierno.getMinisterios().get(index).getDepartamentos().get(index2).Datos();
                		}else{
                			int index= gobierno.Buscar(ID);
                			datos=gobierno.getMinisterios().get(index).Datos();
                		}
                	}
                }
				campo1.setText(datos[0]);
				campo2.setText(datos[1]);
				campo3.setText(datos[2]);
				campo4.setText(datos[3]);
				campo5.setText(datos[4]);
			}
		});
		scrollPane.setViewportView(tabla);
		
		
		/**
		 * Inicializa en Menu el panel actualizable PanelInformacion
		 */
		panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(349, 12, 377, 336);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Button button = new Button("Volver");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		button.setBounds(640, 530, 86, 23);
		contentPane.add(button);
		panel.setVisible(true);
		PanelInformacion(gobierno);
	}
	/**
	 * Panel donde se muestra la informacion de algun nodo seleccionado en Arbol,
	 * en este lugar del codigo inicializa los JLabel's en el que se mostraran
	 * posteriormente la informacion.
	 * Son 5 espacios (labels) asignados para mostrar los datos.
	 * @param gobierno
	 */
	public void PanelInformacion(Gobierno gobierno){
		campo1.setVisible(true);
		campo1.setBounds(20, 50, 345, 38);
		panel.add(campo1);
		campo2.setVisible(true);
		campo2.setBounds(20, 100, 350, 38);
		panel.add(campo2);
		campo3.setVisible(true);
		campo3.setBounds(20, 150, 350, 38);
		panel.add(campo3);
		campo4.setVisible(true);
		campo4.setBounds(20, 200, 350, 38);
		panel.add(campo4);
		campo5.setVisible(true);
		campo5.setBounds(20, 250, 350, 38);
		panel.add(campo5);
		/**
		 * Edita el nodo seleccionado obteniendo su respectiva posicion dentro de los
		 * ArrayList a traves de su ID
		 */	
		Button editarbtn = new Button("Editar");
		editarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	if(ID%100!=0){
            	}else{
            		if(ID%10000!=0){
            		}else{
            		}
            	}
			}
		});
		editarbtn.setBounds(10, 300, 86, 23);
		panel.add(editarbtn);
		/**
		 * Elimina el nodo seleccionado obteniendo su respectiva posicion dentro de los
		 * ArrayList a traves de su ID
		 */
		Button eliminarbtn = new Button("Eliminar");
		eliminarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            	if(ID%100!=0){
            		int index = gobierno.Buscar(ID);
            		int index2 = gobierno.getMinisterios().get(index).Buscar(ID);
            		gobierno.getMinisterios().get(index).getDepartamentos().get(index2).Eliminar(ID);
        			Menu newmenu = new Menu(gobierno);
        			newmenu.setVisible(true);
        			dispose();

            	}else{
            		if(ID%10000!=0){
            			int index = gobierno.Buscar(ID);
            			gobierno.getMinisterios().get(index).Eliminar(ID);
            			Menu newmenu = new Menu(gobierno);
            			newmenu.setVisible(true);
            			dispose();
            		}else{
            			gobierno.Eliminar(ID);
            			Menu newmenu = new Menu(gobierno);
            			newmenu.setVisible(true);
            			dispose();
            		}
            	}
			}
		});
		eliminarbtn.setBounds(281, 300, 86, 23);
		panel.add(eliminarbtn);
	}
}