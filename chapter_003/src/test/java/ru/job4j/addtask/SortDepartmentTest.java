package ru.job4j.addtask;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test class Sort Department.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 05.08.2018.
 */
public class SortDepartmentTest {

    /**
     * Contains array list of units;
     *
     */
    private String[] list;

    /**
     * Contains object SortDepartment.
     *
     */
    private SortDepartment sort;

    /**
     * Variable initialization.
     */
    @Before
    public void createValue() {
        sort = new SortDepartment();
        list = new String[]{"K1\\SK1", "K1\\SK2", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2",
                "K2", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
    }

    @Test
    public void whenPassListAndGetListSortIncrease() {
        String[] result = sort.sortIncrease(list);
        String[] expected = {"K1", "K1\\SK1", "K1\\SK1\\SSK1", "K1\\SK1\\SSK2", "K1\\SK2",
                "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertThat(result, is(expected));
    }

    @Test
    public void whenPassListAndGetListSortDecrease() {
        String[] result = sort.sortDecrease(list);
        String[] expected = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1", "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        assertThat(result, is(expected));
    }
}