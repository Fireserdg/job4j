package ru.job4j.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Item.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 08.05.2018.
 */
public class Item {
    /**
     * Item id.
     */
    private String id;

    /**
     * Item name.
     *
     */
    private String name;

    /**
     * Item description.
     *
     */
    private String description;

    /**
     * Create item.
     *
     */
    private long create;

    /**
     * List of comments.
     *
     */
    private List<String> comments = new ArrayList<>();

    /**
     * Constructor Item.
     *
     * @param name Item name.
     * @param description Item description.
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor Item.
     *
     * @param name Item name.
     * @param description Item description.
     * @param create create time Item.
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Get item Id.
     *
     * @return item id.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set item Id.
     *
     * @param id item id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get item name.
     *
     * @return item name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get description item.
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get creating of item.
     *
     * @return create time.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Get list of comments.
     *
     * @return list of comments.
     */
    public List<String> getComments() {
        return comments;
    }

    /**
     * Set comment item.
     *
     * @param comment comment.
     */
    public void setComments(String comment) {
        this.comments.add(comment);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(String.format("ID item: %s Name item: %s Description item: %s",
                        this.getId(), this.getName(), this.getDescription()));
        if (this.comments.size() != 0) {
            for (String comment: this.comments) {
                sb.append(System.lineSeparator())
                        .append(String.format("Комментарий №%s: ", this.comments.indexOf(comment) + 1))
                        .append(comment);
            }
        } else {
            sb.append(System.lineSeparator()).append("----Комментарии к заявке отсутствуют.----");
        }
        return sb.toString();
    }
}