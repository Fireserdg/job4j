package ru.job4j.professions;
/**
 *Класс Patient с параметрами, наследуемыми от класса Doctor.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 22.04.2018
 */
public class Patient {
    /**
     * Name patient.
     */
    private String name;

    /**
     * Constructor.
     * @param name name.
     */
    public Patient(String name) {
        this.name = name;
    }

    /**
     * Get patient name.
     * @return name.
     */
    public String getName() {
        return name;
    }
}
