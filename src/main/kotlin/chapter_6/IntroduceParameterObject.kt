package chapter_6

/*
 이름 : 6.8 매개변수 객체 만들기
 개요 : 자주 뭉치는 데이터 무리를 객체로 만들기 (data class)
 배경 :
 이점 -> 1.데이터 사이의 관계 명확 2. 매개변수 수 감소 3. 원소를 참조할때 항상 똑같은 이름이므로 일관성 증가
 새로 만든 데이터 구조가 문제 영역을 훨씬 간결하게 표현하는 새로운 추상 개념으로 경상되어, 코드의 개념적인 그림을 다시 그릴 수 있다.
 데이터가 모이고 -> 새로운 데이터 구조가 보이기 시작함 -> 새로운 개념으로 코드를 다시 그릴 수 있게 됨.
 */

/*
 절차
 1. 적당한 데이터 구조가 아직 마련되어 있지 않다면 새로 만든다.(data class)
 2. 테스트
 3. 함수 선언 바꾸기로 새 데이터 구조를 매개변수로 추가한다.
 4. 테스트
 5. 함수 호출 시 새로운 데이터 구조 인스턴스를 넘기도록 수정, 수정마다 테스트
 6. 기존 매개변수를 사용하던 코드를 새 데이터 구조의 원소를 사용하도록 변경
 7. 다 바꿧다면 기존 매개변수를 제거 후 테스트
 */
class IntroduceParameterObject {
    data class Reading(val temp: Int, val time: String)

    data class Station(val name: String, val readings: List<Reading>)

    data class NumberRange(val min:Int,val max:Int)

    fun readingsOutsideRange(station: Station,numberRange: NumberRange): List<Reading> {
        return station.readings.filter { r -> r.temp < numberRange.min || r.temp > numberRange.max }
    }

    val station = Station(
        name = "ZB1",
        readings = listOf(
            Reading(temp = 47, time = "2016-11-10 09:10"),
            Reading(temp = 53, time = "2016-11-10 09:20"),
            Reading(temp = 58, time = "2016-11-10 09:30"),
            Reading(temp = 53, time = "2016-11-10 09:40"),
            Reading(temp = 51, time = "2016-11-10 09:50")
        )
    )

    val alerts = readingsOutsideRange(station, numberRange = NumberRange(50,55))

}