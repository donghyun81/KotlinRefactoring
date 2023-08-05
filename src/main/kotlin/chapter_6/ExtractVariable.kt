package chapter_6

/*
 이름 : 6.3 변수 추출하기
 개요 : 표현식에 사용되는 각 값을 변수로 추출한다
 배경 :
 1. 표현식이 복잡할 경우 변수로 추출하여 표현식을 관리하기 쉽다.(디버깅의 중단점의 기준이 될 수 있어 디버깅에도 도움이 된다)
 2. 현 함수안에서만 의미가 있을 경우 변수로 추출 그 외에는 함수로 추출한다.(다른 함수에도 의미가 있을 경우 넓게 사용하여 중복을 줄이기 위해서)
 3. 문맥을 넓힐 때 할 일이 늘어나는 단점이 있다. 많이 늘어날것 같다면 임시 변수를 질의 함수로 바꾸기를 적용한다.
 */

/*
 절차
 1. 추출하려는 표현식에 부작용이 없는지 확인
 2. 불변 변수를 하나 생성하고 이름이 필요한 표현식의 일부를 대입한다.
 3. 원본 표현식을 변수로 대체
 4. 테스트
 5. 표현식이 여러곳에서 사용된다면 모두 변경한다.(교체할때마다 테스트)
 */

data class Order(val quantity: Int, val itemPrice: Double)

fun main() {
    val order = Order(600, 10.0)
    val totalPrice = ExtractVariable(order).price()
    println("Total Price: $totalPrice")
}


class ExtractVariable(order:Order) {
    private val basicPrice = order.quantity * order.itemPrice
    private val quantityDiscount = maxOf(0, order.quantity - 500) * order.itemPrice * 0.05
    private val shopping = minOf(order.quantity * order.itemPrice * 0.1, 100.0)
    fun price(): Double {
        // 가격 = 기본가격 - 수량할인 + 배송비
        return basicPrice - quantityDiscount + shopping
    }

}