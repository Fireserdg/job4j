package ru.job4j.professions;
/**
 *Класс Teacher с параметрами, наследуемыми от класса Profession.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 22.04.2018
 */
public class Teacher extends Profession {

    /**
     * Constructor teacher.
     *
     * @param name teacher name.
     * @param profession profession.
     */
    public Teacher(String name, String profession) {
        super(name, profession);
    }

    public void teachStudents() {

    }
}
