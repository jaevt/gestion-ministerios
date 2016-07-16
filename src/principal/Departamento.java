package principal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Departamento extends Institucion implements Arreglo {
	
	public Departamento(int ID,String titulo, String telefono,String direccion,String correo){
		super(ID,titulo,telefono,direccion,correo);
	}	

	public void AgregarFuncionario(String nombre, String apellido,String correo){
		setUlt_ID(getUlt_ID()+1);
		Funcionario funcionarioNuevo = new Funcionario(getID()+(getUlt_ID()),nombre,apellido,correo);
		AgregarFuncionario(funcionarioNuevo);
		ActualizarBD();
		AgregarBD(funcionarioNuevo);
	}
	public void AgregarFuncionario(int ID,String nombre, String apellido,String correo){
		Funcionario funcionarioNuevo = new Funcionario(ID,nombre,apellido,correo);
		AgregarFuncionario(funcionarioNuevo);
	}

	/**
	 * Agrega a la base de datos postgresql 9.4 la institucion, tabla bd_instituciones
	 * @param a
	 */
	public void AgregarBD(Funcionario a){
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
			String sql ="INSERT INTO bd_funcionarios (ID,nombre,apellido,correo) VALUES ("
						+a.getID()+ ",'" 
						+a.getNombre()+ "','" 
						+a.getApellido()+ "','" 
						+a.getCorreo()+"');";
			ResultSet resultado = comando.executeQuery(sql);
			getConnection().commit();
			resultado.close();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public String[] Datos() {
		// TODO Auto-generated method stub
		String[] datos = {"ID: "+Integer.toString(this.getID()),"Titulo: "+this.getNombre(),"Direccion: "+this.getDireccion(),"Telefono: "+this.getTelefono(),"Correo: "+this.getCorreo()};
		return datos;
	}
	
	@Override
	public void Agregar(Institucion a) {
		// TODO Auto-generated method stub

	}


	@Override
	public void Eliminar(String texto) {
		// TODO Auto-generated method stub


	}

	@Override
	public void Eliminar(int pos) {
		// TODO Auto-generated method stub
		for(int i =0;i<getFuncionarios().size();i++){
			if(getFuncionarios().get(i).getID()==pos){
				conectarBD();
				//Realiza la consulta y cierra la conexion
				try {
					Statement comando = getConnection().createStatement();
					String sql ="DELETE FROM bd_funcionarios WHERE id="
								+getFuncionarios().get(i).getID()+";";
					ResultSet resultado = comando.executeQuery(sql);
					getConnection().commit();
					resultado.close();
					comando.close();
					getConnection().close();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				getFuncionarios().remove(i);
				return;
			}
		}
	}

	public void Buscar(ArrayList<String> arreglo,String texto) {
		// TODO Auto-generated method stub
		for( int i=0 ; i<getFuncionarios().size() ; i++)
		{
			if(	getFuncionarios().get(i).getNombre().equals(texto) ||
					getFuncionarios().get(i).getApellido().equals(texto)||
					getFuncionarios().get(i).getCorreo().equals(texto)
				)
			{
				System.out.println( getFuncionarios().get(i).getID() );
				System.out.println( getFuncionarios().get(i).getCorreo() );
				System.out.println( getFuncionarios().get(i).getApellido() );
				System.out.println( getFuncionarios().get(i).getNombre() );
				
				
				arreglo.add( Integer.toString( getFuncionarios().get(i).getID() ) );
				arreglo.add( getFuncionarios().get(i).getNombre() );
				arreglo.add( getFuncionarios().get(i).getApellido() );
				arreglo.add( getFuncionarios().get(i).getCorreo() );
			}
		}
	}
	/**
	* Actualiza la ultima posicion dentro de la base de datos
	* @param a
	*/
	public void ActualizarBD(){
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
	        String sql = "UPDATE bd_departamentos SET ult = "+Integer.toString(getUlt_ID())+" WHERE id="+getID()+";";
	        comando.executeUpdate(sql);
	        getConnection().commit();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public int Buscar(int ID) {
		int aux=0;
		for( int i=0 ; i<getFuncionarios().size() ; i++)
		{
			if(getFuncionarios().get(i).getID()==ID)
			{
				aux=i;
			}
		}
		return aux;
		// TODO Auto-generated method stub
	}
	
	@Override
	public void AgregarFuncionario(Persona p) {
		// TODO Auto-generated method stub
		this.getFuncionarios().add((Funcionario) p);
		System.out.println(p.getNombre() +" ID- FUNCIONARIO:"+ ((Funcionario) p).getID()+   "AGREGADO");
	}

	@Override
	public Object[][] Buscar(String texto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Editar(int i,String nombre,String apellido,String correo) {
		// TODO Auto-generated method stub
		getFuncionarios().get(i).setNombre(nombre);
		getFuncionarios().get(i).setApellido(apellido);
		getFuncionarios().get(i).setCorreo(correo);
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
	        String sql = "UPDATE bd_funcionarios SET nombre = '"+nombre+"',apellido='"+apellido+"',correo='"+correo+"' WHERE id="+getFuncionarios().get(i).getID()+";";
	        comando.executeUpdate(sql);
	        getConnection().commit();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
