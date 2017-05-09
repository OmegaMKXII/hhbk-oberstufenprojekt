package de.hhbk.wizardpdfgen.model.generator;

import de.hhbk.wizardpdfgen.model.enums.DisplayConfig;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by x1n4u on 5/8/17.
 */
public class Lernsituation extends Config{
    Integer lsnr, lsid, ustunden;
    String name, szenario, handlungsprodukt, kompentenzen, inhalte, umaterial, organisation, arbeitstechniken;

    public Lernsituation(Set<DisplayConfig> config,ResultSet set) throws SQLException {
        super(config);
        this.lsnr = set.getInt("ls.lsnr");
        this.name = set.getString("ls.name");
        this.ustunden = set.getInt("ls.ustunden");
        this.lsid = set.getInt("ls.lsid");
        this.szenario = set.getString("ls.szenario");
        this.handlungsprodukt = set.getString("ls.handlungsprodukt");
        this.kompentenzen = set.getString("ls.kompetenzen");
        this.inhalte = set.getString("ls.inhalte");
        this.umaterial = set.getString("ls.umaterial");
        this.organisation = set.getString("ls.organisation");
        this.arbeitstechniken = set.getString("ls.arbeitstechnik");
    }

    public Lernsituation(ResultSet set) throws SQLException {
        this(new HashSet(), set);
    }
}
