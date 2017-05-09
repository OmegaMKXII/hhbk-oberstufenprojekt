package de.hhbk.wizardpdfgen.model.generator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by x1n4u on 5/9/17.
 */
public class Nachweis {
    ResultSet set;

    Integer lnid;
    String art;

    public Nachweis(ResultSet set) throws SQLException {
        this.set = set;
        this.lnid = set.getInt("ln.lnid");
        this.art = set.getString("ln.art");
    }

    public boolean next() throws SQLException {
        return set.next() && set.getInt("ln.lnid") == this.lnid;
    }
}
