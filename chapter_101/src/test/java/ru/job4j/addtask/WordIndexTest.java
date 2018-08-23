package ru.job4j.addtask;

import org.junit.*;
import java.util.Set;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test Word index.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.08.2018.
 */
public class WordIndexTest {

    /**
     * Contains text file.
     *
     */
    private StringBuilder textFile;

    /**
     * Create method for test.
     *
     */
    @Before
    public void wordTest() {
        this.textFile = new StringBuilder()
                .append("The main landmass of the United States lies")
                .append("in central North America. In the north it borders on Canada!")
                .append(System.lineSeparator())
                .append("In the south it borders on Mexico. The United States is washed ")
                .append("by the Atlantic Ocean in the east and the Pacific Ocean in the west.")
                .append(System.lineSeparator())
                .append("The two newest states, Alaska (united in 1959) and Hawaii ")
                .append("(united in 1959), are separated from the continental Unites States.")
                .append(System.lineSeparator())
                .append("Alaska borders on northwestern Canada. ")
                .append("Hawaii lies in the central Pacific.");
    }
    @Test
    public void whenFindWordAndGetList2Index() {
        WordIndex index = new WordIndex();
        index.loadFile(textFile.toString());
        Set<Integer> result = index.getIndexes4Word("Alaska");
        assertThat(result.contains(259), is(true));
        assertThat(result.contains(362), is(true));
        assertThat(textFile.indexOf("Alaska"), is(259));
        assertThat(textFile.indexOf("Alaska", 260), is(362));
    }

    @Test
    public void whenFindWordAndGetList3Index() {
        WordIndex index = new WordIndex();
        index.loadFile(textFile.toString());
        Set<Integer> result = index.getIndexes4Word("The");
        assertThat(result.contains(0), is(true));
        assertThat(result.contains(139), is(true));
        assertThat(result.contains(236), is(true));
        assertThat(textFile.indexOf("The"), is(0));
        assertThat(textFile.indexOf("The", 1), is(139));
        assertThat(textFile.indexOf("The", 140), is(236));
    }

    @Test
    public void whenFindWordButIsNotInText() {
        WordIndex index = new WordIndex();
        index.loadFile(this.textFile.toString());
        Set<Integer> result = index.getIndexes4Word("Apple");
        assertNull(result);
    }

}