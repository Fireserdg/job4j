package ru.job4j.tracker;

import ru.job4j.item.Item;
import ru.job4j.StartUI;
import ru.job4j.action.BaseAction;
import ru.job4j.action.UserAction;
import ru.job4j.input.Input;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Реализация класса MenuTracker.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 12.07.2018.
 */

public class MenuTracker {
    /**
     * @param хранит ссылку на объект.
     */
    private Input input;
    /**
     * @param хранит ссылку на объект.
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор MenuTracker
     *
     * @param input объект типа Input.
     * @param tracker объект типа Tracker.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Метод заполняет массив actions.
     */
    public void fillActions(StartUI startUI) {
        this.actions.add(this.new AddItem(0, "Add the new Item."));
        this.actions.add(new MenuTracker.ShowItems(1, "Show all items."));
        this.actions.add(new EditItem(2, "Edit the new Item."));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete the new Item."));
        this.actions.add(this.new FindById(4, "Find Item by Id ."));
        this.actions.add(new FindByName(5, "Find Item by name."));
        this.actions.add(new AddComment(6, "Add comment."));
        this.actions.add(new ExitProgram(7, "Exit program.", startUI));
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action: this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLength() {
        return this.actions.size();
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод задающий диапозон пунктов меню.
     *
     */
    public List<Integer> listValueCreate() {
        List<Integer> range = new ArrayList<>();
        for (int i = 0; i < this.getActionsLength(); i++) {
            range.add(i);
        }
        return range;
    }

    /**
     * Реализация внутреннего класса по добавлению пользователем новой заявки.
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор класса AddItem.
         *
         * @param key Пункт меню.
         * @param name Наименование пункта меню.
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод для обмена информации с пользователем.
         *
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new Item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = tracker.add(new Item(name, desc, System.currentTimeMillis()));
            System.out.println(String.format("---- New Item with getId: %s ----", item.getId()));
            System.out.println(String.format("---- New Item with Name: %s ----", item.getName()));
            System.out.println(String.format("---- New Item with Description: %s ----", item.getDescription()));
            System.out.println(String.format("---- New Item with Date And Time: %s ----", new Timestamp(item.getCreate())));
        }
    }

    /**
     * Статический внутренний класс реализует просмотр списка всех заявок.
     */
    private static class ShowItems extends BaseAction {

        /**
         * Конструктор класса ShowItem.
         *
         * @param key Пункт меню.
         * @param name Наименование пункта меню.
         */
        public ShowItems(int key, String name) {
            super(key, name);
        }

        /**
         * Метод для обмена информации с пользователем.
         *
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----List of Items----");
            for (Item item: tracker.findAll()) {
                System.out.println(item);
            }
        }
    }
    /**
     * Статический внутренний класс реализует удаление заявки.
     */
    private static class DeleteItem extends BaseAction {

        /**
         * Конструктор класса DeleteItem.
         *
         * @param key Пункт меню.
         * @param name Наименование пункта меню.
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод для обмена информации с пользователем.
         *
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------Delete Item--------------");
            String answer = input.ask("Please, provide item ID:");
            if (tracker.delete(id -> id.equals(answer))) {
                System.out.println("Item has deleted");
            } else {
                System.out.println("Item not found");
            }
        }
    }
    /**
     * Внутренний класс, реализующий поиск по ID.
     */
    public class FindById extends BaseAction {

        /**
         * Конструктор класса FindById.
         *
         * @param key Пункт меню.
         * @param name Наименование пункта меню.
         */
        public FindById(int key, String name) {
            super(key, name);
        }

        /**
         * Метод для обмена информации с пользователем.
         *
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------Find item by ID--------------");
            String answer = input.ask("Please, provide item ID:");
            try {
                Item item = tracker.findById(id -> id.equals(answer));
                System.out.println(item);
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
    }
}

/**
 * Внутренний "внешний класс", реализующий редактирование заявок.
 */
class EditItem extends BaseAction {

    /**
     * Конструктор класса EditItem.
     *
     * @param key Пункт меню.
     * @param name Наименование пункта меню.
     */
    public EditItem(int key, String name) {
        super(key, name);
    }

    /**
     * Метод для обмена информации с пользователем.
     *
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Edit Item --------------");
        String answer = input.ask("Please, provide item ID:");
        String name = input.ask("Please, provide item name:");
        String desc = input.ask("Please, provide item description:");
        Item item = new Item(name, desc);
        if (tracker.replace(o -> o.equals(answer), item)) {
            System.out.println("Item update");
        } else {
            System.out.println("Item not found");
        }
    }
}

/**
 * Внутренний "внешний класс", реализующий поиск по Name.
 */
class FindByName extends BaseAction {

    /**
     * Конструктор класса FindByName.
     *
     * @param key Пункт меню.
     * @param name Наименование пункта меню.
     */
    public FindByName(int key, String name) {
        super(key, name);
    }

    /**
     * Метод для обмена информации с пользователем.
     *
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------Find item by Name--------------");
        String  name = input.ask("Please, provide item name:");
        List<Item> result = tracker.findByName(key -> key.equals(name));
        if (result.size() == 0) {
            System.out.println("Item not found");
        }
        for (Item item : result) {
            if (name.equals(item.getName())) {
                System.out.println(item);
            }
        }
    }
}

/**
 * Внутренний класс, реализующий пункт меню Add comments.
 */
class AddComment extends BaseAction {

    /**
     * Конструктор класса AddComment.
     *
     * @param key Пункт меню.
     * @param name Наименование пункта меню.
     */
    public AddComment(int key, String name) {
        super(key, name);
    }

    /**
     * Метод для обмена информации с пользователем.
     *
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------Add comments in Item--------------");
        String answer = input.ask("Please, provide item ID:");
        String comment = input.ask("Please, provide comments:");
        boolean result = tracker.addComment(id -> id.equals(answer), comment);
        if (result) {
            System.out.println("Add comment");
        } else {
            System.out.println("Item not found");
        }
    }
}

/**
 * Внутренний "внешний класс", реализующий пункт меню Exit Program.
 */
class ExitProgram extends BaseAction {
    /**
     * Поле, где объявляется переменная класса StartUI.
     */
    private final StartUI startUI;

    /**
     * Конструктор класса DeleteItem.
     *
     * @param key Пункт меню.
     * @param name Наименование пункта меню.
     * @param startUI объект класса StartUI.
     */
    public ExitProgram(int key, String name, StartUI startUI) {
        super(key, name, startUI);
        this.startUI = startUI;
    }

    /**
     * Метод для выхода из программы.
     *
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("You're out of the program. See you soon.");
        startUI.stop();
    }
}