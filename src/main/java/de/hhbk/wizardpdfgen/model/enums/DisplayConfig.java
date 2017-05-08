package de.hhbk.wizardpdfgen.model.enums;

/**
 * Created by x1n4u on 5/8/17.
 */
public enum DisplayConfig {
    KOMPETENZEN("KOMPETENZEN"),
    HANDLUNGSPRODUKT("HANDLUNGSPRODUKT"),
    INHALTE("INHALTE"),
    UMATERIAL("UMATERIAL"),
    ORGANISATION("ORGANISATION"),
    ATECHNIKEN("ATECHNIKEN"),
    NACHWEISE("NACHWEISE");


    private final String config;

    DisplayConfig(final String config)
    {
        this.config = config;
    }

    @Override
    public String toString() {
        return config;
    }
}
