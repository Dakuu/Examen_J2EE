package fr.aifcc.master.stock_api;

public class Denree extends Item {
	
	private String nom;
	private String categorie;
	private int quantite;
	
	//Accesseur de nom
	public String getNom(){
		return this.nom;
	}
	public void setNom(String nom){
		this.nom = nom;
	}
	
	//Accesseur de categorie
	public String getCategorie(){
		return this.categorie;
	}
	public void setCategorie(String categorie){
		this.categorie = categorie;
	}
	
	//Accesseur de quantite
	public int getQuantite(){
		return this.quantite;
	}
	public void setQuantite(int quantite){
		this.quantite = quantite;
	}
}
