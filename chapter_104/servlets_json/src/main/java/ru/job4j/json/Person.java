package ru.job4j.json;

import java.util.Objects;

/**
 * Model Person for Json servlet
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 15.12.18
 */
public class Person {

    /**
     * Contains id
     *
     */
    private String id;

    /**
     * Contains first name.
     *
     */
    private String firstName;

    /**
     * Contains last name.
     *
     */
    private String lastName;

    /**
     * Contains sex.
     *
     */
    private String sex;

    /**
     * Contains desc.
     *
     */
    private String desc;

    /**
     * Constructor.
     */
    public Person() {

    }

    /**
     * Get id person.
     *
     * @return person id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set person id.
     *
     * @param id id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get first name person.
     *
     * @return first name.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Set First name person.
     *
     * @param firstName first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name person.
     *
     * @return last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Set last name person.
     *
     * @param lastName last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get sex person.
     *
     * @return sex.
     */
    public String getSex() {
        return this.sex;
    }

    /**
     * Set sex person.
     *
     * @param sex sex.
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Get description for person.
     *
     * @return description.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Set description for person.
     *
     * @param desc description.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Equals for check two persons.
     * @param o Person
     * @return true if person the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(this.id, person.id)
                && Objects.equals(this.firstName, person.firstName)
                && Objects.equals(this.lastName, person.lastName)
                && Objects.equals(this.sex, person.sex)
                && Objects.equals(this.desc, person.desc);
    }

    /**
     * Get hashcode for person object.
     *
     * @return hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName,
                this.lastName, this.sex, this.desc);
    }

    /**
     * The object as a string.
     *
     * @return string.
     */
    @Override
    public String toString() {
        return String.format("Person {id=%s, firstName=%s, lastName=%s, sex=%s, desc=%s}",
                this.id, this.firstName, this.lastName, this.sex, this.desc);
    }
}