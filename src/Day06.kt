fun main() {

    fun hasDuplicates(list: ArrayList<Char>): Boolean {
        for (char in list) {
            val temp: ArrayList<Char> = ArrayList(list)
            temp.remove(char)
            if (temp.contains(char)) return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        val buffer: ArrayList<Char> = ArrayList()
        var cpt = 0
        for (char in input[0].toCharArray()) {
            cpt++
            buffer.add(char)
            if (buffer.size > 4) {
                buffer.removeFirst()
                if (!hasDuplicates(buffer)) break
            }
        }
        return cpt
    }

    fun part2(input: List<String>): Int {
        val buffer: ArrayList<Char> = ArrayList()
        var cpt = 0
        for (char in input[0].toCharArray()) {
            cpt++
            buffer.add(char)
            if (buffer.size > 14) {
                buffer.removeFirst()
                if (!hasDuplicates(buffer)) break
            }
        }
        return cpt
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
