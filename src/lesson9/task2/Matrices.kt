@file:Suppress("UNUSED_PARAMETER")

package lesson9.task2

import lesson9.task1.Cell
import lesson9.task1.Matrix
import lesson9.task1.MatrixImpl
import lesson9.task1.createMatrix
import java.util.*
import javax.swing.Spring
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    require(!(width != other.width || height != other.height))
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */
fun generateSpiral(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */
fun generateRectangles(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */
fun generateSnake(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */
fun <E> rotate(matrix: Matrix<E>): Matrix<E> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */
fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Средняя (4 балла)
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes = TODO()

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя (3 балла)
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Простая (2 балла)
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> = TODO(this.toString())

/**
 * Средняя (4 балла)
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> = TODO(this.toString())

/**
 * Сложная (7 баллов)
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> = TODO()

/**
 * Сложная (8 баллов)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */
fun Matrix<Int>.getCopy(): Matrix<Int> {
    val list = mutableListOf<Int>()
    for (y in 0 until height)
        for (x in 0 until width)
            list.add(this[y, x])
    return MatrixImpl(height, width, list)

}

fun Matrix<Int>.search(value: Int): Cell {
    for (y in 0 until height)
        for (x in 0 until width)
            if (this[y, x] == value) return Cell(y, x)
    return Cell(-1, -1)
}

data class Field15(val matrix: Matrix<Int>) {
    private var currentZeroPos = Cell(-1, -1)
    fun findZeroPos() {
        currentZeroPos = matrix.search(0)
    }

    val listOfActions = mutableListOf<Int>()
    val setOfClosed = mutableSetOf<Int>()
    var currentStart = Cell(0, 0)
    fun seeDifferences(other: Matrix<Int>): Int {
        var counter = 0
        for (y in 0 until matrix.height)
            for (x in 0 until matrix.width)
                if (other[y, x] != matrix[y, x] && matrix[y, x] != 0)
                    counter++
        return counter
    }

    fun toRight() {
        val action = matrix[currentZeroPos.row, currentZeroPos.column + 1]
        doAction(action)
        listOfActions.add(action)
    }

    fun toLeft() {
        val action = matrix[currentZeroPos.row, currentZeroPos.column - 1]
        doAction(action)
        listOfActions.add(action)
    }

    fun toUp() {
        val action = matrix[currentZeroPos.row - 1, currentZeroPos.column]
        doAction(action)
        listOfActions.add(action)
    }

    fun toDown() {
        val action = matrix[currentZeroPos.row + 1, currentZeroPos.column]
        doAction(action)
        listOfActions.add(action)
    }

    fun downIsEmpty() = currentZeroPos.row < matrix.height - 1
            && matrix[currentZeroPos.row + 1, currentZeroPos.column] !in setOfClosed

    fun upIsEmpty() = currentZeroPos.row > 0
            && matrix[currentZeroPos.row - 1, currentZeroPos.column] !in setOfClosed

    fun rightIsEmpty() = currentZeroPos.column < matrix.width - 1
            && matrix[currentZeroPos.row, currentZeroPos.column + 1] !in setOfClosed

    fun leftIsEmpty() = currentZeroPos.column > 0
            && matrix[currentZeroPos.row, currentZeroPos.column - 1] !in setOfClosed

    private fun getNearestCells(cell: Cell): Set<Int> {
        val result = mutableSetOf<Int>()
        val y = cell.row
        val x = cell.column
        if (x < matrix.width - 1)
            result += matrix[Cell(y, x + 1)]
        if (x > 0)
            result += matrix[Cell(y, x - 1)]
        if (y < matrix.height - 1)
            result += matrix[Cell(y + 1, x)]
        if (y > 0)
            result += matrix[Cell(y - 1, x)]
        return result
    }

    fun scroll() { //вращаем кольцо по периметру
        val lastPos = Cell(currentZeroPos.row, currentZeroPos.column - 1)
        //println(lastPos)
        if (currentZeroPos != currentStart) error("не на стартовой позиции")
        while (currentZeroPos.column < matrix.width - 1) toRight()
        while (currentZeroPos.row < matrix.height - 1) toDown()
        while (currentZeroPos.column > 0) toLeft()
        while (currentZeroPos.row > 0 && lastPos != currentZeroPos) toUp()
        while (currentZeroPos.column < matrix.width - 1 && lastPos != currentZeroPos) toRight()
        //println(currentZeroPos)
    }

    fun reScroll() { //вращаем кольцо по периметру в обратную сторону
        val (zY, zX) = currentZeroPos
        val nextPos = when {
            zX == matrix.width - 1 && zY < matrix.height - 1 -> Cell(zY + 1, zX)
            zY == matrix.height - 1 && zX > 0 -> Cell(zY, zX - 1)
            zX == 0 && zY > 0 -> Cell(zY - 1, zX)
            zY == 0 && zX < matrix.width - 1 -> Cell(zY, zX + 1)
            else -> error("Беды с рескролингом \n$matrix")
        }

        while (currentZeroPos != nextPos) {
            val (y, x) = currentZeroPos
            when {
                x < matrix.width - 1 && y == matrix.height - 1 -> toRight()
                x > 0 && y == 0 -> toLeft()
                x == matrix.width - 1 && y > 0 -> toUp()
                x == 0 && y < matrix.width - 1 -> toDown()
            }

        }
    }

    fun toStartPos() {
        while (rightIsEmpty()) toRight() //вправо пока справа свободно
        while (upIsEmpty()) toUp() //вврерх пока сверху свободно
        while (leftIsEmpty()) toLeft() //влево
        if (currentStart != currentZeroPos) error("Ошибка в toStart")
    }

    fun targetIsBlocked(targetCell: Cell): Boolean {
        val actions = getNearestCells(targetCell)
        if ((actions - setOfClosed).size < 2) return true
        return false
    }

    fun toNumber(targetCell: Cell): Boolean {
        val (y, x) = targetCell
        if (currentZeroPos.row == y) {
            toDown()
            while (x > currentZeroPos.column) toRight()
            toUp()
            while (leftIsEmpty()) toLeft()
            if (currentStart != currentZeroPos) error("toStart в toNumber")
            return true
        }
        while (currentZeroPos.row < y) toDown()
        val checker = currentZeroPos.column > x
        while (currentZeroPos.column > x) toLeft()
        while (currentZeroPos.column < x) toRight()
        if (checker) {
            when {
                downIsEmpty() -> toDown()
                upIsEmpty() -> toUp()
                else -> error("цель заблокирована")
            }
        }
        return true
    }

    fun laySolution(type: Boolean): List<Int> {
        val list = if (matrix.height == 4) listOf(1, 2, 3, 4, 8, 12, if (type) 15 else 14)
        else listOf(5, 6, 7, 11, if (type) 14 else 15, 13, 9)
        findZeroPos()
        toStartPos()
        for (number in list) {
            if (targetIsBlocked(matrix.search(number))) {
                reScroll()
                currentStart = currentZeroPos
                toNumber(matrix.search(number))
                toStartPos()
                scroll()
                currentStart = currentZeroPos
            }
            while (matrix[currentStart.row, currentStart.column + 1] != number) {
                val targetCell = matrix.search(number)
                toNumber(targetCell)
                toStartPos()
            }
            toRight()
            setOfClosed += number
            currentStart = currentZeroPos
            if (currentStart == Cell(0, matrix.width - 1)) {
                scroll()
                currentStart = currentZeroPos
            }
        }
        while (matrix[0, 0] != list[0]) {
            reScroll()
        }

        if (matrix.height == 4) {
            println(matrix)
            val listForMatrix = mutableListOf<Int>()
            for (y in 1 until 4)
                for (x in 0 until 3)
                    listForMatrix.add(matrix[y, x])
            val newField = Field15(MatrixImpl<Int>(3, 3, listForMatrix))
            newField.laySolution(type)
            //Офигеть это работает!!!
            newField.toDown()
            newField.toRight()
            newField.toRight()
            println(newField.matrix)
            return listOfActions + newField.listOfActions + matrix[3, 3]
        }
        return listOfActions
    }

    fun availableActions(): Map<Int, Cell> {
        val mapOfActions = mutableMapOf<Int, Cell>()
        if (currentZeroPos == Cell(-1, -1)) findZeroPos()
        val y = currentZeroPos.row
        val x = currentZeroPos.column
        if (x < matrix.width - 1)
            mapOfActions[matrix[y, x + 1]] = Cell(y, x + 1)
        if (x > 0)
            mapOfActions[matrix[y, x - 1]] = Cell(y, x - 1)
        if (y < matrix.height - 1)
            mapOfActions[matrix[y + 1, x]] = Cell(y + 1, x)
        if (y > 0)
            mapOfActions[matrix[y - 1, x]] = Cell(y - 1, x)
        return mapOfActions
    }

    fun doActualAction(action: Pair<Int, Cell>) {
        if (matrix[action.second] != action.first) throw error("Действие не актуально")
        matrix[currentZeroPos] = matrix[action.second]
        matrix[action.second] = 0
        currentZeroPos = action.second
    }

    fun doAction(action: Int) {
        val cell = availableActions()[action] ?: error("Некорректное действие \n$matrix \naction:$action")
        doActualAction(action to cell)
    }

    fun getCopy(): Field15 {
        val newField = Field15(matrix.getCopy())
        newField.currentZeroPos = this.currentZeroPos
        return newField
    }
}

fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    val field = Field15(matrix)
    for (action in moves)
        field.doAction(action)
    return field.matrix
}

/**
 * Очень сложная (32 балла)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */




fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> = Field15(matrix).laySolution(typeOfMatrix(matrix))

fun typeOfMatrix(matrix: Matrix<Int>): Boolean {
    var checksum = 0
    for (i in 0 until 16) {
        if (matrix[i / 4, i % 4] == 0) continue
        checksum += (i / 4) + 1
        for (j in i until 16)
            if (matrix[j / 4, j % 4] < matrix[i / 4, i % 4] && matrix[j / 4, j % 4] != 0) checksum++
    }
    return checksum % 2 == 0
}

