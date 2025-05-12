import java.io.File
import java.io.PrintWriter


data class Node(val x: Int, val y: Int)


enum class Direction {
    UP, DOWN, LEFT, RIGHT, UP_LEFT, UP_RIGHT, DOWN_RIGHT, DOWN_LEFT
}

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

fun mapDirection(direction: Direction, node: Node): Node {
    return when (direction) {
        Direction.UP -> node.up()
        Direction.DOWN -> node.down()
        Direction.LEFT -> node.left()
        Direction.RIGHT -> node.right()
        Direction.DOWN_RIGHT -> node.downRight()
        Direction.DOWN_LEFT -> node.downLeft()
        Direction.UP_LEFT -> node.upLeft()
        Direction.UP_RIGHT -> node.upRight()
    }
}

fun main(args: Array<String>) {
    val input = File(args.first()).readLines()
    val graph = mutableMapOf<Int, List<String>>()
    input.forEachIndexed { a, b -> graph[a] = b.toCharArray().map { it.toString() } }
    var nodes = emptyList<Node>()
    graph.forEach { (x, row) ->
        row.forEachIndexed{ y, value ->
            if (graph.getNode(Node(x, y)) == "M") {
                val start = Node(x, y)
                nodes = nodes.plus((search(graph, start, "M",null, emptyList<Node>())))
            }
        }
    }

    var results = mapOf<Node, Int>()
    nodes.forEach {
       if (results[it] == null) {
           results = results.plus(it to 1)
       } else {
           results = results.plus(it to results[it]!! + 1)
       }
    }

        println("Result: ${results.filter { it.value == 2 }.size}")

}

fun search(graph: Map<Int, List<String>>, start: Node, nextLetter: String?, direction: Direction? = null, word: List<Node>): List<Node> {
    if (start.x < 0 || start.y < 0 || start.x >= graph.size || start.y >= graph[start.x]!!.size) {
        return emptyList()
    }

    if (graph.getNode(start) == "S" && nextLetter == "S") {
        val reverseDirection = reverseDirection(direction!!)
        val a = mapDirection(reverseDirection, start)
        return listOf(a)
    }

    if (graph.getNode(start) == nextLetter) {
        if (direction != null) {
            return search(graph, mapDirection(direction, start), nextLetter(nextLetter!!), direction, word + start)
        } else {
            val nextLetter = nextLetter(nextLetter!!)
            return listOfNotNull(
                search(graph, mapDirection(Direction.UP_LEFT, start), nextLetter, Direction.UP_LEFT, word + start),
                search(graph, mapDirection(Direction.UP_RIGHT, start), nextLetter, Direction.UP_RIGHT, word + start),
                search(graph, mapDirection(Direction.DOWN_LEFT, start), nextLetter, Direction.DOWN_LEFT, word + start),
                search(graph, mapDirection(Direction.DOWN_RIGHT, start), nextLetter, Direction.DOWN_RIGHT, word + start),
            ).flatten()
        }
    }
    return emptyList()
}

fun nextLetter(letter: String): String? {
    return when (letter) {
        "M" -> "A"
        "A" -> "S"
        else -> null
    }
}

fun reverseDirection(direction: Direction) : Direction {
    when (direction) {
        Direction.UP -> return Direction.DOWN
        Direction.DOWN -> return Direction.UP
        Direction.LEFT -> return Direction.RIGHT
        Direction.RIGHT -> return Direction.LEFT
        Direction.DOWN_RIGHT -> return Direction.UP_LEFT
        Direction.DOWN_LEFT -> return Direction.UP_RIGHT
        Direction.UP_LEFT -> return Direction.DOWN_RIGHT
        Direction.UP_RIGHT -> return Direction.DOWN_LEFT
    }
}

fun Map<Int, List<String>>.getNode(node: Node): String = this[node.x]!![node.y]


// Pt 2
// Same algo to find all "MAS", keep track of location of A, count up all the double A's, to find A reverse firection of S by 1 and return that location as the answer