package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Persona {
	private String nombre = "";
	private String apellido = "";
	private String correo = "";
	
	public Persona(){
		
	}
	
	public Persona(String nombre,String apellido, String correo){
		this.nombre=nombre;
		this.apellido=apellido;
		this.correo=correo;
	}
	
	/**
	 * Agrega a la base de datos postgresql 9.4 el ministerio, tabla bd_ministerios
	 * @param a
	 */
	public void AgregarBD(Funcionario a){
		// Verifica si existe la libreria de PostgreSQL 9.1
		try {
			Class.forName("org.postgresql");
		}catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
		}
		Connection connection = null;
		//Intenta conexion
		try {
			connection = DriverManager.getConnection(	
							"jdbc:postgresql://localhost:5432/gestion_ministerios",						
							"progra_av", 							
							"progra_av"
						);
		}catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		//Realiza la consulta y cierra la conexion
		try {
			Statement comando = connection.createStatement();
			String sql ="INSERT INTO bd_personas (ID,nombre,correo) VALUES (" 
						+a.getID()+ ",'" 
						+a.getNombre()+ "','" 
						+a.getApellido()+ "','"
						+a.getCorreo()+"');";
			ResultSet resultado = comando.executeQuery(sql);
			resultado.close();
			comando.close();
			connection.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
