package ru.job4j.professions;
/**
 *Класс Doctor с параметрами, наследуемыми от класса Profession.
 *
 *@author Sergey Filippov (serdg1984@yandex.ru)
 *@version $Id$
 *@since 22.04.2018
 */
public class Doctor extends Profession {

    public Doctor(String name, String profession) {
        super(name, profession);
    }

    public void treatPatient(Patient patient) {
    }
}
