package fr.formation.afpa.application.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.formation.afpa.application.model.Etudiant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public interface IEtudiantService {
//	ObservableList<Etudiant> list = FXCollections.observableArrayList();
	public ObservableList<Etudiant> listEtudiant();
	
	public void ajouterEtudiant(Etudiant student);
	
	public void modifierEtudiant(Etudiant e);
	
	public void removerEtudiant(Etudiant e);
	

}
