import io.kotlintest.properties.Gen
import java.io.File

fun generateTest(n: Int, m: Int, q: Int): String {
    val result = StringBuilder()
    result.append(listOf(n, m, q).joinToString(separator = " ")).append('\n')
    val dict = (1..n).map { Gen.string().generate() }
    result.append(dict.joinToString(separator = " ")).append('\n')

    val relations = (1..m).map { listOf(Gen.choose(1, 3).generate(), Gen.oneOf(dict).generate(), Gen.oneOf(dict).generate()) }
            .map { it.joinToString(separator = " ") }
    result.append(relations.joinToString(separator = "\n"))
    val queries = (1..q).map { listOf(Gen.oneOf(dict).generate(), Gen.oneOf(dict).generate()) }
            .map { it.joinToString(separator = " ") }
    result.append(queries.joinToString(separator = "\n"))

    return result.toString()
}

fun main(args: Array<String>) {
    val f = File("/Users/Maksim_Novik/src/algoholism/src/main/resources/temp.txt")
    f.createNewFile()
    f.printWriter().use { out -> out.print(generateTest(100000, 543, 76658)) }
}
