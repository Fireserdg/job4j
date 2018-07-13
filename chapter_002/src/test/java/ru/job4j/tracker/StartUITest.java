package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
    /**
     * Поле с объектом класса трекер.
     */
    private final Tracker tracker = new Tracker();
    /**
     * Поле содержит дефолтный вывод на консоль.
     */
    private final PrintStream stdout = System.out;
    /**
     * Поле содержит буфер результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Input input = new StubInput(new String[]{"1", "test name", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    @Test
    public void whenUserUpdateThenTrackerHasUpdatedValue() {
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"3", item.getId(), "test names", "desc", "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test names"));
    }

    @Test
    public void whenUserDeleteThenTrackerHasDeleteValue() {
        Item item = tracker.add(new Item("test name", "desc"));
        Item item1 = tracker.add(new Item("test1 name", "desc1"));
        Input input = new StubInput(new String[]{"4", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is(item1.getName()));
    }

    @Test
    public void whenUserFindItemByIdThenTrackerShowIdItem() {
       Item item = tracker.add(new Item("test name", "desc"));
       Input input = new StubInput(new String[]{"5", item.getId(), "y"});
       new StartUI(input, tracker).init();
       assertThat(tracker.findAll()[0].getId(), is(item.getId()));
    }

    @Test
    public void whenUserFindItemByNameThenTrackerShowNameItem() {
        Item item = tracker.add(new Item("test name", "test desc"));
        Input input = new StubInput(new String[]{"6", item.getName(), "y"});
        String name = item.getName();
        new StartUI(input, tracker).init();
        assertThat(tracker.findByName(name)[0].getName(), is("test name"));
    }

    @Test
    public void whenUserHasSelectedShowByAllItems() {
        Item item = tracker.add(new Item("test name", "test desc"));
        Item item1 = tracker.add(new Item("test1 name", "test1 desc"));
        Item item2 = tracker.add(new Item("test2 name", "test2 name"));
        Input input = new StubInput(new String[]{"2", "y"});
        new StartUI(input, tracker).init();
        assertThat((tracker.findAll()), is(new Item[]{item, item1, item2}));
    }

    /**
     * Метод реализации меню.
     * @return возвращает отображение меню Tracker.
     */
    public String consoleMenu() {
        return new StringBuilder().append("1. Add the new Item.")
                .append(System.lineSeparator())
                .append("2. Show all items.")
                .append(System.lineSeparator())
                .append("3. Edit the new Item.")
                .append(System.lineSeparator())
                .append("4. Delete the new Item.")
                .append(System.lineSeparator())
                .append("5. Find Item by Id .")
                .append(System.lineSeparator())
                .append("6. Find Item by name.")
                .append(System.lineSeparator())
                .append("7. Exit Program.")
                .append(System.lineSeparator())
                .toString();
    }

    /**
     * Метод заменяющий вывод на консоль на вывод в память.
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод возвращающий вывод на консоль.
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserHasSelectedShowByAllItemThenConsoleShowResult() {
        Item item1 = tracker.add(new Item("test out", "123"));
        Item item2 = tracker.add(new Item("test out2", "123"));
        Input input = new StubInput(new String[]{"2", "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(consoleMenu() + (new StringBuilder()
                                                    .append("----List of Items----")
                                                    .append(System.lineSeparator())
                                                    .append("ID item: " + item1.getId())
                                                    .append(" Name item: " + item1.getName())
                                                    .append(" Description item: " + item1.getDescription())
                                                    .append(System.lineSeparator())
                                                    .append("ID item: " + item2.getId())
                                                    .append(" Name item: " + item2.getName())
                                                    .append(" Description item: " + item2.getDescription())
                                                    .append(System.lineSeparator())
                                                )));
    }

    @Test
    public void whenUserFindItemByNameThenConsoleShowResult() {
        Item item = tracker.add(new Item("testID", "descID"));
        Input input = new StubInput(new String[]{"6", item.getName(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(consoleMenu() + (new StringBuilder()
                                                    .append("------------Find item by Name--------------")
                                                    .append(System.lineSeparator())
                                                    .append("ID item: " + item.getId())
                                                    .append(" Name item: " + item.getName())
                                                    .append(" Description item: " + item.getDescription())
                                                    .append(System.lineSeparator())
        )));
    }

    @Test
    public void whenUserDeleteItemThenConsoleShowResult() {
        Item item = tracker.add(new Item("test1", "desc1"));
        Input input = new StubInput(new String[]{"4", item.getId(), "y"});
        new StartUI(input, tracker).init();
        assertThat(new String(out.toByteArray()), is(consoleMenu() + (new StringBuilder()
                                                    .append("------------Delete Item--------------")
                                                    .append(System.lineSeparator())
                                                    .append("Item has deleted")
                                                    .append(System.lineSeparator())
        )));
    }
}