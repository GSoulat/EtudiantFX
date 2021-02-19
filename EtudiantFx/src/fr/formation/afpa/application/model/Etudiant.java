package fr.formation.afpa.application.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


public class Etudiant  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String photo;
	
	List<Integer> notes;

	private int idEtudiant;
	private static int count=0;
	
	{
		count++;
	}

	public static int getCount() {
		return count;
	}

	public static void setCount(int count) {
		Etudiant.count = count;
	}

	public Etudiant(String nom, String prenom, LocalDate dateNaissance, String photo) {
		this.idEtudiant = count;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
	}

	public Etudiant(int id , String nom, String prenom, LocalDate dateNaissance, String photo) {
		this.idEtudiant = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.photo = photo;
	}
	
	public Etudiant(String nom, String prenom) {
		this.idEtudiant = count;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Etudiant(String nom, String prenom, LocalDate dateNaissance) {
		this.idEtudiant = count;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}
	
	public Etudiant() {
		this.idEtudiant = count;
	}

	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", photo=" + photo
				+ ", notes=" + notes + ", idEtudiant=" + idEtudiant + "]";
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<Integer> getNotes() {
		return notes;
	}

	public void setNotes(List<Integer> notes) {
		this.notes = notes;
	}

	public int getIdEtudiant() {
		return idEtudiant;
	}

	public void setIdEtudiant(int idEtudiant) {
		this.idEtudiant = idEtudiant;
	}
}
