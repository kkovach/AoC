package com.provach.aoc

import scala.io.{BufferedSource, Source}

object Main {

  def main(args: Array[String]): Unit = {

    val priorities = Vector("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

    val filename = "rucksacks.txt"
//    part1(Source.fromFile(filename), priorities)
    part2(Source.fromFile(filename), priorities)
  }

  def part1(source: BufferedSource, priorities: Vector[String]): Unit = {
    var total = 0
    for (line <- source.getLines()) {
      val listSize = line.size
      val one = line.substring(0, listSize / 2)
      val two = line.substring(listSize / 2)
      val duplicate = one.intersect(two).charAt(0).toString
      total = total + (priorities.indexOf(duplicate) + 1)
    }
    println(s"Total = $total")
  }

  def part2(source: BufferedSource, priorities: Vector[String]): Unit = {
    var total = 0
    val elfGroup = Array[String]("", "", "")
    var foo: Int = 0
    for (line <- source.getLines()) {
      println(line)
      val index = foo % 3
      elfGroup(index) = line
      if(index == 2) {
        val intersection = elfGroup(0).intersect(elfGroup(1)).intersect(elfGroup(2)).charAt(0).toString
        total = total + (priorities.indexOf(intersection) + 1)
        print(elfGroup(0))
        print(elfGroup(1))
        println(elfGroup(2))
        println(s"Intersection: $intersection")
        println("-")
      }
      foo += 1
    }
    println(s"Total = $total")
  }
}
