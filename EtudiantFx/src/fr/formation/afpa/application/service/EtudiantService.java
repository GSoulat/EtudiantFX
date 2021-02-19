package fr.formation.afpa.application.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import fr.formation.afpa.application.dao.EtudiantDaoFile;
import fr.formation.afpa.application.model.Etudiant;
import javafx.collections.ObservableList;
import fr.formation.afpa.application.dao.IEtudiantDao;


public class EtudiantService implements IEtudiantService {
//	ObservableList<Etudiant> list = FXCollections.observableArrayList();
	private IEtudiantDao dao = new EtudiantDaoFile();



	@Override
	public void ajouterEtudiant(Etudiant student) {
		dao.add(student);
		
	}

	@Override
	public ObservableList<Etudiant> listEtudiant() {
		// TODO Auto-generated method stub
		return dao.getAll();
	}

	@Override
	public void modifierEtudiant(Etudiant e) {
		dao.update(e);
	}

	@Override
	public void removerEtudiant(Etudiant e) {
		dao.removerEtudiant(e);
		
	}

	
	
}
