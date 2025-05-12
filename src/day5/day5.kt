/*
package day5

import java.io.File

data class Rule(val first: Int, val second: Int)

val rules = listOf<Rule>()
var violatedRuleMap = mutableMapOf<Int, List<Rule>>()

fun checker(pages: List<List<Int>>, rules: List<Rule>): List<List<Int>> {
    return pages.filterIndexed { index, it->
        // Returns after first rule
        var violated = false
        for (rule in rules) {
            if (rule.first in it && rule.second in it) {
                if (it.indexOf(rule.first) > it.indexOf(rule.second)) {
                    violated = true
                    if (violatedRuleMap.get(index) == null) {
                        violatedRuleMap.put(index, listOf(rule))
                    } else {
                        violatedRuleMap[index] = violatedRuleMap.get(index)!!.plus(rule)
                    }
                }
            }
        }
        violated
    }
}

fun main(args: Array<String>) {
    val input = File(args.first()).readLines()
    val split = input.indexOf("")
    val rules = input.subList(0, split).map {
        val (first, second) = it.split("|")
        Rule(first.toInt(), second.toInt())
    }
    val pages = input.subList(split + 1, input.size).map { it.split(",").map { it.toInt() } }

    */
/*
        rules.forEach { println(it) }
        pages.forEach { println(it) }
    *//*

    println()
    println()
    println()
    val incorrectPages = checker(pages, rules)
    val correctPages = pages.filter { it !in incorrectPages }
    val answer = correctPages.map {
        val middle = (it.size / 2)
        println(it)
        println("Middle: " + it[middle])
        it[middle]
    }.sum()
    println(answer)

}
*/
