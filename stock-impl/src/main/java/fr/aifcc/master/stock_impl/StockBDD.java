package fr.aifcc.master.stock_impl;

import fr.aifcc.master.stock_api.*;
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;

public class StockBDD implements StockInterface{

	private final Connection connection;
	private final String table = "Stock";
	private final String id = "id";
	private final String nom = "nom";
	private final String categorie = "categorie";
	private final String quantite = "quantite";

	public StockBDD(String driverClass, String dataBaseUrl) throws StockException{
		try{
			Class.forName(driverClass);
			this.connection = DriverManager.getConnection(dataBaseUrl);
		}catch(ClassNotFoundException e){
			throw new StockException("Le driver de connexion n'existe pas");
		}catch(SQLException e){
			throw new StockException(e);
		}
	}

    public Collection<Denree> getListeDenree( long position, long nombreitems )throws StockException
    {
		return null;
	}

    public Denree getDenree( long id ) throws StockException
    {
		return null;
	}

    public void mAjDenree( Denree denree ) throws StockException
    {}

    public void ajouterDenree( Denree item ) throws StockException
    {}

    public Collection<Denree> getDenreeRecherche( String critere )throws StockException
    {
		return null;
	}


	/*@Override
	public synchronized Person getPerson(long id) throws DirectoryException{
		ResultSet res;
		try{
			String requete = "select * from "+table +" where id = ?";
			PreparedStatement p = this.connection.prepareStatement(requete);
			p.setLong(1,id);
			
			res = p.executeQuery();
			int taille = res.getFetchSize();
			if(!res.next())
			{
				res.close();
				throw new DirectoryException("Plus de données dans la table");
			}
			if(taille != 0)
			{
				res.close();
				throw new DirectoryException("Resultat attendu 0, obtenu : "+taille);
			}
			
			Person personne = new Person();
			personne.setId(res.getLong(1));
			personne.setFirstName(res.getString(2));
			personne.setLastName(res.getString(3));
			
			res.close();
			return personne;
			
		}catch(SQLException e){
			throw new DirectoryException(e);
		}
	}*/

	@Override
	public synchronized void dispose() throws StockException{
		try{
			this.connection.close();
		}catch(SQLException e){
			throw new StockException(e);
		}
	}
}
