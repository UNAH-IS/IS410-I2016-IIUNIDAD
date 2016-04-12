package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import modelo.Usuario;
import utilidades.GestorConexiones;

public class ControladorLogin implements Initializable{
	private Main main;
	private GestorConexiones gestorConexiones;
	
	@FXML TextField txtUsuario;
	@FXML PasswordField txtPassword;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		gestorConexiones = new GestorConexiones();
		gestorConexiones.establecerConexion();
		gestorConexiones.cerrarConexion();
	}
	
	@FXML
	public void abrirFormularioPrincipal(){
		Usuario usuario = new Usuario(txtUsuario.getText(), txtPassword.getText());
		gestorConexiones.establecerConexion();
		if (usuario.verificarUsuario(gestorConexiones.getConexion())==-1){
			Alert mensaje = new Alert(AlertType.ERROR);
			mensaje.setContentText("Usuario/Contrasena invalidos");
			mensaje.show();
		}else{
			main.abrirFormularioPrincipal();
		}
		gestorConexiones.cerrarConexion();
	}
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
}
