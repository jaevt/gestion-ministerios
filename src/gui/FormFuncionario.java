package gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import principal.Gobierno;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FormFuncionario extends Form {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Button submit,cancelar;
	private int seleccionado,seleccionado2;
	/**
	 * Formulario para Ingresar un nuevo departamento, hereda caracteristicas de Form, recibe el elemento
	 * gobierno para modificarlo.
	 * @param gobierno
	 */
	public FormFuncionario(Gobierno gobierno) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addElements(contentPane);
		addTxtTitulo("AGREGAR FUNCIONARIO");
		addTxtLabel1("Nombre");
		addTxtLabel2("Apellido");
		addTxtLabel3("Correo");
		addCombo1("Ministerios",gobierno.listaMinisterios());
		this.getCombo1().addItemListener(new ItemListener() {    
			public void itemStateChanged(ItemEvent e) {
				seleccionado=getCombo1().getSelectedIndex();
				addCombo2("Departamentos",gobierno.getMinisterios().get(seleccionado).listaDepartamentos());
	        }
		});
		submit = new Button("Aceptar");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seleccionado2= getCombo2().getSelectedIndex();
				gobierno.getMinisterios().get(seleccionado).getDepartamentos().get(seleccionado2).AgregarFuncionario(getTxttF1(), getTxttF2(), getTxttF3());
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		submit.setBounds(166, 580, 70, 22);
		contentPane.add(submit);
		cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		cancelar.setBounds(226, 580, 70, 22);
		contentPane.add(cancelar);
	}
	/**
	 * Formulario para Editar un nuevo objeto Funcionario, recibe las posiciones del Ministerio,
	 * Departamento y Funcionario a Editar y lo modificado.
	 * @param gobierno
	 * @param ID
	 * @param index
	 * @param index2
	 * @param index3
	 */
	public FormFuncionario(Gobierno gobierno, int ID, int index, int index2, int index3) {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addElements(contentPane);
		addTxtTitulo("EDITAR FUNCIONARIO");
		addTxtLabel1("Nombre");
		gettF1().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getFuncionarios().get(index3).getNombre());
		addTxtLabel2("Apellido");
		gettF2().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getFuncionarios().get(index3).getApellido());
		addTxtLabel3("Correo");
		gettF3().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getFuncionarios().get(index3).getCorreo());
		submit = new Button("Aceptar");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gobierno.getMinisterios().get(index).getDepartamentos().get(index2).Editar(index3, getTxttF1(), getTxttF2(), getTxttF3());
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		submit.setBounds(166, 580, 70, 22);
		contentPane.add(submit);
		cancelar = new Button("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		cancelar.setBounds(226, 580, 70, 22);
		contentPane.add(cancelar);
	}
}