package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.UserAdministrationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03.05.2017.
 */
public class MySqlUserAdministrationDAO implements UserAdministrationDAO {


    // MySQL
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/DB_DWPC";
    private static final String ID = "root";
    private static final String PASS = "";

    // MySQL Statement
    private static final String GET_ALL_USER = "SELECT  USER.USER, USER.PASSWORD, AUTHORISATION.Role FROM USER inner join AUTHORISATION on FK_AuthorisationID = Authorisation.AuthorisationID";

    private static final String FIND_ID_FROM_USER = "SELECT USER.USERID FROM USER  WHERE USER.USER = ?"; // TODO used?

    private static final String FIND_PASSWORD_BY_USER_PASSWORD = "SELECT USER.USER, USER.PASSWORD, Authorisation.Role FROM USER inner join Authorisation on FK_AuthorisationID = Authorisation.AuthorisationID WHERE USER.Password = ? and USER.USER = ?";

    private static final String INSERT_USER = "Insert into User(UserID, User, Password, FK_AuthorizationID) VALUES (NULL,?,?,2) ";

    private static final String DELETE_USER = "Delete from User where User.User = ? and User.password = ? ";


    public MySqlUserAdministrationDAO() {
        ArrayList<User> administrationList = new ArrayList<User>();
    }

    public static List<User> selectUserID(String user) {
        List<User> userListe = new ArrayList<User>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(FIND_ID_FROM_USER);
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // TODO userListe.add(new User(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userListe;
    }

    /**
     * Searches for user and password.<br>
     * If there is no entry with this username and its corresponding password, null will be returned.
     * @param username - name of the user
     * @param password - password
     * @return The user if found, null otherwise
     */
    public User selectPasswordByUser(String username, String password) {
        User foundUser = null;
        try {
            PreparedStatement statement = getConnection().prepareStatement(FIND_PASSWORD_BY_USER_PASSWORD);
            statement.setString(1, password);
            statement.setString(2, username);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // TODO Gleiche Daten
                String db_username = rs.getString(1);
                String db_password = rs.getString(2);
                String authLevelString = rs.getString(3);
                AuthorisationLevel authLevel = AuthorisationLevel.valueOf(authLevelString);

                foundUser = new User(db_username, db_password, authLevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return foundUser;
    }


    public static List<User> selectAllUser() {
        List<User> userListe = new ArrayList<User>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(GET_ALL_USER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                //TODO gleicher Code
                String db_username = rs.getString(1);
                String db_password = rs.getString(2);
                String authLevelString = rs.getString(3);
                AuthorisationLevel authLevel = AuthorisationLevel.valueOf(authLevelString);

                userListe.add(new User(db_username, db_password, authLevel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userListe;
    }


    public static boolean insertUser(User u) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_USER);

            String user = u.getUsername();
            String password = u.getPassword();
            statement.setString(1, u.getUsername());
            statement.setString(2, u.getPassword());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


    private static Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }


    public static boolean deleteUser(String user, String password) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(DELETE_USER);
            statement.setString(1, user);
            statement.setString(2, password);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


}
