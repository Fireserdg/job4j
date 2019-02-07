package ru.job4j.professions;
/**
 *Класс Engineer с параметрами, наследуемыми от класса Profession.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 22.04.2018
 */
public class Engineer extends Profession {

    public Engineer(String name, String profession) {
        super(name, profession);
    }

    public void buildHouse(House house) {
    }
}
