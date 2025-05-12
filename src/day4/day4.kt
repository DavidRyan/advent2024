import java.io.File


/*
fun Node.up() = this.copy(y = this.y + 1)
fun Node.down() = this.copy(y = this.y - 1)
fun Node.left() = this.copy(x = this.x - 1)
fun Node.right() = this.copy(x = this.x + 1)
fun Node.upLeft() = this.copy(x = this.x - 1, y = this.y + 1)
fun Node.upRight() = this.copy(x = this.x + 1, y = this.y + 1)
fun Node.downRight() = this.copy(x = this.x + 1, y = this.y - 1)
fun Node.downLeft() = this.copy(x = this.x - 1, y = this.y - 1)
fun Node.neighbors() = listOf(
    this.up(),
    this.down(),
    this.left(),
    this.right(),
    this.upLeft(),
    this.upRight(),
    this.downRight(),
    this.downLeft()
)

class day4 {

    data class Node(val x: Int, val y: Int)

    enum class Direction {
        UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT
    }

    fun mapDirection(direction: Direction): (Node) -> Node {
        return when (direction) {
            Direction.UP -> Node::up
            Direction.DOWN -> Node::down
            Direction.LEFT -> Node::left
            Direction.RIGHT -> Node::right
            Direction.DOWN_RIGHT -> Node::downRight
            Direction.DOWN_LEFT -> Node::downLeft
            Direction.UP_LEFT -> Node::upLeft
            Direction.UP_RIGHT -> Node::upRight
        }
    }

    fun main(args: Array<String>) {
        val input = File(args.first()).readLines().map { it.toCharArray().map { it.toString() } }
        var count = 0

        input.forEachIndexed { index1, strings ->
            strings.forEachIndexed { index2, s ->
                if (input[index1][index2] == "X") {
                    count += search(input, Node(index1, index2), "X")
                }
            }
        }
        println("Count: $counter")
    }

    var counter = 0

    fun search(graph: List<List<String>>, start: Node, nextLetter: String?, direction: Direction? = null): Int {
        if (start.x < 0 || start.x >= graph.size || start.y < 0 || start.y >= graph[start.x].size) {
            return 0
        }
        if (nextLetter == "S" && graph.get(start) == "S") {
            counter++
            return 1
        }
        if (graph.get(start) == nextLetter) {
            return if (direction != null) {
                search(graph, mapDirection(direction)(start), nextLetter(nextLetter), direction)
            } else {
                search(graph, start, nextLetter, Direction.UP) +
                        search(graph, start, nextLetter, Direction.DOWN) +
                        search(graph, start, nextLetter, Direction.LEFT) +
                        search(graph, start, nextLetter, Direction.RIGHT) +
                        search(graph, start, nextLetter, Direction.UP_RIGHT) +
                        search(graph, start, nextLetter, Direction.UP_LEFT) +
                        search(graph, start, nextLetter, Direction.DOWN_RIGHT) +
                        search(graph, start, nextLetter, Direction.DOWN_LEFT)
            }
        }
        return 0
    }

    fun nextLetter(letter: String): String? {
        return when (letter) {
            "X" -> "M"
            "M" -> "A"
            "A" -> "S"
            else -> null
        }
    }

    fun List<List<String>>.get(node: Node) = this[node.x][node.y]


// Pt 2
// Same algo to find all "MAS", keep track of location of A, count up all the double A's d
}
*/
