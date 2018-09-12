package ru.job4j.calculator;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {

    /**
     * Contains calc.
     *
     */
    private Calculator calc;

    /**
     * Contains buffer.
     *
     */
    private List<Double> buffer;

    /**
     * Create object for test.
     *
     */
    @Before
    public void createObject() {
        calc = new Calculator();
        buffer = new ArrayList<>();
    }

    @Test
    public void whenAdd1Until3() {
        calc.multiple(
                0, 3, 1,
                (value, index) -> (double) value + index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(1D, 2D, 3D)));
    }

    @Test
    public void whenAdd4Until4ThenSubtraction() {
        calc.multiple(
                0, 4, 4,
                (value, index) -> (double) value - index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(4D, 3D, 2D, 1D)));
    }

    @Test
    public void whenAdd2Until4ThenMultiple() {
        calc.multiple(0, 4, 2,
                (value, index) -> (double) value * index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(0D, 2D, 4D, 6D)));
    }

    @Test
    public void whenAdd60From4To6ThenDivision() {
        calc.multiple(4, 7, 60,
                (value, index) -> (double) value / index,
                result -> buffer.add(result)
        );
        assertThat(buffer, is(Arrays.asList(15D, 12D, 10D)));
    }
}