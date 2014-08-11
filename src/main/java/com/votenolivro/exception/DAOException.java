package com.votenolivro.exception;

import com.votenolivro.utils.I18N;


@SuppressWarnings( "unchecked" )
public class DAOException extends RuntimeException
{

    private static final long serialVersionUID = -7509998033484813745L;

    /**
     * Nome da DAO que ocorreu a exception
     */

    private Class clazz;

    private String message;

    public DAOException( Exception ex, Class classe, String message )
    {
        super( ex.getLocalizedMessage() );
        super.setStackTrace( ex.getStackTrace() );
        this.clazz = classe;
        this.message = I18N.getString( message );

    }

    public DAOException( Class classe, String message )
    {
        this.clazz = classe;
        this.message = I18N.getString( message );

    }

    public DAOException( Exception ex, String message )
    {
        super( ex.getLocalizedMessage() );
        super.setStackTrace( ex.getStackTrace() );
        this.message = I18N.getString( message );
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    /**
     * @return the clazz
     */
    public Class getClazz()
    {
        return clazz;
    }

    /**
     * @param clazz the clazz to set
     */
    public void setClazz( Class clazz )
    {
        this.clazz = clazz;
    }
}
