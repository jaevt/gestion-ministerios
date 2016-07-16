package principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Institucion implements Arreglo{
	private int ID;
	private String titulo;
	private String telefono;
	private String direccion;
	private String correo;
	private ArrayList<Funcionario> funcionarios;
	private Connection connection;
	private int ult_ID=0;
	public Institucion(int ID,String titulo, String telefono,String direccion,String correo){
		this.ID=ID;
		this.titulo=titulo;
		this.telefono=telefono;
		this.direccion=direccion;
		this.correo=correo;
		this.setFuncionarios(new ArrayList<Funcionario>());
	}
	
	public void conectarBD(){
		// Verifica si existe la libreria de PostgreSQL 9.4
		try {
			Class.forName("org.postgresql.Driver");
		}catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			e.printStackTrace();
			return;
		}
		this.setConnection(null);
		//Intenta conexion
		try {
			setConnection(DriverManager.getConnection(	
							"jdbc:postgresql://localhost:5432/gestion_ministerios",						
							"progra_av", 							
							"progra_av"
						));
			getConnection().setAutoCommit(false);
		}catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}	
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre() {
		return titulo;
	}
	public void setNombre(String nombre) {
		this.titulo = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public int getUlt_ID() {
		return ult_ID;
	}

	public void setUlt_ID(int ult_ID) {
		this.ult_ID = ult_ID;
	}
}
