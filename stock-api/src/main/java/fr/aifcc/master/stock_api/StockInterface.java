package fr.aifcc.master.stock_api;

import java.util.Collection;

/**
 * Interface d'accès au stockage persistant des données.
 * @author PIVARD Julien
 * */
public interface StockInterface
{

    /**
     * Permet de récupérer un ensemble de denrées dans un ordre arbitraire.
     * L'ordre des denrées sera le même entre deux même appels.
     * @param position
     * L'index du premier item à récupérer.
     * @param nombreitems
     * Le nombre d'item dont on veut récupérer les données.
     * @return La liste des denrées demandé.
     * */
    public Collection<Denree> getListeDenree( long position, long nombreitems )
            throws StockException;

    /**
     * @param id
     * L'identifiant unique de la denrée à récupérer dans le stockage persistant
     * @return La denrée demandé.
     * */
    public Denree getDenree( long id ) throws StockException;

    /**
     * met à jour la denrée dans le stockage persistant.
     * @param denree
     * La denrée modifié.
     * */
    public void mAjDenree( Denree denree ) throws StockException;

    /**
     * Permet d'ajouter une denrée dans le stock
     * @param item
     * La denrée à ajouter.
     * */
    public void ajouterDenree( Denree item ) throws StockException;

    /**
     * Permet de récupérer la liste des denrées qui répondent au
     * critère de recherche
     * @param critere
     * Le critère de recherche.
     * @return La liste des denrées qui correspondent à la recherche.
     * */
    public Collection<Denree> getDenreeRecherche( String critere )
            throws StockException;


    /**
     * Libère les ressources occupé par cette instance de stock.
     * */
    public void dispose() throws StockException;

}
