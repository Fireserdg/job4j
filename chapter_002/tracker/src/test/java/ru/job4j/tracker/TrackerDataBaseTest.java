package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.connection.ConnectionInit;
import ru.job4j.item.Item;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 *  Test tracker database with rollback
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 04.02.19
 */
public class TrackerDataBaseTest {

    @Test
    public void whenAddItemByTracker() throws SQLException {
        try (TrackerDataBase tracker = new TrackerDataBase(ConnectionInit.getProperties(),
                ConnectionInit.creteRollback(ConnectionInit.init()))) {
            Item first = new Item("test1", "testDescription1", System.currentTimeMillis());
            Item second = new Item("test2", "testDescription2", System.currentTimeMillis());
            tracker.add(first);
            tracker.add(second);
            assertThat(tracker.findAll().get(0).getName(), is("test1"));
            assertThat(tracker.findAll().get(1).getDescription(), is("testDescription2"));
        }
    }

    @Test
    public void whenReplaceTheItem() throws SQLException {
        try (TrackerDataBase tracker = new TrackerDataBase(ConnectionInit.getProperties(),
                ConnectionInit.creteRollback(ConnectionInit.init()))) {
            Item first = new Item("test", "testDescription", System.currentTimeMillis());
            Item second = new Item("test1", "testDescription1", System.currentTimeMillis());
            Item third = new Item("test2", "testDescription2", System.currentTimeMillis());
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            Item currentItem = new Item("new_name", "new_desc");
            String secondId = tracker.findAll().get(1).getId();
            boolean result = tracker.replace(id -> id.equals(secondId), currentItem);
            assertThat(result, is(true));
            assertThat(tracker.findAll().get(1).getName(), is("new_name"));
        }
    }

    @Test
    public void whenDeleteFromTrackerBD() throws SQLException {
        try (TrackerDataBase tracker = new TrackerDataBase(ConnectionInit.getProperties(),
                ConnectionInit.creteRollback(ConnectionInit.init()))) {
            Item first = new Item("test1", "testDescription1", System.currentTimeMillis());
            Item second = new Item("test2", "testDescription2", System.currentTimeMillis());
            Item third = new Item("test3", "testDescription3", System.currentTimeMillis());
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            assertThat(tracker.findAll().get(1).getName(), is("test2"));
            boolean result = tracker.delete(id -> id.equals(tracker.findAll().get(1).getId()));
            assertThat(result, is(true));
            assertThat(tracker.findAll().get(1).getName(), is("test3"));
        }
    }

    @Test
    public void whenFindByIdToTrackerBD() throws SQLException {
        try (TrackerDataBase tracker = new TrackerDataBase(ConnectionInit.getProperties(),
                ConnectionInit.creteRollback(ConnectionInit.init()))) {
            long currentTime = System.currentTimeMillis();
            Item first = new Item("test1", "testDescription1", System.currentTimeMillis());
            Item second = new Item("test2", "testDescription2", currentTime);
            tracker.add(first);
            tracker.add(second);
            Item result = tracker.findById(id -> id.equals(tracker.findAll().get(1).getId()));
            assertThat(result.getName(), is("test2"));
            assertThat(new Timestamp(result.getCreate()).toString(), is(new Timestamp(currentTime).toString()));
        }
    }

    @Test
    public void whenFindByNameThenGetResult() throws SQLException {
        try (TrackerDataBase tracker = new TrackerDataBase(ConnectionInit.getProperties(),
                ConnectionInit.creteRollback(ConnectionInit.init()))) {
            Item first = new Item("item1", "testDescription1", System.currentTimeMillis());
            Item second = new Item("item2", "testDescription2", System.currentTimeMillis());
            Item third = new Item("item1", "testDescription3", System.currentTimeMillis());
            tracker.add(first);
            tracker.add(second);
            tracker.add(third);
            List<Item> result = tracker.findByName(key -> key.equals("item1"));
            assertThat(result.get(0).getName(), is("item1"));
            assertThat(result.get(1).getName(), is("item1"));
        }
    }

    @Test
    public void whenAddCommentsByItemThenGetResult() throws SQLException {
        try (TrackerDataBase tracker = new TrackerDataBase(ConnectionInit.getProperties(),
                ConnectionInit.creteRollback(ConnectionInit.init()))) {
            Item first = new Item("item1", "testDescription1", System.currentTimeMillis());
            Item second = new Item("item2", "testDescription2", System.currentTimeMillis());
            tracker.add(first);
            tracker.add(second);
            Item item = tracker.findById(id -> id.equals(tracker.findAll().get(1).getId()));
            String commentOne = "Comment 1";
            String commentTwo = "Comment 2";
            boolean result = tracker.addComment(id -> id.equals(item.getId()), commentOne);
            tracker.addComment(id -> id.equals(item.getId()), commentTwo);
            List<String> expected = Arrays.asList("Comment 1", "Comment 2");
            List<String> resultComm = tracker.findAll().get(1).getComments();
            assertThat(result, is(true));
            assertThat(resultComm, is(expected));
        }
    }
}