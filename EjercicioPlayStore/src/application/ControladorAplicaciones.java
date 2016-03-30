package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Aplicacion;
import modelo.Categoria;
import modelo.Desarrollador;
import modelo.Empresa;
import utilidades.GestorConexiones;

public class ControladorAplicaciones implements Initializable{
	
	private GestorConexiones gestorConexiones;
	@FXML private ComboBox<Categoria> cboCategorias;
	@FXML private ComboBox<Desarrollador> cboDesarrolladores;
	@FXML private ComboBox<Empresa> cboEmpresas;
	@FXML private TableView<Aplicacion> tblViewAplicaciones;
	
	//Columnas del TableView
	@FXML private TableColumn<Aplicacion, String> tblClmnNombreAplicacion;
	@FXML private TableColumn<Aplicacion, Desarrollador> tblClmnDesarrollador;
	@FXML private TableColumn<Aplicacion, Categoria> tblClmnCategoria;
	@FXML private TableColumn<Aplicacion, String> tblClmnVersion;
	@FXML private TableColumn<Aplicacion, Double> tblClmnCalificacion;
	
	
	private ObservableList<Categoria> categorias;
	private ObservableList<Desarrollador> desarrolladores;
	private ObservableList<Empresa> empresas;
	private ObservableList<Aplicacion> aplicaciones;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		categorias = FXCollections.observableArrayList();
		desarrolladores = FXCollections.observableArrayList();
		empresas = FXCollections.observableArrayList();
		aplicaciones = FXCollections.observableArrayList();
		cboCategorias.setItems(categorias);
		cboDesarrolladores.setItems(desarrolladores);
		cboEmpresas.setItems(empresas);
		tblViewAplicaciones.setItems(aplicaciones);
		inicializarInformacion();
		asociarColumnas();
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
		aplicaciones.add(
				new Aplicacion(1,
						new Desarrollador(0,"Juan","wwww.pagina.com","juan@gmail.com"),
						null,null,
						"Angry Bird", "1.0",
						"Pajaros enojados",5.0, null)
			);
		aplicaciones.add(
				new Aplicacion(1,null,null,null,
						"Angry Bird", "1.0",
						"Pajaros enojados",5.0, null)
			);
		
	}
	
	public void asociarColumnas(){
		tblClmnNombreAplicacion.setCellValueFactory(
				 new PropertyValueFactory<Aplicacion, String>("nombreAplicacion")
		);
		tblClmnDesarrollador.setCellValueFactory(
				 new PropertyValueFactory<Aplicacion, Desarrollador>("desarrollador")
		);
		tblClmnCategoria.setCellValueFactory(
				 new PropertyValueFactory<Aplicacion, Categoria>("categoria")
		);
		tblClmnVersion.setCellValueFactory(
				 new PropertyValueFactory<Aplicacion, String>("version")
		);
		tblClmnCalificacion.setCellValueFactory(
				 new PropertyValueFactory<Aplicacion, Double>("calificacion")
		);
	}
}
