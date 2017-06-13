
package fr.aifcc.master.stock_bean;

import javax.faces.bean.*;

import fr.aifcc.master.stock_api.*;
import fr.aifcc.master.stock_impl.*;

/**
 * Cette classe ne sera jamais instancié manuellement.
 * Toutes ces instanciations seront faites par JSF
 */
@ManagedBean( name = "stockBean" )
@ApplicationScoped          // L'application est instancié dés que l'app est lancée
public class StockBean
{

    private final StockBDD stockBDD;

    /**
     * Une instance géré par JSF qui donne accès à la BDD.
     * */
    public StockBean()
        throws Exception
    {
        this.stockBDD = new StockBDD( "org.apache.derby.jdbc.ClientDriver", "jdbc:derby://localhost:1527/Stock" );
    }

    /**
     * @return L'instance de directory.
     * */
    public StockBDD getStock()
    {
        return this.stockBDD;
    }

}
