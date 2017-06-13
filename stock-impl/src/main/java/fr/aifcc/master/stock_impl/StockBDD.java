package fr.aifcc.master.stock_impl;

import fr.aifcc.master.stock_api.*;
import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.LinkedList;

public class StockBDD implements StockInterface{

	private final Connection connection;
	private final String TABLE_NAME = "Denree";
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
		ResultSet res;
		Collection<Denree> denrees = new ArrayList<Denree>();

		try
		{
			String requete = "SELECT * FROM "+ TABLE_NAME + " WHERE id >=? fetch next ? rows only";
			// Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
			PreparedStatement statement = connection.prepareStatement(requete);
			statement.setLong(1, position);
			statement.setLong(2, nombreitems);
			res = statement.executeQuery();

			if (!res.next())
			{
				res.close();
				throw new StockException("Probleme SQL, la base est vide !");
			}
			else
			{
				do 
				{
					Denree denree = new Denree();
					denree.setId(res.getLong(1));
					denree.setNom(res.getString(2));
					denree.setCategorie(res.getString(3));
					denree.setQuantite(res.getInt(4));
					denrees.add(denree);
				}
				while(res.next());
				res.close();
			}	
		}
		catch(SQLException sqlDefault)
		{
			throw new StockException("Probleme sql du getListeDenree !!!");
		}
		return denrees;
	}
	
    public Denree getDenree( long id ) throws StockException
    {
		ResultSet res;
		try
		{
			String requete = "SELECT * FROM " + TABLE_NAME + " WHERE ID=?";
			// Une requête doit TOUJOURS être préparé pour éviter les injections SQL.v
			PreparedStatement statement = connection.prepareStatement(requete);
			statement.setLong(1, id);
			res = statement.executeQuery();
			int taille = res.getFetchSize();
			if (!res.next())
			{
				res.close();
				throw new StockException("Probleme SQL, la base est vide !");
			}
			else if (taille != 0)
			{
				throw new StockException("Probleme SQL, taille à 0 !");
			}
			else
			{
				Denree denree = new Denree();
				denree.setId(res.getLong(1));
				denree.setNom(res.getString(2));
				denree.setCategorie(res.getString(3));
				denree.setQuantite(res.getInt(4));
				res.close();
				return denree;
			}	
		}
		catch(SQLException sqlDefault)
		{
			throw new StockException("SQL Exception lors de la récupération d'une denree : "+sqlDefault.getMessage());
		}
	}


    public void mAjDenree( Denree denree ) throws StockException
    {
		try{
			String requete = "UPDATE " + TABLE_NAME + " SET nom = ?, categorie = ?, quantite = ? WHERE id = ? ";
			// Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
			PreparedStatement statement = connection.prepareStatement(requete);
			statement.setString(1,denree.getNom());
			statement.setString(2,denree.getCategorie());
			statement.setInt(3,denree.getQuantite());
			statement.setLong(4,denree.getId());
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			throw new StockException(e);
		}
	}

    public void ajouterDenree( Denree item ) throws StockException
    {
		try{
			
			String requete = "INSERT INTO " + TABLE_NAME + " ( id, nom, categorie, quantite ) VALUES (?,?,?,?)";
			// Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
			PreparedStatement statement = connection.prepareStatement(requete);
			statement.setLong(1,item.getId());
			statement.setString(2,item.getNom());
			statement.setString(3,item.getCategorie());
			statement.setInt(4,item.getQuantite());
			statement.executeUpdate();
			statement.close();
		}catch(SQLException e){
			throw new StockException(e);
		}
	}

    public Collection<Denree> getDenreeRecherche( String critere )throws StockException
    {
		ResultSet res;
		try{
			
			String requete = "SELECT * FROM " + TABLE_NAME + " WHERE NOM LIKE ?";
		 
			// Une requête doit TOUJOURS être préparé pour éviter les injections SQL.
			PreparedStatement statement = connection.prepareStatement(requete);
			statement.setString(1, critere);
			res = statement.executeQuery();

            Collection<Denree> listDenree = new LinkedList<>();

            while ( res.next() )
            {
                listDenree.add( getDenree( res.getLong( 1 ) ) );
            }
            res.close();

			return listDenree;
			
		}catch(SQLException e){
			throw new StockException(e);
		}
		
	}

	@Override
	public synchronized void dispose() throws StockException{
		try{
			this.connection.close();
		}catch(SQLException e){
			throw new StockException(e);
		}
	}
}
