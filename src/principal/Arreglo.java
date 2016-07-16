package principal;

public interface Arreglo {
	public void Agregar(Institucion a);
	public void Eliminar(String texto);
	public void Eliminar (int pos);
	public void AgregarFuncionario(Persona p);
	public Object[][] Buscar(String texto);
	public int Buscar(int ID);
	public String[] Datos();
}