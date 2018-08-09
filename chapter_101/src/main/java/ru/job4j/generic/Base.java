package ru.job4j.generic;

/**
 * Abstract class for model Storage.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public abstract class Base {

    /**
     * Contains id.
     *
     */
    private final String id;

    /**
     * Constructor Base.
     *
     * @param id new id.
     */
    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
