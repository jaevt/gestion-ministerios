package principal;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class Gobierno extends Institucion implements Arreglo {
	static final int gob = 1000000;
	private ArrayList<Ministerio> ministerios;
	/**
	 * Constructor de Gobierno, requiere 4 Strings para llenar sus datos.
	 * @param titulo
	 * @param telefono
	 * @param direccion
	 * @param correo
	 */
	public Gobierno(String titulo, String telefono,String direccion,String correo){
		super(gob,titulo,telefono,direccion,correo);
		ministerios = new ArrayList<Ministerio>();
	}
	/**
	 * Agrega un ministerio dentro del programa, lo agregar al ArrayList ministerios
	 * y posteriormente a la base de datos tabla bd_ministerios
	 * @param titulo
	 * @param telefono
	 * @param direccion
	 * @param correo
	 */
	public void AgregarMinisterio(String titulo, String telefono,String direccion,String correo){
		this.setUlt_ID(this.getUlt_ID()+1);
		Ministerio ministerioNuevo = new Ministerio(gob+(getUlt_ID()*10000),titulo,telefono,direccion,correo);
		Agregar(ministerioNuevo);
		ActualizarBD();
		AgregarBD(ministerioNuevo);
	}
	/**
	 * Agrega un ministerio dentro del programa, lo agregar al ArrayList ministerios
	 * y posteriormente a la base de datos tabla bd_ministerios
	 * @param titulo
	 * @param telefono
	 * @param direccion
	 * @param correo
	 */
	public void AgregarMinisterio(int ID,String titulo, String telefono,String direccion,String correo,int ult){
		Ministerio ministerioNuevo = new Ministerio(ID,titulo,telefono,direccion,correo);
		ministerioNuevo.setUlt_ID(ult);
		Agregar(ministerioNuevo);
	}
	/**
	 * Devuelve un arreglo de String con los datos de los ministerios que contiene Gobierno
	 * devuelve String[] en un formato ID+" "+NombreMinisterio 
	 * @return
	 */
	public String[] listaMinisterios(){
		String[] resultado = new String[ministerios.size()];
		for( int i=0 ; i<ministerios.size() ; i++)
		{
			resultado[i]=Integer.toString(ministerios.get(i).getID()) + " " +ministerios.get(i).getNombre();
		}
		return resultado;
	}
	/**
	 * Devuelve la respectiva Lista de departamentos de un determinado MInisterio,
	 * este ministerio es buscado por su ID y retorna el String[] con la lista
	 * que retorna la funcion listaDepartamentos de ese Ministerio
	 * @param consulta
	 * @return
	 */
	public String[] listaDepartamentos(int consulta){
		for( int i=0 ; i<ministerios.size() ; i++)
		{
			if(ministerios.get(i).getID()==consulta){
				return ministerios.get(i).listaDepartamentos();
			}
		}
		return null;
	}
	/**
	 * Agrega a la base de datos postgresql 9.4 la institucion, tabla bd_ministerios
	 * @param a
	 */
	public void AgregarBD(Institucion a){
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
			String sql ="INSERT INTO bd_ministerios (ID,nombre,direccion,correo,telefono,ult) VALUES (" 
						+a.getID()+ ",'" 
						+a.getNombre()+ "','" 
						+a.getDireccion()+ "','"
						+a.getCorreo()+"','"
						+a.getTelefono() + "',"
						+a.getUlt_ID()+
						");";
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
	        String sql = "UPDATE gobierno SET ult = "+Integer.toString(getUlt_ID())+" WHERE ult>-1;";
	        comando.executeUpdate(sql);
	        getConnection().commit();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * 
	 */
	public void LeerBD(){
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
			String sql ="SELECT * FROM gobierno";
			ResultSet resultado = comando.executeQuery(sql);
			resultado.next();
			this.setUlt_ID(resultado.getInt(1));
			sql ="SELECT * FROM bd_ministerios";
			resultado = comando.executeQuery(sql);
			int ult=0;
			while(resultado.next()){ 
				int ID=resultado.getInt(1);
				String titulo = resultado.getString(2);
				String direccion = resultado.getString(3);
				String correo = resultado.getString(4);
				String telefono = resultado.getString(5);
				ult = resultado.getInt(6);
				AgregarMinisterio(ID,titulo, telefono, direccion, correo,ult);
			}
			sql ="SELECT * FROM bd_departamentos";
			resultado = comando.executeQuery(sql);
			while(resultado.next()){ 
				int ID=resultado.getInt(1);
				String titulo = resultado.getString(2);
				String direccion = resultado.getString(3);
				String correo = resultado.getString(4);
				String telefono = resultado.getString(5);
				ult = resultado.getInt(6);
				int index= Buscar(ID-(ID%10000));
				ministerios.get(index).AgregarDepartamento(ID, titulo, telefono, direccion, correo,ult);
			}
			sql ="SELECT * FROM bd_funcionarios";
			resultado = comando.executeQuery(sql);
			while(resultado.next()){ 
				int ID=resultado.getInt(1);
				String nombre = resultado.getString(2);
				String apellido = resultado.getString(3);
				String correo = resultado.getString(4);
				int index= Buscar(ID-(ID%10000));
				int index2=ministerios.get(index).Buscar(ID-(ID%100));
				ministerios.get(index).getDepartamentos().get(index2).AgregarFuncionario(ID,nombre, apellido, correo);
			}
			resultado.close();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	
	@Override
	public String[] Datos(){
		String[] datos = {"ID: "+Integer.toString(this.getID()),"Titulo: "+this.getNombre(),"Direccion: "+this.getDireccion(),"Telefono: "+this.getTelefono(),"Correo: "+this.getCorreo()};
		return datos;
	}
	
	
	@Override
	public void Agregar(Institucion a) {
		// TODO Auto-generated method stub
		ministerios.add((Ministerio) a);
		System.out.println(a.getNombre() +" ID:"+ a.getID()+ " AGREGADO");
	}




	@Override
	public void Eliminar(String texto) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void Eliminar(int ID) {
		// TODO Auto-generated method stub
		for(int i=0;i<ministerios.size();i++){
			if(ministerios.get(i).getID()==ID){
				conectarBD();
				//Realiza la consulta y cierra la conexion
				try {
					Statement comando = getConnection().createStatement();
					String sql ="DELETE FROM bd_ministerios WHERE id ="+ministerios.get(i).getID()+";";
					ResultSet resultado = comando.executeQuery(sql);
					getConnection().commit();
					resultado.close();
					comando.close();
					getConnection().close();
				}catch(Exception e) {
					System.out.println(e.getMessage());
				}
				ministerios.remove(i);
				return;
			}
		}
		
	}


	@Override
	public Object[][] Buscar(String texto) {
		// TODO Auto-generated method stub
		
		ArrayList<String> arreglo = new ArrayList<String>();
		for( int i=0 ; i<ministerios.size() ; i++)
		{
			if(	ministerios.get(i).getNombre().equals(texto) ||
				ministerios.get(i).getCorreo().equals(texto)||
				ministerios.get(i).getDireccion().equals(texto)||
				ministerios.get(i).getTelefono().equals(texto)
				)
			{
				System.out.println( ministerios.get(i).getID() );
				System.out.println( ministerios.get(i).getCorreo() );
				System.out.println( ministerios.get(i).getDireccion() );
				System.out.println( ministerios.get(i).getNombre() );
				System.out.println( ministerios.get(i).getTelefono() );
				
				
				arreglo.add(Integer.toString( ministerios.get(i).getID() ) );
				arreglo.add(ministerios.get(i).getNombre() );
				arreglo.add(ministerios.get(i).getCorreo() );
				arreglo.add(ministerios.get(i).getDireccion() );
			}
			ministerios.get(i).Buscar(arreglo,texto);
		}
		Object[][] resultado = new Object[arreglo.size()/4][4];
		int x=0;
		for( int i=0; i<arreglo.size();i+=4){
			System.out.println( arreglo.get(i) );
			resultado[x][0]=(Object)arreglo.get(i).toString();
			resultado[x][1]=(Object)arreglo.get(i+1).toString();
			resultado[x][2]=(Object)arreglo.get(i+2).toString();
			resultado[x][3]=(Object)arreglo.get(i+3).toString();
			x++;
		}
		return resultado;
	}

	@Override
	public int Buscar(int ID) {
		// TODO Auto-generated method stub
		int aux=ID%10000;
		ID=ID-aux;
		for( int i=0 ; i<ministerios.size() ; i++)
		{
			if(ministerios.get(i).getID()==ID)
			{
				aux=i;
			}
		}
		return aux;
	}


	@Override
	public void AgregarFuncionario(Persona p) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public ArrayList<Ministerio> getMinisterios() {
		return ministerios;
	}
	public void setDepartamentos(ArrayList<Ministerio> ministerios) {
		this.ministerios = ministerios;
	}
	
	public void Editar(int i,String titulo,String telefono,String direccion,String correo) {
		// TODO Auto-generated method stub
		ministerios.get(i).setNombre(titulo);
		ministerios.get(i).setTelefono(telefono);
		ministerios.get(i).setDireccion(direccion);
		ministerios.get(i).setCorreo(correo);
		conectarBD();
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = getConnection().createStatement();
	        String sql = "UPDATE bd_ministerios SET nombre = '"+titulo+"', direccion = '"+direccion+"', correo = '"+correo+"', telefono = '"+telefono+"',  WHERE id="+ministerios.get(i).getID()+";";
	        System.out.println(sql);
	        comando.executeUpdate(sql);
	        getConnection().commit();
			comando.close();
			getConnection().close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
