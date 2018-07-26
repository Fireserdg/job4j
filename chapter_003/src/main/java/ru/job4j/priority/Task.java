package ru.job4j.priority;

/**
 * Priority Queue.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.07.2018.
 */
public class Task {

    /**
     * Fields contains desk.
     */
    private String desc;
    /**
     * Fields contains priority.
     */
    private int priority;

    /**
     * Class constructor that accepts input parameters.
     *
     * @param desc desc new Task.
     * @param priority priority new Task.
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    /**
     * Get description.
     *
     * @return desc Task.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Get priority.
     *
     * @return priorityTask
     */
    public int getPriority() {
        return priority;
    }
}
