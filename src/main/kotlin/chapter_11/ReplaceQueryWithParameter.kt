package chapter_11

/*
 이름 : 11.6 질의 함수를 매개변수로 바꾸기
 개요 : 질의 함수를 매개변수로 변경
 배경 : 함수안에 두기 거북한 참조(전역 변수 참조, 제거하길 원하는 원소 참조) 책임을 호출자로 넘겨 이 문제를 해결한다
 이런 상황은 코드의 의존 관계 변경시 발생, 대상 함수가 특정 원소에 의존하길 원치 않을 때 발생.
 한쪽은 끝없는 매개변수를 만들고,한 쪽은 함수들끼리 많은 것을 공유하여 의존성이 강하다는 결함을 발생한다.
 프로그램의 이해도에 따라 더 나은쪽으로 스스로 개선하기 쉽게 설계 해두는게 중요
 참조 투명성(똑같은 값을 건네면 항상 똑같은 결과인 코드)이 보장되지 않은 코드에 접근하는 모든 함수는 투명성을 잃게된다.
 이 문제는 해당 원소를 매개변수로 바꿔서 해결한다(책임이 호출자로 가지만 투명성 보장은 더 큰 이점을 제공한다)
 그래서 모듈 개발 시에 순수 함수를 따로 구분하고, 프로그램의 입출력과 기타 ㅏ변 원소들을 다루는 로직으로 순수 함수들의 겉을 감싸는 패턴을 많이 활용
 */
 /*
 절차
 1. 변수 추출하기로 질의 코드를 함수 본문의 나머지 코드와 분리
 2. 함수 본문 중 해당 질의를 호출하지 않는 코드들을 별도 함수로 추출
 3. 방금 만든 변수를 인라인하여 제거
 4. 원래 함수도 인라인
 5. 새 함수의 이름을 원래 함수의 이름으로 수정
  */



class Thermometer {
    var selectedTemperature = 15
    var currentTemperature = 20
}

fun main() {
    val thermostat = Thermometer()
    val thePlan = HeatingPlan(thermostat)

    when {
        thePlan.targetTemperature > thermostat.currentTemperature -> setToHeat()
        thePlan.targetTemperature < thermostat.currentTemperature -> setToCool()
        else -> setOff()
    }
}

class HeatingPlan(thermostat:Thermometer) {
    val selectedTemperature =  thermostat.selectedTemperature
    private val _min = 0// 최소값 설정
        private val _max = 100// 최대값 설정

    val targetTemperature: Int
        get() {
            return when {
                selectedTemperature > _max -> _max
                selectedTemperature < _min -> _min
                else -> selectedTemperature
            }
        }
}

fun setToHeat() {
    // 난방 모드로 설정하는 로직
}

fun setToCool() {
    // 냉방 모드로 설정하는 로직
}

fun setOff() {
    // 모든 기기를 꺼내는 로직
}
