package ru.job4j.tracker;

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
     * Конструктор
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
    public void fillActions() {
        this.actions.add(this.new AddItem());
        this.actions.add(new MenuTracker.ShowItems());
        this.actions.add(new EditItem());
        this.actions.add(new MenuTracker.DeleteItem());
        this.actions.add(this.new FindById());
        this.actions.add(new FindByName());
        this.actions.add(this.new ExitProgram());
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
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Реализация внутреннего класса по добавлению пользователем новой заявки.
     */
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new Item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ New Item with getId : " + item.getId());
            System.out.println("------------ New Item with Name : " + item.getName());
            System.out.println("------------ New Item with Description : " + item.getDescription());
        }

        @Override
        public String info() {
            return String.format("%s. Add the new Item.", this.key());
        }
    }

    /**
     * Статический внутренний класс реализует просмотр списка всех заявок.
     */
    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("----List of Items----");
            for (Item item: tracker.findAll()) {
                System.out.println(String.format("ID: %s. name: %s.", item.getId(), item.getName()));
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items.");
        }
    }
    /**
     * Статический внутренний класс реализует удаление заявки.
     */
    private static class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------Delete Item--------------");
            String answer = input.ask("Please, provide item ID:");
            if (tracker.delete(answer)) {
                System.out.println("Item has deleted");
            } else {
                System.out.println("Item not found");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete the new Item.");
        }
    }
    /**
     * Внутренний класс, реализующий поиск по ID.
     */
    public class FindById implements UserAction {
        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------Find item by ID--------------");
            String answer = input.ask("Please, provide item ID:");
            Item item = tracker.findById(answer);
            if (item != null) {
                System.out.println("Name item: " + item.getName() + " Description item: "
                        + item.getDescription());
            } else {
                System.out.println("Item not found.");
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find Item by Id .");
        }
    }
    /**
     * Внутренний класс, реализующий выход из программы.
     */
    public class ExitProgram implements UserAction {
        @Override
        public int key() {
            return 7;
        }

        @Override
        public void execute(Input input, Tracker tracker) {

        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program.");
        }
    }
}
/**
 * Внутренний "внешний класс", реализующий редактирование заявок.
 */
class EditItem implements UserAction {
    @Override
    public int key() {
        return 3;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Edit Item --------------");
        String answer = input.ask("Please, provide item ID:");
        String name = input.ask("Please, provide item name:");
        String desc = input.ask("Please, provide item description:");
        Item item = new Item(name, desc);
        if (tracker.replace(answer, item)) {
            System.out.println("Item update");
        } else {
            System.out.println("Item not found");
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit the new Item.");
    }
}
/**
 * Внутренний "внешний класс", поиск по Name.
 */
class FindByName implements UserAction {
    @Override
    public int key() {
        return 6;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------Find item by Name--------------");
        String  name = input.ask("Please, provide item name:");
        Item[] result = tracker.findByName(name);
        if (result.length == 0) {
            System.out.println("Item not found");
        }
        for (Item item : result) {
            if (name.equals(item.getName())) {
                System.out.println("Name item: " + item.getName()
                        + " Description item: " + item.getDescription()
                        + " item ID: " + item.getId());
            }
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Find Item by name.");
    }
}
