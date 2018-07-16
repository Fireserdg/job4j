package ru.job4j.tracker;

/**
 * Абстрактный класс, описыващий действия интерфейся UserAction.
 */
public abstract class BaseAction implements UserAction {
    /**
     * Поле содержит параметр key.
     */
    private final int key;
    /**
     * Поле содержит параметр name.
     */
    private final String name;

    /**
     * Конструктор класс BaseAction
     *
     * @param key номер пункта меню.
     * @param name наименование пункта меню.
     */
    public BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    /**
     *
     * Конструктор класса BaseAction для реализации в классе ExitProgram.
     *
     * @param key номер пункта меню.
     * @param name наименование пункта меню.
     * @param startUI объект класса StartUI для реализации в классе ExitProgram.
     */
    public BaseAction(final int key, final String name, final StartUI startUI) {
        this.key = key;
        this.name = name;
    }

    /**
     * Метод возращающий номер пункта меню.
     *
     * @return номер пункта меню.
     */
    @Override
    public int key() {
        return this.key;
    }

    /**
     * Абстрактный метод, обязательный для реализации в подклассах.
     * Метод для реализации пунктов меню.
     *
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    @Override
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Метод формирующий строку меню.
     *
     * @return Строка меню.
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }
}
