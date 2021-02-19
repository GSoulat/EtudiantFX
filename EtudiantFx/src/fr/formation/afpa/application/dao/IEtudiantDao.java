package fr.formation.afpa.application.dao;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.formation.afpa.application.model.Etudiant;
import fr.formation.afpa.application.service.ObservableList;
import javafx.collections.ObservableList;

public interface IEtudiantDao {

	public void add(Etudiant e);
		
	
	public void update(Etudiant e);


	public ObservableList<Etudiant> getAll();
	
	public void removerEtudiant(Etudiant e);

}
