package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ControladorPrincipal implements Initializable{
	private Main main;
	
	@FXML private Label lblMensajePrincipal;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML 
	public void abrirFormulario1(){
		main.mostrarFormulario1();
	}
	
	@FXML
	public void abrirFormulario2(){
		main.mostrarFormulario2();
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	public void definirMensaje(String mensaje){
		lblMensajePrincipal.setText(mensaje);
	}
}
