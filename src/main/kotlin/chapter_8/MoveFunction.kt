package chapter_8

/*
 이름 : 8.1 함수 옮기기
 개요 : 함수의 위치를 다른곳으로 옮긴다.
 배경 : 좋은 소프트웨어의 설계 핵심은 모듈화가 얼마나 잘 되어있는지 뜻하는 모듈성(수정시에 해당 기능과 깊이 관련된 작은 일부만 이해해도 가능하게 해주는 능력)이다.
 모듈성이 높은 특징 -> 서로 연관된 요소들을 함께 묶고,요소 사이의 연결 관계를 쉽게 찾을수 있어야함
 객체지향의 핵심 모듈화 컨텍스트 -> 클래스,함수를 다른 함수에 중첩시킨 또 다른 공통 컨텍스트
 함수 옮기기 기준 세우기 :
 1. 모듈 A,B중에 함수를 더 많이 참조하는곳으로 함수를 옮긴다(캡슐화가 더 좋아진다)
 2. 다른 함수 안에서 도우미 역할로 정의된 함수 중 독립적으로도 고유한 가치가 있는 것은 접근하기 더 쉬운 장소로 옮기는게 좋다.
 3. 대상 함수의 현재 컨텍스트와 후보 컨텍스트를 둘러보며 대상
 1)함수 호출 함수가 무엇인지.
 2) 대상 함수가 호출하는 함수들이 무엇인지
 3) 대상 함수가 사용하는 데이터는 무엇인지
 살펴본 뒤에 서로 연관된 여러 함수를 묶을 새로운 컨텍스트가 필요해질 때가 많은데, 여러 함수를 클래스 묶기,클래스 추출하기로 해결한다.
 */
/*
 절차
 1. 선택한 함수가 현재 컨텍스트에서 사용 중인 모든 프로그램 요소를 살펴본다. 이 요소들 중에도 함께 옮겨야 할 게 있는지 고민한다.
 2. 선택한 함수가 다형 메서드인지 확인
 3. 선택한 함수를 타깃 컨텍스트로 복사(이때 원래의 함수를 소스 함수라 하고 복사해서 만든 새로운 함수를 타깃 함수라 칭함)타깃 함수가 새로운 터전에 자리 잡도록 다듬는다.
 4. 정적 분석을 수행
 5. 소스 컨텍스트에서 타깃 함수를 참조할 방법을 찾아 반영
 6. 소스 함수를 타깃 함수의 위임 함수가 되도로 수정
 7. 테스트
 8. 소스 함수를 인라인 할지 고민한다.
 */
data class TrackSummary(val time:Int,val distance:Int,val pace:Int)

fun trackSummary(points:List<Pair<String,Int>>) : TrackSummary{
    fun calculateTime() :Int = 0

    val totalTime = calculateTime()
    val totalDistance = xx_calculateDistance(points)
    val pace = totalTime / 60 / totalDistance
    return TrackSummary(totalTime,totalDistance,pace)
}

fun xx_calculateDistance (points:List<Pair<String,Int>>) :Int {
    var result = 0
    for (point in points.sortedBy { it.second }){
        val (_,distance) = point
        result += distance
    }
    return result
}

data class Type(val isPremium:Boolean)
class Account(){
    val daysOverdrawn = 0
    fun getBankCharge():Double{ // 은행 이자 계산
        var result = 4.5
        if (daysOverdrawn > 0) result += AccountType(daysOverdrawn).overdraftCharge
        return result
    }
}

class AccountType(val daysOverdrawn:Int){
    val type = Type(false)
    val overdraftCharge:Double
        get() {
            if (type.isPremium){
                val baseCharge = 10.0
                return if (daysOverdrawn <= 7) baseCharge
                else baseCharge + (daysOverdrawn-7)*0.85
            }
            return daysOverdrawn* 1.75
        }

}