package chapter_10

/*
 이름 : 10.2 조건식 통합하기
 개요 : 조건식을 and,or등을 통해 같은 목적을 가진 조건문을 통합한다
 배경 : 조건문의 통합 조건 2가지
 1. 여러 조각으로 나뉜 조건들을 하나로 통합함으로써 하려는 일이 명확해짐
 2. 이 작업이 함수 추출하기까지 이어질 가능성이 높아서 -> 복잡한 조건식을 함수로 추출시 코드의 의도가 분명하게 드러나는 경우가 많기 때문에
 조건식을 통합하는 이유는 이 리팩터링을 하지 말아야할 이유도 설명 -> 하나의 검사라고 생각할 수 없는, 진짜로 독립된 검사들이라고 판단되면 절대 하면 안된다.
 */
/*
 절차
 1. 해당 조건식들 모두에 부수효과가 없는지 확인
 2. 조건문 두 개를 선택하여 두 조건문의 조건식들을 논리 연산자로 결합
 3. 테스트
 4. 조건이 하나만 남을 때까지 2~3 반복
 5. 하나로 합쳐진 조건식을 함수로 추출할지 고려
 */
fun main(){

    fun isScholarship(reportScore:Int,midtermExam:Int,Finals:Int) : Boolean{
      if (reportScore >= 80) return false
        if (midtermExam >= 80) return false
        if (Finals >= 80) return false
        return  true
    }
}
class ConsolidateConditionalExpression {
}