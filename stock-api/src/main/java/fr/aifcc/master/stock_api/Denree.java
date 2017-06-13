package fr.aifcc.master.stock_api;

public class Denree extends Item {
	
	private String nom;
	private String categorie;
	
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	
	public String getCategorie(){
		return this.categorie;
	}
	public void setCategorie(String categorie){
		this.categorie = categorie;
	}
}
