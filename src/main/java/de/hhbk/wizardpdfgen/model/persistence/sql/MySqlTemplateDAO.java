package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.Configuration;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.TemplateDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 03.05.2017.
 */
public class MySqlTemplateDAO implements TemplateDAO {



    // MySQL
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/DB_DWPC";
    private static final String ID = "root";
    private static final String PASS = "";

    // MySQL Statement


    private static final String SELECT_CONFIGURATION = "Select Configuration.ConfigurationID, Configuration.Name from Configuration";

    private static final String INSERT_TEMPLATE = "Insert into Template (TEMPLATE.TEMPLATEID, TEMPLATE.TITLE, FK_USERID)"
            +"VALUES (NULL,?,?) ";
    public MySqlTemplateDAO() {ArrayList<User> templateList = new ArrayList<User>();
    }
    public static List<Configuration> selectAllConfigurations() {
        List<Configuration> configurationList = new ArrayList<Configuration>();
        try {
            PreparedStatement statement = getConnection().prepareStatement(SELECT_CONFIGURATION);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                configurationList.add(new Configuration (rs.getInt(1),rs.getString(2)));
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

        return configurationList;
    }


    public static boolean addTemplate(String templateName, Integer userID) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_TEMPLATE);



            statement.setString(1,templateName);
            statement.setInt(2,userID);
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



}
