fun main() {
    fun part1(input: List<String>): Int {
        var best = 0
        var total = 0
        for (line in input) {
            if (line.isBlank()) {
                if (total > best) best = total
                total = 0
            } else {
                total += line.toInt()
            }
        }
        return best
    }

    fun part2(input: List<String>): Int {
        val best3 = intArrayOf(0, 0, 0)
        var total = 0
        for (line in input) {
            if (line.isBlank()) {
                if (total > best3[0]) best3[0] = total
                best3.sort()
                total = 0
            } else {
                total += line.toInt()
            }
        }
        return best3.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
