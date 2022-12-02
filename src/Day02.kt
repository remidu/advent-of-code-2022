import Result.*
import Shape.*

enum class Shape(val code1: String, val code2: String, val points: Int) {
    ROCK("A", "X", 1),
    PAPER("B", "Y", 2),
    SCISSORS("C", "Z", 3);

    companion object {
        infix fun of(code: String): Shape {
            return values().first { it.code1 == code || it.code2 == code }
        }
    }
}

enum class Result(val code: String, val points: Int) {
    WIN("Z", 6),
    DRAW("Y", 3),
    FAIL("X", 0);

    companion object {
        infix fun of(code: String): Result {
            return values().first { it.code == code }
        }
    }
}

fun main() {

    fun play(yourShape: Shape, myShape: Shape): Int {
        if (yourShape == ROCK && myShape == PAPER) return 6
        if (yourShape == ROCK && myShape == SCISSORS) return 0
        if (yourShape == PAPER && myShape == SCISSORS) return 6
        if (yourShape == PAPER && myShape == ROCK) return 0
        if (yourShape == SCISSORS && myShape == ROCK) return 6
        if (yourShape == SCISSORS && myShape == PAPER) return 0
        return 3 // draw
    }

    fun part1(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val codes = line.split(" ")
            val yourShape = Shape.of(codes[0])
            val myShape = Shape.of(codes[1])
            total += play(yourShape, myShape) + myShape.points
        }
        return total
    }

    fun myShape(yourShape: Shape, result: Result): Shape {
        if (yourShape == ROCK && result == WIN) return PAPER
        if (yourShape == PAPER && result == WIN) return SCISSORS
        if (yourShape == SCISSORS && result == WIN) return ROCK
        if (yourShape == ROCK && result == FAIL) return SCISSORS
        if (yourShape == PAPER && result == FAIL) return ROCK
        if (yourShape == SCISSORS && result == FAIL) return PAPER
        return yourShape // draw
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val codes = line.split(" ")
            val yourShape = Shape.of(codes[0])
            val result = Result.of(codes[1])
            total += myShape(yourShape, result).points + result.points
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
