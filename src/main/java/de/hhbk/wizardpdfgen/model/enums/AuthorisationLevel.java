package de.hhbk.wizardpdfgen.model.enums;

/**
 * Created by monikaschepan on 02.05.17.
 */
public enum AuthorisationLevel {

    GUEST("GUEST"),
    ADMIN("ADMIN"),
    TEACHER("TEACHER");

    private final String level;

    AuthorisationLevel(final String level)
    {
        this.level = level;
    }

    @Override
    public String toString() {
        return level;
    }


}
