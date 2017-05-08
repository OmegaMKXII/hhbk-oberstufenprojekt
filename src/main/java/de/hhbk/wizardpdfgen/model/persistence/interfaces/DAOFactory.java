package de.hhbk.wizardpdfgen.model.persistence.interfaces;

import de.hhbk.wizardpdfgen.model.enums.DBType;
import de.hhbk.wizardpdfgen.model.persistence.sql.DidacticWizardDAO;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlTemplateDAO;
import de.hhbk.wizardpdfgen.model.persistence.sql.MySqlUserAdministrationDAO;

/**
 * Author: Kenji Kokubo on 08.05.17 <br>
 * Abstract class for generating DAOs statically.
 */
public abstract class DAOFactory {

    /**
     * Returns a new TemplateDAO which database types matches to the given one
     * @param dbType database type of database to connect
     * @return the demanded DAO
     */
    public static TemplateDAO createTemplateDAO(DBType dbType){
       switch(dbType){
           case MYSQL_DB:
               return new MySqlTemplateDAO();
           default:
               return null;
       }
    };

    /**
     * Returns a new UserAdministrationDAO which database types matches to the given one
     * @param dbType database type of database to connect
     * @return the demanded DAO
     */
    public static UserAdministrationDAO createUserAdminDAO(DBType dbType){
        switch(dbType){
            case MYSQL_DB:
                return new MySqlUserAdministrationDAO();
            default:
                return null;
        }
    };

    /**
     * Returns a new DidacticWizardDAO which database types matches to the given one
     * @param dbType database type of database to connect
     * @return the demanded DAO
     */
    public static DidacticWizardDAO createDidkatWizardDAO(DBType dbType){
        switch(dbType){
            case DIDACTSQL_DB:
                return new DidacticWizardDAO();
            default:
                return null;
        }
    };
}
