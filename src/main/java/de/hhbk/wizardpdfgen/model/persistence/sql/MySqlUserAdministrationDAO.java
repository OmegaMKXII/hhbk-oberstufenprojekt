package de.hhbk.wizardpdfgen.model.persistence.sql;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.persistence.interfaces.UserAdministrationDAO;

/**
 * Created by user on 03.05.2017.
 */
public class MySqlUserAdministrationDAO implements UserAdministrationDAO{
    @Override
    public User getUser(String username, String passwd) {
        return null;
    }
}
