package chapter_7

/*
 이름 : 7.7 위임 숨기기
 개요 : 클라이언트가 알 필요없는 위임을 숨긴다.
 배경 : 서버 객체의 필드가 가리키는 객체(위임 객체)의 메서드를 호출하려면 클라이언트는 위임 객체를 알아야한다.
 위임 객체의 인터페이스가 달라지면 클라이언트는 모든 코드를 수정해야한다 이 의존성을 없애려면
 서버 객체 자체에 우임 메서드를 만들어서 객체의 존재를 숨기면 된다
 */
/*
 절차
 1. 위임 객체의 각 메서드에 해당하는 위임 메서드를 서버에 생성
 2. 클라이언트가 위임 객체 대신 서버를 호출하도록 수정. 하나씩 바꿀 때마다 테스트
 3. 모두 수정했다면, 서버로부터 위임 객체를 얻는 접근자를 제거
 4. 테스트
 */
fun main(){
    data class Department(var chargeCode:Int,var manager:String)
    data class Person(val name:String,val department:Department){
        fun getManager() = department.manager
    }
    val manager = Person("나",Department(123,"너")).getManager()
    println(manager)
}