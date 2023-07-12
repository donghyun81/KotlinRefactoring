package chapter_1

import kotlin.math.floor

data class Play(val name: String, val type: String)
data class Invoice(val customer: String, val performances: List<Performance>)
data class Performance(val playID: String, val audience: Int)

fun main(){
    val plays = listOf(
        Play("Hamlet","tragedy"),
        Play("As you Like it","comedy"),
        Play("Othello","tragedy")
    )
    val invoices = Invoice("BigCo",
            listOf(
                Performance("Hamlet",55),
                Performance("As you Like it",35),
                Performance("Othello",40)
            )
        )
    println(statement(invoices,plays))
}
fun statement(invoice: Invoice, plays: List<Play>):String {
    var totalAmount = 0
    var volumeCredits = 0.0
    var result = "청구 내약 (고객명: ${invoice.customer})\n"
    for (performance in invoice.performances){
        val play = plays.find { it.name == performance.playID }!!
        var thisAmount = 0
        when(play.type){
            "tragedy" -> {
                thisAmount = 40000
                if (performance.audience > 30) thisAmount += 1000 *(performance.audience -30)
            }
            "comedy" -> {
                thisAmount = 30000
                if (performance.audience > 20) thisAmount += 10000 + 500 *(performance.audience -20)
                thisAmount += 300 * performance.audience
            }
            else -> {}
        }
        volumeCredits += maxOf(performance.audience -30,0)
        if (play.type == "comedy") volumeCredits += floor(performance.audience.toDouble()/5 )
        result += "${play.name}: $${thisAmount/100} (${performance.audience}석)\n"
        totalAmount += thisAmount
    }
        result += "총액: ${totalAmount/100}\n"
        result += "적립포인트: ${volumeCredits}점\n"
    return result
}