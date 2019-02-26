package ru.job4j.car.models;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Car test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-23
 */
public class CarTest {

    /**
     * Car
     *
     */
    private Car first = null;

    @Before
    public void init() {
        first = new Car("BMW", new CarBody("Body"),
                new Engine("Engine"),
                new Transmission("Trans"));
        first.setId(1L);
    }

    @Test
    public void whenCreateCarThenGetResult() {
        assertThat(first.getId(), is(1L));
        assertThat(first.getName(), is("BMW"));
        assertThat(first.getBody(), is(new CarBody("Body")));
        assertThat(first.getEngine(), is(new Engine("Engine")));
        assertThat(first.getTransmission(), is(new Transmission("Trans")));
    }

    @Test
    public void whenUpdateCarThenGetResult() {
        first.setBody(new CarBody("BodyTwo"));
        first.setEngine(new Engine("EngineTwo"));
        first.setTransmission(new Transmission("TransTwo"));
        assertThat(first.getBody().getName(), is("BodyTwo"));
        assertThat(first.getEngine().getName(), is("EngineTwo"));
        assertThat(first.getTransmission().getName(), is("TransTwo"));
    }

    @Test
    public void whenCheckEqualsAndHashCode() {
        var second = new Car("BMW", new CarBody("Body"),
                new Engine("Engine"),
                new Transmission("Trans"));
        second.setId(1L);
        Object engine = new Engine("Engine2");
        assertThat(first.equals(second), is(true));
        assertThat(first.equals(engine), is(false));
        assertThat(first.hashCode(), is(second.hashCode()));
        second.setId(2L);
        assertThat(first.equals(second), is(false));
        assertThat(first.hashCode() == second.hashCode(), is(false));
    }

    @Test
    public void whenGetStringRepresentation() {
        var expect = "Car{id=1, name=BMW}";
        assertThat(first.toString(), is(expect));
    }
}