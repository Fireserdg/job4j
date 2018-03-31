package ru.job4j.max;
/**
*@author Sergey Filippov (serdg1984@yandex.ru)
*@version $Id$
*@since 0.1
*/
public class Max {
	/**
	* Отвечает на вопросы.
	* @param first and second Значение.
	* @return Возвращает максимальное число.
	*/
	public int max(int first, int second) {
		return first > second ? first : second;
	}
}