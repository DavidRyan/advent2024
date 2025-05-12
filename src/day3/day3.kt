import java.io.File

val regex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don\'t\\(\\)")
val doCommand = Regex("do\\(\\)")
val dontCommand = Regex("don\'t\\(\\)")
val numbersRegex = Regex("\\d{1,3}")

fun Array<String>.read() = File(this.first()).readText()

fun main(args: Array<String>) {
    val input = args.read()
    var execute = true

    regex.findAll(input)


    val result = regex.findAll(input).map {
        if (doCommand.matches(it.value)) {
            execute = true
            return@map 0
        } else if (dontCommand.matches(it.value)) {
            execute = false
            return@map 0
        }
        if (execute){
            numbersRegex.findAll(it.value).map { it.value.toInt() }.reduce {a, b -> a * b}
        } else {
            return@map 0
        }
    }.reduce { acc, i ->  acc + i}

    println("Result $result")
/*
    val matches = regex.findAll(input)
*/
}
