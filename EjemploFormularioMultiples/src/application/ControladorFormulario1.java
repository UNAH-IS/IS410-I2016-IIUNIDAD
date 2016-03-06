package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class ControladorFormulario1 implements Initializable{
	private Main main;
	@FXML private TextField txtMensaje;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
				
	}
	
	@FXML
	public void accionAceptar(){
		main.definirMensaje(txtMensaje.getText());
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
