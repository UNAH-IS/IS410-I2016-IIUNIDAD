package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	private Stage formularioPrincipal;
	private ControladorVista1 controladorVista1; 
	private ControladorVista2 controladorVista2;
	private Scene escena1;
	private Scene escena2;
	
	@Override
	public void start(Stage primaryStage) {
		formularioPrincipal = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Vista1.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			escena1 = new Scene(root);
			escena1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			formularioPrincipal.setScene(escena1);
			controladorVista1 = loader.getController();
			controladorVista1.setMain(this);
			formularioPrincipal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cambiarEscena2(){
		if (escena2 == null){
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("Vista2.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				escena2 = new Scene(root);
				controladorVista2 = loader.getController();
				controladorVista2.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formularioPrincipal.setScene(escena2);
	}
	
	public void cambiarEscena1(String mensaje){
		controladorVista1.definirMensaje(mensaje);
		formularioPrincipal.setScene(escena1);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
