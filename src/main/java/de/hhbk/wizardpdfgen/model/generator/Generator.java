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

    private static Reader generateDocument(de.hhbk.wizardpdfgen.model.generator.Document document) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("src/main/resources/de/hhbk/wizardpdfgen/generator/report.mustache");

        StringWriter out = new StringWriter();
        StringWriter out2 = new StringWriter();
        mustache.execute(out, document).flush();

        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.parse(new StringReader(out.toString()), out2);

        (new FileWriter("foo.html")).write(out2.toString());

        return new StringReader(out2.toString());
    }

    public static void toPDF(de.hhbk.wizardpdfgen.model.generator.Document document, File file) throws IOException, DocumentException {
        Document doc = new Document();
        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(file));
        doc.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, doc,
                Generator.generateDocument(document));
        doc.close();
    }
}

