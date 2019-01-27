package ru.job4j.crud.store;

import org.junit.Test;
import ru.job4j.crud.models.Role;
import ru.job4j.crud.models.User;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test DataBaseStore
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 23.11.18
 */
public class DbStoreTest {

    @Test
    public void whenOperationByUsersThenGetResult() {
        DbStore db = DbStore.getInstance();
        User one = new User("test",
                "login", "123",  "email",
                System.currentTimeMillis(), Role.USER, "Russia",
                "Moscow");
        User two = new User("newName",
                "newLogin", "123", "NewEmail",
                System.currentTimeMillis(), Role.ADMIN, "USA", "New-York");
        one = db.add(one);
        System.out.println(one);
        two = db.add(two);
        System.out.println(two);
        int sizeAfterAdd = db.findAll().size();
        assertThat(db.findById(one.getId()), is(one));
        assertThat(db.findById(two.getId()).getCreate(), is(two.getCreate()));
        User user = new User(one.getId(), "newName",
                "newLogin", "NewEmail", "333",
                one.getCreate(), Role.ADMIN, "Russia", "Voronezh");
        db.update(user);
        User afterUp = db.findById(user.getId());
        assertThat(afterUp.getName(), is(user.getName()));
        assertThat(afterUp.getLogin(), is(user.getLogin()));
        assertThat(afterUp.getEmail(), is(user.getEmail()));
        assertThat(afterUp.getRole(), is(user.getRole()));
        db.delete(one.getId());
        db.delete(two.getId());
        int sizeAfterDelete = db.findAll().size();
        assertThat(sizeAfterAdd, is(sizeAfterDelete + 2));
    }
}