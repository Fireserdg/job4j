package ru.job4j.switcher;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Switcher.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 12.09.2018.
 */
public class SwitcherTest {

    @Test
    public void whenAddElementInSwitcherForOneCycles() throws InterruptedException {
        Switcher switcher = new Switcher(1);
        Thread one = new Thread(switcher);
        one.start();
        one.join();
        String result = "11111111112222222222";
        assertThat(switcher.getString(), is(result));
    }

    @Test
    public void whenAddElementInSwitcherFor2Cycles() throws InterruptedException {
        Switcher switcher = new Switcher(2);
        Thread two = new Thread(switcher);
        two.start();
        two.join();
        String result = "1111111111222222222211111111112222222222";
        assertThat(switcher.getString(), is(result));
    }
}