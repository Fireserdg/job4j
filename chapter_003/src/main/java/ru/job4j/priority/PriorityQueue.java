package ru.job4j.priority;

import java.util.Comparator;
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
        tasks.add(0, task);
        tasks.sort(new SortPriority());
    }

    /**
     * Get delete element.
     *
     * @return delete element.
     */
    public Task take() {
        return this.tasks.poll();
    }

    /**
     * Class implementing the comparison object of Task.
     */
    private class SortPriority implements Comparator<Task> {

        /**
         * compare two Tasks.
         *
         * @param o1 first Tasks.
         * @param o2 second Tasks
         * @return result compare.
         */
        public int compare(Task o1, Task o2) {
            return Integer.compare(o1.getPriority(), o2.getPriority());
        }
    }
}
