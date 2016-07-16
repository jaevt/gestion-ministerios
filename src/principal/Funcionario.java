package principal;
public class Funcionario extends Persona{
	private int ID;
	public Funcionario(int ID,String nombre,String apellido, String correo){
		super(nombre,apellido,correo);
		this.setID(ID);
	}
	


	public String[] Datos() {
		// TODO Auto-generated method stub
		String[] datos = {"ID: "+Integer.toString(this.getID()),"Nombre: "+this.getNombre(),"Apellido: "+this.getApellido(),"Correo: "+this.getCorreo()," "};
		return datos;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
