package chapter_8

import java.time.LocalDate
import java.util.Date
import java.util.Locale

/*
 이름 : 8.2 필드 옮기기
 개요 : 데이터 구조가 잘못 되어있는 필드를 옮긴다
 배경 : 프로그램의 진짜 힘은 데이터 구조에서 나온다. 적합한 데이터 구조를 활용하면 동작 코드는 자연스럽게 단순하고 직관적으로 짜여진다.
 경험,도메인 주도 설계 같은 기술(사용하는게 좋다는 뜻이다)로 보완해도 실수는 빈번하다
 */

/*
 절차
 1. 소스 필드가 캡슐화되어 있지 않다면 캡슐화한다.
 2. 테스트
 3. 타깃 객체에 필드(와 접근자 메서드들)를 생성한다.
 4. 정적 검사를 수행
 5. 소스 객체에서 타깃 객체를 참조할 수 있는지 확인한다.
 6. 접근자들이 타깃 필드를 사용하도록 수정
 7. 테스트
 8. 소스 필드를 제거
 9. 테스트
 */
fun main(){
    class CustomerContract(now: LocalDate?){
        val disCountRate = 0.0
    }

    class Customer(name:String,customerContract: CustomerContract){
        var discountRate = customerContract.disCountRate


        fun becomePreferred(){
            discountRate += 0.03
        }

        fun applyDiscount(amount:Double):Double{
            return amount.times(discountRate)
        }
    }

}
class MoveField {
}