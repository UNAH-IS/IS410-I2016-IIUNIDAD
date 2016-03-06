package application;
	
import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private Stage formulario1;
	private Stage formulario2;
	private Stage formularioPrincipal;
	private ControladorPrincipal controladorPrincipal;
	private ControladorFormulario1 controladorFormulario1;
	private ControladorFormulario2 controladorFormulario2;
	
	
	@Override
	public void start(Stage stagePrincipal) {
		formularioPrincipal = stagePrincipal;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaPrincipal.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stagePrincipal.setScene(scene);
			//stagePrincipal.setFullScreen(true);
			//stagePrincipal.setOpacity(0.9);
			stagePrincipal.setTitle("Formulario principal");
			controladorPrincipal = loader.getController();
			controladorPrincipal.setMain(this);
			stagePrincipal.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mostrarFormulario1(){
		if(formulario1==null){
			formulario1 = new Stage();
			formulario1.initOwner(formularioPrincipal);
			formulario1.initModality(Modality.WINDOW_MODAL);
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaFormulario1.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				formulario1.setScene(scene);
				formulario1.setTitle("Formulario 1");
				controladorFormulario1 = loader.getController();
				controladorFormulario1.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formulario1.show();
	}
	
	public void mostrarFormulario2(){
		if(formulario2==null){
			formulario2 = new Stage();
			formulario2.initOwner(formularioPrincipal);
			formulario2.initModality(Modality.WINDOW_MODAL);
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaFormulario2.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				formulario2.setScene(scene);
				formulario2.setTitle("Formulario 2");	
				controladorFormulario2 = loader.getController();
				controladorFormulario2.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formulario2.show();
	}
	
	public void definirMensaje(String mensaje){
		controladorPrincipal.definirMensaje(mensaje);
		formulario1.hide();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
