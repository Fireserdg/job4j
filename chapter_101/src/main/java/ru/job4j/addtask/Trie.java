package ru.job4j.addtask;

import java.util.*;

/**
 * Prefix Trie.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 22.08.2018.
 */
public class Trie {

    /**
     * Contains root in trie.
     *
     */
    private final TrieNode root;

    /**
     * Constructor.
     *
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Insert in Trie.
     *
     * @param word new word.
     * @param index index first letter.
     */
   public void insert(String word, int index) {
       TrieNode current = this.root;
       for (int i = 0; i < word.length(); i++) {
           char ch = word.charAt(i);
           TrieNode node = current.children.get(ch);
           if (node == null) {
               node = new TrieNode();
               current.children.put(ch, node);
           }
           current = node;
       }
       current.index.add(index);
       current.isEnd = true;
   }

    /**
     * Find word and get set index.
     *
     * @param word word
     * @return index of set.
     */
    public Set<Integer> find(String word) {
        TrieNode current = this.root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                break;
            }
            current = node;
        }
        return current.isEnd ? current.getIndex() : null;
    }

    /**
     * Trie node.
     *
     */
    private class TrieNode {

        /**
         * Contains children's node.
         *
         */
        Map<Character, TrieNode> children;

        /**
         * If word is end.
         *
         */
        boolean isEnd;

        /**
         * Contains index first letter word.
         *
         */
        Set<Integer> index;

        /**
         * Constructor.
         *
         */
        TrieNode() {
            this.children = new TreeMap<>();
            this.isEnd = false;
            this.index = new TreeSet<>();
        }

        /**
         * Get index set.
         *
         * @return index set.
         */
        public Set<Integer> getIndex() {
            return this.index;
        }
    }
}