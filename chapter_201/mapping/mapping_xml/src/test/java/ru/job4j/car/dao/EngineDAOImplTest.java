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
 * Engine test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-25
 */
public class EngineDAOImplTest {

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
        Engine body = engineDAO.findById(2L);
        assertThat(body.getId(), is(2L));
        assertThat(body.getName(), is("diesel"));
    }

    @Test
    public void whenAddEngineThenDelete() {
        Long id = engineDAO.add(new Engine("EngineNew"));
        Engine result = engineDAO.findById(id);
        assertThat(result.getId(), is(id));
        assertThat(result.getName(), is("EngineNew"));
        engineDAO.delete(result);
    }

    @Test
    public void whenGetAllEngines() {
        List<Engine> bodies = engineDAO.findAll();
        assertThat(bodies.get(0).getName(), is("petrol"));
        assertThat(bodies.get(1).getName(), is("diesel"));
        assertThat(bodies.get(2).getName(), is("gas"));
    }

    @Test
    public void whenGetAllCarsByNameEngine() {
        List<Car> cars = engineDAO.findAllElementsByNameDetail("diesel");
        assertThat(cars.get(0).getId(), is(1L));
        assertThat(cars.get(0).getModel(), is("BMW"));
        assertThat(cars.get(0).getCarBody().getName(), is("coupe"));
    }

    @Test
    public void whenDeleteOneEngineThenDeleteAllCarsWithThisDetail() {
        Long id = engineDAO.add(new Engine("EngineSecond"));
        Engine engine = engineDAO.findById(id);
        CarBody firstEn = bodyDAO.findById(1L);
        CarBody secondEn = bodyDAO.findById(2L);
        Transmission firstTr = transDAO.findById(1L);
        Long firstId = carDAO.add(new Car("CarFirst", firstEn, engine, firstTr));
        Long secondId = carDAO.add(new Car("CarSecond", secondEn, engine, firstTr));
        assertThat(carDAO.findById(secondId).getModel(), is("CarSecond"));
        engineDAO.delete(engine);
        assertThat(Objects.isNull(carDAO.findById(firstId)), is(true));
    }
}