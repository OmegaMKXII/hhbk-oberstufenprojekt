package de.hhbk.wizardpdfgen.model.base;

import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04.05.2017.
 */
public class User {

    private String name;
    private AuthorisationLevel authLvl;
    private List<Template> templates;

    public User(String name, AuthorisationLevel authLvl){
        this(name, authLvl, new ArrayList<Template>());
    }

    public User(String name, AuthorisationLevel authLvl, List<Template> templates){
        this.setName(name);
        this.setAuthLvl(authLvl);
        this.setTemplates(templates);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public AuthorisationLevel getAuthLvl() {
        return authLvl;
    }

    private void setAuthLvl(AuthorisationLevel authLvl) {
        this.authLvl = authLvl;
    }

    public List<Template> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
    }
}
