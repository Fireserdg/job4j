package ru.job4j.car.models;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Transmission test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-25
 */
public class TransmissionTest {

    /**
     * Transmission
     *
     */
    private Transmission first = null;

    /**
     * List of cars
     *
     */
    private List<Car> list = List.of(new Car("Car1"),
            new Car("Car2"), new Car("Car3"));

    @Before
    public void init() {
        first = new Transmission("Transmission");
        first.setId(1L);
        first.setCars(list);
    }

    @Test
    public void whenCreateCarThenGetResult() {
        assertThat(first.getId(), is(1L));
        assertThat(first.getName(), is("Transmission"));
        assertThat(first.getCars(), is(list));
    }

    @Test
    public void whenUpdateCarThenGetResult() {
        first.setId(2L);
        first.setName("TransmissionUp");
        first.setCars(List.of(new Car("Car1Up"),
                new Car("Car2Up"), new Car("Car3Up")));
        assertThat(first.getId(), is(2L));
        assertThat(first.getName(), is("TransmissionUp"));
        assertThat(first.getCars().get(0).getModel(), is("Car1Up"));
        assertThat(first.getCars().get(1).getModel(), is("Car2Up"));
        assertThat(first.getCars().get(2).getModel(), is("Car3Up"));
    }

    @Test
    public void whenCheckEqualsAndHashCode() {
        var second = new Transmission("Transmission");
        second.setId(1L);
        Object engine = new CarBody("CarBody");
        assertThat(first.equals(second), is(true));
        assertThat(first.equals(engine), is(false));
        assertThat(first.hashCode(), is(second.hashCode()));
        second.setId(2L);
        assertThat(first.equals(second), is(false));
        assertThat(first.hashCode() == second.hashCode(), is(false));
    }

    @Test
    public void whenGetStringRepresentation() {
        var expect = "Transmission{id=1, name=Transmission}";
        assertThat(first.toString(), is(expect));
    }
}