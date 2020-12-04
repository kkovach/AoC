
import java.io.File

fun main() {

    var x = 0
    var trees = 0
    var totalTrees = 1L
    var secondOne = false
    var doubleSlope = false
    val slopes = listOf(1, 3, 5, 7, 1)
    val treesHit = mutableListOf<Int>()

    slopes.forEach { slope ->

        File("src/main/resources/slopes.txt").forEachLine {

            if (slope == 1 && secondOne) {

                if(!doubleSlope) {

                    val line = it

                    if (line[x].toString() == "#")
                        trees++
                    x += slope
                    if (x >= line.length)
                        x -= line.length

                }

                doubleSlope = !doubleSlope

            } else {

                val line = it
                if (line[x].toString() == "#")
                    trees++
                x += slope
                if (x >= line.length)
                    x -= line.length
            }
        }

        if(slope == 1)
            secondOne = true

        treesHit.add(trees)

        x = 0
        trees = 0
    }

    treesHit.forEach {
        totalTrees *= it
    }

    println("Trees hit: $treesHit, Total Trees: $totalTrees")
}
