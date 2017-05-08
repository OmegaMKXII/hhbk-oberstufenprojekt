package de.hhbk.wizardpdfgen.model.persistence.util;

import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Author: Kenji Kokubo on 08.05.17 <br>
 * This class manages the Driver for databases. <br>
 * Information on DB access are gathered in here.
 */
public class DBUtil {

    private static Logger logger = LogManager.getLogger(MySqlUserAdministrationDAO.class);

    private static final String mySqlUser = "root";
    private static final String mySqlPwd = "";
    private static final String mySQLCS = "jdbc:mysql://localhost/DB_DWPC";

    private static final String didactSqlUser = "root";
    private static final String didactSqlPwd = "";
    private static final String didactSQLCS = "jdbc:mysql://localhost/didakt";

    private static final String mySQLDR = "com.mysql.jdbc.Driver";

    /**
     * Retrieves connection depending on given database type {@link DBType}
     * @param dbType database type of databse to connect
     * @return Connection object of demanded database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection(DBType dbType) throws SQLException {
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
