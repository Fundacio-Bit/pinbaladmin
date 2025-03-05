package org.fundaciobit.pinbaladmin.logic.utils;

public class Responsable {
	private String nif;
	private String nom;
	private String ape1;
	private String ape2;
	private String cargo;
	private String telefon;
	private String mail;
	private String nomOcult;

	public Responsable(String nif, String nom, String ape1, String ape2, String cargo, String telefon, String mail,
			String nomOcult) {
		this.nif = nif;
		this.nom = nom;
		this.ape1 = ape1;
		this.ape2 = ape2;
		this.cargo = cargo;
		this.telefon = telefon;
		this.mail = mail;
		this.nomOcult = nomOcult;
	}

	//Constructor per defecte
	public Responsable(String NIF, String nomComplet) {
		this.nif = NIF;
		this.nom = null;
		this.ape1 = null;
		this.ape2 = null;
		this.cargo = null;
		this.telefon = null;
		this.mail = null;
		this.nomOcult = nomComplet;
	}
	
	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getApe1() {
		return ape1;
	}

	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}

	public String getApe2() {
		return ape2;
	}

	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNomOcult() {
		return nomOcult;
	}

	public void setNomOcult(String nomOcult) {
		this.nomOcult = nomOcult;
	}
}
