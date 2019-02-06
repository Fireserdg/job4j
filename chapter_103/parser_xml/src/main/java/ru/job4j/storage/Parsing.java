package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *  Parsing XML file.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class Parsing {

    /**
     * Contains result.
     *
     */
    private long result = 0;

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(Parsing.class);

    /**
     * Get result after parse.
     *
     * @param filename name of XML file.
     * @return sum value of attribute from XML file.
     */
    public long getResult(String filename) {
        parseXml(filename);
        return this.result;
    }

    /**
     * Parse XML file.
     *
     * @param filename name of XML file.
     */
    public void parseXml(String filename) {
        try (InputStream is = new FileInputStream(filename)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(is, getHandler());
            LOG.info("Parsing was successful");
        } catch (SAXException | ParserConfigurationException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Get Handler for parse.
     *
     * @return Default Handler.
     */
    private DefaultHandler getHandler() {
        return new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes)  {
                if (qName.equals("entry")) {
                    result += Integer.parseInt(attributes.getValue("field"));
                }
            }
        };
    }
}