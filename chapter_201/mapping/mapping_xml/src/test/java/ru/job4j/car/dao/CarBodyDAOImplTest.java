package ru.job4j.car.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.car.models.Car;
import ru.job4j.car.models.CarBody;
import ru.job4j.car.models.Engine;
import ru.job4j.car.models.Transmission;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Car body test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-25
 */
public class CarBodyDAOImplTest {

    /**
     * Car body DAO
     *
     */
    private DetailEntityDAO<CarBody> bodyDAO;

    /**
     * Engine DAO
     *
     */
    private DetailEntityDAO<Engine> engineDAO;

    /**
     * Car DAO
     *
     */
    private CarEntityDAO carDAO;

    /**
     * Wrapper for transaction
     *
     */
    private DetailEntityDAO<Transmission> transDAO;

    /**
     * Init instance Car body DAO
     *
     */
    @Before
    public void init() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        TransactionWrapper wrapper = new TransactionWrapper(factory);
        bodyDAO = new CarBodyDAOImpl(wrapper);
        engineDAO = new EngineDAOImpl(wrapper);
        carDAO = new CarDAOImpl(wrapper);
        transDAO = new TransmissionDAOImpl(wrapper);
    }

    @Test
    public void whenFindCarBodyById() {
        CarBody body = bodyDAO.findById(3L);
        assertThat(body.getId(), is(3L));
        assertThat(body.getName(), is("pickup"));
    }

    @Test
    public void whenAddCarBodyThenDelete() {
        Long id = bodyDAO.add(new CarBody("CarBodyNew"));
        CarBody result = bodyDAO.findById(id);
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is("CarBodyNew"));
        bodyDAO.delete(result);
    }

    @Test
    public void whenGetAllBodies() {
        List<CarBody> bodies = bodyDAO.findAll();
        assertThat(bodies.get(0).getName(), is("sedan"));
        assertThat(bodies.get(1).getName(), is("hatchback"));
        assertThat(bodies.get(2).getName(), is("pickup"));
        assertThat(bodies.get(3).getName(), is("van"));
        assertThat(bodies.get(4).getName(), is("coupe"));
    }

    @Test
    public void whenGetAllCarsByNameCarBody() {
        List<Car> cars = bodyDAO.findAllElementsByNameDetail("hatchback");
        assertThat(cars.get(0).getId(), is(3L));
        assertThat(cars.get(0).getModel(), is("Lada"));
        assertThat(cars.get(0).getEngine().getName(), is("petrol"));
    }

    @Test
    public void whenDeleteOneCarBodyThenDeleteAllCarsWithThisDetail() {
        Long id = bodyDAO.add(new CarBody("CarBodySecond"));
        CarBody body = bodyDAO.findById(id);
        Engine firstEn = engineDAO.findById(1L);
        Engine secondEn = engineDAO.findById(2L);
        Transmission firstTr = transDAO.findById(1L);
        Long firstId = carDAO.add(new Car("CarFirst", body, firstEn, firstTr));
        Long secondId = carDAO.add(new Car("CarSecond", body, secondEn, firstTr));
        assertThat(carDAO.findById(secondId).getModel(), is("CarSecond"));
        bodyDAO.delete(body);
        assertThat(Objects.isNull(carDAO.findById(firstId)), is(true));
    }
}