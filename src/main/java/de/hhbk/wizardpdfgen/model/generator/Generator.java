package de.hhbk.wizardpdfgen.model.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;


/**
 * Created by x1n4u on 5/5/17.
 */
public class Generator {

    private static String generateDocument(de.hhbk.wizardpdfgen.model.generator.Document document) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("src/main/resources/de/hhbk/wizardpdfgen/generator/report.mustache");

        StringWriter out = new StringWriter();
        StringWriter out2 = new StringWriter();
        mustache.execute(out, document).flush();

        Tidy tidy = new Tidy();
        tidy.setXHTML(true);
        tidy.parse(new StringReader(out.toString()), out2);

        return out2.toString();
    }

    public static void toPDF(de.hhbk.wizardpdfgen.model.generator.Document document, File file) throws IOException, com.lowagie.text.DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(Generator.generateDocument(document));
        renderer.layout();
        renderer.createPDF(new FileOutputStream(file));
    }
}

