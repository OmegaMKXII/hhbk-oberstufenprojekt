package de.hhbk.wizardpdfgen.model.generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x1n4u on 5/8/17.
 */
public class Lernfeld {
    ResultSet set;

    Integer lfid;
    String description;
    List<Lernsituation> lernsituations;
    public Lernfeld(ResultSet set) throws SQLException {
        this.set = set;
        this.lfid = set.getInt("lf.lfid");
        this.description = set.getString("lf.bezeichnung");
        this.lernsituations = new ArrayList<>();
    }

    public boolean next() throws SQLException {
        return set.next() && set.getInt("lf.lfid") == this.lfid;
    }

    public void add(Lernsituation lernsituation) {
        lernsituations.add(lernsituation);
    }
}
