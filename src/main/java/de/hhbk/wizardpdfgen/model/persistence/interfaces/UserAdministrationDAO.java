package de.hhbk.wizardpdfgen.model.persistence.interfaces;

import de.hhbk.wizardpdfgen.model.base.User;

/**
 * Created by user on 03.05.2017.
 */
public interface UserAdministrationDAO {

    public User getUser(String username, String passwd);

}
