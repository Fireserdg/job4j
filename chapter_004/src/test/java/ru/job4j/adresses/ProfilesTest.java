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
        Profile fourth = new Profile(new Address("Anapa", "Nikolaevskay str", 5, 44));
        Profile fifth = new Profile(new Address("Omsk", "Pushkina str", 4, 100));
        profiles = Arrays.asList(first, second, third, fourth, fifth);
    }

    @Test
    public void whenGetListOfAddressFromListOfProfiles() {
        List<Address> list = new Profiles().collect(profiles);
        assertThat(list.size(), is(5));
        assertThat(list.get(0).getHome(), is(2));
        assertThat(list.get(1).getCity(), is("Omsk"));
        assertThat(list.get(2).getApartment(), is(22));
        assertThat(list.get(3).getStreet(), is("Nikolaevskay str"));
    }

    @Test
    public void whenGetListOfAddressSortedAndDistinctFromListOfProfiles() {
        List<Address> list = new Profiles().collectDistinct(profiles);
        assertThat(list.size(), is(4));
        assertThat(list.get(0).getCity(), is("Anapa"));
        assertThat(list.get(list.size() - 1).getCity(), is("Tver"));
    }

    @Test
    public void whenUseEqualsAndHashcodeAndToStringForAddress() {
        List<Address> list = new Profiles().collect(profiles);
        assertThat(list.get(0).equals(list.get(1)), is(false));
        Address address = list.get(4);
        assertThat(list.get(1).equals(address), is(true));
        assertThat(list.get(1).equals(list.get(2)), is(false));
        assertThat(list.get(0).hashCode()
                == list.get(1).hashCode(), is(false));
        assertThat(list.get(1).hashCode()
                == list.get(4).hashCode(), is(true));
        address = null;
        assertThat(list.get(1).equals(address), is(false));
        address = list.get(1);
        assertThat(list.get(1).equals(address), is(true));
        assertThat(list.get(0).toString(), is(
                "Address{city=Moscow, street=Lenina str, home=2, apartment=45"));
    }
}