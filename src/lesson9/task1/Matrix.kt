@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1



// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int) {
    override fun equals(other: Any?) = other is Cell && other.row == row && other.column == column
    override fun hashCode() = row * 31 + column
}

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
    fun search(value: E): Cell
    fun writeList(list: List<E>): Matrix<E>
    fun getCopy(): Matrix<E> {
        val list = mutableListOf<E>()
        for (y in 0 until height)
            for (x in 0 until width)
                list.add(this[y, x])
        return MatrixImpl(height, width, list)

    }
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */

fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    if (width <= 0 || height <= 0) throw IllegalArgumentException()
    return MatrixImpl<E>(height, width, MutableList(height * width) { e })
}

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(
    override val height: Int, override val width: Int, val matrixList: MutableList<E>
) : Matrix<E> {
    override fun get(row: Int, column: Int): E = matrixList[row * width + column]
    override fun get(cell: Cell): E = get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        matrixList[row * width + column] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) = other is MatrixImpl<*> && other.height == height && other.width == width
            && other.matrixList == matrixList

    override fun hashCode(): Int {
        var result = 5
        result = result * 31 + height
        result = result * 31 + width
        for (i in matrixList) {
            result *= 31
            result += i.toString().length
        }
        return result
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0 until height) {
            sb.append("[")
            for (column in 0 until width) {
                sb.append(this[row, column], ' ')
            }
            sb.append("] \n")
        }
        sb.append("]")
        return sb.toString()
    }

    override fun search(value: E): Cell {
        val index = matrixList.indexOf(value)
        return Cell(index / width, index % width)
    }

    override fun writeList(list: List<E>): Matrix<E> {
        for (i in matrixList.indices) matrixList[i] = list[i]
        return this
    }

    override fun getCopy(): Matrix<E> = MatrixImpl(height, width, this.matrixList.toMutableList())
}


