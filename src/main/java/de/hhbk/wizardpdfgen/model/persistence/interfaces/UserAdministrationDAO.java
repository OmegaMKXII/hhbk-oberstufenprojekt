package de.hhbk.wizardpdfgen.model.persistence.interfaces;


import de.hhbk.wizardpdfgen.model.base.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 03.05.2017.
 */
public interface UserAdministrationDAO {

    public User getUser(String username, String password) throws SQLException;

    public List<User> getAllUser() throws SQLException;

    public boolean insertUser(User usr) throws SQLException;

    public boolean deleteUser(User usr) throws SQLException;

}
