package chapter_7

/*
 이름 : 7.2 컬렉션 캡슐화하기
 개요 : 컬렉션 타입을 캡슐화한다.
 배경 : 컬렉션 변수로의 접근을 캡슐화하면서 게터가 컬렉션 자체를 반환할시에 감싼 클래스가 눈치채지 못하는 상태에서 컬렉션의 원소가 바뀔수 있다
 (컬렉션은 주소값을 저장하므로 게터로 컬렉션을 호출하여 내부(주소의 일부)값을 변경시킨다면 컬렉션 내부의 값이 변경되므로 무결성이 깨지게 된다)
 방법 1. 내부 컬렉션을 직접 수정하지 못하도록 컬렉션 값을 반환하지 않는법 ex) aCustomer.orders.size -> aCustomer.ordersCnt()
 방법 2. 읽기 전용으로 내부 데이터를 수정할 수 없도록 설정
 방법 3. (가장 흔한 방식) 컬렉션 게터를 제공하되 내부 컬렉션의 복사본을 반환하도록 설정
 3가지 방법이 있지만 프로젝트에서 한가지 방법으로 통일하는게 중요하다
 */
/*
 절차
 1. 아직 컬렉션을 캡슐화하지 않았다면 변수 캡슐화하기부터 한다.
 2. 컬렉션에 원소를 추가/제거하는 함수 추가
 3. 정적 검사를 수행
 4. 컬렉션을 참조하는 부분을 모두 찾는다. 컬렉션으 변경자를 호출하는 코드가 모두 앞에서 추가한 추가/제거 함수를 호출하도록 수정
 수정마다 테스트
 5. 컬렉션 게터를 수정해서 원본 내용을 수정할 수 없는 읽기전용 프락시나 복제본을 반환하게 한다.
 6. 테스트
 */

fun main(){
    class Person(val name: String) {
        private val courses: MutableList<Course> = mutableListOf()

        // 컬렉션을 읽기 위한 메서드
        fun getCourses() = courses
        fun addCourses(course: Course) {courses.add(course)}
        fun removeCourses(course: Course) {require(courses.remove(course))}
    }
    val person = Person("나")
    for (i in 0 until 4){
        person.addCourses(Course("캡슐화",true))
    }
    println(person.getCourses())
    person.removeCourses(Course("캡슐화",true))
    println(person.getCourses())


}
//data class Person(val name:String,private val courses:MutableList<Course>){
//    fun getCourses() = courses
//    fun addCourses(course: Course) {courses.add(course)}
//    fun removeCourses(course: Course) {require(courses.remove(course))}
//}

// 데이터 표현하고 다루기뿐 아니라 컬렉션의 추가 작업도 실행되기 때문에 class를 사용하는게 더 적합하다고 판단했습니다.

data class Course(val name:String,val isAdvanced:Boolean)
class EncapsulateCollection {
}