package de.hhbk.wizardpdfgen.model.generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x1n4u on 5/8/17.
 */
public class Fach {
    Integer fid;
    String description;
    List<Lernfeld> lernfelds;
    public Fach(ResultSet set) throws SQLException {
        this.fid = set.getInt("f.fid");
        this.description = set.getString("f.bezeichnung");
        this.lernfelds = new ArrayList<>();
    }

    public boolean next(ResultSet set) throws SQLException {
        return set.next() && set.getInt("f.fid") == fid;
    }

    public void add(Lernfeld lernfeld) {
        lernfelds.add(lernfeld);
    }
}
