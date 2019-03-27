package ru.job4j.car.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.car.models.Car;
import ru.job4j.car.models.CarBody;
import ru.job4j.car.models.Engine;
import ru.job4j.car.models.Transmission;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Car DAO impl test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class CarDAOImplTest {

    /**
     * CarDAO
     *
     */
    private static CarEntityDAO carDAO;

    /**
     * Engine DAO
     *
     */
    private static DetailEntityDAO<Engine> engineDAO;

    /**
     * Factory
     *
     */
    private static SessionFactory factory;

    /**
     * Init car DAO
     *
     */
    @BeforeClass
    public static void init() {
        factory = new Configuration().configure().buildSessionFactory();
        TransactionWrapper wrapper = new TransactionWrapper(factory);
        carDAO = new CarDAOImpl(wrapper);
        engineDAO = new EngineDAOImpl(wrapper);
    }

    /**
     * Close factory
     *
     */
    @AfterClass
    public static void close() {
        factory.close();
    }
    @Test
    public void whenFindCarById() {
        Car car = carDAO.findById(1L);
        assertThat(car.getId(), is(1L));
        assertThat(car.getModel(), is("BMW"));
    }

    @Test
    public void whenFindCarByIdThenGetDetail() {
        Car car = carDAO.findById(2L);
        assertThat(car.getModel(), is("Volkswagen"));
        assertThat(car.getCarBody().getName(), is("sedan"));
        assertThat(car.getEngine().getName(), is("petrol"));
        assertThat(car.getTransmission().getName(), is("automatic"));
    }

    @Test
    public void whenGetAllCars() {
        List<Car> cars = carDAO.findAll();
        assertThat(cars.size(), is(3));
        assertThat(cars.get(0).getId(), is(1L));
        assertThat(cars.get(1).getId(), is(2L));
        assertThat(cars.get(2).getId(), is(3L));
        assertThat(cars.get(0).getModel(), is("BMW"));
        assertThat(cars.get(1).getModel(), is("Volkswagen"));
        assertThat(cars.get(2).getModel(), is("Lada"));
    }

    @Test
    public void whenAddCarThenDelete() {
        Car car = new Car("AUDI", new CarBody(1L),
                new Engine(1L), new Transmission(1L));
        Long id = carDAO.add(car);
        Car carFind = carDAO.findById(id);
        assertThat(carFind.getModel(), is("AUDI"));
        assertThat(carFind.getId(), is(id));
        carDAO.delete(carFind);
    }

    @Test
    public void whenAddCarThenUpdate() {
        Car car = carDAO.findByName("Lada");
        assertThat(car.getModel(), is("Lada"));
        assertThat(car.getEngine().getName(), is("petrol"));
        Engine engine = engineDAO.findById(2L);
        car.setEngine(engine);
        carDAO.update(car);
        assertThat(car.getEngine().getName(), is("diesel"));
        car.setEngine(engineDAO.findById(1L));
        carDAO.update(car);
    }
}