package fhc.qualification

import java.io.File
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

val sampleInput = "/Users/Maksim_Novik/src/facebook hacker cup/resources/fighting_the_zombie_example_input.txt"
val temp = "/Users/Maksim_Novik/src/facebook hacker cup/resources/temp.txt"

data class TestCase(val health: Int, val numSpells: Int, val spells: List<Spell>)

data class Spell(val numDice: Int, val faces: Int, val modifier: Int = 0)

//10 2
//10d6-10 1d6+1
fun parseTestCase(t: Pair<String, String>): TestCase {
    val healthAndNumSpells = t.first.split(' ')
    val health = healthAndNumSpells[0].toInt()
    val numSpells = healthAndNumSpells[1].toInt()
    val spells = t.second.trim().split(' ').map(::parseSpell)
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
            .map(::parseTestCase)
    return testCases
}

fun solve(testCase: TestCase): BigDecimal? {
    return testCase.spells.map {
        val adjustedHealth = testCase.health + it.modifier

        val countWaysToGetAtLeastX = BigDecimal(countWaysToGetAtLeastX(it.numDice, it.faces, adjustedHealth))
        countWaysToGetAtLeastX.setScale(6)
        val total = BigDecimal(it.faces).pow(it.numDice)
        total.setScale(6)
        val result = countWaysToGetAtLeastX.divide(total, 6, RoundingMode.HALF_UP)
        result
    }.max()
}

fun countWaysToGetAtLeastX(n: Int, m: Int, x: Int): BigInteger {
    if (x <= 0) return bigInt(m).pow(n)
    if (n == 0) return bigInt(0)
    //f(x, n) = sum(f(x - i, n - 1)) for all 1 <= i <= m.
    var result = bigInt(0)
    for (i in 1..m) {
        result += countWaysToGetAtLeastX(n - 1, m, x - i)
    }
    return result
}

fun main(args: Array<String>) {
    val readInput = readInput(File(sampleInput))
    val map = readInput.map(::solve)
    map.forEach(::println)
}

fun <T : Number> bigInt(t: T) = BigInteger(t.toString())

