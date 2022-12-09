import java.lang.Math.abs

fun main() {

    var headPositionX = 0
    var headPositionY = 0
    var tailPositionX = 0
    var tailPositionY = 0
    var history: ArrayList<String> = ArrayList()

    fun init() {
        headPositionX = 0
        headPositionY = 0
        tailPositionX = 0
        tailPositionY = 0
        history = ArrayList()
    }

    fun saveTailPosition() {
        val headPosition = "$headPositionX $headPositionY"
        val tailPosition = "$tailPositionX $tailPositionY"
        if (!history.contains(tailPosition)) history.add(tailPosition)
    }

    fun moveUp() {
        headPositionY++
        if (abs(headPositionY - tailPositionY) > 1) {
            tailPositionY++
            if (headPositionX != tailPositionX) tailPositionX = headPositionX
        }
    }

    fun moveDown() {
        headPositionY--
        if (abs(headPositionY - tailPositionY) > 1) {
            tailPositionY--
            if (headPositionX != tailPositionX) tailPositionX = headPositionX
        }
    }

    fun moveLeft() {
        headPositionX--
        if (abs(headPositionX - tailPositionX) > 1) {
            tailPositionX--
            if (headPositionY != tailPositionY) tailPositionY = headPositionY
        }
    }

    fun moveRight() {
        headPositionX++
        if (abs(headPositionX - tailPositionX) > 1) {
            tailPositionX++
            if (headPositionY != tailPositionY) tailPositionY = headPositionY
        }
    }

    fun move(direction: String) {
        when (direction) {
            "U" -> moveUp()
            "D" -> moveDown()
            "L" -> moveLeft()
            "R" -> moveRight()
        }
    }

    fun part1(input: List<String>): Int {
        init()
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

    fun part2(input: List<String>): Int {
        var result = 0
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    check(part1(testInput) == 13)
    //check(part2(testInput) == 8)

    val input = readInput("Data")
    println(part1(input))
    //println(part2(input))
}
