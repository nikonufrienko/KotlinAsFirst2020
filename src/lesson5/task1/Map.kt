@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson5.task1

import kotlin.collections.setOf as setOf


// Урок 5: ассоциативные массивы и множества
// Максимальное количество баллов = 14
// Рекомендуемое количество баллов = 9
// Вместе с предыдущими уроками = 33/47

/**
 * Пример
 *
 * Для заданного списка покупок `shoppingList` посчитать его общую стоимость
 * на основе цен из `costs`. В случае неизвестной цены считать, что товар
 * игнорируется.
 */
fun shoppingListCost(
    shoppingList: List<String>,
    costs: Map<String, Double>
): Double {
    var totalCost = 0.0

    for (item in shoppingList) {
        val itemCost = costs[item]
        if (itemCost != null) {
            totalCost += itemCost
        }
    }

    return totalCost
}

/**
 * Пример
 *
 * Для набора "имя"-"номер телефона" `phoneBook` оставить только такие пары,
 * для которых телефон начинается с заданного кода страны `countryCode`
 */
fun filterByCountryCode(
    phoneBook: MutableMap<String, String>,
    countryCode: String
) {
    val namesToRemove = mutableListOf<String>()

    for ((name, phone) in phoneBook) {
        if (!phone.startsWith(countryCode)) {
            namesToRemove.add(name)
        }
    }

    for (name in namesToRemove) {
        phoneBook.remove(name)
    }
}

/**
 * Пример
 *
 * Для заданного текста `text` убрать заданные слова-паразиты `fillerWords`
 * и вернуть отфильтрованный текст
 */
fun removeFillerWords(
    text: List<String>,
    vararg fillerWords: String
): List<String> {
    val fillerWordSet = setOf(*fillerWords)

    val res = mutableListOf<String>()
    for (word in text) {
        if (word !in fillerWordSet) {
            res += word
        }
    }
    return res
}

/**
 * Пример
 *
 * Для заданного текста `text` построить множество встречающихся в нем слов
 */
fun buildWordSet(text: List<String>): MutableSet<String> {
    val res = mutableSetOf<String>()
    for (word in text) res.add(word)
    return res
}


/**
 * Простая (2 балла)
 *
 * По заданному ассоциативному массиву "студент"-"оценка за экзамен" построить
 * обратный массив "оценка за экзамен"-"список студентов с этой оценкой".
 *
 * Например:
 *   buildGrades(mapOf("Марат" to 3, "Семён" to 5, "Михаил" to 5))
 *     -> mapOf(5 to listOf("Семён", "Михаил"), 3 to listOf("Марат"))
 */
fun buildGrades(grades: Map<String, Int>): Map<Int, List<String>> {
    val answerGrades = mutableMapOf<Int, MutableList<String>>()
    for ((student, rating) in grades)
        answerGrades[rating]?.add(student) ?: (answerGrades.getOrPut(rating) { mutableListOf(student) })
    return answerGrades
}

/**
 * Простая (2 балла)
 *
 * Определить, входит ли ассоциативный массив a в ассоциативный массив b;
 * это выполняется, если все ключи из a содержатся в b с такими же значениями.
 *
 * Например:
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "z", "b" to "sweet")) -> true
 *   containsIn(mapOf("a" to "z"), mapOf("a" to "zee", "b" to "sweet")) -> false
 */
fun containsIn(a: Map<String, String>, b: Map<String, String>): Boolean {
    for ((key, value) in a)
        if (b[key] != value)
            return false
    return true
}

/**
 * Простая (2 балла)
 *
 * Удалить из изменяемого ассоциативного массива все записи,
 * которые встречаются в заданном ассоциативном массиве.
 * Записи считать одинаковыми, если и ключи, и значения совпадают.
 *
 * ВАЖНО: необходимо изменить переданный в качестве аргумента
 *        изменяемый ассоциативный массив
 *
 * Например:
 *   subtractOf(a = mutableMapOf("a" to "z"), mapOf("a" to "z"))
 *     -> a changes to mutableMapOf() aka becomes empty
 */
