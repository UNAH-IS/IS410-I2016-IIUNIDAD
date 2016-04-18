package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;

public class ControladorEjemplo implements Initializable {
	@FXML private Label etiqueta;
	@FXML private ProgressBar barraProgreso;
	@FXML private ProgressIndicator indicadorProgreso;
	private int i;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Task<String> tarea = new Task<String>() {
			public String call(){
				//Cuerpo del hilo o tarea
				for(i=0; i<100;i++){
					try {
						Thread.sleep(500);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(String.valueOf(i));

					//Si se desea modificar algun elemento de la GUI
					//se debe utilizar obligatoriamente runLater
					Platform.runLater(new Runnable(){
						public void run(){
							etiqueta.setText(String.valueOf(i));
							barraProgreso.
								setProgress(Double.valueOf(i)/100d);
							indicadorProgreso.
								setProgress(Double.valueOf(i)/100d);
						}
					});					
				}
				return "La tarea finalizo";
			}
		};	
		Thread hilo = new Thread(tarea);
		hilo.setDaemon(true);
		hilo.start();
	}
}
