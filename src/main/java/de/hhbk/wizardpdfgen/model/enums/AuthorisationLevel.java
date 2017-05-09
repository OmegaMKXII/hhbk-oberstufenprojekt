package de.hhbk.wizardpdfgen.model.enums;

/**
 * Author: Kenji Kokubo on 08.05.17 <br>
 * This enumeration is primarily for expandability.<br>
 * New authorisation level such as ORACLE DB can be registered in this enum. <br>
 */
public enum AuthorisationLevel {

    GUEST("GUEST"),
    ADMIN("ADMIN"),
    TEACHER("TEACHER");

    private final String level;

    /**
     * Constructs and set specific string in this enum
     * @param level string, which represents the authorisation level
     */
    AuthorisationLevel(final String level)
    {
        this.level = level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return level;
    }

}
