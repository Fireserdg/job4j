package ru.job4j.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;

/**
 * Optimization for storage data from database to XML file.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class Optimization {

    /**
     * Contains logger.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(Optimization.class);

    /**
     * Contains config.
     *
     */
    private final Config config = new Config();

    /**
     * Contains storage SQL.
     *
     */
    private final StorageSQL storageSQL;

    /**
     * Contains storage XML.
     *
     */
    private final StoreXML storeXML;

    /**
     * Contains Convert XSQT.
     *
     */
    private final ConvertXSQT convertXSQT;

    /**
     * Contains Parsing.
     *
     */
    private final Parsing parsing;

    /**
     * Constructor Optimization.
     *
     * @param properties properties file.
     */
    public Optimization(final String properties) {
        this.config.loadConfig(properties);
        this.storageSQL = new StorageSQL(this.config);
        this.storeXML = new StoreXML(new File(this.config.getValue("st.storeXML")));
        this.convertXSQT = new ConvertXSQT();
        this.parsing = new Parsing();
        LOG.info("Parameters initialized");
    }

    /**
     * Add value to database.
     *
     * @param value value.
     */
    public void addValueDB(final int value) {
        storageSQL.generate(value);
    }

    /**
     * Create XML file from database values.
     *
     */
    public void createXMLFromDB() {
        this.storeXML.save(this.storeXML.getEntryFromDB(this.config));
    }

    /**
     * Convert XML file.
     *
     */
    public void convert() {
        File source = new File(this.config.getValue("st.storeXML"));
        File scheme = new File(this.config.getValue("st.scheme"));
        File dest = new File(this.config.getValue("st.convertXML"));
        this.convertXSQT.convert(source, dest, scheme);
    }

    /**
     * Get result after parse XML file.
     *
     * @return sum values from XML file.
     */
    public long parseResult() {
        return this.parsing.getResult(this.config.getValue("st.convertXML"));
    }
}