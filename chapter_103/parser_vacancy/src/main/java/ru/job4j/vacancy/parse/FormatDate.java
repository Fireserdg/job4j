package ru.job4j.vacancy.parse;


import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Map;

import static java.util.Map.entry;

/**
 * Format date for parse date.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 08.02.19
 */
public class FormatDate {

    /**
     * Text lookup for DateTimeFormatter.
     *
     */
    public static final Map<Long, String> NAME_MONTH = Map.ofEntries(
            entry(1L, "янв"), entry(2L, "фев"), entry(3L, "мар"),
            entry(4L, "апр"), entry(5L, "май"), entry(6L, "июн"),
            entry(7L, "июл"), entry(8L, "авг"), entry(9L, "сен"),
            entry(10L, "окт"), entry(11L, "ноя"), entry(12L, "дек"));

    /**
     * Format for date with two symbol in day.
     */
    public static final DateTimeFormatter FRM_TWO_DAYS = new DateTimeFormatterBuilder()
            .appendPattern("dd ")
            .appendText(ChronoField.MONTH_OF_YEAR, NAME_MONTH)
            .appendPattern(" yy,")
            .appendPattern(" HH:mm")
            .toFormatter();

    /**
     * Format for date with one symbol in day.
     */
    public static final DateTimeFormatter FRM_ONE_DAY = new DateTimeFormatterBuilder()
            .appendPattern("d ")
            .appendText(ChronoField.MONTH_OF_YEAR, NAME_MONTH)
            .appendPattern(" yy,")
            .appendPattern(" HH:mm")
            .toFormatter();
}