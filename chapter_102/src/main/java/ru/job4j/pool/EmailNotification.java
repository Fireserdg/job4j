package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Email notification.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 05.09.2018.
 */
public class EmailNotification {

    /**
     * Contains threads pool
     *
     */
    private final ExecutorService pool;

    /**
     * Constructor.
     *
     */
    public EmailNotification() {
        int size = Runtime.getRuntime().availableProcessors();
        this.pool = Executors.newFixedThreadPool(size);
    }

    /**
     * Send e-mail.
     *
     * @param user User.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            final String subject = String.format("Notification %s to email %s",
                    user.getUsername(), user.getEmail());
            final String body = String.format("Add a new event to %s.", user.getUsername());
            send(subject, body, user.getEmail());
        });

    }

    /**
     * Close pool.
     *
     */
    public void close() {
        this.pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Send data.
     *
     * @param subject subject.
     * @param body body.
     * @param email e-mail.
     */
    public void send(String subject, String body, String email) {
        System.out.println(String.format("%s %s %s.", subject, body, email));
    }
}