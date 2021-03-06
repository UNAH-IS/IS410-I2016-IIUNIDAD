package application;

import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Aplicacion;
import modelo.Categoria;
import modelo.Desarrollador;
import modelo.Empresa;
import utilidades.GestorConexiones;

public class ControladorAplicaciones implements Initializable{
	
	private GestorConexiones gestorConexiones;
	
	@FXML Button btnActualizar;
	@FXML Button btnEliminar;
	@FXML Button btnGuardar;
	
	@FXML private ComboBox<Categoria> cboCategorias;
	@FXML private ComboBox<Desarrollador> cboDesarrolladores;
	@FXML private ComboBox<Empresa> cboEmpresas;
	@FXML private TableView<Aplicacion> tblViewAplicaciones;
	
	@FXML private TextField txtNombreAplicacion;
	@FXML private TextField txtVersion;
	@FXML private TextArea txtDescripcion;
	@FXML private Slider sldCalificacion;
	@FXML private DatePicker dpckFechaPublicacion;
	
	
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
		
		tblViewAplicaciones.
				getSelectionModel().
				selectedItemProperty().
				addListener(new ChangeListener<Aplicacion>() {
					@Override
					public void changed(
							ObservableValue<? extends Aplicacion> observable,
							Aplicacion oldValue, //valor seleccionado previamente
							Aplicacion newValue //valor seleccionado actualmente
					) {
							
						if (newValue!=null){
							llenarComponentes(newValue);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);
							btnGuardar.setDisable(true);
						}
					}
					
				});
	}
	
	public void llenarComponentes(Aplicacion a){
		txtNombreAplicacion.setText(a.getNombreAplicacion());
		cboDesarrolladores.getSelectionModel().select(a.getDesarrollador());
		cboCategorias.getSelectionModel().select(a.getCategoria());
		cboEmpresas.getSelectionModel().select(a.getEmpresa());
		txtDescripcion.setText(a.getDescripcion());
		txtVersion.setText(a.getVersion());
		sldCalificacion.setValue(a.getCalificacion());
		dpckFechaPublicacion.setValue(a.getFechaPublicacion().toLocalDate());
		System.out.println("Codigo: "+a.getCodigoAplicacion());
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
		Aplicacion.llenarListaAplicaciones(
				aplicaciones, gestorConexiones.getConexion()
		);
		/*aplicaciones.add(
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
		*/
	}
	
	@FXML
	public void almacenarRegistro(){
		String errores = validarCampos();
		if (!errores.equals("")){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setTitle("Error al guardar");
			mensaje.setHeaderText("Se encontraron los siguientes errrores");
			mensaje.setContentText(errores);
			mensaje.show();
			return;
		}
			
		
		//Crear un nuevo objeto del tipo aplicacion con los valores 
		//que estan actualmente en los componentes
		Aplicacion a = new Aplicacion(
				0,
				cboDesarrolladores.getSelectionModel().getSelectedItem(),
				cboEmpresas.getSelectionModel().getSelectedItem(),
				cboCategorias.getSelectionModel().getSelectedItem(),
				txtNombreAplicacion.getText(),
				txtVersion.getText(),
				txtDescripcion.getText(),
				sldCalificacion.getValue(),
				Date.valueOf(dpckFechaPublicacion.getValue())
		);
		//Llamar al metodo almacenarRegistro()
		gestorConexiones.establecerConexion();
		int resultado = a.almacenarRegistro(gestorConexiones.getConexion());
		gestorConexiones.cerrarConexion();
		
		if (resultado == 1)
			aplicaciones.add(a);
	}
	
	@FXML
	public void actualizarRegistro(){
		Aplicacion a = tblViewAplicaciones.getSelectionModel().getSelectedItem();
		a.setDesarrollador(cboDesarrolladores.getSelectionModel().getSelectedItem());
		a.setEmpresa(cboEmpresas.getSelectionModel().getSelectedItem());
		a.setCategoria(cboCategorias.getSelectionModel().getSelectedItem());
		a.setNombreAplicacion(txtNombreAplicacion.getText());
		a.setVersion(txtVersion.getText());
		a.setDescripcion(txtDescripcion.getText());
		a.setCalificacion(sldCalificacion.getValue());
		a.setFechaPublicacion(Date.valueOf(dpckFechaPublicacion.getValue()));
		gestorConexiones.establecerConexion();
		int resultado = a.actualizarRegistro(gestorConexiones.getConexion());
		gestorConexiones.cerrarConexion();
		
		if (resultado==1){
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setContentText("Registro con codigo " + a.getCodigoAplicacion()+ " ha sido actualizado con exito.");
			mensaje.show();
			aplicaciones.set(
				tblViewAplicaciones.getSelectionModel().getSelectedIndex(),
				a
			);
		}
		
	}
	
	@FXML 
	public void eliminarRegistro(){
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Eliminar registro");
		alert.setHeaderText("Eliminar registro");
		alert.setContentText("�Esta seguro de que desea eliminar este registro?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			Aplicacion a = tblViewAplicaciones.getSelectionModel().getSelectedItem();
			gestorConexiones.establecerConexion();
			int resultado = a.eliminarRegistro(gestorConexiones.getConexion());
			gestorConexiones.cerrarConexion();
			if (resultado == 1){
				aplicaciones.remove(a);
			}
			limpiarComponentes();
		}		
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
	
	@FXML
	public void limpiarComponentes(){
		cboCategorias.getSelectionModel().select(null);
		cboDesarrolladores.getSelectionModel().select(null);
		cboEmpresas.getSelectionModel().select(null);
		//tblViewAplicaciones;
		txtNombreAplicacion.setText(null);
		txtVersion.setText(null);
		txtDescripcion.setText(null);
		sldCalificacion.setValue(0);
		dpckFechaPublicacion.setValue(null);
		
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
		btnGuardar.setDisable(false);
	}
	
	public String validarCampos(){
		String errores = "";
		if (txtNombreAplicacion.getText().equals(""))
			errores += "Debe ingresar el nombre de la aplicaci�n\n";
		if (txtDescripcion.getText().equals(""))
			errores += "Debe ingresar la descripci�n\n";
		if (txtVersion.getText().equals(""))
			errores += "Debe ingresar la versi�n\n";
		if (cboCategorias.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar una categoria\n";
		if (cboEmpresas.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar una empresa\n";
		if (cboDesarrolladores.getSelectionModel().getSelectedItem()==null)
			errores += "Debe seleccionar un desarrollador\n";
		if (dpckFechaPublicacion.getValue()==null)
			errores += "Debe seleccionar una fecha\n";
		try{
			Double.valueOf(txtVersion.getText());
		}catch (NumberFormatException e){
			errores += "La versi�n es un n�mero no v�lido\n";
			//e.printStackTrace();
		}
		
		Pattern pattern = Pattern.compile("[0-9]{4}-[0-9]{4}-[0-9]{5}");
		Matcher matcher = pattern.matcher(txtNombreAplicacion.getText());
		if (!matcher.matches())
			errores += "Nombre aplicacion no coincide con el patron de la identidad\n";
		
		return errores;
	}
}
