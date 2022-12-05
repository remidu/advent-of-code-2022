fun main() {

    var map: HashMap<Int, ArrayList<Char>> = HashMap()

    fun parseStacks(input: List<String>) {
        for (line in input) {
            if (line.contains('[')) {
                var id = 1
                for (i in 1..line.length step 4) {
                    if (!map.contains(id)) {
                        map.put(id, ArrayList())
                    }
                    if (line.get(i).isLetter()) map.get(id)?.add(line.get(i))
                    id++
                }
            }
        }
    }

    fun parseNumber(line: String, prefix: String, suffix: String): Int {
        return line.substring(line.indexOf(prefix) + prefix.length+1, line.indexOf(suffix)-1)
            .toInt()
    }

    fun parseDigit(line: String, prefix: String): Int {
        return line.substring(line.indexOf(prefix) + prefix.length+1, line.indexOf(prefix) + prefix.length+2)
            .toInt()
    }

    fun move(from: Int, to: Int) {
        val thing = map.get(from)?.removeFirst()
        if (thing != null) {
            map.get(to)?.add(0, thing)
        }
    }

    fun move(number: Int, from: Int, to: Int) {
        val buffer: ArrayList<Char> = ArrayList()
        for (i in 0 until number) {
            val thing = map.get(from)?.removeFirst()
            if (thing != null) {
                buffer.add(thing)
            }
        }
        map.get(to)?.addAll(0, buffer)
    }

    fun readTop(): String {
        var result = ""
        for (e:Map.Entry<Int, ArrayList<Char>> in map) {
            val value = e.value
            if (value.isNotEmpty()) {
                val letter = value.first()
                result += letter
            }
        }
        return result
    }

    fun part1(input: List<String>): String {
        map = HashMap()
        parseStacks(input)
        for (line in input) {
            if (line.contains("move")) {
                val number = parseNumber(line, "move", "from")
                val from = parseDigit(line, "from")
                val to = parseDigit(line, "to")
                repeat(number) { move(from, to) }
            }
        }
        return readTop()
    }

    fun part2(input: List<String>): String {
        map = HashMap()
        parseStacks(input)
        for (line in input) {
            if (line.contains("move")) {
                val number = parseNumber(line, "move", "from")
                val from = parseDigit(line, "from")
                val to = parseDigit(line, "to")
                move(number, from, to)
            }
        }
        return readTop()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
