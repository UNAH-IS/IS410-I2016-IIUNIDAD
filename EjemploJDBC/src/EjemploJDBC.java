import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class EjemploJDBC {
	private Connection conexion;
	private final String URL = "jdbc:mysql://localhost/db_playstore";
	private Statement instruccion; //Objeto necesario para ejecutar un instruccion SQL no parametrizada
	private ResultSet resultado; //Gestionar el resultado de una consulta SQL
	public EjemploJDBC(){
		try {
			Class.forName("com.mysql.jdbc.Driver");//Cargar el driver a la clase actual.
			//Si pasa de la siguiente linea la conexion 
			//fue exitosa.
			conexion = 
					DriverManager.getConnection(
							URL, "root", ""
					);
			System.out.println("Conexion Exitosa");
			
			instruccion = conexion.createStatement();
			resultado = instruccion.
					executeQuery("SELECT codigo_categoria, "
							+ "nombre_categoria, descripcion "
							+ "FROM tbl_categorias");
			while(resultado.next()){
				System.out.println(
					"Codigo: " + resultado.getInt("codigo_categoria")+ " " +
					"Categoria: " + resultado.getString("nombre_categoria") + " " +
					"Descripcion: " + resultado.getString("descripcion") 
				);
			}
			instruccion.close();
			resultado.close();
			conexion.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		new EjemploJDBC();
	}

}
