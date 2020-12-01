import java.io.File

fun main() {

    val expenses = mutableListOf<Int>()

    File("src/main/resources/report.txt").forEachLine {
        expenses.add(it.toInt())
    }

    expenses.forEachIndexed { firstIndex, i ->
        expenses.subList(firstIndex + 1, expenses.size).forEachIndexed { secondIndex, j ->
            if (i + j < 2020)
                expenses.subList(firstIndex + secondIndex + 2, expenses.size).forEach { k ->
                    if (i + j + k == 2020) {
                        println("$i * $j * $k = ${i * j * k}")
                        return
                    }
                }
        }
    }
}
