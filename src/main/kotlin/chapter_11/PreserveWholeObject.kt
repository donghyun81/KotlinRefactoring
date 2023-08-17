package chapter_11

/*
 이름 : 11.4 객체 통째로 넘기기
 개요 : 매개변수로 객체를 통째로 넘겨서 본문에서 읽고 사용하기 편하게 바꾸는 기법
 배경 : 레코드를 통째로 넘기면 변화에 대응하기 쉽다.
 1. 함수가 더 다양한 데이터를 사용호도록 바뀌어도 매개변수 목록을 수정할 필요가 없어진다.
 2. 매개변수 목록이 짧아져서 일반적으로는 함수 사용법을 이해하기 쉬워진다.
 3.레코드에 담긴 데이터 중 일부를 받는 함수가 여러 개라면 함수들끼리는 같은 데이터를 사용하는 부분이 있을것이고,
 그 부분의 로직이 중복될 가능성이 커짐, 레코드를 통째로 넘기면 로직 중복도 없앨 수 있다.
 객체로 부터 값 몇개를 얻어서 무언가를 하는 로직이 있다면 그 로직을 객체 안으로 집어넣어야 함을 알려주는 악취로 봐야한다.
 객체 통째로 넘기기는 특히 매개변수 객체 만들기 후, 즉 산재한 수 많은 데이터 더미를 새로운 객체로 묶은 후 적용하곤 한다.
 한 객체가 제공하는 기능 중 항상 똑같은 일부만을 사용하는 코드가 많다면 그 기능만 묶어서 클래스로 추출하라는 신호일 수 있다.
 다른 객체의 메서드를 호출하면서 호출하는 객체 자신이 가지고 있는 데이터 여러 개를 건네는 경우 이런 상황이면 데이터 여러 개 대신 객체 자신의 참조만
 건네도록 수정할 수 있다(자바스크립트라면 this를 건넬 것이다.)
 */

/*
 절차
 1. 매개변수들을 원하는 형태로 받는 빈 함수를 만든다.
 2. 새 함수의 본문에서는 원래 함수를 호출하도록 하며, 새 매개변수와 원래 함수의 매개변수를 매핑
 3. 정적 검사 수행
 4. 모든 호출자가 새 함수를 사용하게 수정, 하나씩 수정하며 테스트
 5. 호출자 모두 수정 후 원래 함수 인라인
 6. 새 함수의 이름을 적절히 수정하고 모든 호출자에 반영
 */

class HeatingPlan(private val temperatureRange: TemperatureRange) {
    fun withinRange(aNumberRange:TemperatureRange): Boolean {
        return aNumberRange.low >= temperatureRange.low && aNumberRange.high <= temperatureRange.high
    }
}

data class TemperatureRange(val low: Int, val high: Int)

val aRoom = Room(TemperatureRange(15, 25))
val aPlan = HeatingPlan(TemperatureRange(18, 22))
val alert = mutableListOf<String>()

class Room(val daysTempRange: TemperatureRange)


fun main(){


    if (aPlan.withinRange(aRoom.daysTempRange)) {
        alert.add("방 온도가 지정범위를 벗어났습니다.")
    }
}
