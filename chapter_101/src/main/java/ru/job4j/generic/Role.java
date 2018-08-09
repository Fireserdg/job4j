package ru.job4j.generic;

/**
 * Class Role.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 09.08.2018.
 */
public class Role extends Base {

    /**
     * Contains name Role.
     *
     */
    private final String nameRole;

    /**
     * Constructor Role.
     *
     * @param id id new Role.
     * @param nameRole name new Role.
     */
    protected Role(String id, final String nameRole) {
        super(id);
        this.nameRole = nameRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    @Override
    public String toString() {
        return String.format("Role{nameRole=%s}", nameRole);
    }
}
