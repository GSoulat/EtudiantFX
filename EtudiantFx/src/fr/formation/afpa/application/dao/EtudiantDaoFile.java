package fr.formation.afpa.application.dao;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fr.formation.afpa.application.model.Etudiant;

public class EtudiantDaoFile implements IEtudiantDao {
	String path = "C:\\Users\\afpa\\eclipse-workspace\\EtudiantFx\\src\\fr\\formation\\afpa\\application\\best\\ob4.txt";
	
	public ObservableList<Etudiant> getAll() {
		List<Etudiant> listEtudiant = new ArrayList();
//		System.out.println(listEtudiant);
		File fichier = new File(path);
		if (!fichier.exists()) {
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!(fichier.length() == 0)) {
			try {
				InputStream is = new FileInputStream(fichier);
				ObjectInputStream fis = new ObjectInputStream(is);
				System.out.println("debut affichage");
				listEtudiant = (List<Etudiant>) fis.readObject();
//				System.out.println(listEtudiant);
				
				fis.close();
				is.close();
				System.out.println("fin affichage");
			} catch (FileNotFoundException fnfe) {
				System.out.println("fichier non trouvé");
				fnfe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				System.out.println("Fichier format mauvais :(");
				cnfe.printStackTrace();
			} catch (IOException ioe) {
				System.out.println("I/O Exception while reading file");
				ioe.printStackTrace();
			}
		}
		return FXCollections.observableList(listEtudiant);
	}
	
	public void add(Etudiant studentAdd) {
		ObservableList<Etudiant> listEtudiant = getAll();
		listEtudiant.add(studentAdd);
		System.out.println(listEtudiant);
		try {
			OutputStream os = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new ArrayList<Etudiant>(listEtudiant));
			System.out.println("save add");
			oos.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public void update(Etudiant etudiant) {
		try {
			System.out.println("update");
			List<Etudiant> listEtudiant = getAll();

			for (int i = 0; i < listEtudiant.size(); i++) {
				if (etudiant.getIdEtudiant() == listEtudiant.get(i).getIdEtudiant()) {
					System.out.println(listEtudiant.get(i).getIdEtudiant());
					listEtudiant.remove(i);
					listEtudiant.add(etudiant);
				}
			}
			OutputStream os = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new ArrayList<Etudiant>(listEtudiant));
			System.out.println("save modifier");
			oos.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removerEtudiant(Etudiant etudiant) {
		try {
			System.out.println("remove");
			System.out.println(etudiant);
			List<Etudiant> listEtudiant = getAll();
			System.out.println("liste etudiant " + listEtudiant);
			for (int i = 0; i < listEtudiant.size(); i++) {
				if (etudiant.getIdEtudiant() == listEtudiant.get(i).getIdEtudiant()) {
					System.out.println(listEtudiant.get(i).getIdEtudiant());
					listEtudiant.remove(i);
				}

			}
			OutputStream os = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(new ArrayList<Etudiant>(listEtudiant));
			oos.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	
}
