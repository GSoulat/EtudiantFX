package fr.formation.afpa.application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.formation.afpa.application.model.Etudiant;
import fr.formation.afpa.application.service.EtudiantService;
import fr.formation.afpa.application.service.IEtudiantService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class McListe2 implements Initializable {
	@FXML
	AnchorPane paneListe, paneAjout, paneListe1, paneHome,paneModif;
	@FXML
	MenuItem menuExit, menuAdd, menuList,menuList1;
	@FXML
	private Pagination paginatation;
	@FXML
	TextField filterField;
	@FXML
	private TableColumn<Etudiant, Integer> colID;
	@FXML
	private TableColumn<Etudiant, String> colNom;
	@FXML
	private TableColumn<Etudiant, String> colPhoto;
	@FXML
	private TableColumn<Etudiant, String> colPrenom;
	@FXML
	private TableView<Etudiant> table;
	ImageView view;
	@FXML
	private TableColumn<Etudiant, String> colDateN;
	private IEtudiantService service = new EtudiantService();
	private final static int ligneParPage = 10;
	private ObservableList<Etudiant> list;
	static Etudiant myVariable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		UpdateAll();
		addButtonToTable();
		removeButtonToTable();
	}

	private Node createPage(int pageIndex) {
		list = service.listEtudiant();
		
		colID.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("idEtudiant"));
		colPrenom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
		colNom.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
		colDateN.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("dateNaissance"));
		colPhoto.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("photo"));

		int fromIndex = pageIndex * ligneParPage;
		int toIndex = Math.min(fromIndex + ligneParPage, list.size());
		int longueur;
		if (list.size() % 10 == 0) {
			longueur = list.size() / 10;
		} else {
			longueur = (list.size() / 10) + 1;
		}
		System.out.println("toIndex: " + toIndex);
		paginatation.setPageCount(longueur);
		table.setItems(FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));
	
		return table;

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
		paneListe1.getChildren().setAll(root);
	};

	public void choice2(ActionEvent event) {
		System.out.println("menu Etudiant");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("AjouterEtudiant.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		paneListe1.getChildren().setAll(root);
	};
	public void choice3(ActionEvent event) {
		System.out.println("menu Liste  2");
		AnchorPane root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("ListeEtudiant.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paneListe1.getChildren().setAll(root);
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

	public void modifEtudiant(ActionEvent event) {

	};

	public void ajoutNote(ActionEvent event) {

	};

	private void addButtonToTable() {
		TableColumn<Etudiant, Void> colBtn = new TableColumn("");
		File file = new File("C:\\Users\\afpa\\Documents\\etudiant\\modifier.png");
		Image img = new Image(file.toURI().toString(), 80, 20, true, true);
		ImageView view = new ImageView(img);
		view.setPreserveRatio(true);
		colBtn.setGraphic(view);

		Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = new Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>>() {
			@Override
			public TableCell<Etudiant, Void> call(final TableColumn<Etudiant, Void> param) {
				final TableCell<Etudiant, Void> cell = new TableCell<Etudiant, Void>() {

					private final Button btn = new Button("");

					{
						btn.setOnAction((ActionEvent event) -> {
							Etudiant Etudiant = getTableView().getItems().get(getIndex());
							System.out.println("Etudiant choisi : " + Etudiant);
							setMyVariable(Etudiant);
							AnchorPane root = null;
							try {
								root = FXMLLoader.load(getClass().getResource("Modifie2.fxml"));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							;
							paneListe1.getChildren().setAll(root);
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
							File file = new File("C:\\Users\\afpa\\Documents\\etudiant\\modifier.png");
							Image img = new Image(file.toURI().toString(), 80, 20, true, true);
							ImageView view = new ImageView(img);
							view.setPreserveRatio(true);
							btn.setGraphic(view);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		table.getColumns().add(colBtn);

	}

	private void removeButtonToTable() {
		TableColumn<Etudiant, Void> colBtn = new TableColumn("");

		File file = new File("C:\\Users\\afpa\\Documents\\etudiant\\poubelle.png");
		Image img = new Image(file.toURI().toString(), 80, 20, true, true);
		ImageView view = new ImageView(img);
		view.setPreserveRatio(true);
		colBtn.setGraphic(view);
		Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>> cellFactory = new Callback<TableColumn<Etudiant, Void>, TableCell<Etudiant, Void>>() {
			@Override
			public TableCell<Etudiant, Void> call(final TableColumn<Etudiant, Void> param) {
				final TableCell<Etudiant, Void> cell = new TableCell<Etudiant, Void>() {

					private final Button btn = new Button("");

					{
						btn.setOnAction((ActionEvent event) -> {
							Etudiant Etudiant = getTableView().getItems().get(getIndex());
							System.out.println("Etudiant choisi : " + Etudiant);
							setMyVariable(Etudiant);
							service.removerEtudiant(Etudiant);
							UpdateAll();
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
							File file = new File("C:\\Users\\afpa\\Documents\\etudiant\\poubelle.png");
							Image img = new Image(file.toURI().toString(), 80, 20, true, true);
							ImageView view = new ImageView(img);
							view.setPreserveRatio(true);
							btn.setGraphic(view);
						}
					}
				};
				return cell;
			}
		};

		colBtn.setCellFactory(cellFactory);

		table.getColumns().add(colBtn);

	}

	public static void setMyVariable(Etudiant myVariable) {
		McListe2.myVariable = myVariable;
	}

	public static Etudiant getMyVariable() {
		return myVariable;
	}

	public void UpdateAll() {
		paginatation.setPageFactory(this::createPage);

	}

}
