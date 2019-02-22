package ru.job4j.car.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import ru.job4j.car.util.SessionFactoryUtil;

import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Car test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-20
 */
public class CarTest {

    @Ignore
    public void test() {

        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            CarBody carBody = new CarBody("Sedan");
            Engine engine = new Engine("Engine2");
            Transmission transmission = new Transmission("Transmission");
            session.save(carBody);
//            System.out.println(ex);
            session.save(engine);
            session.save(transmission);

            session.getTransaction().commit();

            session.beginTransaction();
            Car car = new Car("BMW", carBody, engine, transmission);
            session.save(car);
//            engine.getCars().add(car);


//            System.out.println(engine.getCars());
            System.out.println("id: " + carBody.getId());

            session.getTransaction().commit();

//            System.out.println(carBody.getCars());
            System.out.println(engine.getCars());
//            System.out.println(transmission.getCars());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}