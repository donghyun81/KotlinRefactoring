package chapter_10

/*
 이름 : 10.7 제어 플래그를 탈출문으로 바꾸기
 개요 : 제어 플래그를 break,return을 통해 탈출하기
 배경 : 제어 플래그는 변수 선언,조건문을 추가시키는 악취를 만든다
 */
/*
 절차
 1. 제어 플래그를 사용하는 코드를 함수로 추출할지 고려한다.
 2. 제어 플래그를 갱신하는 코드 각각을 적절한 제어문으로 변경. 바꿀 때마다 테스트
 3. 모두 수정했다면 제어 플래그를 제거
 */

fun main(){

    fun sendAlert(){
        println("알림")
    }
    var found = false
    val people = listOf<String>()
    for (p in people) {
        if (!found) {
            if (p == "조커") {
                sendAlert()
                found = true
            }
            if (p == "사루만") {
                sendAlert()
                found = true
            }
        }
    }

}