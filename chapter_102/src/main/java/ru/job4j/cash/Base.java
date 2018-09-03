package ru.job4j.cash;

import java.util.Objects;

/**
 * Base model.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 03.09.2018.
 */
public class Base {

    /**
     * Contains model id.
     *
     */
    private final int id;

    /**
     * Contains model version.
     *
     */
    private int version;

    /**
     * Contains name.
     *
     */
    private String name;

    /**
     * Constructor.
     *
     * @param id id model.
     * @param name name model.
     */
    public Base(final int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get id.
     *
     * @return id.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Get version.
     *
     * @return version.
     */
    public int getVersion() {
        return this.version;
    }

    /**
     * Increment id.
     *
     */
    public void increment() {
        this.version++;
    }

    @Override
    public String toString() {
        return String.format("Base: id=%d, version=%s, name=%s.",
                this.id, this.version, this.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return id == base.id && version == base.version
                && Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 37 + this.id;
        result = result * 37 + this.version;
        result = result * 37 + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }
}
