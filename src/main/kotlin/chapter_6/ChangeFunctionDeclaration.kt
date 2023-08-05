package chapter_6
/*
 이름 : 6.5 함수 선언 바꾸기
 개요 : 함수 이름을 좋은 이름으로 바꾸기,적절한 매개변수 설정하기
 배경 : 함수는 시스템 구성의 연결부 역할을 한다. 잘못된 정의가 있을 경우 지속적인 방해요소로 작용한다
 이러한 부분에서 제일 중요한것은 함수 이름이다.
 함수 이름이 좋으면 호출문만 보고 어떤 함수인지 파악할 수 있다.
 */
/*
 간단한 절차 :
 1. 매개변수를 제거하려거든 먼저 함수 본문에서 제거 대상 매개변수를 참조하는 곳은 없는지 확인
 2. 메서드 선언을 원하는 형태로 변경
 3. 기존 메서드 선언을 참조하는 부분을 모두 찾아서 바뀐 형태로 수정
 4. 테스트

 마이그레이션 절차 :
 1. 이어지는 추출 단계를 수월하게 만들어야 한다면 함수의 본문을 적절히 리팩터링
 2. 함수 본문을 새로운 함수로 추출
 3. 추출한 함수에 매개변수를 추가해야 한다면 '간단한 절차'를 따라 추가
 4. 테스트
 5. 기존 함수 인라인
 6. 이름을 임시로 붙여뒀다면 함수 선언 바꾸기를 한 번 더 적용해서 원래 이름으로 되돌린다.
 7. 테스트
 */

import kotlin.math.PI


fun main() {
    ChangeFunctionDeclaration().call()
}

class ChangeFunctionDeclaration {
    fun call() {
        val someCustomers = listOf(
            Customer("John", Address("MA")),
            Customer("Jane", Address("NY")),
            Customer("Bob", Address("CT"))
        )
        printNewEnglandCustomers(getNewEnglanders(someCustomers))
        printCircleInfo(5.0)
    }
    fun printCircleInfo(circleRadius:Double){
        val circumference = circumference(circleRadius)
        println("Circumference of a circle with radius $circleRadius is $circumference")
    }
    fun printNewEnglandCustomers( newEnglanders : List<Customer>){
        println("New England Customers:")
        newEnglanders.forEach { customer ->
            println("Name: ${customer.name}, State: ${customer.address.state}")
        }
    }
    fun getNewEnglanders(someCustomers: List<Customer>) = someCustomers.filter { inNewEngland(it.address.state) }

    fun circumference(radius: Double): Double {
        return 2 * PI * radius
    }

    class Book {
        private val _reservations = mutableListOf<Customer>()

        fun addReservation(customer: Customer) {
            _reservations.add(customer)
        }

        fun zz_addReservation(customer: Customer, isPriority: Boolean) {
            if (isPriority) _reservations.add(customer) else return
        }
    }

    data class Customer(val name: String, val address: Address)

    data class Address(val state: String)

    fun inNewEngland(stateCode: String): Boolean = listOf("MA", "CT", "ME", "VT", "NH", "RI").contains(stateCode)


}