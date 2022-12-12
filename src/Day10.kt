fun main() {

    var x = 1
    var cycle = 0
    var strengths: ArrayList<Int> = ArrayList()

    fun startCycle() {
        cycle++
        if (listOf(20, 60, 100, 140, 180, 220).contains(cycle)) {
            strengths.add(x * cycle)
        }
    }

    fun part1(input: List<String>): Int {
        x = 1
        cycle = 0
        strengths = ArrayList()
        for (line in input) {
            if (line == "noop") {
                startCycle()
                continue
            }
            val number = line.split(" ")[1].toInt()
            startCycle()
            startCycle()
            x += number
        }
        return strengths.sum()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    check(part1(testInput) == 13140)
    //check(part2(testInput) == 36)

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
