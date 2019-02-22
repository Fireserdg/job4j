package ru.job4j.car.models;

import javax.persistence.*;

/**
 * Abstract base entity.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    protected AbstractBaseEntity() {

    }

    protected AbstractBaseEntity(String name) {
        this.name = name;
    }

    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s)", getClass().getName(), id);
    }
}
