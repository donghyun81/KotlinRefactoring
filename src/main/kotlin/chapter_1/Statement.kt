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
    println(Statement(invoices,plays).statement())
}
class Statement(private val invoice: Invoice, private val plays: List<Play>){
    fun statement():String {
        var result = "청구 내약 (고객명: ${invoice.customer})\n"
        for (performance in invoice.performances){
            result += "${playFor(performance).name}: $${amountFor(performance)/100} (${performance.audience}석)\n"
        }
        result += "총액: ${totalAmount()/100}\n"
        result += "적립포인트: ${totalVolumeCredits()}점\n"
        return result
    }
    fun totalAmount() : Int{ // statement의 totalAmount 임시변수를 대체
        var result = 0
        for (performance in invoice.performances){
            result += amountFor(performance)
        }
        return result
    }
    fun totalVolumeCredits():Double{ // statement의 volumeCredits 임시변수를 대체
        var volumeCredits = 0.0
        for (performance in invoice.performances){
            volumeCredits +=volumeCreditsFor(performance)
        }
        return volumeCredits
    }
    fun volumeCreditsFor(perf:Performance) =
        if (playFor(perf).type == "comedy") floor(perf.audience.toDouble()/5 )+maxOf(perf.audience -30,0).toDouble()
        else maxOf(perf.audience -30,0).toDouble()

    fun playFor(perf: Performance) = plays.find { it.name == perf.playID }!!
    fun amountFor(perf: Performance) : Int{
        var result = 0 // 함수의 결과를 반환한다는 의미로 변수이름을 result로 지어주는게 좋다.
        when(playFor(perf).type){ // 희극, 코메디만 적용한 함수지만 후에 더 많은 조건이 생길 경우 이 함수의 복잡도가 너무 크게 증가한다
            "tragedy" -> {
                result = 40000
                if (perf.audience > 30) result += 1000 *(perf.audience -30)
            }
            "comedy" -> {
                result = 30000
                if (perf.audience > 20) result += 10000 + 500 *(perf.audience -20)
                result += 300 * perf.audience
            }
        }
        return result
    }


}