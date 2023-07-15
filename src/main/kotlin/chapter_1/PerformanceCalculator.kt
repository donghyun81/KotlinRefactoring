package chapter_1

import kotlin.math.floor

interface PerformanceCalculator {
    fun getVolumeCredits(perf: Performance) = maxOf(perf.audience - 30, 0).toDouble()

    fun getAmount(performance: Performance): Int
}

class TragedyCalculator : PerformanceCalculator {
    override fun getAmount(perf: Performance): Int {
        return if (perf.audience > 30) 40000 + 1000 * (perf.audience - 30) else 40000
    }

}

class ComedyCalculator : PerformanceCalculator {
    override fun getAmount(perf: Performance): Int {
        var result = 30000
        if (perf.audience > 20) result += 10000 + 500 * (perf.audience - 20)
        result += 300 * perf.audience
        return result
    }

    override fun getVolumeCredits(perf: Performance): Double =
        floor(perf.audience.toDouble() / 5) + maxOf(perf.audience - 30, 0)
}

class Calculator(
    private val performanceCalculator: PerformanceCalculator
) : PerformanceCalculator by performanceCalculator