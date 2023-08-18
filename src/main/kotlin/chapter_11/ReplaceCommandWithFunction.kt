package chapter_11

/*
 이름 : 11.10 명령을 함수로 바꾸기
 개요 :
 배경 : 명령은 함수 하나를 호출해 정해진 일을 수행하는 용도로 쓰이는데 로직이 크게 복잡하지 않다면 명령 객체는 장점보다 단점이 많다.
 */
 /*
 절차
 1. 명령을 생성하는 코드와 명령의 실행 메서드를 호출하는 코드를 함께 함수로 추출
 2. 명령의 실행 함수가 호출하는 보조 메서드들 각각을 인라인
 3. 함수 선언 바꾸기를 적용해서 생성자의 매개변수 모두를 명령의 실행 메서드로 옮긴다.
 4. 명령의 실행 메서드에서 참조하는 필드들 대신 대응하는 매개변수를 사용하게끔 변경. 수정마다 테스트
 5. 생성자 호출과 명령의 실행 메서드 호출을 호출자 안으로 인라인
 6. 테스트
 7. 죽은 코드 제거하기로 명령 클래스 제거
  */

fun charge(customer: Customer,usage: Int,provider: Provider):Double{
    val baseCharge =  customer.baseRate * usage
    return baseCharge+provider.connectionCharge
}
data class Customer(val baseRate: Double)
data class Provider(val connectionCharge: Double)

fun main() {
    val customer = Customer(baseRate = 0.1)
    val usage = 100
    val provider = Provider(connectionCharge = 5.0)

    val monthCharge = charge(customer, usage, provider)
    println("Monthly charge: $monthCharge")
}
