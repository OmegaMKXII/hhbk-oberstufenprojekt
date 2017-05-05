package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.UserAdministrationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

/**
 * Created by user on 03.05.2017.
 */
public class MySqlUserAdministrationDAO implements UserAdministrationDAO{


    // MySQL
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/DB_DWPC";
    private static final String ID = "root";
    private static final String PASS = "";

    // MySQL Statement

    private static final String FIND_ALL_USER = "SELECT  USER.USER, USER.PASSWORD FROM USER";

    private static final String FIND_ID_FROM_USER = "SELECT USER.USERID FROM USER  WHERE USER.USER = ?";

    private static final String FIND_PASSWORD_BY_USER_PASSWORD = "SELECT USER.USER, USER.PASSWORD, Authorisation.Role FROM USER inner join Authorisation on FK_AuthorizationID = Authorisation.AuthorisationID WHERE USER.Password = ?";

    private static final String INSERT_USER = "Insert into User(UserID, User, Password, FK_AuthorizationID)"
            +"VALUES (NULL,?,?,2) ";
    private static final String DELETE_USER = "Delete from User where User.User = ? and User.password = ? ";



    public MySqlUserAdministrationDAO() {ArrayList<User> administrationList = new ArrayList<User>();
    }

    public static List<User> selectUserID(String user) {
        List<User> userListe = new ArrayList<User>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(FIND_ID_FROM_USER);
            statement.setString(1,user);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
               userListe.add(new User(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userListe;
    }

    public static List<User> selectPasswordByUser(String user) {
        List<User> userListe = new ArrayList<User>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(FIND_PASSWORD_BY_USER_PASSWORD);
            statement.setString(1,user);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                userListe.add(new User(rs.getString(1),rs.getString(2),rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userListe;
    }


    public static List<User> selectAllUser() {
        List<User> userListe = new ArrayList<User>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(FIND_ALL_USER);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                userListe.add(new User(rs.getString(1),rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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


           String user = u.getUser();
           String password = u.getPassword();
            statement.setString(1,u.getUser());
            statement.setString(2,u.getPassword());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
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
            statement.setString(1,user);
            statement.setString(2,password);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }


}
