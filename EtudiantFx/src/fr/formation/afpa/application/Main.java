package fr.formation.afpa.application;
	


import fr.formation.afpa.application.model.Etudiant;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
//	static Etudiant modifieMemoire;

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Vue.fxml"));
			stage.setTitle("Gestion étudiants");
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
