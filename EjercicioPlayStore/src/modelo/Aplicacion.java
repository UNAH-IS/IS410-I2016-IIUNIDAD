package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Aplicacion{
	private IntegerProperty codigoAplicacion;
	private Desarrollador desarrollador;
	private Empresa empresa;
	private Categoria categoria;
	private StringProperty nombreAplicacion;
	private StringProperty version;
	private StringProperty descripcion;
	private DoubleProperty calificacion;
	private Date fechaPublicacion;

	public Aplicacion(
			int codigoAplicacion, 
			Desarrollador desarrollador, 
			Empresa empresa, 
			Categoria categoria, 
			String nombreAplicacion, 
			String version, 
			String descripcion, 
			Double calificacion, 
			Date fechaPublicacion
	) { 
		this.codigoAplicacion = new SimpleIntegerProperty(codigoAplicacion);
		this.desarrollador = desarrollador;
		this.empresa = empresa;
		this.categoria = categoria;
		this.nombreAplicacion = new SimpleStringProperty(nombreAplicacion);
		this.version = new SimpleStringProperty(version);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.calificacion = new SimpleDoubleProperty(calificacion);
		this.fechaPublicacion = fechaPublicacion;
	}

	//Metodos atributo: codigoAplicacion
	public int getCodigoAplicacion() {
		return codigoAplicacion.get();
	}
	public void setCodigoAplicacion(int codigoAplicacion) {
		this.codigoAplicacion = new SimpleIntegerProperty(codigoAplicacion);
	}
	public IntegerProperty CodigoAplicacionProperty() {
		return codigoAplicacion;
	}
	//Metodos atributo: desarrollador
	public Desarrollador getDesarrollador() {
		return desarrollador;
	}
	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}
	//Metodos atributo: empresa
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	//Metodos atributo: categoria
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	//Metodos atributo: nombreAplicacion
	public String getNombreAplicacion() {
		return nombreAplicacion.get();
	}
	public void setNombreAplicacion(String nombreAplicacion) {
		this.nombreAplicacion = new SimpleStringProperty(nombreAplicacion);
	}
	public StringProperty NombreAplicacionProperty() {
		return nombreAplicacion;
	}
	//Metodos atributo: version
	public String getVersion() {
		return version.get();
	}
	public void setVersion(String version) {
		this.version = new SimpleStringProperty(version);
	}
	public StringProperty VersionProperty() {
		return version;
	}
	//Metodos atributo: descripcion
	public String getDescripcion() {
		return descripcion.get();
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = new SimpleStringProperty(descripcion);
	}
	public StringProperty DescripcionProperty() {
		return descripcion;
	}
	//Metodos atributo: calificacion
	public Double getCalificacion() {
		return calificacion.get();
	}
	public void setCalificacion(Double calificacion) {
		this.calificacion = new SimpleDoubleProperty(calificacion);
	}
	public DoubleProperty CalificacionProperty() {
		return calificacion;
	}
	//Metodos atributo: fechaPublicacion
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	public static void llenarListaAplicaciones(
			ObservableList<Aplicacion> lista,
			Connection conexion
	){
		//Consultar informacion de categorias
		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT A.codigo_aplicacion, " +
				    "A.codigo_desarrollador, " +
				    "A.codigo_empresa, " +
				    "A.codigo_categoria, " +
				    "A.nombre_aplicacion, " +
				    "A.version, " +
				    "A.descripcion AS descripcion_aplicacion, " +
				    "A.calificacion, " +
				    "A.icono, " + 
				    "A.fecha_publicacion, " +
				    "B.nombre_desarrollador, " +
				    "B.pagina_web, " +
				    "B.correo_electronico, " +
				    "C.nombre_empresa, " +
				    "C.direccion, " +
				    "C.telefono, " +
				    "D.nombre_categoria, " +
				    "D.descripcion  AS descripcion_categoria " +
				"FROM tbl_aplicaciones A " +
				"INNER JOIN tbl_desarrolladores B " +
				"ON (A.codigo_desarrollador = B.codigo_desarrollador) " +
				"INNER JOIN tbl_empresas C " +
				"ON (A.codigo_empresa = C.codigo_empresa) " +
				"INNER JOIN tbl_categorias D " +
				"ON (A.codigo_categoria = D.codigo_categoria) "
				+ "ORDER BY A.codigo_aplicacion"
			);
			
			while(resultado.next()){
				lista.add(
					new Aplicacion(
						resultado.getInt("codigo_aplicacion"),
						new Desarrollador(
							resultado.getInt("codigo_desarrollador"),
							resultado.getString("nombre_desarrollador"),
							resultado.getString("pagina_web"),
							resultado.getString("correo_electronico")
						),
						new Empresa(
							resultado.getInt("codigo_empresa"),
							resultado.getString("nombre_empresa"),
							resultado.getString("direccion"),
							resultado.getString("telefono")
						),
						new Categoria(
							resultado.getInt("codigo_categoria"),
							resultado.getString("nombre_categoria"),
							resultado.getString("descripcion_categoria")
						),
						resultado.getString("nombre_aplicacion"),
						resultado.getString("version"),
						resultado.getString("descripcion_aplicacion"),
						resultado.getDouble("calificacion"),
						resultado.getDate("fecha_publicacion")
					)
				);
			}
			
			instruccion.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//Llenar Lista
	}
	
	public int almacenarRegistro(Connection conexion){
		try {
			PreparedStatement instruccion = conexion.prepareStatement(
					"INSERT INTO tbl_aplicaciones ( " +
							"codigo_desarrollador, " +
							"codigo_empresa, " +
							"codigo_categoria, " +
							"nombre_aplicacion, " +
							"version, descripcion, " +
							"calificacion, " +
							"fecha_publicacion " +
					")  " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)"	
			);
			instruccion.setInt(1, desarrollador.getCodigoDesarrollador());
			instruccion.setInt(2, empresa.getCodigoEmpresa());
			instruccion.setInt(3, categoria.getCodigoCategoria());
			instruccion.setString(4, nombreAplicacion.get());
			instruccion.setString(5, version.get()); 
			instruccion.setString(6, descripcion.get());
			instruccion.setDouble(7, calificacion.get());
			instruccion.setDate(8, fechaPublicacion);
			int resultado = instruccion.executeUpdate();
			if (resultado==1){
				//Para obtener el codigo que fue insertado es necesario consultarlo con la 
				//funcion last_insert_id de mysql
				Statement instruccionId = conexion.createStatement();
				ResultSet resultadoId = instruccionId.executeQuery("SELECT last_insert_id() id");
				resultadoId.first();
				codigoAplicacion = new SimpleIntegerProperty(resultadoId.getInt("id"));
			}
			return resultado;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
	}
	
	public int actualizarRegistro(Connection conexion){
		try {
			PreparedStatement instruccion = 
					conexion.prepareStatement(
							"UPDATE tbl_aplicaciones "+
							"SET codigo_desarrollador = ?, "+ 
								"codigo_categoria = ?,  "+
								"codigo_empresa = ?,  "+
								"nombre_aplicacion = ?,  "+
								"descripcion = ?,  "+
								"version = ?,  "+
								"calificacion = ?,  "+
								"fecha_publicacion = ? "+ 
							"WHERE codigo_aplicacion = ?");
			
			instruccion.setInt(1, desarrollador.getCodigoDesarrollador());
			instruccion.setInt(2, categoria.getCodigoCategoria());
			instruccion.setInt(3, empresa.getCodigoEmpresa());
			instruccion.setString(4, nombreAplicacion.get());
			instruccion.setString(5, descripcion.get());
			instruccion.setString(6, version.get());
			instruccion.setDouble(7, calificacion.get());
			instruccion.setDate(8, fechaPublicacion);
			instruccion.setInt(9, codigoAplicacion.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int eliminarRegistro(Connection conexion){
		try {
			PreparedStatement instruccion = conexion.prepareStatement(
					"DELETE FROM tbl_aplicaciones "+ 
					"WHERE codigo_aplicacion = ?"
			);
			instruccion.setInt(1, codigoAplicacion.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}	
}