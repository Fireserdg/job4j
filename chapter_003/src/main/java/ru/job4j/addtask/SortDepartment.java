package ru.job4j.addtask;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Sort Department.
 *
 * @author Sergey Filippov (serdg1984@yandex.ru).
 * @version $Id$.
 * @since 05.08.2018.
 */
public class SortDepartment {

    /**
     * Sort Departments by increase.
     *
     * @param list list position Departments.
     * @return sort list Departments by Decrease.
     */
    public String[] sortIncrease(String[] list) {
        return addElement(list).stream().sorted().toArray(String[]::new);
    }

    /**
     * Sort Departments by decrease.
     *
     * @param list list position Departments.
     * @return sort list Departments by Decrease.
     */
    public String[] sortDecrease(String[] list) {
        return addElement(list)
                .stream().sorted(((o1, o2) -> IntStream.range(0, Math.min(o1.length(),
                        o2.length())).filter(index -> o1.charAt(index) != o2.charAt(index))
                        .map(result -> o2.charAt(result) - o1.charAt(result))
                        .findFirst().orElse(o1.length() - o2.length())))
                .toArray(String[]::new);
    }

    /**
     * Add missing divisions.
     *
     * @param array List of departments as an array of strings.
     * @return full List of departments
     */
    private Set<String> addElement(String[] array) {
        Set<String> result = new HashSet<>();
        Arrays.stream(array).forEach(element -> {
            String[] arrays = element.split("\\\\");
            System.out.println(Arrays.toString(arrays));
            IntStream.range(0, arrays.length - 1).forEach(x -> {
                String value = String.format("%s\\%s", arrays[x], arrays[x + 1]);
                result.add(arrays[x]);
                result.add(value);
                arrays[x + 1] = value;
            });
        });
        return result;
    }
}
