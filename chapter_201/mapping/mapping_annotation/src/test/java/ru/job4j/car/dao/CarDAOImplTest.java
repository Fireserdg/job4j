package ru.job4j.car.dao;

import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.car.models.Car;
import ru.job4j.car.models.CarBody;
import ru.job4j.car.models.Engine;
import ru.job4j.car.models.Transmission;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Car DAO impl test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-21
 */
public class CarDAOImplTest {

    @Test
    public void whenAddCar() {
        CarBody carBody = new CarBody("sedan");
        System.out.println(carBody.getId());
        CarBodyDAOImpl.getInstance().add(carBody);
//        CarBodyDAOImpl.getInstance().add(carBody);
        Engine engine = new Engine("petrol");
        EngineDAOImpl.getInstance().add(engine);
        Transmission transmission = new Transmission("manual");
        TransmissionDAOImpl.getInstance().add(transmission);
//        System.out.println(carBody.getId());
        CarDAOImpl.getInstance().add(
                new Car("BMW", new CarBody(1L),
                        new Engine(1L), new Transmission(1L)));

        CarBody byId = CarBodyDAOImpl.getInstance().findById(1L);
        System.out.println(byId.getCars());
    }
}