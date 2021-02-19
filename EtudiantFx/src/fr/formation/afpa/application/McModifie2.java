package fr.formation.afpa.application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import fr.formation.afpa.application.model.Etudiant;
import fr.formation.afpa.application.service.EtudiantService;
import fr.formation.afpa.application.service.IEtudiantService;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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

public class McModifie2 implements Initializable {
	@FXML
	AnchorPane paneListe, paneAjout, paneListe1, paneHome, paneModif, paneModif2;
	@FXML
	MenuItem menuExit, menuAdd, menuList,menuList1;
	@FXML
	private Button btnSave, gene,btnBrowser, modifier;
	@FXML
	private Image imagePhoto;
	@FXML
	private ImageView iv, iv2;
	@FXML
	private DatePicker tdate;
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
		System.out.println("test " + McListe2.getMyVariable());;
		tprenom.setText(McListe2.getMyVariable().getPrenom());
		tnom.setText(McListe2.getMyVariable().getNom());
		tdate.setValue(McListe2.getMyVariable().getDateNaissance());
		tphoto.setText(McListe2.getMyVariable().getPhoto());
		File file = new File(McListe2.getMyVariable().getPhoto());
		try {
			InputStream input = new FileInputStream(file);
			System.out.println("test photo : "+ file.getAbsolutePath());
			Image imagePhoto = new Image(input);
			iv2 = new ImageView(imagePhoto);
			iv2.setVisible(true);
			iv2.setFitHeight(353);
			iv2.setFitWidth(351);
			iv2.setX(472);
			iv2.setY(64);
			paneModif2.getChildren().addAll(iv2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		tprenom.setEditable(false);
		tnom.setEditable(false);
		tdate.setEditable(false);
//		tphoto.setEditable(false);
		btnBrowser.setVisible(false);

	}
    @FXML
    void modifieEtudiant(ActionEvent event) {
		tprenom.setEditable(true);
		tnom.setEditable(true);
		tdate.setEditable(true);
		tphoto.setEditable(true);
		btnBrowser.setVisible(true);

    }
	public void quitter(ActionEvent event) {
		System.out.println("quitter");
		Platform.exit();
	};

	public void choice1(ActionEvent event) {
		System.out.println("menu Liste");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant.fxml"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		paneModif2.getChildren().setAll(root);
	};

	public void choice2(ActionEvent event) {
		System.out.println("menu Etudiant");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AjouterEtudiant.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		paneModif2.getChildren().setAll(root);
	};
	public void choice3(ActionEvent event) {
		System.out.println("menu Liste");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant2.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paneModif2.getChildren().setAll(root);
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
		System.out.println("Modifier");
		if (prenom > 3 && nom > 3 && dateN > 0) {
			System.out.println("Modifier 2");
			Etudiant studentAdd = new Etudiant(McListe2.getMyVariable().getIdEtudiant(), tnom.getText(), tprenom.getText(), tdate.getValue(),
					tphoto.getText());
			System.out.println(studentAdd);
			service.modifierEtudiant(studentAdd);
			tnom.clear();
			tprenom.setText("");
			tphoto.setText("");
			iv.setImage(null);
			tdate.setValue(null);
			System.out.println("sauvegarder modifier");		AnchorPane root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("ListeEtudiant2.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paneModif.getChildren().setAll(root);
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

	public void handle(ActionEvent event) {
		fileChooser.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
				new FileChooser.ExtensionFilter("Tous les fichiers", "*.*"));
		File file = fileChooser.showOpenDialog(btnBrowser.getScene().getWindow());
		if (file != null) {
			tphoto.setText(file.getAbsolutePath());
			System.out.println("test photo : "+ file.getAbsolutePath());
			Image imagePhoto = new Image(file.toURI().toString(), 100, 150, true, true);
			iv.setImage(imagePhoto);
			iv2.setVisible(false);
		}
	}
}