package ru.job4j.adresses;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Profiles
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 02.02.19
 */
public class Profiles {

    /**
     * Get list of addresses from list of profile.
     *
     * @param profiles list of profile.
     * @return list of address.
     */
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress).collect(Collectors.toList());
    }

    /**
     *  Get list of addresses from list of profile with sorted
     *  and distinct.
     *
     * @param profiles list of profile.
     * @return list of address.
     */
    public List<Address> collectDistinct(List<Profile> profiles) {
        return profiles.stream().map(Profile::getAddress)
                .sorted(Comparator.comparing(Address::getCity))
                .distinct().collect(Collectors.toList());
    }
}