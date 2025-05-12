package day5

import java.io.File

//class Day5_2 {

    data class Rule(val first: Int, val second: Int)

    var rules = listOf<Rule>()

    var violatedRuleMap = mutableMapOf<Int, List<Rule>>()

    fun fixer(pages: List<List<Int>>): List<List<Int>>  {
        rules.forEach {
            println(it)
        }
        val newPages = mutableListOf<List<Int>>()
        for (page in pages) {
            //println("Page: $page")
            val newPage = page.sortedWith(comparator = Comparator { o1, o2 ->
                //println("sorting $o1 and $o2")
                for (rule in rules!!) {
                    if (rule.first == o1 && rule.second == o2) {
                        //println("KEEP : $o1 and $o2 because $rule")
                        return@Comparator -1
                    } else if (rule.first == o2 && rule.second == o1) {
                        //println("SWAP: $o1 and $o2 because $rule")
                        return@Comparator 1
                    }
                }
                return@Comparator 0
            })
            newPages.add(newPage)
        }
        return newPages
    }


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
        rules = input.subList(0, split).map {
            val (first, second) = it.split("|")
            Rule(first.toInt(), second.toInt())
        }
        val pages = input.subList(split + 1, input.size).map { it.split(",").map { it.toInt() } }

        /*
            rules.forEach { println(it) }
            pages.forEach { println(it) }
        */
        val incorrectPages = checker(pages, rules)
        val fixedpages = fixer(incorrectPages)
        fixedpages.forEach {
            println(it)
        }
        val answer = fixedpages.map {
            val middle = (it.size / 2)
            println(it)
            println("Middle: " + it[middle])
            it[middle]
        }.sum()

        println("Answer: $answer")


    }

//}
