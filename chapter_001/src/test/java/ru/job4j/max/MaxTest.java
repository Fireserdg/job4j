package ru.job4j.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
*@author Sergey Filippov (serdg1984@yandex.ru)
*@version $Id$
*@since 0.1
*/
public class MaxTest {
	@Test
	public void maximumNumber() {
    Max maxim = new Max();
    int result = maxim.max(6, 3, 5);
    assertThat(result, is(6));
	}
}