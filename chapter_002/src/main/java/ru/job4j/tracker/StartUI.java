package ru.job4j.tracker;

import java.lang.String;

/**
 * Реализация класса StartUI.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для получения списка всех заявок.
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявок.
     */
    private static final String EDIT = "2";
    /**
     * Константа для удаления заявок.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FINDBYID = "4";
    /**
     * Константа меню для поиска по имени.
     */
    private static final String FINDBYNAME = "5";
    /**
     * Константа меню для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.showItem();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDBYID.equals(answer)) {
                this.findItemByID();
            } else if (FINDBYNAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }
    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой языки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }
    /**
     * Метод реализует отображение всех заявок.
     */
    private void showItem() {
        System.out.println("------------ Показать все заявки --------------");
        Item[] result = this.tracker.findAll();
        for (Item item : result) {
            System.out.println("Имя заявки: " + item.getName() + " Описание заявки: "
                                + item.getDescription() + " ID заявки:" + item.getId());
        }
    }
    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println("------------ Отредактировать заявку --------------");
        Item[] result = this.tracker.findAll();
        String answer = this.input.ask("Введите свой ID:");
        for (Item item : result) {
            if (answer.equals(item.getId())) {
                String name = this.input.ask("Введите новое имя заявки:");
                String desc = this.input.ask(" Введите новое описание заявки:");
                Item items = new Item(name, desc);
                this.tracker.replace(answer, items);
                break;
            } else {
                System.out.println("Вы ввели не верный ID заявки. Введите верный ID.");
            }
        }
    }
    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------Удалить заявку--------------");
        String answer = this.input.ask("Введите свой ID: ");
        Item[] result = this.tracker.findAll();
        for (Item item : result) {
            if (answer.equals(item.getId())) {
                this.tracker.delete(item.getId());
                break;
            } else {
                System.out.println("Вы ввели не верный ID заявки. Введите верный ID.");
            }
        }
    }
    /**
     * Метод реализует поиски заявки по ID.
     */
    private void findItemByID() {
        System.out.println("------------Найти заявку по ID--------------");
        String answer = this.input.ask("Введите свой ID: ");
        Item[] result = this.tracker.findAll();
        for (Item item : result) {
            if (answer.equals(item.getId())) {
                System.out.println("Имя заявки: " + item.getName() + " Описание заявки: " + item.getDescription());
                break;
            } else {
                System.out.println("Заявки с таким ID не существует. Введите верный ID.");
            }
        }
    }
    /**
     *  Метод реализует поиск заявки по имени.
     */
    private void findItemByName() {
        System.out.println("------------Найти заявку по имени--------------");
        String  name = this.input.ask("Введите имя Вашей заявки: ");
        Item[] result = this.tracker.findByName(name);
        for (Item item : result) {
            if (name.equals(item.getName())) {
                System.out.println("Имя заявки: " + item.getName() +
                        "Описание заявки: " + item.getDescription() + " ID Вашей заявки: " + item.getId());
                break;
            } else {
                System.out.println("Заявки с таким именем не существует. Введите верное имя.");
            }
        }
    }
    /**
     * Метод реализует отображение меню Трекера.
     */
    private void showMenu() {
        System.out.println("-------Меню.-------");
        System.out.println("0.Add new Item.");
        System.out.println("1. Show all items.");
        System.out.println("2. Edit Item.");
        System.out.println("3. Delete Item.");
        System.out.println("4. Find Item by Id.");
        System.out.println("5. Find Item by name.");
        System.out.println("6. Exit Program.");
    }
    /**
     * Запуск программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
