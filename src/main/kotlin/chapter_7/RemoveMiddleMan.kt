package chapter_7
/*
 이름 : 7.8 중개자 제거하기
 개요 : 중개자 메서드를 제거한다
 배경 : 클라이언트가 위임 객체의 여러 기능을 사용할때 서버의 서버의 위임 메서드가 계속 추가되어 클라이언트에서 위임 객체를 직접 호출하는게 더 좋은 경우 사용
 */
/*
 절차
 1. 위임 객체를 얻는 게터를 만든다.
 2. 위임 메서드를 호출하는 클라이언트가 모두 이 게터를 거치도록 수정, 하나씩 바꿀 때마다 테스트
 3. 모두 수정했다면 위임 메서드를 제거
 */
fun main(){
    data class Department(var chargeCode:Int,var manager:String)
    data class Person(val name:String,val department:Department){
        fun getManager() = department.manager
    }
    val manager = Person("나",Department(123,"너")).getManager()
    println(manager)
}