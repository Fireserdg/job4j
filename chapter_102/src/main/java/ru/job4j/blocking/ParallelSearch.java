package ru.job4j.blocking;

import net.jcip.annotations.ThreadSafe;

/**
 * Parallel Search.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 27.08.2018.
 */
@ThreadSafe
public class ParallelSearch {

    /**
     * Contains marker for stop thread.
     *
     */
    private static boolean running = false;

    /**
     * Stop thread.
     *
     */
    private static void stop() {
        running = true;
    }

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        final Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    System.out.println(queue.pool());
                    if (running) {
                        Thread.currentThread().interrupt();
                    }
                } catch (InterruptedException e) {
                   return;
                }
            }
        });
        consumer.start();
        new Thread(() -> {
            for (int index = 0; index != 3; index++) {
                try {
                    queue.offer(index);
                    if (index == 2) {
                        stop();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}