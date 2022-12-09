import kotlin.math.abs

private var xPositions: ArrayList<Int> = ArrayList()
private var yPositions: ArrayList<Int> = ArrayList()

private fun moveUp(number: Int) = moveUp(number, left = false, right = false)
private fun moveUpLeft(number: Int) = moveUp(number, left = true, right = false)
private fun moveUpRight(number: Int) = moveUp(number, left = false, right = true)
private fun moveUp(number: Int, left: Boolean, right: Boolean) {
    yPositions[number]++
    if (left) moveLeft(number)
    if (right) moveRight(number)
    if (number < xPositions.size-1) {
        if (abs(yPositions[number] - yPositions[number+1]) > 1
            && xPositions[number] < xPositions[number+1]) {
            moveUpLeft(number+1)
        } else if (abs(yPositions[number] - yPositions[number+1]) > 1
            && xPositions[number] > xPositions[number+1]) {
            moveUpRight(number+1)
        } else if (abs(yPositions[number] - yPositions[number+1]) > 1) {
            moveUp(number+1)
            if (xPositions[number] < xPositions[number+1]) moveLeft(number+1)
            else if (xPositions[number] > xPositions[number+1]) moveRight(number+1)
        }
    }
}

private fun moveDown(number: Int) = moveDown(number, left = false, right = false)
private fun moveDownLeft(number: Int) = moveDown(number, left = true, right = false)
private fun moveDownRight(number: Int) = moveDown(number, left = false, right = true)
private fun moveDown(number: Int, left: Boolean, right: Boolean) {
    yPositions[number]--
    if (left) moveLeft(number)
    if (right) moveRight(number)
    if (number < xPositions.size-1) {
        if (abs(yPositions[number] - yPositions[number+1]) > 1
            && xPositions[number] < xPositions[number+1]) {
            moveDownLeft(number+1)
        } else if (abs(yPositions[number] - yPositions[number+1]) > 1
            && xPositions[number] > xPositions[number+1]) {
            moveDownRight(number+1)
        } else if (abs(yPositions[number] - yPositions[number+1]) > 1) {
            moveDown(number+1)
            if (xPositions[number] < xPositions[number+1]) moveLeft(number+1)
            else if (xPositions[number] > xPositions[number+1]) moveRight(number+1)
        }
    }
}

private fun moveLeft(number: Int) = moveLeft(number, up = false, down = false)
private fun moveLeftDown(number: Int) = moveLeft(number, up = false, down = true)
private fun moveLeftUp(number: Int) = moveLeft(number, up = true, down = false)
private fun moveLeft(number: Int, up: Boolean, down: Boolean) {
    xPositions[number]--
    if (down) moveDown(number)
    if (up) moveUp(number)
    if (number < xPositions.size-1) {
        if (abs(xPositions[number] - xPositions[number+1]) > 1
            && yPositions[number] < yPositions[number+1]) {
            moveLeftDown(number+1)
        } else if (abs(xPositions[number] - xPositions[number+1]) > 1
            && yPositions[number] > yPositions[number+1]) {
            moveLeftUp(number+1)
        } else if (abs(xPositions[number] - xPositions[number+1]) > 1) {
            moveLeft(number+1)
            if (yPositions[number] < yPositions[number+1]) moveDown(number+1)
            else if (yPositions[number] > yPositions[number+1]) moveUp(number+1)
        }
    }
}

private fun moveRight(number: Int) = moveRight(number, up = false, down = false)
private fun moveRightDown(number: Int) = moveRight(number, up = false, down = true)
private fun moveRightUp(number: Int) = moveRight(number, up = true, down = false)
private fun moveRight(number: Int, up: Boolean, down: Boolean) {
    xPositions[number]++
    if (down) moveDown(number)
    if (up) moveUp(number)
    if (number < xPositions.size-1) {
        if (abs(xPositions[number] - xPositions[number+1]) > 1
            && yPositions[number] < yPositions[number+1]) {
            moveRightDown(number+1)
        } else if (abs(xPositions[number] - xPositions[number+1]) > 1
            && yPositions[number] > yPositions[number+1]) {
            moveRightUp(number+1)
        } else if (abs(xPositions[number] - xPositions[number+1]) > 1) {
            moveRight(number+1)
            if (yPositions[number] < yPositions[number+1]) moveDown(number+1)
            else if (yPositions[number] > yPositions[number+1]) moveUp(number+1)
        }
    }
}

fun main() {

    var history: ArrayList<String> = ArrayList()

    fun saveTailPosition() {
        val headPosition = "${xPositions.first()} ${yPositions.first()}"
        val tailPosition = "${xPositions.last()} ${yPositions.last()}"
        if (!history.contains(tailPosition)) history.add(tailPosition)
    }

    fun move(direction: String) {
        when (direction) {
            "U" -> moveUp(0)
            "D" -> moveDown(0)
            "L" -> moveLeft(0)
            "R" -> moveRight(0)
        }
    }

    fun play(input: List<String>): Int {
        history = ArrayList()
        saveTailPosition()
        for (line in input) {
            val times = line.split(" ")[1].toInt()
            repeat(times) {
                move(line.split(" ")[0])
                saveTailPosition()
            }
        }
        return history.size
    }

    fun part1(input: List<String>): Int {
        xPositions = arrayListOf(0,0)
        yPositions = arrayListOf(0,0)
        return play(input)
    }

    fun part2(input: List<String>): Int {
        xPositions = arrayListOf(0,0,0,0,0,0,0,0,0,0)
        yPositions = arrayListOf(0,0,0,0,0,0,0,0,0,0)
        return play(input)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    //check(part1(testInput) == 13)
    check(part2(testInput) == 36)

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
