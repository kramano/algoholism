package fhc.qualification

import org.funktionale.memoization.memoize
import java.io.File
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

class FightingZombie {

    val sampleInput = "/Users/mnovik/src/facebook hacker cup/resources/fighting_the_zombie_example_input.txt"
    val sampleOutput = "/Users/mnovik/src/facebook hacker cup/resources/fighting_the_zombie_example_output.txt"
    val input = "./src/main/resources/fighting_the_zombie.txt"
    val output = "./src/main/resources/fighting_the_zombie_out.txt"

    data class TestCase(val health: Int, val numSpells: Int, val spells: List<Spell>)

    data class Spell(val numDice: Int, val faces: Int, val modifier: Int = 0)

    //10 2
    //10d6-10 1d6+1
    fun parseTestCase(t: Pair<String, String>): TestCase {
        val healthAndNumSpells = t.first.split(' ')
        val health = healthAndNumSpells[0].toInt()
        val numSpells = healthAndNumSpells[1].toInt()
        val spells = t.second.trim().split(' ').map { parseSpell(it) }
        return TestCase(health, numSpells, spells)
    }

    fun parseSpell(spellString: String): Spell {
        val matchResult = """(\d+)d(\d+)([+-]\d+)?""".toRegex().matchEntire(spellString)
        if (matchResult != null) {
            val (a, b, c) = matchResult.destructured
            val spell = Spell(a.toInt(), b.toInt())
            if (c.isNotEmpty()) {
                return spell.copy(modifier = c.toInt())
            }
            return spell
        }
        throw RuntimeException("Invalid input for spell: $spellString")
    }

    fun readInput(input: File): List<TestCase> {
        val readLines = input.readLines()
        val testCases = readLines.drop(1)
                .withIndex()
                .groupBy { it.index / 2 }
                .map { it.value.map { it.value } }
                .map { it[0] to it[1] }
                .map { parseTestCase(it) }
        return testCases
    }

    fun solve(testCase: TestCase): BigDecimal? {
        return testCase.spells.map {
            val adjustedHealth = testCase.health - it.modifier
            val countWaysToGetAtLeastX = BigDecimal(numWays(it.numDice, it.faces, adjustedHealth))
            countWaysToGetAtLeastX.setScale(6)
            val total = BigDecimal(it.faces).pow(it.numDice)
            total.setScale(6)
            val result = countWaysToGetAtLeastX.divide(total, 6, RoundingMode.HALF_UP)
            result
        }.max()
    }


    fun numWays(dice: Int, faces: Int, health: Int): BigInteger {
        var f: (Int, Int, Int) -> BigInteger = { i, i1, i2 -> bigInt(1) }

        f = { n: Int, m: Int, x: Int ->
            when {
                x > m * n -> bigInt(0)
                x == m * n -> bigInt(1)
                x <= n -> bigInt(m).pow(n)
                else -> (1..m).sumByBig { f(n - 1, m, x - it) }
            }
        }.memoize()
        return f(dice, faces, health)
    }

}

fun <T : Number> bigInt(t: T) = BigInteger(t.toString())

fun main(args: Array<String>) {
    val fz = FightingZombie()
    val outputFile = File(fz.output)
    if (outputFile.exists()) outputFile.delete()
    outputFile.createNewFile()
    val readInput = fz.readInput(File(fz.input))
    val results = readInput.map { fz.solve(it) }
            .mapIndexed { i, answer -> "Case #${i+1}: $answer" }
    outputFile.writeText(results.joinToString(separator = "\n"))

}

inline fun <T> Iterable<T>.sumByBig(selector: (T) -> BigInteger): BigInteger {
    var sum: BigInteger = bigInt(0)
    for (element in this) {
        sum += selector(element)
    }
    return sum
}


