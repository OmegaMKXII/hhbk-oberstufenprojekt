package de.hhbk.wizardpdfgen.model.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import org.w3c.tidy.Tidy;

import java.io.*;
import java.nio.ByteBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by x1n4u on 5/5/17.
 */
public class Generator {

    List<Fach> fachs = new ArrayList<>();

    public Generator(ResultSet set) throws SQLException {
        while (set.next()) {
            this.fachs.add(new Fach(set))  ;
        }
    }

    public Reader generateDocument() throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("src/main/resources/de/hhbk/wizardpdfgen/generator/report.mustache");

        StringWriter out = new StringWriter();
        StringWriter out2 = new StringWriter();
        mustache.execute(out, this).flush();

        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.parse(new StringReader(out.toString()), out2);

        (new FileWriter("foo.html")).write(out2.toString());

        return new StringReader(out2.toString());
    }

    public void toPDF(File file) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                this.generateDocument());
        document.close();
    }

    static class Fach {
        public Fach(ResultSet set) throws SQLException {
            int fid = set.getInt("f.fid");
            this.description = set.getString("f.bezeichnung");
            this.lernfelds = new ArrayList<>();
            do {
                this.lernfelds.add(new Lernfeld(set))  ;
            } while (set.next() && set.getInt("f.fid") == fid );
        }
        String description;
        List<Lernfeld> lernfelds;
    }

    static class Lernfeld {
        public Lernfeld(ResultSet set) throws SQLException {
            this.lfid = set.getInt("lf.lfid");
            this.description = set.getString("lf.bezeichnung");
            this.lernsituations = new ArrayList<>();
            do {
                this.lernsituations.add(new Lernsituation(set))  ;
            } while (set.next() && set.getInt("lf.lfid") == this.lfid);
        }
        Integer lfid;
        String description;
        List<Lernsituation> lernsituations;
    }

    static class Lernsituation {
        public Lernsituation(ResultSet set) throws SQLException {
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

        Integer lsnr, lsid, ustunden;
        String name;
        String szenario, handlungsprodukt, kompentenzen, inhalte, umaterial, organisation, arbeitstechniken;
    }

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://172.17.0.2:3306/didakt","root", "password");
        PreparedStatement stmt = con.prepareStatement("select f.fid, lf.lfid, ls.lsid, f.bezeichnung, lf.bezeichnung, ls.lsnr, ls.name, ls.ustunden, ls.lsid, ls.szenario, ls.handlungsprodukt, ls.kompetenzen, ls.inhalte, ls.umaterial, ls.organisation, ls.arbeitstechnik  from tbl_fach as f " +
                "join tbl_beruffach as bf on bf.id_fach = f.fid " +
                "join tbl_lernfeld as lf on lf.id_beruffach = bf.bfid " +
                "join tbl_lernsituation as ls on ls.id_lernfeld = lf.lfid " +
                "order by f.lernbereich, f.fid, lf.lfid, ls.lsid;");
        ResultSet set = stmt.executeQuery();

        Generator g = new Generator(set);
        con.close();
        set.close();
        g.toPDF(new File("foo.pdf"));
    }
}

