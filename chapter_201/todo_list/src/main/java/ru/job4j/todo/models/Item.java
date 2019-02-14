package ru.job4j.todo.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Item
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-11
 */
public class Item {

    /**
     * Item id
     *
     */
    private long id;

    /**
     * Item description
     *
     */
    private String desc;

    /**
     * Item created
     *
     */
    @JsonFormat(pattern = "dd-MM-yyy HH:mm", timezone = "GMT+3")
    private Timestamp created;

    /**
     * Item done
     *
     */
    private boolean done;

    /**
     * Item get id
     *
     * @return item id
     */
    public long getId() {
        return id;
    }

    /**
     * Item set id
     *
     * @param id item id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get Item description
     *
     * @return item description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Item set description
     *
     * @param desc description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Get created Item
     *
     * @return item created
     */
    public Timestamp getCreated() {
        return created;
    }

    /**
     * Set created item
     *
     * @param created created
     */
    public void setCreated(Timestamp created) {
        this.created = created;
    }

    /**
     * Item is done
     *
     * @return true if done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Set done item
     *
     * @param done done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * String representation of an object.
     * @return representation of an object.
     */
    @Override
    public String toString() {
        return String.format("Item{id=%d, desc=%s, created=%s, done=%s}", id, desc, created, done);
    }

    /**
     * Equals between objects.
     * @param o other object.
     * @return true if the same object.
     */
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
                && Objects.equals(created, item.created);
    }

    /**
     * Item hashcode.
     * @return hashcode account.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, created);
    }
}
