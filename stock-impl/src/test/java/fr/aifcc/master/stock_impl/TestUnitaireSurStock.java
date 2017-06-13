/*package fr.aifcc.master.directory_impl;
import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import fr.aifcc.master.directory_api.Person;
import static org.junit.Assert.assertEquals;
import java.util.Collection;
import java.util.Iterator;
import java.util.ArrayList;
import java.sql.*;


public class TestDataBaseDirectory
{
	private static Connection connection;
	private static String DRIVER = "org.apache.derby.jdbc.ClientDriver";
	private static String URL = "jdbc:derby://localhost:1527/NomBDD";
	private DataBaseDirectory dataBaseDirectory;

	@BeforeClass
	public static void onlyOnce() throws Exception
	{
		Class.forName(DRIVER);
		connection = DriverManager.getConnection(URL);
		clearTable();
		insertPerson("TOTO", "toto");
		insertPerson("TATA", "tata");
		insertPerson("TUTU", "tutu");

	}

	@Before
		public void setUp() throws Exception
		{
			dataBaseDirectory = new DataBaseDirectory(DRIVER,URL);
		}


	@Test
	public void getOnePerson() throws Exception
	{

	}
		
	private static void insertPerson(String firstName, String lastName) throws Exception
	{
		String requete = "INSERT INTO PERSON (firstName,lastName) VALUES (?,?)";
		PreparedStatement statement = connection.prepareStatement(requete);
		statement.setString(1,firstName);
		statement.setString(2,lastName);
		statement.executeUpdate();
		statement.close();
	}

	@After
	public void tearDown() throws Exception
	{
		dataBaseDirectory.dispose();
	}

	@AfterClass
	public static void end() throws Exception
	{
		//clearTable();
		connection.close();
	}

	public static void clearTable() throws Exception
	{
		String requete = "DELETE FROM PERSON";
		PreparedStatement statement = connection.prepareStatement(requete);
		statement.executeUpdate();
		statement.close();
	}

}*/
