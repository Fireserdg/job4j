package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/** Test class for check Iterator for Even values.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 06.08.2018.
 */
public class EventItTest {

    /**
     * Contains Iterator.
     */
    private Iterator<Integer> it;

    /**
     * Declare variables Iterator.
     */
    @Before
    public void setUp() {
        it = new EventIt(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldReturnEvenNumbersSequentially() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(6));
    }

    @Test
    public void  shouldReturnFalseIfNoAnyEvenNumbers() {
        it = new EventIt(new int[]{1});
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void allNumbersAreEven() {
        it = new EventIt(new int[]{2, 4, 6, 8});
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(6));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
    }
}