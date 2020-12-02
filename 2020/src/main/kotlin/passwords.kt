import java.io.File

fun main() {

    val passwords = mutableListOf<String>()
    var validPasswords = 0
    var numMatches = 0

    File("src/main/resources/passwords.txt").forEachLine {
        passwords.add(it)
    }

    passwords.forEach { policyAndPassword ->

        val range = policyAndPassword.split(" ")[0].split("-")
        val letter = policyAndPassword.split(" ")[1].split(":")[0]
        val password = policyAndPassword.split(" ")[2]
        val matches = Regex(letter).findAll(password)

        matches.forEach { matchResult ->
            if (((matchResult.range.first + 1) == range[0].toInt()) or ((matchResult.range.first + 1) == range[1].toInt())) {
                numMatches++
            }
        }

        if(numMatches == 1)
            validPasswords++

        numMatches = 0
    }

    println("$validPasswords valid password.")
}
