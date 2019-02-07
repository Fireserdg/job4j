package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class ConverterTest {
    /**
     * Test RubleToDollar.
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        double result = converter.rubleToDollar(60);
        assertThat(result, is(1D));
    }
    /**
     * Test RubleToEuro.
     */
    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        double result = converter.rubleToEuro(70);
        assertThat(result, is(1D));
    }
    /**
     * Test EuroToRuble.
     */
    @Test
    public void when1EuroToRubleThen70() {
        Converter converter = new Converter();
        double result = converter.euroToRuble(70);
        assertThat(result, is(1D));
    }
    /**
     * Test DollarToRuble.
     */
    @Test
    public void when1DollarToRubleThen60() {
        Converter converter = new Converter();
        double result = converter.dollarToRuble(60);
        assertThat(result, is(1D));
    }
}
