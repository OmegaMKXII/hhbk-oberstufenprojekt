package de.hhbk.wizardpdfgen.model.persistence.interfaces;

import de.hhbk.wizardpdfgen.model.base.User;
import de.hhbk.wizardpdfgen.model.generator.Config;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 03.05.2017.
 */
public interface TemplateDAO {

    public List<Config> getAllTemplatesByUser(User user) throws SQLException;
}
