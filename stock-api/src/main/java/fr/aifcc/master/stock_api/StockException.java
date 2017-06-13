package fr.aifcc.master.stock_api;

public class StockException extends Exception
{

    /**
     * Crée une nouvelle instance de ExceptionDivisionParZero
     * */
    public StockException()
    {}

    /**
     * Crée une nouvelle instance de ExceptionDivisionParZero
     * @param message
     * Le message détaillant exception
     * */
    public StockException( String message )
    {
        super( message );
    }

    /**
     * Crée une nouvelle instance de ExceptionDivisionParZero
     * @param cause
     * L'exception à l'origine de cette exception
     * */
    public StockException( Throwable cause )
    {
        super( cause );
    }

    /**
     * Crée une nouvelle instance de ExceptionDivisionParZero
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