fun subtractOf(a: MutableMap<String, String>, b: Map<String, String>) {
    for (key in b.keys)
        if (a[key] == b[key])
            a -= key
}

/**
 * Простая (2 балла)
 *
 * Для двух списков людей найти людей, встречающихся в обоих списках.
 * В выходном списке не должно быть повторяюихся элементов,
 * т. е. whoAreInBoth(listOf("Марат", "Семён, "Марат"), listOf("Марат", "Марат")) == listOf("Марат")
 */
fun whoAreInBoth(a: List<String>, b: List<String>): List<String> = (a.toSet().intersect(b.toSet())).toList()

/**
 * Средняя (3 балла)
 *
 * Объединить два ассоциативных массива `mapA` и `mapB` с парами
 * "имя"-"номер телефона" в итоговый ассоциативный массив, склеивая
 * значения для повторяющихся ключей через запятую.
 * В случае повторяющихся *ключей* значение из mapA должно быть
 * перед значением из mapB.
 *
 * Повторяющиеся *значения* следует добавлять только один раз.
 *
 * Например:
 *   mergePhoneBooks(
 *     mapOf("Emergency" to "112", "Police" to "02"),
 *     mapOf("Emergency" to "911", "Police" to "02")
 *   ) -> mapOf("Emergency" to "112, 911", "Police" to "02")
 */
fun mergePhoneBooks(mapA: Map<String, String>, mapB: Map<String, String>): Map<String, String> {
    val result = mapA.toMutableMap()
    for (key in mapB.keys)
        if (result[key] == null)
            result[key] = mapB[key] ?: error("Этого не могло произойти, ведь на это была проверка")
        else if (result[key] != mapB[key])
            result[key] += ", " + mapB[key]
    return result
}

/**
 * Средняя (4 балла)
 *
 * Для заданного списка пар "акция"-"стоимость" вернуть ассоциативный массив,
 * содержащий для каждой акции ее усредненную стоимость.
 *
 * Например:
 *   averageStockPrice(listOf("MSFT" to 100.0, "MSFT" to 200.0, "NFLX" to 40.0))
 *     -> mapOf("MSFT" to 150.0, "NFLX" to 40.0)
 */
fun averageStockPrice(stockPrices: List<Pair<String, Double>>): Map<String, Double> {
    val pricesAndNumber = mutableMapOf<String, Pair<Double, Int>>()
    for ((stock, price) in stockPrices) {
        val currPriceAndNumber = pricesAndNumber[stock]
        if (currPriceAndNumber == null)
            pricesAndNumber[stock] = price to 1
        else pricesAndNumber[stock] = (currPriceAndNumber.first + price) to (currPriceAndNumber.second + 1)
    }
    return pricesAndNumber.mapValues { it.value.first / it.value.second }
}

/**
 * Средняя (4 балла)
 *
 * Входными данными является ассоциативный массив
 * "название товара"-"пара (тип товара, цена товара)"
 * и тип интересующего нас товара.
 * Необходимо вернуть название товара заданного типа с минимальной стоимостью
 * или null в случае, если товаров такого типа нет.
 *
 * Например:
 *   findCheapestStuff(
 *     mapOf("Мария" to ("печенье" to 20.0), "Орео" to ("печенье" to 100.0)),
 *     "печенье"
 *   ) -> "Мария"
 */
fun findCheapestStuff(stuff: Map<String, Pair<String, Double>>, kind: String): String? {
    var currName: String? = null
    var minPrice: Double = Double.MAX_VALUE
    for ((name, typeAndPrice) in stuff)
        if (typeAndPrice.first == kind) {
            if (minPrice >= typeAndPrice.second) {
                minPrice = typeAndPrice.second
                currName = name
            }
        }
    return currName
}

/**
 * Средняя (3 балла)
 *
 * Для заданного набора символов определить, можно ли составить из него
 * указанное слово (регистр символов игнорируется)
 *
 * Например:
 *   canBuildFrom(listOf('a', 'b', 'o'), "baobab") -> true
 */
fun canBuildFrom(chars: List<Char>, word: String): Boolean {
    val lowerChars = chars.map { it.toLowerCase() }.toSet()
    for (char in word.toLowerCase())
        if (char !in lowerChars)
            return false
    return true
}

