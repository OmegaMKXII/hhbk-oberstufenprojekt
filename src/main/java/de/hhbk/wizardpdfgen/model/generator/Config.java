package de.hhbk.wizardpdfgen.model.generator;

import de.hhbk.wizardpdfgen.model.enums.DisplayConfig;

import java.util.Set;

/**
 * Created by x1n4u on 5/8/17.
 */
public class Config {
    String displayhandlungsprodukt, displaykompentenzen, displayinhalte, displayumaterial, displayorganiation, displayarbeitstechniken, displaynachweis;

    public Config(Set<DisplayConfig> config) {
        displayhandlungsprodukt = config.contains(DisplayConfig.HANDLUNGSPRODUKT) ? "" : "none";
        displaykompentenzen = config.contains(DisplayConfig.KOMPETENZEN) ? "" : "none";
        displayinhalte = config.contains(DisplayConfig.INHALTE) ? "" : "none";
        displayumaterial = config.contains(DisplayConfig.UMATERIAL) ? "" : "none";
        displayorganiation = config.contains(DisplayConfig.ORGANISATION) ? "" : "none";
        displaynachweis = config.contains(DisplayConfig.NACHWEISE) ? "" : "none";
        displayarbeitstechniken = config.contains(DisplayConfig.ATECHNIKEN) ? "" : "none";
    }
}
