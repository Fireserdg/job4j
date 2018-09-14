package ru.job4j.stream;

/**
 * Task.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru)
 * @version $1.0$
 * @since 14.09.2018.
 */
public class Task {
    /**
     * Contains name.
     */
    private final String name;

    /**
     * Contains spent.
     */
    private final long spent;

    /**
     * Constructor.
     *
     * @param name task name.
     * @param spent task spent.
     */
    public Task(String name, long spent) {
        this.name = name;
        this.spent = spent;
    }

    public String getName() {
        return name;
    }

    public long getSpent() {
        return spent;
    }

    @Override
    public String toString() {
        return String.format("Task{name=%s, spent=%s}", name, spent);
    }
}
