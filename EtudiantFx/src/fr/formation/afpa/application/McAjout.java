package fr.formation.afpa.application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fr.formation.afpa.application.model.Etudiant;
import fr.formation.afpa.application.service.EtudiantService;
import fr.formation.afpa.application.service.IEtudiantService;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class McAjout implements Initializable {
	@FXML
	AnchorPane paneListe, paneAjout, paneListe1,paneHome,paneModif;
	@FXML
	MenuItem menuExit, menuAdd, menuList,menuList1;
	@FXML
	private Button btnSave, gene,  btnBrowser;
	@FXML
	private Image imagePhoto;
	@FXML
	private ImageView iv;
	@FXML
	private DatePicker tdate;
	@FXML
	private LocalDate datelocal;

	@FXML
	private TextField tphoto, tprenom, tnom, tgene;

	final FileChooser fileChooser = new FileChooser();
	private int prenom;
	private int nom;
	private int dateN;
	private File fichierPhoto;
	private IEtudiantService service = new EtudiantService();

	private Desktop desktop = Desktop.getDesktop();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void quitter(ActionEvent event) {
		System.out.println("quitter");
		Platform.exit();
	};

	public void choice1(ActionEvent event) {
		System.out.println("menu Liste");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant2.fxml"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		paneAjout.getChildren().setAll(root);
	};

	public void choice2(ActionEvent event) {
		System.out.println("menu Etudiant");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AjouterEtudiant.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		paneAjout.getChildren().setAll(root);
	};
	public void choice3(ActionEvent event) {
		System.out.println("menu Liste");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paneAjout.getChildren().setAll(root);
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

	@FXML
	void ajout(ActionEvent event) {

		prenom = tprenom.getText().length();
		nom = tnom.getText().length();
		dateN = tdate.getValue().lengthOfYear();
		File fichierPhoto = new File(tphoto.getText());
		datelocal = tdate.getValue();
		System.out.println("save");
		if (prenom > 3 && nom > 3 && dateN > 0 && fichierPhoto.exists()) {
			Etudiant studentAdd = new Etudiant(numAuto(), tnom.getText(), tprenom.getText(), datelocal,
					tphoto.getText());
			System.out.println(studentAdd);
			service.ajouterEtudiant(studentAdd);
			tnom.clear();
			tprenom.setText("");
			tphoto.setText("");
			iv.setImage(null);;
			tdate.setValue(null);
			
		}
	}

	public int numAuto() {
		List<Etudiant> listEtudiantAff = new ArrayList<Etudiant>();
		listEtudiantAff = service.listEtudiant();

		if (listEtudiantAff.size() == 0) {
			return 1;
		} else {
			Etudiant etudiant = listEtudiantAff.get(listEtudiantAff.size() - 1);
			return ((Etudiant) etudiant).getIdEtudiant() + 1;
		}
	}
	
	public static final LocalDate LOCAL_DATE (String dateString){
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    LocalDate localDate = LocalDate.parse(dateString, formatter);
	    return localDate;
	}

	@FXML
	void supergene(ActionEvent event) {
		for (int i = 0; i < Integer.parseInt(tgene.getText()); i++) {
			tprenom.setText("Mohamed" + i);
			tnom.setText("nom" + i);
			tphoto.setText("C:\\Users\\afpa\\Documents\\etudiant\\profil.png");
			tdate.setValue(LOCAL_DATE("01-05-2016"));
			System.out.println("Création" + i);
			Etudiant studentAdd = new Etudiant(numAuto(), tnom.getText(), tprenom.getText(), tdate.getValue(),
					tphoto.getText());
			System.out.println(studentAdd);
			service.ajouterEtudiant(studentAdd);
		}
	}

	public void handle(ActionEvent event) {
		fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
				new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
		File file = fileChooser.showOpenDialog(btnBrowser.getScene().getWindow());
		if (file != null) {
			tphoto.setText(file.getAbsolutePath());
			System.out.println("test photo : "+ file.getAbsolutePath());
			Image imagePhoto = new Image(file.toURI().toString(), 100, 150, true, true);
			iv.setImage(imagePhoto);
		}
	}
}