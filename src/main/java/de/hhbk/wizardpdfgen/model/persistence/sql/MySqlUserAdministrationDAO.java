package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.UserAdministrationDAO;
import de.hhbk.wizardpdfgen.model.persistence.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Author: Monica Schepan on 05.05.17 <br>
 * Edited by: Kenji Kokubo on 08.05.17<br>
 * This class is the data access object for the user administration tables.<br>
 * It supports CRD-operation on user
 */
public class MySqlUserAdministrationDAO implements UserAdministrationDAO {


    private static final String GET_ALL_USER = "SELECT  USER.USER, USER.PASSWORD, AUTHORISATION.Role FROM USER inner join AUTHORISATION on FK_AuthorisationID = Authorisation.AuthorisationID";
    private static final String GET_USER_BY_NAME_PASSWD = "SELECT USER.USER, USER.PASSWORD, Authorisation.Role FROM USER inner join Authorisation on FK_AuthorisationID = Authorisation.AuthorisationID WHERE USER.Password = ? and USER.USER = ?";
    private static final String INSERT_USER = "Insert into User(UserID, User, Password, FK_AuthorizationID) VALUES (NULL,?,?,?) ";
    private static final String DELETE_USER = "Delete from User where User.User = ? and User.password = ? ";

    private static Logger logger = LogManager.getLogger(MySqlUserAdministrationDAO.class);

    /**
     * Searches for user and password.<br>
     * If there is no entry with this username and its corresponding password, null will be returned.
     *
     * @param username name of the user
     * @param password password
     * @return user object if demanded user was found, null otherwise
     * @throws SQLException if an IO error occurs
     */
    public User getUser(String username, String password) throws SQLException {

        Connection conn = null;
        User foundUser = null;

        try {
            conn = DBUtil.getConnection(DBType.MYSQL_DB);
            PreparedStatement statement = conn.prepareStatement(GET_USER_BY_NAME_PASSWD);
            statement.setString(1, password);
            statement.setString(2, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String db_username = rs.getString(1);
                String db_password = rs.getString(2);
                String authLevelString = rs.getString(3);
                AuthorisationLevel authLevel = AuthorisationLevel.valueOf(authLevelString);

                foundUser = new User(db_username, db_password, authLevel);
            }
        } catch (SQLException e) {
            String errMsg = "Error while connecting to database";
            logger.error(errMsg);
            throw new SQLException(errMsg, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                String errMsg = "Error while closing the connection to database";
                logger.error(errMsg);
                throw new SQLException(errMsg, e);
            }
        }
        return foundUser;
    }


    /**
     * Retrieves all user on database.
     *
     * @return list of users; if there are no user entries, an empty list will be returned
     */
    public static List<User> getAllUser() throws SQLException {

        Connection conn = null;
        List<User> userListe = new ArrayList<User>();

        try {
            conn = DBUtil.getConnection(DBType.MYSQL_DB);
            PreparedStatement statement = conn.prepareStatement(GET_ALL_USER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String db_username = rs.getString(1);
                String db_password = rs.getString(2);
                String db_authLvl = rs.getString(3);
                AuthorisationLevel authLevel = AuthorisationLevel.valueOf(db_authLvl);

                userListe.add(new User(db_username, db_password, authLevel));
            }
        } catch (SQLException e) {
            String errMsg = "Error while connecting to database";
            logger.error(errMsg);
            throw new SQLException(errMsg, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                String errMsg = "Error while closing the connection to database";
                logger.error(errMsg);
                throw new SQLException(errMsg, e);
            }
        }
        return userListe;
    }

    /**
     * Adds a user into databases.
     *
     * @param usr user to be added
     * @return true, if user has been deleted successfully; false otherwise
     * @throws SQLException if an IO error occurs
     */
    public static boolean insertUser(User usr) throws SQLException {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection(DBType.MYSQL_DB);
            PreparedStatement statement = conn.prepareStatement(INSERT_USER);

            String user = usr.getUsername();
            String password = usr.getPassword();
            statement.setString(1, usr.getUsername());
            statement.setString(2, usr.getPassword());
            statement.setString(3, usr.getRole().toString());
            statement.execute();
        } catch (SQLException e) {
            String errMsg = "Error while connecting to database";
            logger.error(errMsg);
            throw new SQLException(errMsg, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                String errMsg = "Error while closing the connection to database";
                logger.error(errMsg);
                throw new SQLException(errMsg, e);
            }
        }
        return true;
    }


    /**
     * Deletes a user from database.
     *
     * @param usr user to be deleted
     * @return true, if user has been deleted successfully; false otherwise
     * @throws SQLException if an IO error occurs
     */
    public static boolean deleteUser(User usr) throws SQLException {
        Connection conn = null;
        try {
            conn = DBUtil.getConnection(DBType.MYSQL_DB);
            PreparedStatement statement = conn.prepareStatement(DELETE_USER);
            statement.setString(1, usr.getUsername());
            statement.setString(2, usr.getPassword());
            statement.execute();
        } catch (SQLException e) {
            String errMsg = "Error while connecting to database";
            logger.error(errMsg);
            throw new SQLException(errMsg, e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                String errMsg = "Error while closing the connection to database";
                logger.error(errMsg);
                throw new SQLException(errMsg, e);
            }
        }
        return true;
    }
}
