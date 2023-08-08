package chapter_8

/*
 이름 : 8.8 반복문을 파이프라인으로 바꾸기
 개요 : for() {} -> .forEach{},.map{} 등으로 바꾸기
 배경 : 논리 표현식을 파이프라인으로 표현하면 이해하기 훨씬 쉬워진다.
 */

/*
 절차
 1. 반복문에서 사용하는 컬렉션을 가리키는 변수를 하나 만든다.
 2. 반복문의 첫 줄부터 시작해서, 각각의 단위 행위를 적절한 컬렉션 파이프라인 연산으로 대체
    이때 컬렉션 파이프라인 연산은 1에서 만든 반복문 컬렉션 변수에서 시작하여, 이전 연산의 결과를 기초로 연쇄적으로 수행.
    대체할 때마다 테스트
 3. 반복문의 모든 동작을 대체했다면 반복문 자체를 지운다.
 */
class ReplaceLoopWithPipeline {
    fun main(){
        val weekdaySales = listOf(1000,2000,3000,4000,5000,6000,7000)
        var netProfit = getTotalWeekdayNetProfit(weekdaySales)
        var sales = getTotalWeekdaySales(weekdaySales)
        println(netProfit)
        println(sales)

    }
    fun getTotalWeekdaySales(weekdaySales:List<Int>) :Double {
        var result = 0.0
        weekdaySales.forEach { result += it }
        return result
    }
    fun getTotalWeekdayNetProfit(weekdaySales:List<Int>) = weekdaySales.map { it*0.5 }.sum()


}