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
        int result = 1;
        int size = left.length() < right.length() ? left.length() : right.length();
        for (int i = 0; i < size; i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }

        if (result == 0) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}
