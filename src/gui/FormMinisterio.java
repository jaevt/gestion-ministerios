package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import principal.Gobierno;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormMinisterio extends Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Button submit,cancelar;
	/**
	 * Recibe un objeto tipo Gobierno para insertarle un Ministerio
	 * @param gobierno
	 */
	public FormMinisterio(Gobierno gobierno) {
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
				gobierno.AgregarMinisterio(getTxttF1(), getTxttF2(), getTxttF3(), getTxttF4());
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		submit.setBounds(146, 580, 70, 22);
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
		
		addTxtTitulo("CREAR Ministerio");
		addTxtLabel1("Titulo Ministerio");
		addTxtLabel2("Telefono");
		addTxtLabel3("Direccion");
		addTxtLabel4("Correo");
	}
	
	/**
	 * Recibe el indice de la posicion del ministerio dentro de gobierno y un objeto tipo Gobierno.
	 * Esta ventana se usa para Editar un Ministerio
	 * @param gobierno
	 * @param index
	 */
	public FormMinisterio(Gobierno gobierno, int index) {
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
				gobierno.Editar(index,getTxttF1(), getTxttF2(), getTxttF3(), getTxttF4());
				Menu nuevoMenu = new Menu(gobierno);
				nuevoMenu.setVisible(true);
				dispose();
			}
		});
		submit.setBounds(146, 580, 70, 22);
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
		
		addTxtTitulo("EDITAR Ministerio");
		addTxtLabel1("Titulo Ministerio");
		gettF1().setText(gobierno.getMinisterios().get(index).getNombre());
		addTxtLabel2("Telefono");
		gettF2().setText(gobierno.getMinisterios().get(index).getTelefono());
		addTxtLabel3("Direccion");
		gettF3().setText(gobierno.getMinisterios().get(index).getDireccion());
		addTxtLabel4("Correo");
		gettF4().setText(gobierno.getMinisterios().get(index).getCorreo());
	}
}
