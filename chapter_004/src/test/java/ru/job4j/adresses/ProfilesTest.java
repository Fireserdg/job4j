package ru.job4j.adresses;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Profiles test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class ProfilesTest {

    /**
     * List of profiles.
     */
    private List<Profile> profiles;

    /**
     * Init profiles.
     */
    @Before
    public void initProfiles() {
        Profile first = new Profile(new Address("Moscow", "Lenina str", 2, 45));
        Profile second = new Profile(new Address("Omsk", "Pushkina str", 4, 100));
        Profile third = new Profile(new Address("Tver", "Gorky str", 3, 22));
        profiles = Arrays.asList(first, second, third);
    }

    @Test
    public void whenGetListOfAddressFromListOfProfiles() {
        List<Address> list = new Profiles().collect(profiles);
        assertThat(list.size(), is(3));
        assertThat(list.get(0).getHome(), is(2));
        assertThat(list.get(1).getCity(), is("Omsk"));
        assertThat(list.get(2).getApartment(), is(22));
        assertThat(list.get(0).getStreet(), is("Lenina str"));
    }
}