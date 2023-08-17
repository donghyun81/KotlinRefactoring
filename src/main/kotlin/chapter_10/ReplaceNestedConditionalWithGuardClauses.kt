package chapter_10

/*
 이름 : 10.3 중첩 조건문을 보호 구문으로 바꾸기
 개요 : 중첩 조건문을 보호 구문으로 덮어서 조건 해당시 바로 반환되도록
 배경 : 조건문의 두가지 형태
 1. 참인 경로, 거짓 경로 모두 정상 동작(if else 절 사용)
 2. 한쪽만 정상인 형태 (조건이 비정상이면 함수를 빠져나온다 이 형태를 보호 구문이라고 한다)
 이 리팩터링의 핵심 : 의도를 부각한다
 if then else 일 경우 : if와 else는 똑같이 중요하다는 뜻을 전달
 보호구문 : 이 함수의 핵심은 일이 끝나면 무언가 조치를 취한 후 함수를 빠져나온다는 뜻을 전달
 */
/*
 절차
 1. 교체해야 할 조건 중 가장 바깥 것을 선택하여 보호 구문으로 바꾼다.
 2. 테스트
 3. 1~2 과정을 필요만큼 반복
 4. 모든 보호 구문이 같은 결과를 반환한다면 보호 구문들의 조건식을 통합
 */

fun main(){

    fun isScholarship(reportScore:Int,midtermExam:Int,Finals:Int) : Boolean =
        !(reportScore >= 80 || midtermExam >= 80 || Finals >= 80)
}