package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class FitTest {
    /**
     * Test manWeight.
     */
    @Test
    public void manWeight() {
        Fit fit = new Fit();
        double weight = fit.manWeight(180);
        assertThat(weight, closeTo(92.0, 0.1));
    }
    /**
     * Test womanWeight.
     */
    @Test
    public void womanWeight() {
        Fit fit = new Fit();
        double weight = fit.womanWeight(170);
        assertThat(weight, closeTo(69.0, 0.1));
    }
}
