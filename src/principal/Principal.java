package principal;

import gui.Menu;

public class Principal {
	/**
	 * Se inicia el programa, se crea el Gobierno que se utilizara para guardar la 
	 * informacion dentro del programa, luego se le agrega a este los datos almacenados en la base 
	 * de datos 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gobierno gobierno = new Gobierno( "Chile" , "(2) 2690 4000","Moneda S/N, Santiago, Regi�n Metropolitana" , "gob@gob.cl" );
		gobierno.LeerBD();
		Menu menu = new Menu(gobierno);
		menu.setVisible(true);
	}

}
