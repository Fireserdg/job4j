package ru.job4j.professions;
/**
 *Класс профессии.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 22.04.2018
 */
public class Profession {
    /**
     * Name.
     */
    private String name;

    /**
     * Profession.
     */
    private String profession;

    /**
     * Constructor.
     *
     * @param name name
     * @param profession profession.
     */
    public Profession(String name, String profession) {
        this.name = name;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public String getProfession() {
        return profession;
    }
}
