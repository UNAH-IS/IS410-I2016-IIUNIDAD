package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class Categoria {
	private int codigoCategoria;
	private String nombreCategoria;
	private String descripcion;
	
	
	public Categoria(int codigoCategoria, String nombreCategoria,
			String descripcion) {
		this.codigoCategoria = codigoCategoria;
		this.nombreCategoria = nombreCategoria;
		this.descripcion = descripcion;
	}
	public int getCodigoCategoria() {
		return codigoCategoria;
	}
	public void setCodigoCategoria(int codigoCategoria) {
		this.codigoCategoria = codigoCategoria;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return nombreCategoria;
	}
	
	public static void llenarListaCategorias(
				ObservableList<Categoria> lista,
				Connection conexion
			){
		//Consultar informacion de categorias
		try {
			Statement instruccion = conexion.createStatement();
			ResultSet resultado = instruccion.executeQuery("SELECT codigo_categoria, "
					+ "nombre_categoria, descripcion "
					+ "FROM tbl_categorias");
			
			while(resultado.next()){
				lista.add(
						new Categoria(
							resultado.getInt("codigo_categoria"),
							resultado.getString("nombre_categoria"),
							resultado.getString("descripcion")
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
}
