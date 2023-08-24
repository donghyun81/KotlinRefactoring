package chapter_12

/*
 이름 : 12.7 서브클래스 제거하기
 개요 : 서브 클래스를 제거하고 타입코드로 바꾸기
 배경 : 서브 클래싱 -> 원래 데이터 구조와는 다른 변종을 만들거나 종류에 따라 동작이 달라지게 할 수 있는 메커니즘
 다름을 프로그래밍하는 멋진 수단이다.
 서브클래스로 만든 변종이 다른 모듈로 이동하거나 완전히 사라지기도 하며 가치가 바래지기도한다.
 더이상 쓰지 않는 서브클래스는 슈퍼클래스의 필드로 대체해 제거한다.
 */
/*
 절차
 1. 서브클래스의 생성자를 팩터리 함수로 변경
 2. 서브클래스의 타입을 검사하는 코드가 있다면 그 검사 코드에 함수 추출하기와 함수 옮기기를 차례로 적용하여 슈퍼클래스로 옮긴다. 변경마다 테스트
 3. 서브클래스의 타입을 나타내는 필드를 슈퍼클래스에 만든다.
 4. 서브클래스를 참조하는 메서드가 방금 만든 타입 필드를 이용하도록 수정
 5. 서브클래스를 지운다.
 6. 테스트
 */


    open class Person(val name: String,val genderCode:String){
        fun getIsMale() = genderCode == "M"

    }




    data class Data(val name: String, val gender: String)
    fun createPerson(aRecord:Data) : Person {
        var result : Person  = when(aRecord.gender){
            "M" -> Person(aRecord.name,"M")
            "F" -> Person(aRecord.name,"F")
            else -> Person(aRecord.name,"X")
        }
        return result
    }
    fun loadFromInput(dataList: List<Data>): List<Person> = dataList.map { createPerson(it) }

        val dataList = listOf(
            Data("John", "M"),
            Data("Jane", "F"),
            Data("Alex", "X")
        )

fun main(){
        val persons = loadFromInput(dataList)
        println(persons.map { it.genderCode })
}