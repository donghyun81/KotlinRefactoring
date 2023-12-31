package chapter_11
/*
 이름 : 11.1 질의 함수와 변경 함수 분리하기(명령 질의 원칙)
 개요 : 함수가 질의와 변경을 같이 할 경우에 분리한다
 배경 : 우리는 외부에서 관할이 가능한 겉보기 부수효과가 전혀 없는 값을 반환하는 함수를 추구해야한다.
 언제든 호출 가능하고 테스트 하기도 쉬운 코드가 된다.
 질의 함수(읽기 함수)는 모두 부수효과가(명령 질의 분리 원칙) 없어야한다.
 */
/*
 절차
 1. 대상 함수를 복제하고 질의 목적에 충실한 이름에 짓는다.
 2. 새 질의 함수에서 부수효과를 모두 제거한다.
 3. 정적 검사 수행
 4. 원래 함수(변경 함수)를 호출하는 곳을 모두 찾는다. 호출하는 곳에서 반환 값을 사용한다면 질의 함수를 호출하도록 바꾸고, 원래 함수를 호출하는 코드를 바로 아래줄에
 새로 추가한다. 수정 할때마다 테스트
 5. 원래 함수에서 질의 관련 코드 제거
 6. 테스트
 */
fun  main() {

}
fun alertForMiscreant(people: List<String>){
        for (p in people) {
            if (p == "조커" || p == "사루만") {
                setOffAlarms()
            }
        }
    }

fun findMiscreant(people: List<String>): String {
    for (p in people) {
        if (p == "조커" || p == "사루만") {
            return " $p"
        }
    }
    return ""
}



fun setOffAlarms() {
    println("알람 끄기")
}

val people = listOf("조커", "배트맨", "스폰")
    val found = findMiscreant(people)
