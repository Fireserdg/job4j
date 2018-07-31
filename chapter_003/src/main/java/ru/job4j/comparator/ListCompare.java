package ru.job4j.comparator;

import java.util.Arrays;
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
        int size = left.length() - right.length();
        if (size < 0) {
            while (size != 0) {
                left = String.format("%s ", left);
                size++;
            }
            size = left.length();
        } else if (size > 0) {
            while (size != 0) {
                right = String.format("%s ", right);
                size--;
            }
            size = right.length();
        } else {
            size = left.length();
        }

        for (int i = 0; i < size; i++) {
            result += Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }
        return result;
    }
}
