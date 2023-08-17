package chapter_11

import java.time.LocalDate

/*
 이름 : 11.3 플래그 인수(실행할 로직을 호출하는 쪽에서 선택하기 위해 전달하는 인수) 제거하기
 개요 : 호출할 수 있는 함수가 무엇인지 어떻게 호출하는지 어렵게 만드는 플래그 인수를 제거한다.
 배경 : 불리언 플래그는 코드를 읽는이에게 뜻을 온전히 절단하지 못하기 때문에 좋지 못하다.
 ispremium로된 플래그 인수 사용보다는 premiumBook과 같은 명시적인 함수를 선언하는게 좋다.
 플래그 인수가 2개이상 사용될시에는 플래그 인수마다 함수를 만들어야하니 중복 방지를 위해 사용하는 근거가 될 수 있다.
 하지만 플래그 인수를 2개이상 쓰는건 기능이 많다는것일수도 있으니 적절히 리팩터링 할 수 있도록 해야한다.
 */
/*
 절차
 1. 매개변수로 주어질 수 있는 값 각각에 대응하는 명시적 함수들을 생성
 2. 원래 함수를 호출하는 코드들을 모두 찾아서 각 리터럴 값에 대응되는 명시적 함수를 호출하도록 수정
 */

fun main() {
    data class Order(val placedOn: LocalDate, val deliveryState: String)

    fun rushDeliveryDate(anOrder: Order): LocalDate {
        val deliveryTime = when (anOrder.deliveryState) {
            "MA", "CT" -> 1
            "NY", "NH" -> 2
            else -> 3
        }
        return anOrder.placedOn.plusDays((1 + deliveryTime).toLong())
    }

    fun regularDeliveryDate(anOrder: Order): LocalDate {
        val deliveryTime = when (anOrder.deliveryState) {
            "MA", "CT", "NY" -> 2
            "ME", "NH" -> 3
            else -> 4
        }
        return anOrder.placedOn.plusDays((2 + deliveryTime).toLong())
    }

    fun deliveryDate(anOrder: Order, isRush: Boolean): LocalDate
    = if (isRush) rushDeliveryDate(anOrder) else regularDeliveryDate(anOrder)

    val anOrder = Order(LocalDate.now(), "내집")
    // 클라이언트1
    val delivery1 = deliveryDate(anOrder, true)

// 클라이언트2
    val delivery2 = deliveryDate(anOrder, false)


}

class RemoveFlagArgument {
}
