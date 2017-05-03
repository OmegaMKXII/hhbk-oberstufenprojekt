package sample.util;

/**
 * Created by monikaschepan on 02.05.17.
 */
public enum Authorisation {

    GUEST("Gast"),
    ADMIN("Admin"),
    TEACHER("Lehrer");

    private final String level;

    private Authorisation(final String level)
    {
        this.level = level;

    }

    @Override
    public String toString() {
        return level;
    }
}
