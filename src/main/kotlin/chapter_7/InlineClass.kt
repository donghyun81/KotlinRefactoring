package chapter_7
/*
 이름 : 7.6 클래스 인라인하기
 개요 : 제 역할을 못하는 클래스를 인라인한다.
 배경 : 역할을 옮기다보니 특정 클래스에 역할이 거의 없을때 자주 사용되어지는 클래스로 흡수시킨다.
 두 클래스의 기능을 지금과 다르게 배분하고 싶을때 인라인 하여 하나로 합친뒤에 새로운 클래스로 추출한다.
 */
/*
 절차
 1.소스 클래스의 각 public 메서드에 대응하는 메서드들을 타깃 클래스에 생성한다.
 2.소스 클래스의 메서드를 사용하는 코드를 모두 타깃 클래스의 위임 메서드를 사용하도록 변경,바꿀때마다 테스트
 3.소스 클래스의 메서드와 필드를 모두 타깃 클래스로 옮긴다. 하나씩 옮길대마다 테스트
 4.소스 클래스를 삭제하고 조의를 표한다.
 */

fun main(){
    println(Shipment().getTrackingInfo())

}

class Shipment(){
    private val shippingCompany = 0
    private val trackingNumber = 0
    fun getTrackingInfo() = "$shippingCompany: $trackingNumber"
}