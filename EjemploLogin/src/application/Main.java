package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	private Stage formularioLogin;
	private Stage formularioPrincipal;
	
	private ControladorLogin controladorLogin;
	private ControladorPrincipal controladorPrincipal;
	@Override
	public void start(Stage primaryStage) {
		formularioLogin = primaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaLogin.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			controladorLogin = loader.getController();
			controladorLogin.setMain(this);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void abrirFormularioPrincipal(){
		if (formularioPrincipal == null){
			formularioPrincipal = new Stage();
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
				BorderPane root = (BorderPane)loader.load();
				Scene scene = new Scene(root);
				formularioPrincipal.setScene(scene);
				controladorPrincipal = loader.getController();
				controladorPrincipal.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formularioPrincipal.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
