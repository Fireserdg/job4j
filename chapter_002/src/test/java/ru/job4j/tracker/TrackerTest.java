package ru.job4j.tracker;

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

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription1", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    @Test
    public void whenDeleteItemThenTrackerHasOneItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.add(item1);
        tracker.delete(item.getId());
        assertThat(tracker.findAll()[0].getName(), is("test2"));
    }

    @Test
    public void whenNeedAllFindItemsThenAllItems() {
        Tracker tracker = new Tracker();
        Item[] allItems = new Item[3];
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        allItems [0] = item;
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.add(item1);
        allItems [1] = item1;
        Item result = tracker.findById(item.getId());
        assertThat(result, is(allItems[0]));
        result = tracker.findById(item1.getId());
        assertThat(result, is(allItems[1]));
    }

    @Test
    public void whenFindByNameItemThenTrackerCanFindByNameItems() {
        Tracker tracker = new Tracker();
        Item[] allItems = new Item[2];
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        allItems[0] = item1;
        tracker.add(item1);
        Item item2 = new Item("test3", "testDescription3", 12345L);
        tracker.add(item2);
        allItems[1] = item2;
        assertThat(tracker.findByName("test2")[0].getName(), is(allItems[0].getName()));
    }

    @Test
    public void whenFindByIdItemThenTrackerCanFindById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2", 1234L);
        tracker.add(item1);
        assertThat(tracker.findById(item1.getId()), is(item1));
    }
}