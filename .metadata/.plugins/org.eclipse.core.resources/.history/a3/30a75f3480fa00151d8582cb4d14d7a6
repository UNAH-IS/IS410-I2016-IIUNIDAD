package modelo;

import java.sql.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
}