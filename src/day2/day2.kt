import java.io.File
import kotlin.math.abs


fun main(args: Array<String>) {
    val lines = File(args[0]).readLines().map {
        it.split(" ").map { it.toIntOrNull()!! }
    }
    val safeLines = lines.filter {
        var safe = true
        it.forEachIndexed { index, item ->
            if (index+1 < it.size) {
                val diff = abs(item - it[index+1])
                if (diff !in 1..3) {
                    safe = false
                }
            }
        }
        safe
    }
    val increasingLines = lines.map {
        it.sorted()
    }
    val decreasingLines = lines.map {
        it.sortedDescending()
    }
    val newSafeLines = safeLines.filter {
        increasingLines.contains(it) || decreasingLines.contains(it)
    }
    println("Result: ${newSafeLines.size}")
}

