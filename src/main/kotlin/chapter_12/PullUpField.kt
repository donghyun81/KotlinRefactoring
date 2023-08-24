package chapter_12

/*
 이름 : 12.2 필드 올리기
 개요 : 중복된 필드를 상위 클래스에 옮긴다
 배경 : 서브 클래스의 필드를 분석해서 같거나 비슷한 용도로 사용되는 코드는 상위 클래스로 옮겨야한다.
 이점
 1. 데이터 중복 선언 제거
 2. 해당 필드를 사용하는 동작을 서브클래스에서 슈퍼클래스로 옮길수 있다.(동작 중복 제거)
 */
 /*
 절차
 1. 후보 필드들을 사용하는 곳 모두가 필드들을 똑같은 방식으로 사용하는지 면밀히 살핀다.
 2. 필드들의 이름이 각기 다르다면 똑같은 이름으로 바꾼다(필드 이름 바꾸기).
 3. 슈퍼클래스에 새로운 필드를 생성한ㄷ.
 4. 서브클래스의 필드들을 제거한다.
 5. 테스트
  */
fun main(){
        open class Employee(){
            val name = "나"

        }
    class SalesPerson : Employee(){

    }
    class Engineer : Employee(){

    }

    val person1 = SalesPerson()
    val person2 = Engineer()
    person1.name
    person2.name
}
