package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Gobierno;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormDepartamento extends Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Button submit,cancelar;

	/**
	 * Formulario para Ingresar un nuevo departamento, hereda caracteristicas de Form, recibe el elemento
	 * gobierno para modificarlo.
	 * @param gobierno
	 */
	public FormDepartamento(Gobierno gobierno) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addElements(contentPane);
		
		submit = new Button("Aceptar");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int seleccionado=getCombo1().getSelectedIndex();
				gobierno.getMinisterios().get(seleccionado).AgregarDepartamento(getTxttF1(), getTxttF2(), getTxttF3(), getTxttF4());
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
		
		
		addTxtTitulo("CREAR Departamento");
		addTxtLabel1("Titulo Departamento");
		addTxtLabel2("Telefono");
		addTxtLabel3("Direccion");
		addTxtLabel4("Correo");
		addCombo1("Ministerios",gobierno.listaMinisterios());
	}

	public FormDepartamento(Gobierno gobierno, int index, int index2) {
		// TODO Auto-generated constructor stub
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addElements(contentPane);
		
		submit = new Button("Aceptar");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				gobierno.getMinisterios().get(index).Editar(index2, getTxttF1(), getTxttF2(), getTxttF3(), getTxttF4());
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
		
		
		addTxtTitulo("EDITAR Departamento");
		addTxtLabel1("Titulo Departamento");
		gettF1().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getNombre());
		addTxtLabel2("Telefono");
		gettF2().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getTelefono());
		addTxtLabel3("Direccion");
		gettF3().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getDireccion());
		addTxtLabel4("Correo");
		gettF4().setText(gobierno.getMinisterios().get(index).getDepartamentos().get(index2).getCorreo());
	}
}