package ru.job4j.car.models;

import javax.persistence.*;
import java.util.Objects;

/**
 * Abstract base entity.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
@MappedSuperclass
public abstract class AbstractBaseEntity {

    /**
     * Entity id
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Entity name
     *
     */
    private String name;

    /**
     * Default constructor
     *
     */
    protected AbstractBaseEntity() {

    }

    /**
     * Constructor with param is name
     *
     * @param name name
     */
    protected AbstractBaseEntity(String name) {
        this.name = name;
    }

    /**
     * Constructor with param is id
     *
     * @param id id
     */
    protected AbstractBaseEntity(Long id) {
        this.id = id;
    }

    /**
     * Get entity id
     *
     * @return entity id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set entity id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get entity name
     *
     * @return entity name
     */
    public String getName() {
        return name;
    }

    /**
     * Set entity name
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
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
        AbstractBaseEntity that = (AbstractBaseEntity) o;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name);
    }

    /**
     * Get hashcode for objects.
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    /**
     * String representation of an object.
     *
     * @return representation of an object.
     */
    @Override
    public String toString() {
        return  String.format("%s{id=%s, name=%s}",
                this.getClass().getSimpleName(), getId(), getName());
    }
}
