package chapter_6

import chapter_1.Invoice
import java.util.*

/*
 이름 : 함수 추출하기
 개요 : 행위 코드의 일을 파악하고 독립 함수로 추출하여 적절한 이름을 부여한다.
 배경 : 목적과 구현을 분리하는 방식이 가장 합리적인거 같다.(무슨일인지 파악이 어렵다면 함수로 추출하여 '무슨 일'이름을 붙인다)
 단순 구현에서 reserve()만 사용한 api가 있을수 있지만 사용 목적의 분리로서 의도 파악을 위해 분리하는게 좋다.
 */

/*
 절차
 1. 함수 새로 생성후 목적이 잘 들어난 이름을 붙인다(어떻게 x 무엇을 o)
 2. 추출할 코드를 원본 함수에서 복사하여 새 함수에 붙여넣는다.
 3. 추출한 코드 중 원본 함수의 지역 변수를 참조하거나 추출한 함수의 유효범위를 벗어나는 변수가 존재할 경우 매개변수로 전달한다
 4. 변수 처리 후 컴파일
 5. 원본 함수에서 추출한 코드 부분을 새로 만든 함수를 호출하는 문장으로 바꾼다 (추출한 함수로 일을 위임)
 6. 테스트
 7. 다른 코드에 방금 추출한 것과 똑같거나 비슷한 코드가 없는지 살피고 방금 추출한 새 함수를 호출할지 검토(인라인 코드를 함수 호출로 바꾸기 8.5절)
 */

fun main() {
    val orders = listOf(ExtractFunction.Order(100.0), ExtractFunction.Order(200.0), ExtractFunction.Order(300.0))
    val invoice = ExtractFunction.Invoice("John Doe", orders, Date())
    ExtractFunction().printOwing(invoice)
}

// 예시
class ExtractFunction {

    data class Order(val amount: Double)

    data class Invoice(val customer: String, val orders: List<Order>, var dueDate: Date)

    object Clock {
        val today: Date
            get() = Date() // Implement this method to get the current date
    }

    fun printOwing(invoice: Invoice) {
        var outstanding = calculatorOutstanding(invoice)
        printBanner()
        calculatorOutstanding(invoice)
        recordDueDate(invoice)
        printDetails(invoice, outstanding)

    }

    fun printBanner() {
        // 배너 출력
        println("***********")
        println("**고객 채무**")
        println("***********")
    }

    fun calculatorOutstanding(invoice: Invoice) = invoice.orders.sumOf { it.amount }.toLong()

    fun recordDueDate(invoice: Invoice) {
        // 마감일 기록
        val today = Clock.today
        val cal = Calendar.getInstance()
        cal.time = today
        cal.add(Calendar.DATE, 30)
        invoice.dueDate = cal.time
    }

    fun printDetails(invoice: Invoice, outstanding: Long) {
        // 세부사항 출력
        println("고객명: ${invoice.customer}")
        println("채무액: $outstanding")
        println("마감일: ${invoice.dueDate.toLocaleString()}")

    }

}