/**
 * Средняя (4 балла)
 *
 * Найти в заданном списке повторяющиеся элементы и вернуть
 * ассоциативный массив с информацией о числе повторений
 * для каждого повторяющегося элемента.
 * Если элемент встречается только один раз, включать его в результат
 * не следует.
 *
 * Например:
 *   extractRepeats(listOf("a", "b", "a")) -> mapOf("a" to 2)
 */
fun extractRepeats(list: List<String>): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
    for (element in list)
        if (result[element] == null) result[element] = 1
        else result[element] = result[element]?.plus(1) ?: error("Непредвиденная ошибка в extractRepeats")
    return result.filter { it.value > 1 }
}

/**
 * Средняя (3 балла)
 *
 * Для заданного списка слов определить, содержит ли он анаграммы.
 * Два слова здесь считаются анаграммами, если они имеют одинаковую длину
 * и одно можно составить из второго перестановкой его букв.
 * Скажем, тор и рот или роза и азор это анаграммы,
 * а поле и полено -- нет.
 *
 * Например:
 *   hasAnagrams(listOf("тор", "свет", "рот")) -> true
 */
fun hasAnagrams(words: List<String>): Boolean {
    val mapOfWords = mutableMapOf<Int, List<String>>()
    words.forEach { mapOfWords[it.length] = mapOfWords[it.length]?.plus(it) ?: listOf(it) }
    for (wordsOneLong in mapOfWords.values)
        if (wordsOneLong.map { it.toSet() }.toSet().size != wordsOneLong.size)
            return true
    return false
}

/**
 * Сложная (5 баллов)
 *
 * Для заданного ассоциативного массива знакомых через одно рукопожатие `friends`
 * необходимо построить его максимальное расширение по рукопожатиям, то есть,
 * для каждого человека найти всех людей, с которыми он знаком через любое
 * количество рукопожатий.
 *
 * Считать, что все имена людей являются уникальными, а также что рукопожатия
 * являются направленными, то есть, если Марат знает Свету, то это не означает,
 * что Света знает Марата.
 *
 * Оставлять пустой список знакомых для людей, которые их не имеют (см. EvilGnome ниже),
 * в том числе для случая, когда данного человека нет в ключах, но он есть в значениях
 * (см. GoodGnome ниже).
 *
 * Например:
 *   propagateHandshakes(
 *     mapOf(
 *       "Marat" to setOf("Mikhail", "Sveta"),
 *       "Sveta" to setOf("Marat"),
 *       "Mikhail" to setOf("Sveta"),
 *       "Friend" to setOf("GoodGnome"),
 *       "EvilGnome" to setOf()
 *     )
 *   ) -> mapOf(
 *          "Marat" to setOf("Mikhail", "Sveta"),
 *          "Sveta" to setOf("Marat", "Mikhail"),
 *          "Mikhail" to setOf("Sveta", "Marat"),
 *          "Friend" to setOf("GoodGnome"),
 *          "EvilGnome" to setOf(),
 *          "GoodGnome" to setOf()
 *        )
 */
fun propagateHandshakes(friends: Map<String, Set<String>>): Map<String, Set<String>> {
    val allPersons = friends.keys.toMutableSet()
    val result = mutableMapOf<String, Set<String>>()
    for ((person, friendsOfPerson) in friends) {
        allPersons.addAll(friendsOfPerson)
        val used = mutableSetOf(person) //уже рассмотренные
        val toUse = friendsOfPerson.toMutableSet() //список на рассмотрение
        while (toUse.isNotEmpty()) {
            val currPerson = toUse.random() //я не знаю как иначе получить элемент из множества
            toUse += (friends[currPerson] ?: setOf()).toSet()
            used += currPerson
            toUse -= used
        }
        result[person] = used - person
    }
    for (person in allPersons)
        if (person !in result.keys)
            result[person] = setOf()
    return result
}

