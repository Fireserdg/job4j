package ru.job4j.storage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *  Entries for contains Entry.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 04.10.2018.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {

    /**
     * Contains list of entry.
     *
     */
    @XmlElement
    private List<Entry> entry = new ArrayList<>();

    /**
     * Set list Entry for field entry.
     *
     * @param entry list of entry.
     */
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    /**
     * Get entry.
     *
     * @return list of the entry.
     */
    public List<Entry> getEntry() {
        return this.entry;
    }
}