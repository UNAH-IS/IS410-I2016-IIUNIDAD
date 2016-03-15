package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.Categoria;
import utilidades.UtilidadConexion;

public class ControladorAplicaciones implements Initializable{
	
	private UtilidadConexion utilidadConexion;
	@FXML private ComboBox<Categoria> cboCategorias;
	
	
	private ObservableList<Categoria> categorias;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		utilidadConexion = new UtilidadConexion();
		utilidadConexion.establecerConexion();
		categorias = FXCollections.observableArrayList();
		cboCategorias.setItems(categorias);
		inicializarInformacion();
	}
	
	public void inicializarInformacion(){
		categorias.add(new Categoria(1,"Prueba", "Descripcion Prueba"));
	}
	
	
}
