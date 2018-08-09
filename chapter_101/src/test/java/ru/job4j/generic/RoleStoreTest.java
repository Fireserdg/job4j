package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class for Role Store.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public class RoleStoreTest {

    /**
     * Contains roles list.
     *
     */
    private Store<Role> roles;

    /**
     * Create roles list.
     *
     */
    @Before
    public void createRole() {
        roles = new RoleStore<>(3);
        Role roleOne = new Role("1", "Actor");
        Role roleTwo = new Role("2", "Dancer");
        Role roleThree = new Role("3", "Driver");
        roles.add(roleOne);
        roles.add(roleTwo);
        roles.add(roleThree);
    }

    @Test
    public void whenAddNewRoleInRoleStorage() {
        String result = roles.findById("1").getNameRole();
        assertThat(result, is("Actor"));
    }

    @Test
    public void whenFindByIdRoleAndResultTrue() {
        String result = roles.findById("2").getNameRole();
        assertThat(result, is("Dancer"));
    }

    @Test
    public void whenReplaceRoleInRoleStorage() {
        Role role = new Role("10", "Hunter");
        boolean result = roles.replace("1", role);
        assertThat(result, is(true));
        assertThat(roles.findById("10").getNameRole(), is("Hunter"));
    }

    @Test
    public void whenDeleteRoleFromRoleStorage() {
        boolean result = roles.delete("2");
        assertThat(result, is(true));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenFindByIdAndStoreHasNotIdShouldException() {
        roles.findById("13");
    }
}