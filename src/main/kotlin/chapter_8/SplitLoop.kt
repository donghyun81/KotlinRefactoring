package chapter_8

/*
 이름 : 8.7 반복문 쪼개기
 개요 : 한 반복문에서 두가지 일을 할 경우에 반복문을 쪼개주자
 배경 : 반복문에서 두가지 일을 할 경우 사용하고 이해하기가 힘들다.
 그리고 반복문을 분리해서 함수 추출하기와 같이 사용할 경우 깔끔하고 이해하기 쉬운 코드베이스가 될 수 있다
 반복문을 두번 쓰는걸 귀찮고 성능 저하 때문에 비효율적이라고 생각 할 수 있지만 반복문들의 문제를 쉽게 파악하고 성능을 취적화 할때 더 강력한
 최적화가 되는 경우가 훨씬 많다.
 */

/*
 절차
 1. 반복문을 보제해 두 개로 만든다.
 2. 반복문이 중복되어 생기는 부수효과를 파악해서 제거
 3. 테스트
 4. 완료됐으면, 각 반복문을 함수로 추출할지 고민
 */
fun main(){
    val weekdaySales = listOf(1000,2000,3000,4000,5000,6000,7000)
    var netProfit = getTotalWeekdayNetProfit(weekdaySales)
    var sales = getTotalWeekdaySales(weekdaySales)
    println(netProfit)
    println(sales)

}
fun getTotalWeekdaySales(weekdaySales:List<Int>) = weekdaySales.sum()
fun getTotalWeekdayNetProfit(weekdaySales:List<Int>) = weekdaySales.sumOf { it.times(0.5) }
