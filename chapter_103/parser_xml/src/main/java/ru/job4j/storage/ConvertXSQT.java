package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *  Convert XML file.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class ConvertXSQT {

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(ConvertXSQT.class);

    /**
     * Converter XML file to new format.
     *
     * @param source source file.
     * @param dest dest file.
     * @param scheme File-scheme for convert.
     */
    public void convert(File source, File dest, File scheme) {
        try (InputStream xml = new FileInputStream(source);
             InputStream xsl = new FileInputStream(scheme)) {
            StreamSource xmlSource = new StreamSource(xml);
            StreamSource schemeSource = new StreamSource(xsl);
            StreamResult xmlDest = new StreamResult(dest);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer(schemeSource);
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
            trans.transform(xmlSource, xmlDest);
            LOG.info("XML File conversion was successful");
        } catch (IOException | TransformerException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}