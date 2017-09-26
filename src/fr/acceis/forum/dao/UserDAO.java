package fr.acceis.forum.dao;

import static fr.acceis.forum.dao.DAOUtils.preparedRequestInitialization;
import static fr.acceis.forum.dao.DAOUtils.silentlyClose;
import fr.acceis.forum.dao.DAOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.acceis.forum.beans.User;

public class UserDAO {
	public static void main(String args[]){
		User user;
		try{
			user = findUserByLogin("pierre");
			System.out.println("id "+user.getId()+" email "+user.getEmail()+" login "+user.getLogin()+" password "+user.getPassword());
		}catch(Exception e){
			e.printStackTrace();
			user = null;
		}
		
	}
	
	private static final String SQL_FIND_BY_LOGIN = "SELECT * FROM UTILISATEUR WHERE LOGIN = ?";
	
	public static User findUserByLogin( String login ) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		User found = new User();
		// Instanciation du driver
		Class.forName("org.hsqldb.jdbcDriver").newInstance();
		// Connexion à la base de données
		//Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/baseservlet", "sa",  "");
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:C:/Users/Administrateur/Documents/m2i/duchesne/Servlets/forum/forum/data/baseservlet", "sa",  "");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = preparedRequestInitialization(
                    connexion,
                    SQL_FIND_BY_LOGIN,
                    false,
                    login);
            resultSet = preparedStatement.executeQuery();
            if ( resultSet.next() ) {
                found = map( resultSet );
            } else {
            	return null;
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentlyClose( resultSet, preparedStatement, connexion );
		connexion.close();
	}
        return found;
	}

	/*public static User saveUser( User toSave ){
		User saved = new User();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet generatedValues = null;
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:C:/Users/Administrateur/Documents/m2i/duchesne/Servlets/forum/forum/data/baseservlet", "sa",  "");
            preparedStatement = preparedRequestInitialization(
                    connection,
                    SQL_INSERT,
                    true,
                    room.getName() );
            int statut = preparedStatement.executeUpdate();
            if ( statut == 0 ) {
                throw new DAOException( "Failure at creating room. No new row was inserted." );
            }
            generatedValues = preparedStatement.getGeneratedKeys();
            if ( generatedValues.next() ) {
                saved.setId( generatedValues.getLong( 1 ) );
                saved.setName( room.getName() );
                //TODO create links with dependencies
            } else {
                throw new DAOException( "Failure at creating room in database. No new ID was generated" );
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentlyClose( generatedValues, preparedStatement, connection );
        }   
        return saved;
	}*/
	
	private static User map( ResultSet resultSet ) throws SQLException {
        User user = new User();
        user.setId( resultSet.getLong( "id" ) );
        user.setEmail(resultSet.getString( "email" ));
        user.setLogin(resultSet.getString( "login" ));
        user.setPassword(resultSet.getString( "password" ));
        return user;
    }


}
