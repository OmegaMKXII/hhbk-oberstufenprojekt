package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.Configuration;
import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.TemplateDAO;
import de.hhbk.wizardpdfgen.model.persistence.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Author: Monica Schepan on 05.05.17 <br>
 * Edited by: Kenji Kokubo on 08.05.17<br>
 * This class is the data access object to the tables, which stores template information.<br>
 * It supports CRD-operation on templates
 */
public class MySqlTemplateDAO implements TemplateDAO {


    // MySQL Statement
    private static final String GET_ALL_TEMPLATES_BY_USER = "Select Configuration.ConfigurationID, Configuration.Name from Configuration";
    private static final String INSERT_TEMPLATE = "Insert into Template (TEMPLATE.TEMPLATEID, TEMPLATE.TITLE, FK_USERID) VALUES (NULL,?,?)";
    private static final String DELETE_TEMPLATE = "DELETE * FROM template WHERE Title = ?";

    private static Logger logger = LogManager.getLogger(MySqlTemplateDAO.class);


    public static Set<String> getAllTemplatesByUser() throws SQLException {
        List<Configuration> configurationList = new ArrayList<Configuration>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection(DBType.MYSQL_DB);
            PreparedStatement statement = conn.prepareStatement(GET_ALL_TEMPLATES_BY_USER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                configurationList.add(new Configuration(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            String errMsg = "Error while connecting to database";
            logger.error(errMsg);
            throw new SQLException(errMsg, e);
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                String errMsg = "Error while closing the connection to database";
                logger.error(errMsg);
                throw new SQLException(errMsg, e);
            }
        }
        return null;
        // TODO return configurationList;
    }


    public static boolean addTemplate(String templateName, Integer userID) throws SQLException {

        Connection conn = null;
        try {
            PreparedStatement statement = DBUtil.getConnection(DBType.MYSQL_DB).prepareStatement(INSERT_TEMPLATE);
            statement.setString(1, templateName);
            statement.setInt(2, userID);
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
