package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Store XML
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class StoreXML {

    /**
     * Contains XML file.
     *
     */
    private final File target;

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);

    /**
     * Constructor StoreXML
     *
     * @param target XML file for write value from database.
     */
    public StoreXML(final File target) {
        this.target = target;
    }

    /**
     * Get list Entry.
     *
     * @param config config.
     * @return list of Entry.
     */
    public List<Entry> getEntryFromDB(Config config) {
        List<Entry> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(config.getValue("st.url"))) {
            LOG.info("Connect to the database successfully");
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(config.getValue("st.selectAll"))) {
                    while (rs.next()) {
                        Entry entry = new Entry();
                        entry.setField(rs.getInt("field"));
                        list.add(entry);
                    }
                }
            }
            LOG.info("The list of values created successfully");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Save value from database to XML file.
     *
     * @param list list of Entry.
     */
    public void save(List<Entry> list) {
        Entries en = new Entries();
        en.setEntry(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(en, target);
            LOG.info("Data from the database is saved to XML file");
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}