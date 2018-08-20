package ru.job4j.control;

/**
 * Info about change collections.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 20.08.2018.
 */
public class Info {

    /**
     * Contains count add new items.
     *
     */
    private int addNew;

    /**
     * Contains count delete items.
     *
     */
    private int delete;

    /**
     * Contains count change items.
     *
     */
    private int change;

    /**
     * Constructor Info.
     *
     * @param addNew add new items.
     * @param delete delete items.
     * @param change change items.
     */
    public Info(int addNew, int delete, int change) {
        this.addNew = addNew;
        this.delete = delete;
        this.change = change;
    }

    /**
     * Contains info about change in collections.
     *
     * @return information.
     */
    @Override
    public String toString() {
        return String.format("Добавлено:%s. Удалено:%s. Изменено:%s.",
                this.addNew, this.delete, this.change);
    }
}
