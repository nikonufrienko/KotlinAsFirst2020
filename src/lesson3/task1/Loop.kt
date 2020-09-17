@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.*


//import kotlin.math
// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1 .. n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3 .. sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2 .. (n / 2)) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var z = abs(n)
    var i = 0
    do {
        i++
        z /= 10
    } while (z > 0)
    return i
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
/*
fun pow(var n:Int): Int{

}*/
fun fib(n: Int): Int {
    val k1: Double = ((1 + sqrt(5.0)) / 2).pow(n.toDouble())
    val k2: Double = (1 - sqrt(5.0)) / 2
    return ((k1 - k2) / sqrt(5.0)).toInt()
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2 .. sqrt(n.toDouble()).toInt())
        if (n % i == 0)
            return i
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in (sqrt(n.toDouble()).toInt() until n).reversed())
        if (n % i == 0)
            return i
    return 1
}

/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var k = x
    var i = 0
    while (k != 1) {
        if (k % 2 == 0)
            k /= 2
        else
            k = k * 3 + 1
        i++
    }
    return i
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var a = maxOf(m, n)
    val d = a
    val c = minOf(m, n)
    while (a % c != 0)
        a += d
    return a
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (minOf(m, n) < 4)
        return true
    val a = maxOf(m, n)
    val b = minOf(m, n)
    for (i in 2 .. sqrt(a.toDouble()).toInt())
        if (a % i == 0)
            if (b % i == 0 || b % (a / i) == 0)
                return false
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val a = sqrt(m.toDouble())
    val b = sqrt(n.toDouble())
    if (b.toInt() - a.toInt() >= 1 || a == a.toInt().toDouble() || b == b.toInt().toDouble())
        return true
    return false

}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var m = n
    var k = 0
    while (m > 0) {
        k *= 10
        k += m % 10
        m /= 10
    }
    return k
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = revert(n) == n

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var a = n
    val b = a % 10
    while (a > 0) {
        if (a % 10 != b)
            return true
        a /= 10
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val x2 = x % (2 * PI)
    var d = x2
    var a = 0.0
    var i = 1
    do {
        a += d
        d *= -(x2 * x2) / (2 * i * (2 * i + 1))
        i++
    } while (abs(d) > eps)
    return a
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = sin(PI / 2 - x % (2 * PI), eps)

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 1 4 9 16 25 36 496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int? {
    if (n < 0)
        return null
    var a = 0
    var currDigitNumber = 0 //кол-во цифр с учётом текущего числа
    while (currDigitNumber < n) {
        a++
        if (a > 46340) //ограничение для того что бы квадрат числа не привысил макс. знач. int
            return null
        currDigitNumber += digitNumber(a * a)
    }
    var digitNumberInNumber = n - currDigitNumber + digitNumber(a * a)
    var revertedNumber = revert(a * a) //переворачиваем число для упрощения
    while (digitNumberInNumber > 1) {
        revertedNumber /= 10
        digitNumberInNumber--
    }
    return revertedNumber % 10

}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int? {
    var a = 0
    var buff = 0 //здесь будет лежать значение fib(a) что бы не вызывать функцию по несколько раз
    var currDigitNumber = 0 //кол-во цифр с учётом текущего числа
    while (currDigitNumber < n) {
        a++
        buff = fib(a)
        if (buff == 2147483647) //тк в функции fib происходит приведение через метод toDouble
            return null
        currDigitNumber += digitNumber(buff)
    }
    var digitNumberInNumber = n - currDigitNumber + digitNumber(buff)
    var revertedNumber = revert(buff) //переворачиваем число для упрощения
    while (digitNumberInNumber > 1) {
        revertedNumber /= 10
        digitNumberInNumber--
    }
    return revertedNumber % 10
}
