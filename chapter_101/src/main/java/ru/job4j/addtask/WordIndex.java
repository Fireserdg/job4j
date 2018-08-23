package ru.job4j.addtask;

import java.util.*;

/**
 * Word index.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.08.2018.
 */
public class WordIndex {

    /**
     * Contains container for word and positions.
     *
     */
    private Map<String, Set<Integer>> container;

    /**
     * Loading data from the file and building an index.
     *
     * @param filename the input file name.
     */
    public void loadFile(String filename) {
        int index;
        this.container = new HashMap<>();
        char[] symbol = filename.toCharArray();
        for (int i = 0; i < symbol.length; i++) {
            StringBuilder result = new StringBuilder();
            if (Character.isLetter(symbol[i])) {
                index = i;
                while (Character.isLetter(symbol[i])) {
                    result.append(symbol[i]);
                    if (i == symbol.length - 1) {
                        break;
                    }
                    i++;
                }
                if (!this.container.containsKey(result.toString())) {
                    this.container.put(result.toString(), new TreeSet<>());
                    this.container.get(result.toString()).add(index);
                } else {
                    this.container.get(result.toString()).add(index);
                }
            }
        }
    }

    /**
     * Get list index for the word.
     *
     * @param searchWord searchWord.
     * @return list position searchWord or null if word hasn't in list.
     */
    public Set<Integer> getIndexes4Word(String searchWord) {
        Set<Integer> tree = null;
        if (this.container.containsKey(searchWord)) {
            tree = this.container.get(searchWord);
        }
        return tree;
    }
}