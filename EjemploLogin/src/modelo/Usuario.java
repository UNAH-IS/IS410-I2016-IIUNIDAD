package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario{
	private IntegerProperty codigoUsuario;
	private StringProperty nombreUsuario;
	private StringProperty contrasena;

	public Usuario(int codigoUsuario, String nombreUsuario, String contrasena) { 
		this.codigoUsuario = new SimpleIntegerProperty(codigoUsuario);
		this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
		this.contrasena = new SimpleStringProperty(contrasena);
	}
	public Usuario(String nombreUsuario, String contrasena) { 
		this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
		this.contrasena = new SimpleStringProperty(contrasena);
	}

	//Metodos atributo: codigoUsuario
	public int getCodigoUsuario() {
		return codigoUsuario.get();
	}
	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = new SimpleIntegerProperty(codigoUsuario);
	}
	public IntegerProperty CodigoUsuarioProperty() {
		return codigoUsuario;
	}
	//Metodos atributo: nombreUsuario
	public String getNombreUsuario() {
		return nombreUsuario.get();
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = new SimpleStringProperty(nombreUsuario);
	}
	public StringProperty NombreUsuarioProperty() {
		return nombreUsuario;
	}
	//Metodos atributo: contrasena
	public String getContrasena() {
		return contrasena.get();
	}
	public void setContrasena(String contrasena) {
		this.contrasena = new SimpleStringProperty(contrasena);
	}
	public StringProperty ContrasenaProperty() {
		return contrasena;
	}
	
	public int verificarUsuario(Connection conexion){
		try {
			PreparedStatement instruccion = 
					conexion.prepareStatement(
						"SELECT codigo_usuario " + 
						"FROM tbl_usuarios " +
						"WHERE usuario = ? " +
						"AND contrasena = sha1(?)"
					);
			instruccion.setString(1, nombreUsuario.get());
			instruccion.setString(2, contrasena.get());
			ResultSet resultado = instruccion.executeQuery();
			if(resultado.next())
				return resultado.getInt("codigo_usuario");
			else
				return -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
}