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


data class Read(
    val customer :String,
    val quantity : Int,
    val month :Int,
    val year :Int
)
class Reading(data:Read){
    val customer = data.customer
    val quantity = data.quantity
    val month = data.month
    val year =  data.year
    val baseCharge = baseRate(month,year) * quantity
    val taxableCharge = maxOf(0,baseCharge - taxThreshold(year) )

    fun baseRate(month:Int,year:Int) = month+year

    fun taxThreshold(year:Int) = 10

}
fun main(){
    //클라이언트 1
    val aReading = Reading(acquireReading())
    val baseCharge = aReading.baseCharge
    val taxableCharge = aReading.taxableCharge
}

private fun acquireReading() = Read("ivan", 10,5,2017)