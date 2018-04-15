package ru.job4j.array;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 15.04.2018
 */
public class FindWordTest {
    @Test
    public void whenOneWordInOtherWordThenTrue() {
        FindWord word = new FindWord();
        boolean result = word.contains("Привет", "иве");
        assertThat(result, is(true));
    }
    @Test
    public void whenOneWordInOtherWordThenFalse() {
        FindWord word = new FindWord();
        boolean result = word.contains("Обезъяна", "биз");
        assertThat(result, is(false));
    }
    @Test
    public void whenOneWordBiggerThenOtherWordThenFalse() {
        FindWord word = new FindWord();
        boolean result = word.contains("Клад", "Кладем");
        assertThat(result, is(false));
    }
}