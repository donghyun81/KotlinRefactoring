package chapter_7

/*
 이름 : 7.5 클래스 추출하기
 개요 : 클래스가 커지는걸 방지하기 위해서 일부분을 목적에 맞게 추출한다.
 배경 : 클래스는 반드시 명확하게 추상화하고 소수의 주어진 역할만 처리해야한다(가이드 라인)
 추출 시기
 1. 일부 데이터와 메서드를 따로 묶을수 있을경우
 2. 함께 변경되는 일이 많거나 서로 의존하는 데이터가 있을 경우
 3. 특정 데이터나 메서드를 일부를 제거해도 다른 필드나 메서드들이 논리적으로 문제가 없을 경우에
 4. 작은 일부의 기능만을 위해 서브클래스를 만들거나, 확장해야 할 기능이 무엇이냐에 따라 서브클래스 만드는 방식이 달라진다면
 */
/*
 1. 클래스의 역할을 분리할 방법을 정한다.
 2. 분리될 역할을 담당할 클래스를 새로 만든다.
 3. 원래 클래스의 생성자에서 새로운 클래스의 인스턴스를 생성하여 필드에 저장해둔다.
 4. 분리될 역할에 필요한 필드들을 새 클래스로 옮긴다. 하나씩 옮길 때마다 테스트.
 5. 메서드들도 새 클래스로 옮긴다. 이때 저수준 메서드, 즉 다른 메서드를 호출하기보다는 호출을 당하는 일이 많은 메서드부터 옮긴다.
 6. 양쪽 클래스의 인터페이스를 살펴보면서 불필요한 메서드 제거, 이름도 새로운 환경에 맞게 변경.
 7. 새 클래스를 외부로 노출할지 정한다. 노출하려거든 새 클래스에 참조를 값으로 바꾸기를 적용할지 고민
 */
class Person(var name:String,val officeAreaCode :String,val officeNum:String){
    fun getTelephoneNum() = Telephone(officeAreaCode,officeNum).getTelephoneNum()
}
class Telephone(private val officeAreaCode :String, private val officeNum:String){
    fun getTelephoneNum() = "$officeAreaCode - $officeNum"
}
fun main(){

}