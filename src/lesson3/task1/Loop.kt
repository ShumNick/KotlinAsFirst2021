@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Integer.max
import kotlin.math.*

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
    for (i in 1..n) {
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
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
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
    for (m in 2..n / 2) {
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
    var count = 0
    var number = n
    do {
        count++
        number /= 10
    } while (abs(number) > 0)

    return count


}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result = 1
    var fib1 = 1
    var fib2 = 1

    for (i in 3..n) {
        result = fib1 + fib2
        fib2 = fib1
        fib1 = result
    }
    return result
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..n / 2)
        if (n % i == 0) return i
    return n
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for (i in n / 2 downTo 2)
        if (n % i == 0) return i
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
    var numb = x
    var count = 0
    while (numb != 1) {
        if (numb % 2 == 0)
            numb /= 2
        else
            numb = 3 * numb + 1
        count++
    }
    return count
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val num = if (n > m) m else n
    var nok = num
    if (m % n == 0) return m
    else if (n % m == 0) return n
    else if (isPrime(n) && isPrime(m)) return m * n
    else while (nok != m * n) {
        if (nok % n == 0 && nok % m == 0) break
        nok += num
    }
    return nok
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (m == n) return true
    var dividend = max(m, n)
    var divider = min(m, n)

    while (divider != 0) {
        val tmpDivider = divider
        divider = dividend % divider
        dividend = tmpDivider
        if (divider == 1)
            return true
    }
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
    var digit = 0
    var number = n
    var revNumber = 0

    while (number != 0) {
        digit++
        number /= 10
    }
    number = n
    for (i in digit downTo 1) {
        revNumber += number % 10 * 10.0.pow((digit - 1).toDouble()).toInt()
        number /= 10
        digit--
    }
    return revNumber
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
fun isPalindrome(n: Int): Boolean {
    var number = n
    var digit = 0

    while (number != 0) {
        digit++
        number /= 10
    }
    number = n

    for (i in digit / 2 downTo 1) {
        val last = number % 10
        val first = number / (10.0.pow(digit - 1).toInt())
        if (first != last) return false
        number %= (10.0.pow(digit - 1).toInt())
        number /= 10
        digit -= 2

    }
    return true
}

/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val first = n % 10
    var number = n / 10
    while (number != 0) {
        val second = number % 10
        if (first != second) return true
        number /= 10
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
fun sin(x: Double, eps: Double): Double = TODO()

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double = TODO()

    /**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var sub = 1
    var count = 0
    var subCount = 0


    while (true) {
        var subSquare = sub * sub

        while (subSquare > 0) {
            subCount++
            subSquare /= 10
        }
        subSquare = sub * sub
        var tmpSubCount = subCount
        for (i in 1..subCount) {

            if (tmpSubCount > 1) {
                val number = subSquare / 10.0.pow(tmpSubCount - 1).toInt()
                subSquare %= 10.0.pow(tmpSubCount - 1).toInt()
                count++
                if (count == n) return number
                tmpSubCount--
            } else {
                val number = subSquare % 10
                count++
                if (count == n) return number
            }
        }
        sub++
        subCount = 0
    }
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
fun fibSequenceDigit(n: Int): Int {
    var fib1 = 0
    var fib2 = 1
    var count = 0
    var subCount = 0



    while (true) {
        val oldSub = fib1 + fib2
        var subFib = oldSub

        while (subFib > 0) {
            subCount++
            subFib /= 10
        }
        subFib = oldSub
        var tmpSubCount = subCount
        for (i in 1..subCount) {

            if (tmpSubCount > 1) {
                val number = subFib / 10.0.pow(tmpSubCount - 1).toInt()
                subFib %= 10.0.pow(tmpSubCount - 1).toInt()
                count++
                if (count == n) return number
                tmpSubCount--
            } else {
                val number = subFib % 10
                count++
                if (count == n) return number
            }
        }
        fib2 = fib1
        fib1 = oldSub
        subCount = 0
    }

}
