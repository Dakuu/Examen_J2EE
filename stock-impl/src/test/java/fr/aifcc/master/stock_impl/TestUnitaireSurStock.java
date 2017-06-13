package fr.aifcc.master.stock_impl;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import fr.aifcc.master.stock_api.Denree;
import static org.junit.Assert.assertEquals;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.*;


public class TestUnitaireSurStock
{
	private static Connection connection;
	private static String DRIVER = "org.apache.derby.jdbc.ClientDriver";
	private static String URL = "jdbc:derby://localhost:1527/TestStock";
	
	private StockBDD stockBDD;

	@BeforeClass
	public static void onlyOnce() throws Exception
	{
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL);
		clearTable();
		insertDenree(1,"coca", "boisson", 20);
		insertDenree(2,"oasis", "boisson", 20);
		insertDenree(3,"Sandwich", "encas", 20);
		insertDenree(4,"Magnum", "glace", 20);

	}

	@Before
		public void setUp() throws Exception
		{
			stockBDD = new StockBDD(DRIVER,URL);
		}


	@Test
	public void getOneDenree() throws Exception
	{
		
		final long id = 1;
        final String nomAttendu = "coca";
        final String categorieAttendu = "boisson";
        final int quantiteAttendu = 20;
        
        Denree d = stockBDD.getDenree(id);
        assertEquals(
                "Erreur pour l'identifiant : ",
                id, d.getId()
                );
        assertEquals(
                "Erreur pour le nom : ",
                nomAttendu, d.getNom()
                );
        assertEquals(
                "Erreur pour la catégorie : ",
                categorieAttendu, d.getCategorie()
				);
		assertEquals(
                "Erreur pour la quantite : ",
                quantiteAttendu, d.getQuantite()
				);
	}

	@Test
	public void getAListOfDenree() throws Exception
	{
		
        final long taille = 4;
        Collection<Denree> col = stockBDD.getListeDenree( 1, taille );
        assertEquals(
                "La taille ne correspond pas : ",
                taille, col.size()
                );

        Iterator<Denree> it = col.iterator();
        final int[] tabId = { 1, 2, 3, 4 };
        final String[] tabNom = { "coca", "oasis", "Sandwich", "Magnum" };
        final String[] tabCategorie = { "boisson", "boisson", "encas", "glace" };
        final int[] tabQuantite = {20, 20, 20, 20};

        int i = 0;
        while ( it.hasNext() )
        {
            Denree d = it.next();
            assertEquals(
                    "Erreur pour l'identifiant : ",
                    tabId[i], d.getId()
                    );
            assertEquals(
                    "Erreur pour le nom : ",
                    tabNom[i], d.getNom()
                    );
            assertEquals(
                    "Erreur pour la catégorie : ",
                    tabCategorie[i], d.getCategorie()
                    );
            assertEquals(
                    "Erreur pour la qantité : ",
                    tabQuantite[i], d.getQuantite()
                    );
            i++;
}
	}
	private static void insertDenree(long id, String nom, String categorie, int quantite) throws Exception
	{
		String requete = "INSERT INTO Denree ( id, nom, categorie, quantite ) VALUES (?,?,?,?)";
		 
		PreparedStatement statement = connection.prepareStatement(requete);
		statement.setLong(1,id);
		statement.setString(2,nom);
		statement.setString(3,categorie);
		statement.setInt(4,quantite);
		statement.executeUpdate();
		statement.close();
	}

	@After
	public void tearDown() throws Exception
	{
		stockBDD.dispose();
	}

	@AfterClass
	public static void end() throws Exception
	{
		//clearTable();
		connection.close();
	}

	public static void clearTable() throws Exception
	{
		String requete = "DELETE FROM Denree";
		PreparedStatement statement = connection.prepareStatement(requete);
		statement.executeUpdate();
		statement.close();
	}

}
