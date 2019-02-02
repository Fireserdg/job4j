package ru.job4j.adresses;

import java.util.Objects;

/**
 * Address
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class Address {

    /**
     * Contains city.
     *
     */
    private String city;

    /**
     * Contains street.
     *
     */
    private String street;

    /**
     * Contains number of home.
     *
     */
    private int home;

    /**
     * Contains number of apartment.
     *
     */
    private int apartment;

    /**
     * Constructor
     *
     * @param city city
     * @param street street
     * @param home home
     * @param apartment apartment
     */
    public Address(String city, String street, int home, int apartment) {
        this.city = city;
        this.street = street;
        this.home = home;
        this.apartment = apartment;
    }

    /**
     * Get city.
     *
     * @return city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Get street.
     *
     * @return street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Get home number.
     *
     * @return home number
     */
    public int getHome() {
        return home;
    }

    /**
     * Get number apartment.
     *
     * @return number apartment.
     */
    public int getApartment() {
        return apartment;
    }

    /**
     * Equals between two objects
     *
     * @param o other object
     * @return true if other object the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return home == address.home && apartment == address.apartment
                && Objects.equals(city, address.city)
                && Objects.equals(street, address.street);
    }

    /**
     * Hashcode.
     *
     * @return value hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(city, street, home, apartment);
    }

    /**
     * Object representation as a string.
     *
     * @return Object representation.
     */
    @Override
    public String toString() {
        return String.format("Address{city=%s, street=%s, home=%d, apartment=%d",
                this.city, this.street, this.home, this.apartment);
    }
}
