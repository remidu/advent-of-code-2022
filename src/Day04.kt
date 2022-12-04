fun main() {

    fun extend(range: String): List<Int> {
        val result = ArrayList<Int>()
        val numbers = range.split('-')
        for (i in numbers[0].toInt()..numbers[1].toInt()) {
            result.add(i)
        }
        return result
    }

    fun part1(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val split = line.split(',')
            if (extend(split[0]).containsAll(extend(split[1]))
                or extend(split[1]).containsAll(extend(split[0])))
                total++
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val split = line.split(',')
            val range1 = extend(split[0])
            val range2 = extend(split[1])
            for (number in range1) {
                if (range2.contains(number)) {
                    total++
                    break
                }
            }
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
