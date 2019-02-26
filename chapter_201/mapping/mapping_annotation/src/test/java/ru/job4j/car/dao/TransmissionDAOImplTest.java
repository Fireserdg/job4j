package ru.job4j.car.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.car.models.Car;
import ru.job4j.car.models.CarBody;
import ru.job4j.car.models.Engine;
import ru.job4j.car.models.Transmission;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Transmission DAO
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-25
 */
public class TransmissionDAOImplTest {

    /**
     * Engine DAO
     *
     */
    private DetailEntityDAO<Engine> engineDAO;

    /**
     * Car body DAO
     *
     */
    private DetailEntityDAO<CarBody> bodyDAO;

    /**
     * Wrapper for transaction
     *
     */
    private DetailEntityDAO<Transmission> transDAO;

    /**
     * Car DAO
     *
     */
    private CarEntityDAO carDAO;

    /**
     * Init instance Engine DAO.
     *
     */
    @Before
    public void init() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        TransactionWrapper wrapper = new TransactionWrapper(factory);
        bodyDAO = new CarBodyDAOImpl(wrapper);
        engineDAO = new EngineDAOImpl(wrapper);
        transDAO = new TransmissionDAOImpl(wrapper);
        carDAO = new CarDAOImpl(wrapper);
    }

    @Test
    public void whenFindEngineById() {
        Transmission body = transDAO.findById(2L);
        assertThat(body.getId(), is(2L));
        assertThat(body.getName(), is("automatic"));
    }

    @Test
    public void whenAddTransmissionThenDelete() {
        Long id = transDAO.add(new Transmission("TransNew"));
        Transmission result = transDAO.findById(id);
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is("TransNew"));
        transDAO.delete(result);
    }

    @Test
    public void whenGetAllTransmissions() {
        List<Transmission> bodies = transDAO.findAll();
        assertThat(bodies.get(0).getName(), is("manual"));
        assertThat(bodies.get(1).getName(), is("automatic"));
        assertThat(bodies.get(2).getName(), is("robotic"));
    }

    @Test
    public void whenGetAllCarsByNameTransmission() {
        List<Car> cars = transDAO.findAllElementsByNameDetail("automatic");
        cars = cars.stream().sorted(Comparator.comparing(Car::getId)).collect(Collectors.toList());
        assertThat(cars.get(1).getId(), is(2L));
        assertThat(cars.get(1).getName(), is("Volkswagen"));
        assertThat(cars.get(1).getBody().getName(), is("sedan"));
    }

    @Test
    public void whenDeleteOneTransmissionThenDeleteAllCarsWithThisDetail() {
        Long id = transDAO.add(new Transmission("Transmission"));
        Transmission transmission = transDAO.findById(id);
        CarBody firstEn = bodyDAO.findById(1L);
        CarBody secondEn = bodyDAO.findById(2L);
        Engine engine = engineDAO.findById(1L);
        Long firstId = carDAO.add(new Car("CarFirst", firstEn, engine, transmission));
        Long secondId = carDAO.add(new Car("CarSecond", secondEn, engine, transmission));
        assertThat(carDAO.findById(secondId).getName(), is("CarSecond"));
        transDAO.delete(transmission);
        assertThat(Objects.isNull(carDAO.findById(firstId)), is(true));
    }
}