package ru.job4j.crud.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Role test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 12.12.18
 */
public class RoleTest {

    @Test
    public void whenGetRoleThenResult() {
        final Role[] values = Role.values();
        assertThat(values.length, is(3));
        assertThat(Role.values()[0], is(Role.ADMIN));
        assertThat(Role.values()[1], is(Role.USER));
        assertThat(Role.values()[2], is(Role.GUEST));
    }

}