import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
   val lines = File(args.first()).readLines()
   val firstList = lines.map {
      it.split("   ").first()
   }.map { it.toIntOrNull()!! }.sorted()
   val map = lines.map {
      it.split("   ")[1]
   }.map { it.toIntOrNull()!! }.groupingBy{
      it
   }.eachCount()
   val result = firstList.map {
      (map[it]?: 0)  * it
   }.reduce { acc, i -> acc + i }
   println("result = $result")
}

