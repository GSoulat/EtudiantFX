package fr.formation.afpa.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class McHome implements Initializable {
	@FXML
	AnchorPane paneListe, paneAjout, paneListe1,paneHome,paneModif;
	@FXML
	MenuItem menuExit, menuAdd, menuList,menuList1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	public void quitter(ActionEvent event) {
		System.out.println("quitter");
		Platform.exit();
	};
	
	
	public void choice1(ActionEvent event) {
		System.out.println("menu Liste 2");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		paneHome.getChildren().setAll(root);
	};
	
	public void choice3(ActionEvent event) {
		System.out.println("menu Liste recherhche");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		paneHome.getChildren().setAll(root);
	};
	
	public void choice2(ActionEvent event) {
		System.out.println("menu Etudiant");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AjouterEtudiant.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paneHome.getChildren().setAll(root);
	};

	public void afficheHelp() {
		System.out.println("help");
	};

	public void afficheFile() {
		System.out.println("file");
	};

	public void afficheEtudiant() {
		System.out.println("etudiant");
	};
}
