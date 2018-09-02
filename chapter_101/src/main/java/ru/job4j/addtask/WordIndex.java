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
     * Contains prefix trie for contains words.
     *
     */
    private final Trie trie = new Trie();

    /**
     * Loading data from the file and building an index.
     *
     * @param filename the input file name.
     */
    public void loadFile(String filename) {
        int index;
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
                trie.insert(result.toString(), index);
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
        return trie.find(searchWord);
    }
}