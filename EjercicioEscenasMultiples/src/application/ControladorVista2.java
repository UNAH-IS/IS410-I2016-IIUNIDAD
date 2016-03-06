package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControladorVista2 {
	private Main main;
	
	@FXML private TextField txtMensaje;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void cambiarEscena1(){		
		main.cambiarEscena1(txtMensaje.getText());
	}
}
