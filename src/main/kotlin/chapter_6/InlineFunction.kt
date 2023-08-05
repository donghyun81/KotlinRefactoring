package chapter_6

/*
 이름 : 함수 인라인하기
 개요 : 추출된 함수를 다시 인라인한다
 배경 : 함수 본문이 명확하거나,본문 코드를 이름만큼이나 깔끔하게 리팩터링시에 쓸데없는 간접호출은 거슬릴 뿐이다.
 다른 함수로 단순히 위임하기만 하는 함수들이 너무 많을 경우 인라인한다.
 */

/*
 절차
 1. 다형 메서드인지 확인한다
 2. 인라인할 함수를 호출하는 곳을 모두 찾는다.
 3. 각 호출문을 함수 본문으로 교체한다.
 4. 하나씩 교체할 때마다 테스트
 5. 함수 정의(원래 함수) 삭제.
 */

fun main() {
    val driver = InlineFunction.Driver(7)
    val customer = InlineFunction.Customer("John Doe", "New York")
    println("Driver Rating: ${InlineFunction().rating(driver)}")
    println("Report Lines: ${InlineFunction().reportLines(customer)}")
}

class InlineFunction {

    data class Driver(val numberOfLateDeliveries: Int)

    fun rating(aDriver: Driver): Int {
        return if (moreThanFiveLateDeliveries(aDriver)) 2 else 1
    }

    fun moreThanFiveLateDeliveries(dvr: Driver): Boolean {
        return dvr.numberOfLateDeliveries > 5
    }

    data class Customer(val name: String, val location: String)

    fun reportLines(aCustomer: Customer): List<Pair<String, String>> {
        val lines = mutableListOf<Pair<String, String>>()
        gatherCustomerData(lines, aCustomer)
        return lines
    }

    fun gatherCustomerData(out: MutableList<Pair<String, String>>, aCustomer: Customer) {
        out.add("name" to aCustomer.name)
        out.add("location" to aCustomer.location)
    }

    fun main() {
        val driver = Driver(7)
        val customer = Customer("John Doe", "New York")
        println("Driver Rating: ${rating(driver)}")
        println("Report Lines: ${reportLines(customer)}")
    }


}