package ru.job4j.priority;

import java.util.LinkedList;

/**
 * Priority Queue.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 26.07.2018.
 */
public class PriorityQueue {
    /**
     * Field contains list Tasks.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Add a new Task.
     *
     * @param task received task.
     */
    public void put(Task task) {
        if (tasks.size() == 0) {
            tasks.add(0, task);
        } else if (task.getPriority() < tasks.get(tasks.size() - 1).getPriority()) {
            tasks.add(tasks.size() - 1, task);
        } else {
            tasks.add(tasks.size(), task);
        }
    }

    /**
     * Get first element.
     *
     * @return get first element.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
