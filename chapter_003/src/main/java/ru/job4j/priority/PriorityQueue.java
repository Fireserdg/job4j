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
        int index = tasks.size();
        if (!(index == 0)) {
            for (int i = 0; i < index; i++) {
                if (task.getPriority() < tasks.get(i).getPriority()) {
                    tasks.add(i, task);
                    break;
                } else if (index == 1) {
                    tasks.add(task);
                }
            }
        } else {
            tasks.add(0, task);
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
