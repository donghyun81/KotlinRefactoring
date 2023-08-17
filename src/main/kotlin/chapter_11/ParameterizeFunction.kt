package chapter_11

/*
 이름 : 11.2 함수 매개변수화하기
 개요 : 매개변수로 받아 함수를 처리하도록 특정 값만 다를경우 매개변수를 받아서 함수를 처리한다.
 배경 : 로직이 비슷하고 리터럴 값만 다르다면 다른값만 매개변수로 받아 처리하여 중복을 줄일 수 있다
 */
/*
 1. 비슷한 함수 중 하나를 선택한다.
 2. 함수 선언 바꾸기로 리터럴들을 매개변수로 추가
 3. 이 함수를 호출하는 곳 모두에 적절한 리터럴 값을 추가
 4. 테스트
 5. 매개변수로 받은 값을 사용하도록 함수 본문을 수정. 하나 수정할 때마다 테스트
 6. 비슷한 다른 함수를 호출하는 코드를 찾아 매개변수화된 함수를 호출하도록 하나씩 수정한다. 하나 수정마다 테스트
 */

// Example 1

data class Person(var salary: Double)

fun tenPercentRaise(aPerson: Person) {
    aPerson.salary *= 1.1
}

fun fivePercentRaise(aPerson: Person) {
    aPerson.salary *= 1.05
}

fun raise(aPerson: Person,factor:Double){
    aPerson.salary *= 1+factor
}

// Example 2
fun withinBand(usage: Int,bottom:Int,top:Int): Int {
    return if (usage > bottom) (usage.coerceAtMost(top) - bottom) else 0
}

fun baseCharge(usage: Int): USD {
    if (usage < 0) return usd(0.0)
    val amount = withinBand(usage,0,100) * 0.03 +
            withinBand(usage,100,200) * 0.05 +
            withinBand(usage,200, Int.MAX_VALUE) * 0.07
    return usd(amount)
}

fun bottomBand(usage: Int): Int {
    return usage.coerceAtMost(100)
}


fun topBand(usage: Int): Int {
    return if (usage > 200) (usage - 200) else 0
}

class USD(val value: Double) {
    override fun toString(): String {
        return "\$$value"
    }
}

fun usd(amount: Double): USD {
    return USD(amount)
}

class ParameterizeFunction {
}