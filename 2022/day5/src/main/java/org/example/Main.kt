package org.example

import java.io.File
import kotlin.streams.toList

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val stacks = create()
        display(stacks)
        println("")
        part1(stacks)
        val stacks2 = create()
        println("")
        part2(stacks2)
    }

    private fun part1(stacks: List<ArrayDeque<Char>>) {
        val sortedStacks = sort(stacks)
        display(sortedStacks)
    }

    private fun part2(stacks: List<ArrayDeque<Char>>) {
        val sortedStacks = sort9001(stacks)
        display(sortedStacks)
    }

    private fun create(): List<ArrayDeque<Char>> {
        val stacks = MutableList(9) { ArrayDeque<Char>() }
        File("/Users/kkovach/git/AoC/2022/day5/src/main/resources/stacks.txt").forEachLine { line ->
            var index = 1
            var x = 0
            val chars = line.chars().toList().map { it.toChar() }
            while (index < chars.count()) {
                if (chars[index] != " ".single()) {
                    stacks[x].addFirst(chars[index])
                }
                index += 4
                x++
            }
            if (index < chars.count()) {
                if (chars[index] != " ".single()) {
                    stacks[x].addFirst(chars[index])
                }
            }
        }
        return stacks
    }

    private fun sort(stacks: List<ArrayDeque<Char>>): List<ArrayDeque<Char>> {
        val move = "move ([0-9]+) from ([0-9]+) to ([0-9]+)".toRegex()
        File("/Users/kkovach/git/AoC/2022/day5/src/main/resources/moves.txt").forEachLine { line ->
            val match = move.find(line)
            match?.let { groups ->
                val (num, from, to) = groups.destructured
                for (x in 0 until num.toInt()) {
                    val char: Char = stacks[from.toInt() - 1].removeLast()
                    stacks[to.toInt() - 1].addLast(char)
                }
            }
        }
        return stacks
    }

    private fun sort9001(stacks: List<ArrayDeque<Char>>): List<ArrayDeque<Char>> {
        val move = "move ([0-9]+) from ([0-9]+) to ([0-9]+)".toRegex()
        File("/Users/kkovach/git/AoC/2022/day5/src/main/resources/moves.txt").forEachLine { line ->
            val match = move.find(line)
            match?.let { groups ->
                val (num, from, to) = groups.destructured
                val popped = mutableListOf<Char>()
                for (x in 0 until num.toInt()) {
                    val char: Char = stacks[from.toInt() - 1].removeLast()
                    popped.add(char)
                }
                popped.reverse()
                stacks[to.toInt() - 1].addAll(popped)
            }
        }
        return stacks
    }

    private fun display(stacks: List<ArrayDeque<Char>>) {
        val numStacks = stacks.size
        val highestStack = stacks.maxOf { it.size } - 1
        for (y in highestStack downTo 0) {
            for (x in 0 until numStacks) {
                if (stacks[x].size > y)
                    print("[${stacks[x][y]}] ")
                else
                    print("    ")
            }
            println()
        }
    }
}