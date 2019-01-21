package ru.job4j.vacancy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Parse date.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class ParseDate {

    /**
     * Contains configuration.
     *
     */
    private final Config conf;

    /**
     * Contains date.
     *
     */
    private final String date;

    /**
     * Contains local date and time.
     *
     */
    private LocalDateTime local;

    /**
     * Constructor
     *
     * @param date date
     * @param conf configuration object
     */
    public ParseDate(final String date, final Config conf) {
        this.date = date;
        this.conf = conf;
    }

    /**
     * Convert value.
     *
     * @return local date and time.
     */
    public LocalDateTime convertValue() {
        if (date.matches(conf.getValue("dataLast.regexp"))) {
            this.local = convertLastDate(this.date);
        }  else {
            local = convertCurrentValue(this.date);
        }
        return this.local;
    }

    /**
     * Convert last date
     *
     * @param date last date
     * @return local date and time
     */
    private LocalDateTime convertLastDate(String date) {
        date = date.replace("май", "мая");
        if (date.matches(conf.getValue("dateOneNumber.regexp"))) {
            this.local = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(
                    "d MMM yy, HH:mm").withLocale(new Locale("ru")));
        } else {
            this.local = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(
                    "dd MMM yy, HH:mm").withLocale(new Locale("ru")));
        }
        return this.local;
    }

    /**
     * Convert current date (Today and Yesterday)
     *
     * @param date date
     * @return local date and time
     */
    private LocalDateTime convertCurrentValue(String date) {
        if (date.matches(conf.getValue("dateToday.regexp"))) {
            date = date.replace("сегодня", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            this.local = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd, HH:mm").withLocale(new Locale("ru")));
        } else {
            date = date.replace("вчера", LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yy")));
            local = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(
                    "d MMM yy, HH:mm").withLocale(new Locale("ru"))).minusDays(1);
        }
        return this.local;
    }
}