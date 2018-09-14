package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тестовый класс для проверки работы методов класса Tracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */

public class TrackerTest {
    /**
     * Поле с объектом класса трекер.
     */
    private final Tracker tracker = new Tracker();
    /**
     * Поле с объектом Item.
     */
    private Item item;
    /**
     * Поле с объектом Item
     */
    private Item next;

    /**
     * Добавление новых заявок в трекер.
     */
    @Before
    public void createNewItems() {
        item = new Item("test1", "testDescription", 123L);
        next = new Item("test2", "testDescription2", 1234L);
        tracker.add(item);
        tracker.add(next);
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        assertThat(tracker.findAll().get(0), is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        tracker.replace(id -> id.equals(this.item.getId()), next);
        assertThat(tracker.findById(id -> id.equals(item.getId())).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemThenTrackerHasOneItems() {
        tracker.delete(id -> id.equals(item.getId()));
        assertThat(tracker.findAll().get(0).getName(), is("test2"));
    }

    @Test
    public void whenNeedAllFindItemsThenAllItems() {
        Item result = tracker.findById(id -> id.equals(item.getId()));
        assertThat(result, is(item));
        result = tracker.findById(id -> id.equals(next.getId()));
        assertThat(result, is(next));
    }

    @Test
    public void whenFindByNameItemThenTrackerCanFindByNameItems() {
        assertThat(tracker.findByName(key -> key.equals("test2")).get(0), is(next));
    }

    @Test
    public void whenFindByIdItemThenTrackerCanFindById() {
        assertThat(tracker.findById(id -> id.equals(next.getId())), is(next));
    }
}