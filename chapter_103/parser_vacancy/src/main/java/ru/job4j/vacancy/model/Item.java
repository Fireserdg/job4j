package ru.job4j.vacancy.model;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *  Items for get value from vacancy.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.10.2018.
 */
public class Item {

    /**
     * Contains id
     *
     */
    private int id;

    /**
     * Contains vacancy
     *
     */
    private String vacancy;

    /**
     * Contains url
     *
     */
    private String url;

    /**
     * Contains create
     *
     *
     */
    private Timestamp create;

    /**
     * Constructor for items
     *
     * @param vacancy name of vacancy
     * @param url url of vacancy
     * @param create create vacancy
     */
    public Item(String vacancy, String url, Timestamp create) {
        this.vacancy = vacancy;
        this.url = url;
        this.create = create;
    }

    /**
     * Set vacancy id
     *
     * @param id item id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get vacancy id
     *
     * @return item id
     */
    public int getId() {
        return id;
    }

    /**
     * Get name of vacancy
     *
     * @return name of vacancy
     */
    public String getVacancy() {
        return vacancy;
    }

    /**
     * Get url vacancy
     *
     * @return url vacancy
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get date and time of vacancy
     *
     * @return date and time of vacancy
     */
    public Timestamp getCreate() {
        return create;
    }

    @Override
    public String toString() {
        return String.format("Vacancy: %s, url: %s, date: %s", vacancy, url, create);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return id == item.id
                && Objects.equals(url, item.url)
                && Objects.equals(create, item.create);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, create);
    }
}
