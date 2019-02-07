package ru.job4j.addtask;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Тест проверящий корректную выдачу сдачи.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 24.07.2018.
 */
public class CoffeeMachineTest {

    /**
     * Объект класс CoffeeMachine для тестовых методов.
     */
    private CoffeeMachine coffeeMachine = new CoffeeMachine();

    @Test
    public void whenCoffeeMachineGivesChangeFrom50() {
        int value = 50;
        int price = 35;
        Assert.assertThat(coffeeMachine.changes(value, price), is(new int[]{10, 5}));
    }

    @Test
    public void whenCoffeeMachineGivesChangeFrom100() {
        int value = 100;
        int price = 42;
        Assert.assertThat(coffeeMachine.changes(value, price), is(new int[]{10, 10, 10, 10, 10, 5, 2, 1}));
    }

    @Test
    public void whenCoffeeMachineGivesChangeFrom150() {
        int value = 150;
        int price = 113;
        Assert.assertThat(coffeeMachine.changes(value, price), is(new int[]{10, 10, 10, 5, 2}));
    }

    @Test
    public void whenCoffeeMachineNotGiveChange() {
        int value = 50;
        int price = 55;
        Assert.assertThat(coffeeMachine.changes(value, price), is(new int[]{0}));
    }
}