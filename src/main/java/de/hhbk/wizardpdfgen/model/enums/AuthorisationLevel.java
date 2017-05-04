package de.hhbk.wizardpdfgen.model.enums;

/**
 * Created by monikaschepan on 02.05.17.
 */
public enum AuthorisationLevel {

    GUEST("Gast"),
    ADMIN("Admin"),
    TEACHER("Lehrer");

    private final String level;

    private AuthorisationLevel(final String level)
    {
        this.level = level;

    }

    @Override
    public String toString() {
        return level;
    }
}
