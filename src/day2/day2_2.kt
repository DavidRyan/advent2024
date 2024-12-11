import java.io.File
import kotlin.math.abs

fun checkLine(line: List<Int>) : Boolean {
    var safe = true
    line.forEachIndexed { index, item ->
        if (index+1 < line.size) {
            val diff = abs(item - line[index+1])
            if (diff !in 1..3) {
                safe = false
            }
        }
    }
    val increasingLines = line.sorted()
    val decreasingLines = line.sortedDescending()
    return (safe && (increasingLines == line || decreasingLines == line))
}

fun main(args: Array<String>) {
    val lines = File(args[0]).readLines().map {
        it.split(" ").map { it.toIntOrNull()!! }
    }
    val safeLines = lines.filter {
        var i = 0
        println("Checking line: $it")
        var safe = checkLine(it)
        while (!safe && i < it.size) {
            val mutableList = it.toMutableList()
            mutableList.removeAt(i)
            safe = checkLine(mutableList)
            i++
        }
        safe
    }
    println("Result: ${safeLines.size}")
}

