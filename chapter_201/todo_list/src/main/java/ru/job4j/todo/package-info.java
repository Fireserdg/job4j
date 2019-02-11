/**
 * TodoList
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-02-11
 */
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(
                name = "findItemById",
                query = "select i from item as i where i.id=:id"
        ),

        @org.hibernate.annotations.NamedQuery(
                name = "DeleteItem",
                query = "delete from items where id=:id",
                comment = "custom SQL comment"
        ),
})

package ru.job4j.todo;