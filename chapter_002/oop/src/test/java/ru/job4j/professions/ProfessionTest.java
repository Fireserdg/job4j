package ru.job4j.professions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Profession test.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 07.02.19
 */
public class ProfessionTest {

    @Test
    public void whenCreateProfession() {
        Profession profession = new Profession("default", "none");
        Doctor doctor = new Doctor("Bill", "doctor");
        Patient patient = new Patient("Mike");
        doctor.treatPatient(patient);
        Engineer engineer = new Engineer("Ben", "engineer");
        engineer.buildHouse(new House());
        Teacher teacher = new Teacher("Mina", "history");
        assertThat(profession.getName(), is("default"));
        assertThat(profession.getProfession(), is("none"));
        assertThat(doctor.getName(), is("Bill"));
        assertThat(doctor.getProfession(), is("doctor"));
        assertThat(patient.getName(), is("Mike"));
        assertThat(engineer.getName(), is("Ben"));
        assertThat(engineer.getProfession(), is("engineer"));
        assertThat(teacher.getName(), is("Mina"));
        assertThat(teacher.getProfession(), is("history"));
    }
}