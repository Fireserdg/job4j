package ru.job4j.vacancy.parse;

import ru.job4j.vacancy.config.Config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import static ru.job4j.vacancy.parse.FormatDate.FRM_ONE_DAY;
import static ru.job4j.vacancy.parse.FormatDate.FRM_TWO_DAYS;


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
        return date.matches(conf.getValue("dataLast.regexp"))
                ? convertLastDate(this.date) : convertCurrentValue(this.date);
    }

    /**
     * Convert last date
     *
     * @param date last date
     * @return local date and time
     */
    private LocalDateTime convertLastDate(String date) {
        return date.matches(conf.getValue("dateOneNumber.regexp"))
                ? LocalDateTime.parse(date, FRM_ONE_DAY) : LocalDateTime.parse(date, FRM_TWO_DAYS);
    }

    /**
     * Convert current date (Today and Yesterday)
     *
     * @param date date
     * @return local date and time
     */

    private LocalDateTime convertCurrentValue(String date) {
        LocalDateTime local;
        if (date.matches(conf.getValue("dateToday.regexp"))) {
            date = date.replace("сегодня", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            local = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd, HH:mm").withLocale(new Locale("ru")));
        } else {
            date = date.replace("вчера", LocalDate.now().format(DateTimeFormatter.ofPattern("dd MM yy")));
            local = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(
                    "dd MM yy, HH:mm").withLocale(new Locale("ru"))).minusDays(1);
        }
        return local;
    }
}