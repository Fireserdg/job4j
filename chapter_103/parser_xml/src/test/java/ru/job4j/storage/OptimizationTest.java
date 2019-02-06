package ru.job4j.storage;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 *  Test convert XML.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
public class OptimizationTest {

    @After
    public void deleteValueFromDb() throws SQLException {
        Config config = new Config();
        config.loadConfig("store.properties");
        try (Connection conn = DriverManager.getConnection(config.getValue("st.url"));
            Statement statement = conn.createStatement()) {
            statement.executeUpdate(config.getValue("st.deleteAll"));
        }
    }

    @Ignore
    public void whenAddManyValuesAndWorkingTimeLessThan5minutes() throws SQLException {
        long start = System.currentTimeMillis();
        Optimization op = new Optimization("store.properties");
        op.addValueDB(1_000_000);
        op.createXMLFromDB();
        op.convert();
        long finish = System.currentTimeMillis();
        long result = (start - finish) / 1000;
        int countValue;
        Config config = new Config();
        config.loadConfig("store.properties");
        try (Connection conn = DriverManager.getConnection(config.getValue("st.url"))) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet res = st.executeQuery(config.getValue("st.count"))) {
                    countValue = res.getInt(1);
                }
            }
        }
        assertTrue(result < 300);
        assertThat(countValue, is(1_000_000));
    }

    @Test
    public void whenAdd100ValueToStorageSQLThenConvertToXMLAndGetSumValues() {
        Optimization op = new Optimization("store.properties");
        op.addValueDB(10);
        op.createXMLFromDB();
        op.convert();
        long result = op.parseResult();
        long expected = 55;
        assertThat(result, is(expected));
    }

    @Test
    public void whenAdd5ValueInStorageSQLThenGetFirstField() throws SQLException {
        Config config = new Config();
        config.loadConfig("store.properties");
        StorageSQL storage = new StorageSQL(config);
        storage.generate(5);
        List<Integer> result = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(config.getValue("st.url"))) {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery(config.getValue("st.selectThird"))) {
                    while (rs.next()) {
                        result.add(rs.getInt("field"));
                    }
                }
            }
        }
        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertThat(result, is(expected));
    }

    @Test
    public void whenCreateListEntry() {
        Entries entries = new Entries();
        Entry first = new Entry();
        Entry second = new Entry();
        first.setField(1);
        second.setField(2);
        List<Entry> list = new ArrayList<>();
        list.add(first);
        list.add(second);
        entries.setEntry(list);
        List<Entry> result = entries.getEntry();
        List<Entry> expected = Arrays.asList(first, second);
        assertThat(result, is(expected));
        assertThat(result.get(0).getField(), is(1));
        assertThat(result.get(1).getField(), is(2));
    }


}