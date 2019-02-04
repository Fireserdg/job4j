package ru.job4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.connection.ConnectionInit;
import ru.job4j.input.*;
import ru.job4j.tracker.*;

import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Реализация класса StartUI.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class StartUI {

    /**
     * Logger for get information about error.
     *
     */
    private static final Logger LOG = LoggerFactory.getLogger(StartUI.class);

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Переменная для выхода из программы.
     */
    private boolean marker = true;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);
        do {
            Consumer<MenuTracker> cons = value -> {
                value.show();
                value.select(input.ask("Select", value.listValueCreate()));
            };
            cons.accept(menu);
        } while (this.marker);
    }

    /**
     * Метод останавливающий действие программы
     */
    public void stop() {
        this.marker = false;
    }

    /**
     * Запуск программы.
     * @param args args
     */
    public static void main(String[] args) {
        try (TrackerDataBase tracker = new TrackerDataBase(
                ConnectionInit.getProperties(), ConnectionInit.init())) {
            new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}