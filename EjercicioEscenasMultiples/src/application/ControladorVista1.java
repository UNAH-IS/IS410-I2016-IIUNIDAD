package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControladorVista1 {
	private Main main;
	@FXML private Label lblMensaje;

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
	
	@FXML
	public void cambiarEscena2(){
		main.cambiarEscena2();
	}
	
	public void definirMensaje(String mensaje){
		lblMensaje.setText(mensaje);
	}
}
