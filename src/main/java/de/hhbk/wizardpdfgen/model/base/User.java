package de.hhbk.wizardpdfgen.model.base;

import de.hhbk.wizardpdfgen.model.base.Template;
import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 04.05.2017.
 */
public class User {
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public User(String user, String password, String role, int id) {
        this.user = user;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    String user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id) {

        this.id = id;
    }

    public User(String user, String password, String role) {
        this.user = user;
        this.password = password;
        this.role = role;

    }

    String password;
    String role;
    int id;



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Benutzer: " + user + "-" + "Passwort: " + password;
    }
}
