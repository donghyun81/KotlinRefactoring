package chapter_1

import kotlin.math.floor

data class Play(val name: String, val type: String)
data class Invoice(val customer: String, val performances: List<Performance>)
data class Performance(val playID: String, val audience: Int)

fun main() {
    val plays = listOf(
        Play("Hamlet", "tragedy"),
        Play("As you Like it", "comedy"),
        Play("Othello", "tragedy")
    )
    val invoices = Invoice(
        "BigCo",
        listOf(
            Performance("Hamlet", 55),
            Performance("As you Like it", 35),
            Performance("Othello", 40)
        )
    )
    println(Statement(invoices, plays).statement())
}

class Statement(private val invoice: Invoice, private val plays: List<Play>) {
    fun statement(): String {
        return renderPlainText()
    }
    fun renderPlainText(): String {
        var result = "청구 내약 (고객명: ${invoice.customer})\n"
        for (perf in invoice.performances) {
            result += "${playFor(perf).name}: $${createCalculator(perf).getAmount(perf) / 100} (${perf.audience}석)\n"
        }
        result += "총액: ${totalAmount() / 100}\n"
        result += "적립포인트: ${totalVolumeCredits()}점\n"
        return result
    }

    // statement의 totalAmount 임시변수를 대체
    fun totalAmount(): Int = invoice.performances.sumOf {perf -> createCalculator(perf).getAmount(perf) }

    // statement의 volumeCredits 임시변수를 대체
    fun totalVolumeCredits(): Double = invoice.performances.sumOf { perf -> createCalculator(perf).getVolumeCredits(perf) }

    fun playFor(perf: Performance) = plays.find { it.name == perf.playID }!!
    fun createCalculator(perf: Performance): Calculator =
        when (playFor(perf).type) { // 희극, 코메디만 적용한 함수지만 후에 더 많은 조건이 생길 경우 이 함수의 복잡도가 너무 크게 증가한다
            "tragedy" -> Calculator(TragedyCalculator())
            "comedy" -> Calculator(ComedyCalculator())
            else -> throw Exception()
        }


}