package ru.job4j.todo.models;

import java.sql.Timestamp;

/**
 * Item
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-11
 */
public class Item {

    private int id;

    private String desc;

    private Timestamp created;

    private boolean done;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", desc='" + desc + '\'' + ", created=" + created + ", done=" + done + '}';
    }
}
