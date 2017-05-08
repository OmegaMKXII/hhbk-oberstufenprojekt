package de.hhbk.wizardpdfgen.model.base;

import de.hhbk.wizardpdfgen.model.enums.AuthorisationLevel;

/**
 * Created by user on 04.05.2017.
 */
public class User {

    private String username;
    private String password;
    private AuthorisationLevel role;
    private int id; // TODO benötigt?


    public User(String username, String password, AuthorisationLevel role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, AuthorisationLevel role, int id) {
        this(username, password, role);
        this.id = id;
    }

    public AuthorisationLevel getRole() {
        return role;
    }

    public void setRole(AuthorisationLevel role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Benutzer: " + username + "-" + "Passwort: " + password;
    }
}