/**
 * Сложная (6 баллов)
 *
 * Для заданного списка неотрицательных чисел и числа определить,
 * есть ли в списке пара чисел таких, что их сумма равна заданному числу.
 * Если да, верните их индексы в виде Pair<Int, Int>;
 * если нет, верните пару Pair(-1, -1).
 *
 * Индексы в результате должны следовать в порядке (меньший, больший).
 *
 * Постарайтесь сделать ваше решение как можно более эффективным,
 * используя то, что вы узнали в данном уроке.
 *
 * Например:
 *   findSumOfTwo(listOf(1, 2, 3), 4) -> Pair(0, 2)
 *   findSumOfTwo(listOf(1, 2, 3), 6) -> Pair(-1, -1)
 */
fun findSumOfTwo(list: List<Int>, number: Int): Pair<Int, Int> {
    val mapOfNumbers = mutableMapOf<Int, MutableList<Int>>()
    val err = "Ошибка которая всё равно никогда не произойдёт"
    list.forEachIndexed { index, value ->
        if (mapOfNumbers[value]?.size ?: 0 < 2)
            mapOfNumbers[value]?.add(index) ?: mapOfNumbers.put(value, mutableListOf(index))
    }
    for (i in mapOfNumbers.keys.filter { it <= number / 2 }) {
        if (i != number - i && mapOfNumbers[i] != null && mapOfNumbers[number - i] != null)
            return (mapOfNumbers[i]?.get(0) ?: error(err)) to (mapOfNumbers[number - i]?.get(0) ?: error(err))
        else if (i == number - i && (mapOfNumbers[i]?.size ?: 0) > 1)
            return (mapOfNumbers[i]?.get(0) ?: error(err)) to (mapOfNumbers[i]?.get(1) ?: error(err))
    }
    return -1 to -1
}

/**
 * Очень сложная (8 баллов)
 *
 * Входными данными является ассоциативный массив
 * "название сокровища"-"пара (вес сокровища, цена сокровища)"
 * и вместимость вашего рюкзака.
 * Необходимо вернуть множество сокровищ с максимальной суммарной стоимостью,
 * которые вы можете унести в рюкзаке.
 *
 * Перед решением этой задачи лучше прочитать статью Википедии "Динамическое программирование".
 *
 * Например:
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     850
 *   ) -> setOf("Кубок")
 *   bagPacking(
 *     mapOf("Кубок" to (500 to 2000), "Слиток" to (1000 to 5000)),
 *     450
 *   ) -> emptySet()
 */


fun bagPacking(treasures: Map<String, Pair<Int, Int>>, capacity: Int): Set<String> {
    val capacityMap = mutableMapOf<Int, Pair<Int, Pair<Set<String>, Set<String>>>>()
    // key: [масса] value: ([цена множества] to [[множество использованных имён] to [множество возможных имен] ])
    //val massList = treasures.toList().map { it.second.first to (it.second.second to it.first) }.sortedBy { it.first }
    // [масса] to ([цена]  to [имя])
    capacityMap[0] = 0 to (setOf<String>() to treasures.keys.toSet())
    var check = true
    while (check) {
        check = false
        val newToCapacityMap = mutableMapOf<Int, Pair<Int, Pair<Set<String>, Set<String>>>>()
        for ((curCap, propertiesOfCurCap) in capacityMap) {
            val value = propertiesOfCurCap.first
            val used = propertiesOfCurCap.second.first
            val toUse = propertiesOfCurCap.second.second
            for (name in toUse) {
                val treasure = treasures[name] ?: error("Этого не должно было быть здесь")
                val mass = treasure.first
                val valueOfTreasure = treasure.second
                if (mass + curCap <= capacity) {
                    if (capacityMap[mass + curCap] == null) {
                        newToCapacityMap[mass + curCap] = value + valueOfTreasure to (used + name to toUse - name)
                        check = true
                    } else if (valueOfTreasure + value
                        > capacityMap[mass + curCap]?.first ?: error("Ошибка которая всё равно никогда не произойдёт")
                    ) {
                        capacityMap[mass + curCap] = value + valueOfTreasure to (used + name to toUse - name)
                        check = true
                    }
                } else capacityMap[curCap] = value to (used to toUse - name)
            }
        }
        capacityMap += newToCapacityMap
    }
    return capacityMap.maxByOrNull { it.value.first }!!.value.second.first
}