
package fr.aifcc.master.stock_bean;

import java.util.Collection;
import javax.faces.bean.*;

import fr.aifcc.master.stock_api.*;
import fr.aifcc.master.stock_impl.*;

/**
 * Lien entre la partie métier et la page web.
 */
@ManagedBean( name = "pageBenevoleBean" )
@ViewScoped         // Tant qu'on reste sur la même page cette instance n'est pas déchargé de la mémoire
public class PageBenevole
{

    /**
     * L'instance d'application directory bean sera injecté directement par JSF
     * */
    @ManagedProperty( value = "#{stockBean}" )
    private StockBean stockBean;

    /**
     * Constructeur sans arguments conforme à la norme java bean
     * */
    public PageBenevole()
    {}

    /**
     * @return L'instance de directory bean
     * */
    public StockBean getStockBean()
    {
        return this.stockBean;
    }

    /**
     * @param stockBean
     * L'instance de la directory bean
     * */
    public void setStockBean( StockBean stockBean )
    {
        this.stockBean = stockBean;
    }


    /**
     * Méthode d'affichage des denrées.
     * @return La liste des personnes
     * @throws StockException
     * Une erreur à été levé lors de l'accès aux données.
     * */
    public Collection<Denree> getDenrees()
        throws StockException
    {
        StockBDD dir = this.stockBean.getStock();
        Collection<Denree> it = dir.getListeDenree( 1, 100 );
        return it;
    }
    
  
	public void envelerElement(int id) throws StockException
    {
		StockBDD dir = this.stockBean.getStock();
        Denree d = dir.getDenree(id);
        int qte = d.getQuantite();
        if (qte>0){
            d.setQuantite(qte - 1);
            dir.mAjDenree(d);
        } 
	}

}
