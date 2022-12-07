class Node(val name: String, var size: Int, val children: ArrayList<Node>, var parent: Node?) {
    fun addChild(name: String, size: Int) {
        this.size += size
        this.children.add(Node(name, size, ArrayList(), this))
    }
    fun addChild(node: Node) {
        this.children.add(node)
    }
}

fun main() {

    fun goBack(node: Node): Node {
        val parentNode = node.parent
        if (parentNode != null) {
            parentNode.size += node.size
            return parentNode
        }
        return node
    }

    fun listFolders(node: Node, list: ArrayList<Node>): ArrayList<Node> {
        if (node.children.size > 0) {
            list.add(node)
            for (child in node.children) {
                listFolders(child, list)
            }
        }
        return list
    }

    fun getAllFolders(input: List<String>): ArrayList<Node> {
        var currentFolder = Node("root", 0, ArrayList(), null)
        for (line in input) {
            if (line == "$ cd /") {
                continue
            } else if (line == "$ ls") {
                continue
            } else if (line == "$ cd ..") {
                currentFolder = goBack(currentFolder)
            } else if (line.startsWith("$ cd")) {
                val node = Node(line.split(" ")[2], 0, ArrayList(), currentFolder)
                currentFolder.addChild(node)
                currentFolder = node
            } else if (line.first().isDigit()) {
                currentFolder.addChild(line.split(" ")[1], line.split(" ")[0].toInt())
            }
        }
        while (currentFolder.name != "root") {
            currentFolder = goBack(currentFolder)
        }
        return listFolders(currentFolder, ArrayList())
    }

    fun part1(input: List<String>): Int {
        val folders = getAllFolders(input)
        return folders.filter { f -> f.size <= 100000 }.sumOf { f -> f.size }
    }

    fun part2(input: List<String>): Int {
        val folders = getAllFolders(input)
        val max = 70000000
        val necessary = 30000000
        val used = folders.filter { f -> f.name == "root" }.map { f -> f.size }.first()
        val unused = max - used
        val possibleFolders = folders.filter { f -> f.size > necessary - unused }
        return possibleFolders.minBy { f -> f.size }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Data_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Data")
    println(part1(input))
    println(part2(input))
}
