package ru.job4j.addtask;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 06.05.2018
 */
public class BoolandTest {
    @Test
    public void whatIsORTrue() {
        Booland run = new Booland();
        boolean result = run.whatIsOr(5, 17);
        boolean expected = true;
        assertThat(result, is(expected));
    }
    @Test
    public void whatIsORTrueTwo() {
        Booland run = new Booland();
        boolean result = run.whatIsOr(28, 105);
        boolean expected = true;
        assertThat(result, is(expected));
    }
    @Test
    public void whatIsORFalse() {
        Booland run = new Booland();
        boolean result = run.whatIsOr(7, 18);
        boolean expected = false;
        assertThat(result, is(expected));
    }
    @Test
    public void whatIsAnDTrue() {
        Booland run = new Booland();
        boolean result = run.whatIsAnd(6, 110);
        boolean expected = true;
        assertThat(result, is(expected));
    }
    @Test
    public void whatIsAnDFalse() {
        Booland run = new Booland();
        boolean result = run.whatIsAnd(6, 2);
        boolean expected = false;
        assertThat(result, is(expected));
    }
    @Test
    public void whatIsCheckOr() {
        Booland run = new Booland();
        boolean result = run.checkOr(true, false);
        boolean expected = true;
        assertThat(result, is(expected));
    }
    @Test
    public void whatIsCheckAnd() {
        Booland run = new Booland();
        boolean result = run.checkAnd(true, false);
        boolean expected = false;
        assertThat(result, is(expected));
    }
}