package ru.job4j.bank;

import java.util.Objects;

/**
 * Bank operations.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 02.08.2018.
 */
public class Account {

    /**
     * Contains value.
     */
    private double value;

    /**
     * Contains requisites.
     */
    private String requisites;

    /**
     * Constructor object Account.
     *
     * @param value new value.
     * @param requisites new requisites.
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValues() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisites, account.requisites);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisites);
    }
}
