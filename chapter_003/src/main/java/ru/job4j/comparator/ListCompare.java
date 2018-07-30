package ru.job4j.comparator;

import java.util.Comparator;

/**
 * List Compare.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 31.07.2018.
 */
public class ListCompare implements Comparator<String> {

    /**
     * Compare 2 words.
     *
     * @param left one String.
     * @param right two String.
     * @return result compare.
     */
    @Override
    public int compare(String left, String right) {
        int result = 0;
        if (left.length() == right.length()) {
            for (int i = 0; i < left.length(); i++) {
                result += Character.compare(left.toCharArray()[i], right.toCharArray()[i]);
            }
        } else {
            result = left.length() > right.length() ? 1 : -1;
        }
        return result;
    }
}
