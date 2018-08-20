package ru.job4j.control;

import org.junit.*;
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Store collections.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 20.08.2018.
 */
public class StoreTest {

    /**
     * Contains previous list.
     *
     */
    private List<Store.User> previous;

    /**
     * Contains current list.
     *
     */
    private List<Store.User> current;


    /**
     * Contains store.
     */
    private Store store;

    /**
     * Create store for test.
     *
     */
    @Before
    public void createTestObject() {
        this.store = new Store();
        this.previous = new ArrayList<>();
        this.current = new ArrayList<>();
    }

    @Test
    public void whenAdd4UserThenAddAndDeleteCurrentList() {
        previous.add(new Store.User(123, "Andrey"));
        previous.add(new Store.User(345, "Dima"));
        previous.add(new Store.User(766, "Alex"));
        previous.add(new Store.User(287, "Victor"));
        current.addAll(previous);
        current.add(new Store.User(777, "Petr"));
        current.add(new Store.User(821, "Gena"));
        current.remove(1);
        current.remove(1);
        current.set(1, new Store.User(287, "Pavel"));
        Info info = store.diff(previous, current);
        assertThat(info.toString(), is("Добавлено:2. Удалено:2. Изменено:1."));
    }

    @Test
    public void whenAdd3UsersThenAdd2UserAndChange2UsersCurrentList() {
        previous.add(new Store.User(1, "Kate"));
        previous.add(new Store.User(2, "Olga"));
        previous.add(new Store.User(3, "Jane"));
        current.addAll(previous);
        current.add(new Store.User(4, "Tina"));
        current.add(new Store.User(5, "Ambra"));
        current.set(1, new Store.User(2, "Masha"));
        current.set(2, new Store.User(3, "Lena"));
        Info info = store.diff(previous, current);
        assertThat(info.toString(), is("Добавлено:2. Удалено:0. Изменено:2."));
    }

    @Test
    public void whenAdd3UsersThenDeleteAllinCurrentList() {
        previous.add(new Store.User(11, "Kolya"));
        previous.add(new Store.User(22, "Fil"));
        previous.add(new Store.User(33, "Kale"));
        current.addAll(previous);
        current.clear();
        Info info = store.diff(previous, current);
        assertThat(info.toString(), is("Добавлено:0. Удалено:3. Изменено:0."));
    }
}