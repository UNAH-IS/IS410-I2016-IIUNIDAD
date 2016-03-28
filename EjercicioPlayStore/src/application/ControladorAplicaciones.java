package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.Categoria;
import modelo.Desarrollador;
import modelo.Empresa;
import utilidades.GestorConexiones;

public class ControladorAplicaciones implements Initializable{
	
	private GestorConexiones gestorConexiones;
	@FXML private ComboBox<Categoria> cboCategorias;
	@FXML private ComboBox<Desarrollador> cboDesarrolladores;
	@FXML private ComboBox<Empresa> cboEmpresas;
	
	
	private ObservableList<Categoria> categorias;
	private ObservableList<Desarrollador> desarrolladores;
	private ObservableList<Empresa> empresas;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		categorias = FXCollections.observableArrayList();
		desarrolladores = FXCollections.observableArrayList();
		empresas= FXCollections.observableArrayList();
		cboCategorias.setItems(categorias);
		cboDesarrolladores.setItems(desarrolladores);
		cboEmpresas.setItems(empresas);
		inicializarInformacion();
		gestorConexiones.cerrarConexion();
	}
	
	public void inicializarInformacion(){
		Categoria.llenarListaCategorias(
				categorias, gestorConexiones.getConexion()
		);
		Desarrollador.llenarListaDesarrolladores(
			desarrolladores, gestorConexiones.getConexion()
		);
		Empresa.llenarListaEmpresas(
			empresas, gestorConexiones.getConexion()
		);
		
	}
}
