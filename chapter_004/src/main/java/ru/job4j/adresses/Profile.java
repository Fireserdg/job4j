package ru.job4j.adresses;


/**
 * Profile
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class Profile {

    /**
     * Address
     */
    private Address address;

    /**
     * Constructor profile.
     *
     * @param address address.
     */
    public Profile(Address address) {
        this.address = address;
    }

    /**
     * Get address
     *
     * @return address
     */
    public Address getAddress() {
        return address;
    }
}