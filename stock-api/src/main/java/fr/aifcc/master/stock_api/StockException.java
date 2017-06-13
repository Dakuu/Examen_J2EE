package fr.aifcc.master.stock_api;

public class StockException extends Exception
{

    /**
     * Crée une nouvelle instance de StockException
     * */
    public StockException()
    {}

    /**
     * Crée une nouvelle instance de StockException
     * @param message
     * Le message détaillant exception
     * */
    public StockException( String message )
    {
        super( message );
    }

    /**
     * Crée une nouvelle instance de StockException
     * @param cause
     * L'exception à l'origine de cette exception
     * */
    public StockException( Throwable cause )
    {
        super( cause );
    }

    /**
     * Crée une nouvelle instance de StockException
     * @param message
     * Le message détaillant exception
     * @param cause
     * L'exception à l'origine de cette exception
     * */
    public StockException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
