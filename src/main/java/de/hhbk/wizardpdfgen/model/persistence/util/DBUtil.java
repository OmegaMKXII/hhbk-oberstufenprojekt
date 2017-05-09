package de.hhbk.wizardpdfgen.model.persistence.util;

import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Author: Kenji Kokubo on 08.05.17 <br>
 * This class manages the Driver for databases. <br>
 * Information on DB access are gathered in here.
 */
public class DBUtil {

    private static Logger logger = LogManager.getLogger(MySqlUserAdministrationDAO.class);

    private static String mySqlUser;
    private static String mySqlPwd;
    private static String mySQLCS;

    private static String didactSqlUser;
    private static String didactSqlPwd;
    private static String didactSQLCS;

    private static String mySQLDR;

    public static void readConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/main/resources/config.properties");
            prop.load(input);

            mySQLDR=prop.getProperty("driver");

            mySqlUser= prop.getProperty("clientUser");
            mySqlPwd=prop.getProperty("clientPassword");
            mySQLCS=prop.getProperty("clientJDBC");

            didactSqlUser=prop.getProperty("didaktUser");
            didactSqlPwd=prop.getProperty("didaktPassword");
            didactSQLCS=prop.getProperty("didaktJDBC");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Retrieves connection depending on given database type {@link DBType}
     * @param dbType database type of databse to connect
     * @return Connection object of demanded database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection(DBType dbType) throws SQLException {
        readConfig();
        try {
            Class.forName(mySQLDR);
        } catch (ClassNotFoundException e) {
            String errMsg = "Class " + mySQLDR + "could not been found.";
            logger.error(errMsg);
            new SQLException(errMsg, e);
        }

        switch(dbType){
            case MYSQL_DB:
                    return DriverManager.getConnection(mySQLCS, mySqlUser, mySqlPwd);
            case DIDACTSQL_DB:
                return DriverManager.getConnection(didactSQLCS, didactSqlUser, didactSqlPwd);
            default:
                return null;
        }
    }
}
