import java.io.File
import java.io.FileReader

fun main(args: Array<String>) {
   val lines = File(args.first()).readLines()
   val firstList = lines.map {
      it.split("   ").first()
   }.map { it.toIntOrNull()!! }.sorted()
   val secondList = lines.map {
      it.split("   ")[1]
   }.map { it.toIntOrNull()!! }.sorted()

   val result = firstList.mapIndexed {index, item ->
      Math.abs(item - secondList.get(index))
   }.reduce { acc, i ->
      acc + i
   }
   println("Result = " + result)
}

