package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тестовый класс проверяющий поведение пользователя.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class StartUITest {
    private final Tracker tracker = new Tracker();

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUserUpdateThenTrackerHasUpdatedValue() {
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", item.getId(), "test names", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test names"));
    }

    @Test
    public void whenUserDeleteThenTrackerHasDeleteValue() {
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test1 name", "desc1"));
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is(item1.getName()));
    }

    @Test
    public void whenUserFindItemByIdThenTrackerShowIdItem() {
       Item item = tracker.add(new Item("test name", "desc"));
       Input input = new StubInput(new String[]{"4", item.getId(), "6"});
       new StartUI(input, tracker).init();
       assertThat(tracker.findAll()[0].getId(), is(item.getId()));
    }

    @Test
    public void whenUserFindItemByIdThenTrackerShowNameItem() {
        Item item = tracker.add(new Item("test name", "test desc"));
        Input input = new StubInput(new String[]{"5", item.getName(), "6"});
        String name = item.getName();
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(name)[0].getName(), is("test name"));
    }
    @Test
    public void whenUserHasSelectedShowByAllItems() {
        Item item = tracker.add(new Item("test name", "test desc"));
        Item item1 = tracker.add(new Item("test1 name", "test1 desc"));
        Item item2 = tracker.add(new Item("test2 name", "test2 name"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat((tracker.findAll()), is(new Item[]{item, item1, item2}));
    }
}