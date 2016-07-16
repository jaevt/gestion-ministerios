package gui;

import java.awt.TextField;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import principal.Gobierno;

public abstract class Form extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel titulo,label1, label2,label3,label4,label5,label6,label7,label8,label9;
	private TextField tF1,tF2,tF3,tF4,tF5,tF6,tF7;
	private JComboBox<String> combo1,combo2;
	/**
	 * Inicializa 7 TextFields y 7 Labels, por defecto, estos estan invisibles
	 * para solo ser mostrados cuando el programador los invoque, tienen sus posiciones
	 * fijas dentro de la ventana del Formulario.
	 */
	public Form(){
		titulo =new JLabel();
		titulo.setBounds(10, 0, 248, 23);
		titulo.setVisible(false);
		label1 =new JLabel();
		label1.setBounds(10, 30, 248, 23);
		label1.setVisible(false);
		tF1 = new TextField();
		tF1.setBounds(10, 60, 248, 23);
		tF1.setVisible(false);
		label2 = new JLabel();
		label2.setBounds(10, 90, 248, 23);
		label2.setVisible(false);
		tF2 = new TextField();
		tF2.setBounds(10, 120, 248, 23);
		tF2.setVisible(false);
		label3 = new JLabel();
		label3.setBounds(10, 150, 248, 23);
		label3.setVisible(false);
		tF3 = new TextField();
		tF3.setBounds(10, 180, 248, 23);
		tF3.setVisible(false);
		label4 = new JLabel();
		label4.setBounds(10, 210, 248, 23);
		label4.setVisible(false);
		tF4 = new TextField();
		tF4.setBounds(10, 240, 248, 23);
		tF4.setVisible(false);
		label5 = new JLabel();
		label5.setBounds(10, 270, 248, 23);
		label5.setVisible(false);
		tF5 = new TextField();
		tF5.setBounds(10, 300, 248, 23);
		tF5.setVisible(false);
		label6 = new JLabel();
		label6.setBounds(10, 330, 248, 23);
		label6.setVisible(false);
		tF6 = new TextField();
		tF6.setBounds(10, 360, 248, 23);
		tF6.setVisible(false);
		label7 = new JLabel();
		label7.setBounds(10, 390, 248, 23);
		label7.setVisible(false);
		tF7 = new TextField();
		tF7.setBounds(10, 420, 248, 23);
		tF7.setVisible(false);
		label8 = new JLabel();
		label8.setBounds(10, 450, 248, 23);
		label8.setVisible(false);
		combo1 = new JComboBox<String>();
		combo1.setBounds(10, 480, 300, 23);
		label9 = new JLabel();
		label9.setBounds(10, 510, 248, 23);
		label9.setVisible(false);
		combo1.setVisible(false);
		combo2 = new JComboBox<String>();
		combo2.setBounds(10, 540, 300, 23);
		combo2.setVisible(false);
	}
	public void Cerrar(Gobierno gobierno){
		Menu nuevoMenu = new Menu(gobierno);
		nuevoMenu.setVisible(true);
		dispose();
	}
	
	/**
	 * Recibe el JPanel para agregarle todos los elementos de Form
	 * @param panel
	 */
	public void addElements(JPanel panel){
		panel.add(titulo);
		panel.add(label1);
		panel.add(label2);
		panel.add(label3);
		panel.add(label4);
		panel.add(label5);
		panel.add(label7);
		panel.add(label8);
		panel.add(label9);
		panel.add(tF1);
		panel.add(tF2);
		panel.add(tF3);
		panel.add(tF4);
		panel.add(tF5);
		panel.add(tF6);
		panel.add(tF7);
		panel.add(combo1);
		panel.add(combo2);
		
	}
	/**
	 * Recibe un String de titulo que se mostrará en la parte superior del Jcombo y 
	 * tambien un arreglo de Strings con sus respectivas opciones
	 * @param titulo
	 * @param opciones
	 */
	public void addCombo1(String titulo,String[] opciones ){
		combo1.setModel(new DefaultComboBoxModel<String>(opciones));
		combo1.setVisible(true);
		this.label8.setText(titulo);
		this.label8.setVisible(true);
		
	}
	/**
	 * Recibe un String de titulo que se mostrará en la parte superior del Jcombo y 
	 * tambien un arreglo de Strings con sus respectivas opciones
	 * @param titulo
	 * @param opciones
	 */
	public void addCombo2(String titulo,String[] opciones ){
		combo2.setModel(new DefaultComboBoxModel<String>(opciones));
		combo2.setVisible(true);
		this.label9.setText(titulo);
		this.label9.setVisible(true);
		
	}
	/**
	 * Agrega el texto a Titulo y lo hace visible
	 * @param text
	 */
	public void addTxtTitulo (String text){
		titulo.setText(text);
		titulo.setVisible(true);
	}
	/**
	 * Agrega el texto a Label 1 y hace visible este y tF1
	 * @param text
	 */
	public void addTxtLabel1 (String text){
		label1.setText(text);
		label1.setVisible(true);
		tF1.setVisible(true);
	}
	/**
	 * Agrega el texto a Label 2 y hace visible este y tF2
	 * @param text
	 */
	public void addTxtLabel2 (String text){
		label2.setText(text);
		label2.setVisible(true);
		tF2.setVisible(true);
	}
	/**
	 * Agrega el texto a Label 3 y hace visible este y tF3
	 * @param text
	 */
	public void addTxtLabel3 (String text){
		label3.setText(text);
		label3.setVisible(true);
		tF3.setVisible(true);
	}	
	/**
	 * Agrega el texto a Label 4 y hace visible este y tF4
	 * @param text
	 */
	public void addTxtLabel4 (String text){
		label4.setText(text);
		label4.setVisible(true);
		tF4.setVisible(true);
	}
	/**
	 * Agrega el texto a Label 5 y hace visible este y tF5
	 * @param text
	 */
	public void addTxtLabel5 (String text){
		label5.setText(text);
		label5.setVisible(true);
		tF5.setVisible(true);
	}
	/**
	 * Agrega el texto a Label 6 y hace visible este y tF6
	 * @param text
	 */
	public void addTxtLabel6 (String text){
		label6.setText(text);
		label6.setVisible(true);
		tF6.setVisible(true);
	}
	/**
	 * Agrega el texto a Label 7 y hace visible este y tF7
	 * @param text
	 */
	public void addTxtLabel7 (String text){
		label7.setText(text);
		label7.setVisible(true);
		tF7.setVisible(true);
	}
	

	
	public String getTxttF1(){
		return tF1.getText();
	}
	public String getTxttF2(){
		return tF2.getText();
	}
	public String getTxttF3(){
		return tF3.getText();
	}
	public String getTxttF4(){
		return tF4.getText();
	}
	public String getTxttF5(){
		return tF5.getText();
	}
	public String getTxttF6(){
		return tF6.getText();
	}
	public String getTxttF7(){
		return tF7.getText();
	}
	
	/*GETTERS Y SETTERS
	 * */		
	public JLabel getTitulo() {
		return titulo;
	}
	public void setTitulo(JLabel label1) {
		this.titulo=label1;
	}	
	public JLabel getLabel1() {
		return label1;
	}
	public void setLabel1(JLabel label1) {
		this.label1=label1;
	}
	public JLabel getLabel2() {
		return label2;
	}
	public void setLabel2(JLabel label1) {
		this.label2=label1;
	}
	public JLabel getLabel3() {
		return label3;
	}
	public void setLabel3(JLabel label1) {
		this.label3=label1;
	}
	public JLabel getLabel4() {
		return label4;
	}
	public void setLabel4(JLabel label1) {
		this.label4=label1;
	}
	public JLabel getLabel5() {
		return label5;
	}
	public void setLabel5(JLabel label1) {
		this.label5=label1;
	}
	public TextField gettF1() {
		return tF1;
	}
	public void settF1(TextField tF1) {
		this.tF1 = tF1;
	}
	public TextField gettF2() {
		return tF2;
	}
	public void settF2(TextField tF1) {
		this.tF2 = tF1;
	}
	public TextField gettF3() {
		return tF3;
	}
	public void settF3(TextField tF1) {
		this.tF3 = tF1;
	}
	public TextField gettF4() {
		return tF4;
	}
	public void settF4(TextField tF1) {
		this.tF4 = tF1;
	}
	public TextField gettF5() {
		return tF5;
	}
	public void settF5(TextField tF1) {
		this.tF5 = tF1;
	}
	public TextField gettF6() {
		return tF6;
	}
	public void settF6(TextField tF1) {
		this.tF6 = tF1;
	}
	public TextField gettF7() {
		return tF7;
	}
	public void settF7(TextField tF1) {
		this.tF7 = tF1;
	}
	public JComboBox<String> getCombo1() {
		return combo1;
	}
	public void setCombo1(JComboBox<String> combo) {
		this.combo1 = combo;
	}
	public JComboBox<String> getCombo2() {
		return combo2;
	}
	public void setCombo2(JComboBox<String> combo) {
		this.combo2 = combo;
	}

	public JLabel getLabel8() {
		return label8;
	}

	public void setLabel8(JLabel label8) {
		this.label8 = label8;
	}

	public JLabel getLabel9() {
		return label9;
	}

	public void setLabel9(JLabel label9) {
		this.label9 = label9;
	}
	
}
