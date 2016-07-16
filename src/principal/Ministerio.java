package principal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Ministerio extends Institucion implements Arreglo {
	private ArrayList<Departamento> departamentos;
	
	public Ministerio(int ID,String titulo, String telefono,String direccion,String correo){
		super(ID,titulo,telefono,direccion,correo);
		setDepartamentos(new ArrayList<Departamento>());
	}

	public void AgregarDepartamento(String titulo, String telefono,String direccion,String correo){
		setUlt_ID(getUlt_ID()+1);
		Departamento departamentoNuevo = new Departamento(getID()+(getUlt_ID()*100),titulo,telefono,direccion,correo);
		Agregar(departamentoNuevo);
		ActualizarBD();
		AgregarBD(departamentoNuevo);
	}
	public void AgregarDepartamento(int ID,String titulo, String telefono,String direccion,String correo, int ult){
		Departamento departamentoNuevo = new Departamento(ID,titulo,telefono,direccion,correo);
		departamentoNuevo.setUlt_ID(ult);
		Agregar(departamentoNuevo);
	}

	public String[] listaDepartamentos(){
		String[] resultado = new String[departamentos.size()];
		for( int i=0 ; i<departamentos.size() ; i++)
		{
			resultado[i]=Integer.toString(departamentos.get(i).getID()) + " " +departamentos.get(i).getNombre();
		}
		return resultado;
	}
	
	
	@Override
	public String[] Datos(){
		String[] datos = {"ID: "+Integer.toString(this.getID()),"Titulo: "+this.getNombre(),"Direccion: "+this.getDireccion(),"Telefono: "+this.getTelefono(),"Correo: "+this.getCorreo()};
		return datos;
	}
	
	
	@Override
	public void Agregar(Institucion a) {
		// TODO Auto-generated method stub
		departamentos.add((Departamento) a);
		System.out.println(a.getNombre() +" ID- DEPARTAMENTO:"+ a.getID()+   "AGREGADO");
		
	}


	@Override
	public void Eliminar(String texto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Eliminar(int ID) {
		// TODO Auto-generated method stub
		for(int i=0;i<departamentos.size();i++){
			if(departamentos.get(i).getID()==ID){
				conectarBD();
				//Realiza la consulta y cierra la conexion
				try {
					Statement comando = getConnection().createStatement();
					String sql ="DELETE FROM bd_departamentos WHERE id ="+departamentos.get(i).getID()+";";
					ResultSet resultado = comando.executeQuery(sql);
					getConnection().commit();
					resultado.close();
					comando.close();
					getConnection().close();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				departamentos.remove(i);
				return;
			}
		}
		
	}

	public void Buscar(ArrayList<String> arreglo,String texto) {
		// TODO Auto-generated method stub
		for( int i=0 ; i<departamentos.size() ; i++)
		{
			if(	departamentos.get(i).getNombre().equals(texto) ||
				departamentos.get(i).getCorreo().equals(texto)||
				departamentos.get(i).getDireccion().equals(texto)||
				departamentos.get(i).getTelefono().equals(texto)
				)
			{
				arreglo.add(Integer.toString(departamentos.get(i).getID() ) );
				arreglo.add( departamentos.get(i).getNombre() );
				arreglo.add( departamentos.get(i).getCorreo() );
				arreglo.add( departamentos.get(i).getDireccion() );
			}
			departamentos.get(i).Buscar(arreglo,texto);
		}
	}
	/**
	 * Agrega a la base de datos postgresql 9.4 la institucion, tabla bd_departamentos
	 * @param a
	 */
	public void AgregarBD(Institucion a){
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
			String sql ="INSERT INTO bd_departamentos (ID,nombre,direccion,correo,telefono,ult) VALUES (" 
						+a.getID()+ ",'" 
						+a.getNombre()+ "','" 
						+a.getDireccion()+ "','"
						+a.getCorreo()+"','"
						+a.getTelefono()+"',"
						+a.getUlt_ID()+");";
			ResultSet resultado = comando.executeQuery(sql);
			getConnection().commit();
			resultado.close();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
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
	        String sql = "UPDATE bd_ministerios SET ult = "+Integer.toString(getUlt_ID())+" WHERE id="+getID()+";";
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
		int aux=ID%100;
		ID=ID-aux;
		for( int i=0 ; i<departamentos.size() ; i++)
		{
			if(departamentos.get(i).getID()==ID)
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
		
	}

	@Override
	public Object[][] Buscar(String texto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(ArrayList<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public void Editar(int i,String titulo,String telefono,String direccion,String correo) {
		// TODO Auto-generated method stub
		departamentos.get(i).setNombre(titulo);
		departamentos.get(i).setTelefono(telefono);
		departamentos.get(i).setDireccion(direccion);
		departamentos.get(i).setCorreo(correo);
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
	        String sql = "UPDATE bd_departamentos SET nombre = '"+titulo+"' ,direccion='"+direccion+"',telefono='"+telefono+"',correo='"+correo+"' WHERE id="+departamentos.get(i).getID()+";";
	        comando.executeUpdate(sql);
	        getConnection().commit();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
