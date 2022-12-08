fun main() {

    fun fillGrid(input: List<String>): List<List<Int>> {
        val grid: ArrayList<List<Int>> = ArrayList()
        for (line in input) {
            val row: ArrayList<Int> = ArrayList()
            val chars = line.toCharArray()
            for (char in chars) {
                row.add(char.toString().toInt())
            }
            grid.add(row)
        }
        return grid
    }

    fun part1(input: List<String>): Int {
        var result = 0
        val grid = fillGrid(input)
        for (y in grid.indices) {
            for (x in grid.indices) {
                if (x == 0 || y == 0 || x == grid.size-1 || y == grid.size-1) { // edge
                    result++
                    continue
                }
                val interior = grid[x][y]
                var globallyVisible = false
                var visible = true
                for (i in 0 until x) {
                    if (grid[i][y] >= interior) visible = false
                }
                globallyVisible = globallyVisible || visible
                visible = true
                for (i in grid.size-1 downTo x+1) {
                    if (grid[i][y] >= interior) visible = false
                }
                globallyVisible = globallyVisible || visible
                visible = true
                for (i in 0 until y) {
                    if (grid[x][i] >= interior) visible = false
                }
                globallyVisible = globallyVisible || visible
                visible = true
                for (i in grid.size-1 downTo y+1) {
                    if (grid[x][i] >= interior) visible = false
                }
                globallyVisible = globallyVisible || visible
                if (globallyVisible) result++
            }
        }
        return result
    }

    fun part2(input: List<String>): Int {
        val grid = fillGrid(input)
        val list: ArrayList<Int> = ArrayList()
        for (y in grid.indices) {
            for (x in grid.indices) {
                if (x == 0 || y == 0 || x == grid.size-1 || y == grid.size-1) { // edge
                    continue
                }
                val interior = grid[x][y]
                var up = 0
                for (i in x-1 downTo 0) {
                    up++
                    if (grid[i][y] >= interior) break
                }
                var down = 0
                for (i in x+1 until grid.size) {
                    down++
                    if (grid[i][y] >= interior) break
                }
                var left = 0
                for (i in y-1 downTo 0) {
                    left++
                    if (grid[x][i] >= interior) break
                }
                var right = 0
                for (i in y+1 until grid.size) {
                    right++
                    if (grid[x][i] >= interior) break
                }
                list.add(up * down * left * right)
            }
        }
        return list.max()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
