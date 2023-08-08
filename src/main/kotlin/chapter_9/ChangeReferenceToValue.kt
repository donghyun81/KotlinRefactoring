package chapter_9
/*
 이름 : 9.4 참조를 값으로 바꾸기
 개요 :
 배경 : 객체를 다른 객체에 중첩하면 내부 겍체를 참조 혹은 값으로 취급 가능하다.
 참조냐 값이냐의 차이는 내부 객체의 속성을 갱신하는 방식에서 극명하게 드러남
 참조 -> 내부 객체는 그대로 둔체 그 객체의 속성만 갱신 객체.속성 = 0
 값 -> 객체 = 객체(속성)
 */
/*
 절차
 1. 후보 클래스가 불변인지, 혹은 불변이 될 수 있는지 확인
 2. 각각의 세터를 하나씩 제거
 3. 이 값 객체의 필드들을 사용하는 동치성 비교 메서드 생성.
 */
class Person(){
    val telePhoneNum = TelePhoneNum(123,456)
    var officeAreaCode = telePhoneNum.areaCode
    var officeNumber = telePhoneNum.number

}
data class TelePhoneNum(var areaCode:Int,var number:Int)