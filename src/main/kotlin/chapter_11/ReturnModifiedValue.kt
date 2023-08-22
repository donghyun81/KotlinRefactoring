package chapter_11

/*
 이름 : 11.11 수정된 값 반환하기
 개요 : 값을 변경하는 함수는 반환 값으로 반환하기
 배경 : 데이터가 어떻게 수정되는지 추척하는것은 코드에서 이해하기 제일 어려운 부분중 하나다.
 데이터가 수정된다면 명확히 알려주어서 함수가 무슨 일을 하는지 쉽게 알 수 있게 하는게 매우 중요하다.
 변수를 갱신하는 함수일 경우 수정된 값을 반환한다.
 값 하나를 계산한다는 목적이 있는 함수에 가장 효과적이고 여러 개를 갱신할 경우 적합하지 않다.
 함수 옮기기의 준비 작업으로도 좋은 기법이다.
 */
/*
 절차
 1. 함수가 수정된 값을 반환하게 하여 호출자가 그 값을 자신의 변수에 저장하게 한다.
 2. 테스트
 3. 피호출 함수 안에 반환할 값을 가리키는 새로운 변수 선언
 4. 테스트
 5. 계산이 선언과 동시에 이뤄지도록 통합한다(선언 시점에 계산 로직을 바로 실행해 대입)
 6. 테스트
 7. 피호출 함수의 변수 이름을 새 역할에 어울리도록 바꾼다.
 8  테스트
 */


fun main() {

    data class Point(val elevation: Double)

    val points = listOf(
        Point(100.0),
        Point(150.0),
        Point(125.0),
        // 이곳에 더 많은 포인트를 추가할 수 있습니다.
    )

    fun calculateAscent():Double {
        var result = 0.0
        for (i in 1 until points.size) {
            val verticalChange = points[i].elevation - points[i - 1].elevation
            result  += if (verticalChange > 0.0) verticalChange else 0.0
        }
        return result
    }

    var totalAscent = calculateAscent()
    var totalTime = 0
    var totalDistance = 0

    val pace = totalTime / 60 / totalDistance




    fun calculateTime() {
        // 시간 계산 로직을 여기에 추가
    }

    fun calculateDistance() {
        // 거리 계산 로직을 여기에 추가
    }


// calculateAscent 안에서 totalAscent가 갱신됨이 드러나지 않고 있다.
    calculateAscent()
    calculateTime()
    calculateDistance()

}
