package ru.job4j.pool;

import org.junit.Test;

/**
 * Test Email Notification.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 05.09.2018.
 */
public class EmailNotificationTest {

    @Test
    public void whenSendEmails() {
        EmailNotification notification = new EmailNotification();
        for (int index = 0; index < 15; index++) {
            String username = String.format("%s.Name", index);
            String email = String.format("%s.@gmail.com", index);
            notification.emailTo(new User(username, email));
        }
        notification.close();
    }
}