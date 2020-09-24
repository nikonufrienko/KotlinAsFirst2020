@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = TODO()

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty())
        return list
    val decrement = mean(list)
    for (i in list.indices)
        list[i] -= decrement
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in a.indices)
        result += a[i] * b[i]
    return result
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    var buff = 1
    for (i in p.indices) {
        result += p[i] * buff
        buff *= x
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var buff = 0
    for (i in list.indices) {
        buff += list[i]
        list[i] = buff
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val answer = mutableListOf<Int>()
    for (divisor in 2..n) {
        if (n % divisor != 0)
            continue
        var check = true
        for (prevDivisor in answer)
            if (divisor % prevDivisor == 0)
                check = false
        if (check) {
            answer.add(divisor)
            if (n % sqr(divisor) == 0 && n != Int.MAX_VALUE)
                answer.add(divisor)
        }
    }
    return answer
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString("*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    val answer = mutableListOf<Int>()
    var n2 = n
    while (n2 > 0) {
        answer.add(n2 % base)
        n2 /= base
    }
    if (answer.isEmpty())
        return listOf(0)
    return answer.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    var answer = ""
    val convertedToList = convert(n, base)
    for (digit in convertedToList)
        if (digit > 9)
            answer += 'a' + digit - 10  //частенько юзал эту фичу в Си
        else
            answer += digit.toString()
    return answer
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var answer = 0
    var currPow = 1
    for (digit in digits.reversed()) {
        answer += currPow * digit
        currPow *= base
    }
    return answer
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val listForDecimal = mutableListOf<Int>()
    for (digit in str) {
        listForDecimal += if (digit in (0..9).joinToString(""))
            digit.toString().toInt()
        else
            (digit - 'a' + 10)
    }
    return decimal(listForDecimal, base)
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = when {
    n == 0 -> ""
    n < 4 -> "I" + roman(n - 1)
    n == 4 -> "IV"
    n in 5..8 -> "V" + roman(n - 5)
    n == 9 -> "IX" 
    n in 10..39 -> "X" + roman(n - 10)
    n in 40..49 -> "XL" + roman(n - 40)
    n in 50..89 -> "L" + roman(n - 50)
    n in 90..99 -> "XC" + roman(n - 90)
    n in 100..399 -> "C" + roman(n - 100)
    n in 400..499 -> "CD" + roman(n - 400)
    n in 500..900 -> "D" + roman(n - 500)
    n in 900..999 -> "CM" + roman(n - 900)
    n >= 1000 -> "M" + roman(n - 1000)
    else -> "ERROR"
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String = when(n) {
    0 -> ""
    1 -> "один "
    2 -> "два "
    3 -> "три "
    4 -> "четыре "
    5 -> "пять "
    6 -> "шесть "
    7 -> "семь "
    8 -> "восемь "
    9 -> "девять "
    10 -> "десять "
    11 -> "одиннадцать "
    12 -> "двенадцать "
    13 -> "тринадцать "
    14 -> "четырнадцать "
    15 -> "пятнадцать "
    16 -> "шестнадцать "
    17 -> "семнадцать "
    18 -> "восемнадцать "
    19 -> "девятнадцать "
    in 20..29 -> "двадцать " + russian(n - 20)
    in 30..39 -> "тридцать " + russian(n - 30)
    in 40..49 -> "сорок " + russian(n - 40)
    in 50..59 -> "пятьдесят " + russian(n - 50)
    in 60..69 -> "шестьдесят " + russian(n - 60)
    in 70..79 -> "семьдесят " + russian(n - 70)
    in 80..89 -> "восемьдесят " + russian(n - 80)
    in 90..99 -> "девяносто " + russian(n - 90)
    in 100..199 -> "сто " + russian(n - 100)
    in 200..299 -> "двести " + russian(n - 200)
    in 300..399 -> "триста " + russian(n - 300)
    in 400..499 -> "четыреста " + russian(n - 400)
    in 500..599 -> "пятьсот " + russian(n - 500)
    in 600..699 -> "шестьсот " + russian(n - 600)
    in 700..799 -> "семьсот " + russian(n - 700)
    in 800..899 -> "восемьсот " + russian(n - 800)
    in 900..999 -> "девятьсот " + russian(n - 900)
    in 1000..999999 -> when (n / 1000) {
        1 -> "одна тысяча " + russian(n - 1000)
        2 -> "две тысячи " + russian(n - 2000)
        3 -> "три тысячи " + russian(n - 3000)
        4 -> "четыре тысячи " + russian(n - 4000)
        in 5..19 -> russian(n / 1000) + " тысяч " + russian(n % 1000)
        in 20..99 -> russian((n / 10000) * 10) + " " + (if (n % 10000 < 1000) "тысяч " else "") + russian(n % 10000)
        in 100..999999 -> russian((n / 100000) * 100) + " " + (if (n % 100000 < 1000) "тысяч " else "") + russian(n % 100000)
        else -> "ERROR"
    }
    else -> "ERROR"
}.trim()
fun main()
{
    print(russian(508))
}
