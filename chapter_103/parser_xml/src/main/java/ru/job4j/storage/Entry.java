package ru.job4j.storage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class Entry for contains fields from database.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    /**
     * Contains field.
     *
     */
    @XmlElement
    private int field;

    /**
     * Set field.
     *
     * @param field value of field.
     */
    public void setField(int field) {
        this.field = field;
    }

    /**
     * Get field.
     *
     * @return value of field.
     */
    public int getField() {
        return this.field;
    }
}