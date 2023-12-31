package chapter_10

/*
 이름 : 10.1 조건문 분해하기
 개요 : 조건문에서 실행되는 기능과 코드베이스를 함수로 추출해서 기능을 분해하자
 배경 : 조건부 로직은 코드도 길어지게 만들고 이해하기는 더 어렵게 만든다.
 코드 덩어리를 함수로 분리하고 목적에 맞는 함수로 바꿔서 무엇을 분기했는지 이해하기 쉽고 이용하기 쉽도록 분해한다.
 */
/*
 절차
 1. 조건식과 그 조건식에 딸린 조건절 각각을 함수로 추출한다.
 */
fun main(){
    fun isPremium(point:Int) = point >= 10
    fun applyDiscountRate(discountRate : Double,price:Double) = price * discountRate
    fun discount(point:Int,price:Double):Double =
        if (isPremium(point)) applyDiscountRate(0.1,price)
        else applyDiscountRate(0.05,price)
}