package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Desarrollador {
	private int codigoDesarrollador;
	private String nombreDesarrollador;
	private String paginaWeb;
	private String correoElectronico;
	public Desarrollador(int codigoDesarrollador, String nombreDesarrollador,
			String paginaWeb, String correoElectronico) {
		super();
		this.codigoDesarrollador = codigoDesarrollador;
		this.nombreDesarrollador = nombreDesarrollador;
		this.paginaWeb = paginaWeb;
		this.correoElectronico = correoElectronico;
	}
	public int getCodigoDesarrollador() {
		return codigoDesarrollador;
	}
	public void setCodigoDesarrollador(int codigoDesarrollador) {
		this.codigoDesarrollador = codigoDesarrollador;
	}
	public String getNombreDesarrollador() {
		return nombreDesarrollador;
	}
	public void setNombreDesarrollador(String nombreDesarrollador) {
		this.nombreDesarrollador = nombreDesarrollador;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	@Override
	public String toString() {
		return nombreDesarrollador;
	}
	
	public static void llenarListaDesarrolladores(
			ObservableList<Desarrollador> lista,
			Connection conexion
	){
		Statement instruccion;
		try {
			instruccion = conexion.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT  codigo_desarrollador, nombre_desarrollador,"
					+ "pagina_web, correo_electronico "
					+ "FROM tbl_desarrolladores"
			);
			while(resultado.next()){
				lista.add(
						new Desarrollador(
							resultado.getInt("codigo_desarrollador"),
							resultado.getString("nombre_desarrollador"),
							resultado.getString("pagina_web"),
							resultado.getString("correo_electronico")
						)
				);
			}
			instruccion.close();
			resultado.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
