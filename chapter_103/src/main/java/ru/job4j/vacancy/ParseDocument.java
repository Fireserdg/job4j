package ru.job4j.vacancy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  Parse html document from http://www.sql.ru/forum/job-offers/
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class ParseDocument {

    /**
     * Contains html pages
     *
     */
    private Document doc;

    /**
     * Contains configuration
     *
     */
    private Config conf;

    /**
     * Contains start date and time.
     *
     */
    private LocalDateTime minDate;

    /**
     * Contains list of items.
     *
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Contains value for move next page
     *
     */
    private int value = 2;

    /**
     * Contains logger
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(ParseDocument.class);

    /**
     * Constructor
     *
     * @param config configuration object
     * @param minDate  start date and time
     */
    public ParseDocument(Config config, LocalDateTime minDate) {
        this.conf = config;
        this.minDate = minDate;
        this.init();
    }

    /**
     * Start parse document.
     *
     */
    private void init() {
        try {
            this.doc = Jsoup.connect(conf.getValue("sql.url")).get();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        parseVacancy();
    }

    /**
     * Add vacancy from html document
     *
     * @param document html pages
     * @return parse result
     */
    private boolean addVacancy(Document document) {
        boolean result = false;
        LocalDateTime local;
        Elements row = document.select(conf.getValue("sql.forumTable"));
        for (Element element : row) {
            String vac = element.select(conf.getValue("sql.queryRow")).text();
            String url = element.select(conf.getValue("sql.queryRow")).attr("href");
            String date = element.child(5).text();
            if (vac.matches(conf.getValue("conJava.regexp")) && !vac.matches(conf.getValue("conJavaScript"))) {
                local = new ParseDate(date, this.conf).convertValue();
                if (Timestamp.valueOf(local).getTime() > Timestamp.valueOf(minDate).getTime()) {
                    items.add(0, new Item(vac, url, Timestamp.valueOf(local)));
                } else {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Parse vacancy
     *
     */
    private void parseVacancy() {
        while (!addVacancy(this.doc)) {
            try {
                this.doc = Jsoup.connect(String.format(conf.getValue("sql.urlNext"), this.value++)).get();
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /** Get list of vacancy
     *
     *
     * @return list of vacancy
     */
    public List<Item> getItems() {
        return this.items;
    }
}