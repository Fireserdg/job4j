package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 0.1
 */
public class ArrayCharTest {
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }
    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }
    @Test
    public void whenStartWithPrefixThenTrueFoursimbol() {
        ArrayChar word = new ArrayChar("Cooker");
        boolean result = word.startWith("Cook");
        assertThat(result, is(true));
    }
    @Test
    public void whenStartWithPrefixThenFalseAfterThen() {
        ArrayChar word = new ArrayChar("Cooker");
        boolean result = word.startWith("Cooc");
        assertThat(result, is(false));
    }
}
