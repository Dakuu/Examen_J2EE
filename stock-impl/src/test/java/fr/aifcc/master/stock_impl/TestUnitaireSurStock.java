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

	 /**
     * Cette méthode sera exécutée avant l'appel des tests unitaires
     * */
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
		insertDenree(5,"Magnum vanille", "glace", 20);

	}

	 /**
     * Cette méthode sera exécutée avant CHAQUE test unitaire.
     * */
	@Before
		public void setUp() throws Exception
		{
			stockBDD = new StockBDD(DRIVER,URL);
		}


	 /**
     * Cette méthode permet de tester la récupération d'une denrée avec un id.
     * */
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

	 /**
     * Cette méthode permet de tester la récupération d'une liste de denrée en indiquant la
     * première denrée voulue et le nombre de denrée après.
     * */
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
	
	 /**
     * Cette méthode permet de tester l'ajout de Denrée dans la table.
     * */
	@Test
	public void testAddedDenree() throws Exception
	{
		final long id = 7;
		final String nomAttendu = "fanta";
        final String categorieAttendu = "boisson";
        final int quantiteAttendu = 50;
		Denree d1 = new Denree();
		d1.setId(id);
		d1.setNom(nomAttendu);
		d1.setCategorie(categorieAttendu);
		d1.setQuantite(quantiteAttendu);
		
		stockBDD.ajouterDenree(d1);
        
        Denree d2 = stockBDD.getDenree(id);
        assertEquals(
                "Erreur pour l'identifiant : ",
                d1.getId(), d2.getId()
                );
        assertEquals(
                "Erreur pour le nom : ",
                d1.getNom(), d2.getNom()
                );
        assertEquals(
                "Erreur pour la catégorie : ",
                d1.getCategorie(), d2.getCategorie()
				);
		assertEquals(
                "Erreur pour la quantite : ",
                d1.getQuantite(), d2.getQuantite()
				);
	}
	
	 /**
     * Cette méthode permet de tester la modification de denrée dans la table.
     * */
	@Test
	public void testUpdateDenree() throws Exception
	{
		final int qte = 15;
		Denree d1 = stockBDD.getDenree(5);
		d1.setQuantite(qte);
		
		
        stockBDD.mAjDenree(d1);
        Denree d2 = stockBDD.getDenree(5);
        assertEquals(
                "Erreur pour l'identifiant : ",
                d1.getId(), d2.getId()
                );
        assertEquals(
                "Erreur pour le nom : ",
                d1.getNom(), d2.getNom()
                );
        assertEquals(
                "Erreur pour la catégorie : ",
                d1.getCategorie(), d2.getCategorie()
				);
		assertEquals(
                "Erreur pour la quantite : ",
                qte, d2.getQuantite()
				);
	}
	
	 /**
     * Cette méthode permet de rechercher une liste de denrée selon un critère particulier sur le nom 
     * exemple car les denrées commençant par une meme lettre.
     * */
	@Test
	public void testSearchDenree() throws Exception
	{
        Collection<Denree> col = stockBDD.getDenreeRecherche("M%");
        
        Iterator<Denree> it = col.iterator();
        final int[] tabId = { 4, 5 };
        final String[] tabNom = { "Magnum", "Magnum vanille" };
        final String[] tabCategorie = { "glace", "glace" };
        //Qté attendu 15 pour le Magnum Vanille à cause de l'update effectué dans testUpdateDenree() ci-dessus.
        final int[] tabQuantite = { 20, 15 };

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
                    "Erreur pour la quantite : ",
                    tabQuantite[i], d.getQuantite()
                    );
            i++;
}
	}
	
	 /**
     * Méthode d'insertion de denrée dans la table testStock pour les tests unitaires.
     * */
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

	 /**
     * Cette méthode sera exécutée après CHAQUE test unitaire.
     * */
	@After
	public void tearDown() throws Exception
	{
		stockBDD.dispose();
	}

	 /**
     * Cette méthode sera exécutée après les tests unitaires pour fermer la connexion à la base.
     * */
	@AfterClass
	public static void end() throws Exception
	{
		//clearTable();
		connection.close();
	}

	 /**
     * Cette méthode permet de vider la table de test
     * elle est utilisé dans le @BeforeClass pour partir sur une base neuve à chaque début de test.
     * */
	public static void clearTable() throws Exception
	{
		String requete = "DELETE FROM Denree";
		PreparedStatement statement = connection.prepareStatement(requete);
		statement.executeUpdate();
		statement.close();
	}

}
