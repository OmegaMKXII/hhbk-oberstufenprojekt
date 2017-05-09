package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.Configuration;
import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.enums.DisplayConfig;
import de.hhbk.wizardpdfgen.model.generator.Config;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.TemplateDAO;
import de.hhbk.wizardpdfgen.model.persistence.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Author: Monica Schepan on 05.05.17 <br>
 * Edited by: Kenji Kokubo on 08.05.17<br>
 * This class is the data access object to the tables, which stores template information.<br>
 * It supports CRD-operation on templates
 */
public class MySqlTemplateDAO implements TemplateDAO {


    private static final String GET_ALL_TEMPLATE_TITLE_BY_USER = "SELECT tmpl.title FROM template AS tmpl " +
            "JOIN user AS user ON tmpl.fk_userid = user.userid " +
            "WHERE user.username = ?";

    private static final String GET_ALL_CONFIG_BY_TEMPLATE_TITLE = "SELECT conf.name FROM configuration AS conf " +
            "JOIN  template_configuration as teco ON conf.configurationID = teco.fk_configurationid " +
            "JOIN template AS tmpl ON teco.fk_templateid = tmpl.templateid " +
            "WHERE tmpl.title = ?";

    private static final String INSERT_TEMPLATE = "Insert into Template (TEMPLATE.TEMPLATEID, TEMPLATE.TITLE, FK_USERID) VALUES (NULL,?,?)";

    private static final String DELETE_TEMPLATE = "DELETE * FROM template WHERE Title = ?";

    private static Logger logger = LogManager.getLogger(MySqlTemplateDAO.class);



    public List<Config> getAllTemplatesByUser(User user) throws SQLException {
        List<Config> configurationList = new ArrayList<Config>();
        Connection conn = null;

        try {
            conn = DBUtil.getConnection(DBType.MYSQL_DB);
            PreparedStatement ps = conn.prepareStatement(GET_ALL_TEMPLATE_TITLE_BY_USER);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String title = rs.getString(1);
                Set<DisplayConfig> displayConfigs = new HashSet<DisplayConfig>();

                PreparedStatement innerPs = conn.prepareStatement(GET_ALL_CONFIG_BY_TEMPLATE_TITLE);
                innerPs.setString(1, title);
                ResultSet innerRs = innerPs.executeQuery();

                while(innerRs.next()){
                    String confString = innerRs.getString(1);
                    DisplayConfig displayConfig = DisplayConfig.valueOf(confString.toUpperCase());
                    displayConfigs.add(displayConfig);
                }
                configurationList.add(new Config(title, displayConfigs));
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
        return configurationList;
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
