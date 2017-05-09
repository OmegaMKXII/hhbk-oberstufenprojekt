package de.hhbk.wizardpdfgen.model.generator;

import com.itextpdf.text.DocumentException;
import de.hhbk.wizardpdfgen.model.enums.DisplayConfig;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by x1n4u on 5/8/17.
 */
public class Document {
    Connection con;
    Set<DisplayConfig> config = new HashSet<>();

    List<Fach> fachs = new ArrayList<>();
    String beruf, bildungsgangleitung, unterrichtsform, abteilung, anrede;
    Integer jahr;

    public Document(Set<DisplayConfig> config) {
        this.config = config;
    }

    private PreparedStatement queryData(Connection con, String beruf, int jahr) throws SQLException {
        PreparedStatement statement = con.prepareStatement("select f.fid, lf.lfid, ls.lsid, f.bezeichnung, l.lehrername, l.geschlecht, u.uformname , a.abteilungsname, lf.bezeichnung, ls.lsnr, ls.name, ls.ustunden, ls.lsid, ls.szenario, ls.handlungsprodukt, ls.kompetenzen, ls.inhalte, ls.umaterial, ls.organisation, ls.arbeitstechnik  from tbl_fach as f " +
                "join tbl_beruffach as bf on bf.id_fach = f.fid " +
                "join tbl_lernfeld as lf on lf.id_beruffach = bf.bfid " +
                "join tbl_lernsituation as ls on ls.id_lernfeld = lf.lfid " +
                "join tbl_uformberuf as ub on ub.ubid = bf.id_uformberuf " +
                "join tbl_uform as u on u.uid = ub.id_uform " +
                "join tbl_beruf as b on b.bid = ub.id_beruf " +
                "join tbl_abteilung as a on a.aid = b.id_abteilung " +
                "join tbl_lehrer as l on l.lid = b.id_bleitung " +
                "where bf.Jahr = ? and b.berufname = ? " +
                "order by f.lernbereich, f.fid, lf.lfid, ls.lsid;");
        statement.setString(2,beruf);
        statement.setInt(1, jahr);
        return statement;
    }

    public void getDocumentData(Connection con, String beruf, int jahr) throws SQLException {
        ResultSet set = queryData(con, beruf, jahr).executeQuery();
        set.next();
        abteilung = set.getString("a.abteilungsname");
        bildungsgangleitung = set.getString("l.lehrername");
        anrede = set.getString("l.geschlecht").equals("M") ? "Herr" : "Frau";
        unterrichtsform = set.getString("u.uformname");
        do {
            Fach fach = new Fach(set);
            this.fachs.add(fach);
            do {
                Lernfeld lernfeld = new Lernfeld(set);
                fach.add(lernfeld);
                do {
                    lernfeld.add(new Lernsituation(config, set));
                } while (lernfeld.next());
            } while (fach.next());
        } while(set.next());
        set.close();
    }

    private void generate(File file) throws IOException, DocumentException, SQLException, com.lowagie.text.DocumentException {
        Generator.toPDF(this, file);
    }

    public void generate(File file, Connection con, String beruf, int jahr) throws SQLException, com.lowagie.text.DocumentException, DocumentException, IOException {
        getDocumentData(con, beruf, jahr);
        generate(file);
    }


    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/didakt","root", "password");

        HashSet<DisplayConfig> config = new HashSet();
        config.add(DisplayConfig.ATECHNIKEN);
        config.add(DisplayConfig.INHALTE);

        Document doc = new Document(config);
        doc.generate(new File("foo.pdf"), con, "Fachinformatiker/in Anwendungsentwicklung", 1);
        con.close();
    }

}
