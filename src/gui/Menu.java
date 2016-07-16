package gui;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import principal.Gobierno;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextField;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane,panel;
	private JScrollPane scrollPane;
	private JTree arbol;
	private TextField buscarTodo;
	private Button btn_crearMinis, btn_crearDepto, btnBuscarTodo ;
	private DefaultMutableTreeNode carpetaRaiz;
	private DefaultTreeModel modelo;
	private JLabel campo1= new JLabel(" "),campo2= new JLabel(" "),campo3= new JLabel(" "),campo4= new JLabel(" "),campo5= new JLabel(" ");
	private int ID;
	private String[] datos;
	/**
	 * Ventana principal del programa, contiene los botones para realizar todas las
	 * operaciones disponibles.
	 * Un JTree que muestra toda la informacion del programa, un JPanel que muestra
	 * al instante los datos del Nodo del JTree seleccionado y metodos de busqueda.
	 * @param gobierno
	 */
	public Menu(Gobierno gobierno) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * Inicializa en Menu el Arbol
		 */
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 377, 257);
		contentPane.add(scrollPane);
		Arbol(gobierno);
		/**
		 * Inicializa en Menu el panel actualizable PanelInformacion
		 */
		panel = new JPanel();
		panel.setBounds(12, 281, 377, 336);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(true);
		panel.setBackground(Color.CYAN);
		PanelInformacion(gobierno);
		/**
		 * TextField y Boton del metodo de nuestro busqueda, el cual compara Ministerios
		 * Departamentos y Funcionarios con el texto ingresado e invoca una pantalla
		 * la que contiene una tabla JTable con los resultados
		 */
		buscarTodo = new TextField();
		buscarTodo.setBounds(438, 12, 205, 23);
		contentPane.add(buscarTodo);
		btnBuscarTodo = new Button("Buscar");
		btnBuscarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String auxx= buscarTodo.getText();
				Object[][] resul = gobierno.Buscar(auxx);
				Resultado nuevaVentana = new Resultado(resul,gobierno);
				nuevaVentana.setVisible(true);
				dispose();
			}
		});
		btnBuscarTodo.setBounds(532, 38, 111, 23);
		contentPane.add(btnBuscarTodo);
		/**
		 * Boton que abre una nueva ventana con un formulario para ingresar un ministerio
		 */
		btn_crearMinis = new Button("Crear MINISTERIO");
		btn_crearMinis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormMinisterio nuevaVentana = new FormMinisterio(gobierno);
				nuevaVentana.setVisible(true);
				dispose();
				
			}
		});
		btn_crearMinis.setBounds(438, 67, 205, 23);
		contentPane.add(btn_crearMinis);
		/**
		 * Boton que abre una nueva ventana con un formulario para ingresar un departamento
		 */
		btn_crearDepto = new Button("Crear Departamento");
		btn_crearDepto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormDepartamento nuevaVentana = new FormDepartamento(gobierno);
				nuevaVentana.setVisible(true);
				dispose();
			}
		});
		btn_crearDepto.setBounds(438, 96, 205, 23);
		contentPane.add(btn_crearDepto);
		/**
		 * Boton que abre una nueva ventana con un formulario para ingresar un Funcionario
		 * dentro de esta ventana se seleccionara en que Ministerio y Departamento desea agregarlo
		 * NOTA: Tienen que estar un Ministerio seleccionado para mostrar los departamentos que este
		 * contenga
		 */
		Button btn_crearFunc = new Button("Crear Funcionario");
		btn_crearFunc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormFuncionario nuevaVentana=new FormFuncionario(gobierno);
				nuevaVentana.setVisible(true);
				dispose();
			}
		});
		btn_crearFunc.setBounds(438, 125, 205, 23);
		contentPane.add(btn_crearFunc);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.ORANGE);
		panel_1.setForeground(Color.WHITE);
		panel_1.setBounds(401, 281, 279, 336);
		contentPane.add(panel_1);
		
	}
	/**
	 * Muestra en un JTree la actual informacion que maneja gobierno
	 * @param gobierno
	 */
	public void Arbol(Gobierno gobierno){
		carpetaRaiz = new DefaultMutableTreeNode(gobierno.getID()+" "+gobierno.getNombre());
		modelo = new DefaultTreeModel(carpetaRaiz);
		arbol = new JTree(modelo);
		arbol.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(arbol);
		for(int i=0;i<gobierno.getMinisterios().size();i++){
				String minis = gobierno.getMinisterios().get(i).getNombre();
				DefaultMutableTreeNode ministerio = new DefaultMutableTreeNode(gobierno.getMinisterios().get(i).getID() +" "+minis);
				modelo.insertNodeInto(ministerio, carpetaRaiz, i);
				
				for(int j=0;j<gobierno.getMinisterios().get(i).getDepartamentos().size();j++){
						String deptos = gobierno.getMinisterios().get(i).getDepartamentos().get(j).getNombre();
						DefaultMutableTreeNode departamento = new DefaultMutableTreeNode(gobierno.getMinisterios().get(i).getDepartamentos().get(j).getID()+" "+deptos);
						modelo.insertNodeInto(departamento, ministerio, j);
						for(int k=0;k<gobierno.getMinisterios().get(i).getDepartamentos().get(j).getFuncionarios().size();k++){
								String funcio = gobierno.getMinisterios().get(i).getDepartamentos().get(j).getFuncionarios().get(k).getNombre();
								DefaultMutableTreeNode funcionario = new DefaultMutableTreeNode(gobierno.getMinisterios().get(i).getDepartamentos().get(j).getFuncionarios().get(k).getID()+" "+funcio);
								modelo.insertNodeInto(funcionario, departamento, k);
						}	
				}
				arbol.expandRow(i);
		}	
		/**
		 * Extrae los primeros 7 caracteres del String el cual lo manejaremos como una ID,
		 * esta ID dependiendo del numero que sea sabremos si es un Gobierno, Ministerio, Departamento
		 *  o Funcionario y con esta informacion buscaremos su ID en su respectivo lugar y asi mostrar
		 *  sus datos en PanelInformacion
		 */
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0){
				if(arbol.getLastSelectedPathComponent().toString().equals(gobierno.getID()+" "+gobierno.getNombre())){
                	datos=gobierno.Datos();
    				campo1.setText(datos[0]);
    				campo2.setText(datos[1]);
    				campo3.setText(datos[2]);
    				campo4.setText(datos[3]);
    				campo5.setText(datos[4]);
                }else{
                	String xw=arbol.getLastSelectedPathComponent().toString().substring(0, 7);
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
    				campo1.setText(datos[0]);
    				campo2.setText(datos[1]);
    				campo3.setText(datos[2]);
    				campo4.setText(datos[3]);
    				campo5.setText(datos[4]);
                }

            }
        });
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
            		int index = gobierno.Buscar(ID);
            		int index2 = gobierno.getMinisterios().get(index).Buscar(ID);
            		int index3 = gobierno.getMinisterios().get(index).getDepartamentos().get(index2).Buscar(ID);
        			FormFuncionario newmenu = new FormFuncionario(gobierno,ID,index,index2,index3);
        			newmenu.setVisible(true);
        			dispose();
            	}else{
            		if(ID%10000!=0){
              			int index = gobierno.Buscar(ID);
              			int index2 = gobierno.getMinisterios().get(index).Buscar(ID);
            			FormDepartamento newmenu = new FormDepartamento(gobierno,index,index2);
            			newmenu.setVisible(true);
            			dispose();
            		}else{
            			int index = gobierno.Buscar(ID);
            			FormMinisterio newmenu = new FormMinisterio(gobierno,index);
            			newmenu.setVisible(true);
            			dispose();
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