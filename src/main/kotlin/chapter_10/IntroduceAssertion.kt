package chapter_10
/*
 이름 : 10.6 어서션 추가하기
 개요 : 어서션을 추가하여 프로그래밍적 실수가 없는지 확인하기
 배경 : 특정 조건이 참일 경우에만 실행하는 코드 영역을 어서션을 추가하여 확인작업이 필요하다
 어서션의 있고 없고는 기능의 동작에 아무런 영향을 주지 않도록 작성돼야한다.
 어서션의 이점:
 1. 오류 찾기(테스트 코드)
 2. 프로그램의 상태를 가정한체 실행되는지 알려주는 도구(디버깅시 버그 장소를 찾는데도 유용)
 3. 테스트 코드가 있다면 디버깅 용도로는 효용은 줄어들겠지만 소통 측면에서는 이점이 있다
 */

/*
 절차
 1. 참이라고 가정하는 조건이 보이면 그 조건을 명시하는 어서션을 추가
 */

fun main(){
    class Customer {
        var discountRate: Double = 0.0
            set(value){
                assert(null == value|| value >= 0.0)
                value
            }

        fun applyDiscount(aNumber: Double): Double {
            assert(null == aNumber || discountRate >= 0.0 )
            return if (discountRate != 0.0){ aNumber - discountRate * aNumber }
             else aNumber
        }
    }
}
