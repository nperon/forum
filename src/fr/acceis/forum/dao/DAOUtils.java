package fr.acceis.forum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOUtils {
    /**
     * Initialise la requète préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */
    public static PreparedStatement preparedRequestInitialization( Connection connection, String sql, boolean returnGeneratedKeys, Object... objects ) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS );
        for ( int i = 0; i < objects.length; i++ ) {
            preparedStatement.setObject( i + 1, objects[i] );
        }
        return preparedStatement;
    }

    /**
     * Silent closing of the resultSet
     */
    public static void silentlyClose( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Failed at closing the resultset : " + e.getMessage() );
            }
        }
    }

    /**
     * Silent closing of the statement
     * 
     */

    public static void silentlyClose( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Failed at closing the statement : " + e.getMessage() );
            }
        }
    }

    /**
     * Silent closing of the connection
     * 
     * @param connection
     */
    public static void silentlyClose( Connection connection ) {
        if ( connection != null ) {
            try {
                connection.close();
            } catch ( SQLException e ) {
                System.out.println( "Failed at closing the connection : " + e.getMessage() );
            }
        }
    }

    /**
     * Silent closing of statement and connection.
     */
    public static void silentlyClose( Statement statement, Connection connection ) {
        silentlyClose( statement );
        silentlyClose( connection );
    }

    /**
     * Silent closing of resultset, statement and connection.
     */
    public static void silentlyClose( ResultSet resultSet, Statement statement, Connection connection ) {
        silentlyClose( resultSet );
        silentlyClose( statement );
        silentlyClose( connection );
    }

}
