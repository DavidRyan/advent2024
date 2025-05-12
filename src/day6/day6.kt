package day6

import java.io.File


fun main(args: Array<String>) {
    val input = File(args.first()).readLines()
    val graph = mutableMapOf<Int, MutableList<String>>()
    input.forEachIndexed { a, b -> graph[a] = b.toCharArray().map { it.toString() }.toMutableList() }
    Day6(graph).run()

}

// Part 2 find all obsticles that would make a square? and test them all adding up infinite loops
class Day6(val input: MutableMap<Int, MutableList<String>>) {

    fun Node.up() = this.copy(y = this.y - 1)
    fun Node.down() = this.copy(y = this.y + 1)
    fun Node.left() = this.copy(x = this.x - 1)
    fun Node.right() = this.copy(x = this.x + 1)
    data class Node(val x: Int, val y: Int)
    enum class Direction {
        UP, DOWN, LEFT, RIGHT,
    }

    fun mapDirection(direction: Direction, node: Node): Node {
        return when (direction) {
            Direction.UP -> node.up()
            Direction.DOWN -> node.down()
            Direction.LEFT -> node.left()
            Direction.RIGHT -> node.right()
        }
    }

    fun mapRight90(direction: Direction): Direction {
        return when (direction) {
            Direction.UP -> Direction.RIGHT
            Direction.DOWN -> Direction.LEFT
            Direction.LEFT -> Direction.UP
            Direction.RIGHT -> Direction.DOWN
        }
    }

    fun Map<Int, List<String>>.getNode(node: Node): String = this[node.y]!![node.x]

    fun run() {
        val guard = input.findGuard().first
        println("Found guard at $guard")
        val direction = input.findGuard().second
        println(check(guard, direction))
        println("Distinct: ${visited.size}")
    }

    fun swap(currentNode: Node, nextNode: Node) {
        val currentSprite = input.getNode(currentNode)
        val nextSprite = input.getNode(nextNode)
        input[currentNode.y]!![currentNode.x] = nextSprite
        input[nextNode.y]!![nextNode.x] = currentSprite
    }

    val visited = mutableSetOf<Node>()
    fun check(node: Node, direction: Direction): Int {
        visited.add(node)
        println("Direction: $direction")
        input.forEach {
            println(it.value)
        }
        println()
        println()
        val map = input
        val next = mapDirection(direction, node)
        if (next.x < 0 || next.y < 0 || next.y >= map.size || next.x >= map[next.x]!!.size) {
            return 1
        }
        if (input.getNode(next) == "#") {
            val newDirection = mapRight90(direction)
            val nextNode = mapDirection(newDirection, node)
            swap(node, nextNode)
            return 1 + check(nextNode, newDirection)
        } else {
            val nextNode = mapDirection(direction, node)
            swap(node, nextNode)
            return 1 + check(next, direction)
        }
    }

    fun Map<Int, List<String>>.findGuard(): Pair<Node, Direction> {
        this.forEach {
            it.value.forEachIndexed { index, s ->
                if (s == "^") {
                    return Pair(Node(index, it.key), Direction.UP)
                }
            }
        }
        throw IllegalStateException("Guard not found")
    }
}