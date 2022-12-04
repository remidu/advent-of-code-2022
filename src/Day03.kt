fun main() {

    fun duplicate(list1: List<Char>, list2: List<Char>): Char {
        for (char in list1) {
            if (list2.contains(char)) return char
        }
        return 'a' // should never happen
    }

    fun duplicate(list1: List<Char>, list2: List<Char>, list3: List<Char>): Char {
        for (char in list1) {
            if (list2.contains(char) && list3.contains(char)) return char
        }
        return 'a' // should never happen
    }

    fun priority(letter: Char): Int {
        val ascii = letter.code
        if (ascii <= 90) return ascii-38
        else return ascii-96
    }

    fun part1(input: List<String>): Int {
        var total = 0
        for (line in input) {
            val chars = line.toList()
            val duplicate = duplicate(chars.subList(0, chars.size/2), chars.subList(chars.size/2, chars.size))
            total += priority(duplicate)
        }
        return total
    }

    fun part2(input: List<String>): Int {
        var total = 0
        for (i in input.indices step 3) {
            val duplicate = duplicate(input[i].toList(), input[i+1].toList(), input[i+2].toList())
            total += priority(duplicate)
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
