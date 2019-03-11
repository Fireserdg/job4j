package ru.job4j.scan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.scan.util.TempFilesUtils;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static ru.job4j.scan.util.TempFilesUtils.ROOT;

/**
 * Search test
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version 1.0.
 * @since 2019-03-06
 */
public class SearchTest {

    private TempFilesUtils filesTree;

    @Before
    public void beforeTest() throws IOException {
        filesTree = new TempFilesUtils();
        filesTree.init();
    }

    @After
    public void afterTest() {
        filesTree.delete();
    }

    @Test
    public void getListOfFilesTxt() {
        String root = ROOT.getPath();
        List<File> actual = new Search().files(root, List.of("txt"));
        assertThat(actual.size(), is(4));
    }

    @Test
    public void getListOfFilesXml() {
        String root = ROOT.getPath();
        List<File> actual = new Search().files(root, List.of("xml"));
        assertThat(actual.size(), is(2));
    }

    @Test
    public void getListOfFilesJpg() {
        String root = ROOT.getPath();
        List<File> actual = new Search().files(root, List.of("jpg"));
        assertThat(actual.size(), is(2));
        Set<String> set = new HashSet<>();
        actual.forEach(file -> set.add(file.toString()
                .substring(file.toString().lastIndexOf("/") + 1)));
        assertThat(set, is(Set.of("jpg_2.jpg", "jpg_1.jpg")));
    }

    @Test
    public void getListAllFiles() {
        String root = ROOT.getPath();
        List<File> actual = new Search().files(root, List.of("txt", "jpg", "xml"));
        assertThat(actual.size(), is(8));
    }
}