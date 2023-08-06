package chapter_6

/*
 이름 : 여러 함수를 클래스로 묶기
 개요 : 연관된 여러 함수를 클래스로 묶는다.
 배경 :
 이점 -> 함수들이 공유하는 공통 환경을 더 명확하게 표현 가능, 절단되는 인수를 줄여서 객체 안에서의 함수 호출을 간결하게 가능, 전달하기 위한 참조 제공 가능.
 */

/*
 절차
 1. 함수들이 공유하는 공통 데이터 레코드를 캡슐화
 2. 공통 레코드를 사용하는 함수 각각을 새 클래스로 옮긴다.
 3. 데이터를 조작하는 로직들은 함수로 추출해서 새 클래스로 옮긴다.
 */
class Reading(){

}
fun main(){
    //클라이언트 1

    val aReading = acquireReading()
    val baseCharge = baseRate(aReading.month,aReading.year) * aReading.quantity

    //클라이언트 2

    val aReading = acquireReading()
    val baseCharge = baseRate(aReading.month,aReading.year) * aReading.quantity
    val taxableCharge = maxOf(0,base-taxThreshold(aReading.year))

    //클라이언트 3
    val aReading = acquireReading()
    val baseChargeAmount = baseRate(aReading.month,aReading.year) * aReading.quantity
    fun calculateBaseCharge(aReading) = baseRate(aReading.month,aReading.year) * aReading.quantity
}
class CombineFunctionsIntoClass {
    val aReading = acquireReading()
    val baseCharge = baseRate(aReading.month,aReading.year) * aReading.quantity
    val taxableCharge = maxOf(0,base-taxThreshold(aReading.year))
}