import java.io.File

fun main() {

    var i = 0
    val passports = mutableListOf<String>()
    passports.add("")
    var valid = 0

    File("src/main/resources/passports.txt").forEachLine {

        if(it != "") {
            passports[i] = "${passports[i]} $it"
        } else {
            i++
            passports.add("")
        }
    }

    passports.forEach {
        val passport = it.trim()
        print("Line: $passport - Size: ${passport.split(" ").size}, Cid? ${passport.contains("cid:")} ")
        if(passport.split(" ").size == 8 || (passport.split(" ").size == 7 && !passport.contains(" cid:"))) {
            valid++
            println("valid!")
        } else {
            println("invalid!")
        }
    }

    println("$valid valid passports")
}
