package chapter_11

/*
 이름 : 11.12 오류 코드를 예외로 바꾸기
 개요 : 오류 사항에서 실행할 코드를 예외로 바꾸기
 배경 : 예외는 프로그래밍 언어에서 제공하는 독립적인 오류 처리 메커니즘이다.
 이점 :예외 사용시 일일히 검사 오류를 식별해 콜스택 위로 던지는 일을 신경 쓰지 않아도 된다.
 특성 : 예외에는 독자적인 흐름이 있어서 프로그램의 나머지에서는 오류 발생에 따른 복잡한 상황에 대처하는 코드를 작성하거 읽을 일을 없게 만든다.
 예외는 정교한 메커니즘이고 대다수의 다른 정교한 메커니즘과 같이 정확하게 사용할때 최고의 효과를 낸다.
 정확한 예상 밖의 동작일때만 예외를 써야한다. -> 프로그램의 정상 동작 범주에 들지 않는 오류를 나타낼때
 tip) 예외를 던지는 코드를 프로그램 종료 코드로 바꿔도 프로그램이 여전히 정상 동작할지를 따져본다. 정상 동작하지 않으면 오류 검출을 하여 프로그램을 정상 흐름으로 처리한다.

 */
/*
 절차
 1. 콜스택 상위에 해당 예외를 처리할 예외 핸들러를 작성
 2. 테스트
 3. 해당 오류 코드를 대체할 예외와 그 밖의 예외를 구분할 식별 방법을 찾는다.
 4. 정적 검사 수행
 5. catch절을 수정하여 직접 처리할 수 있는 예외는 적절히 대처하고 그렇지 않은 예외는 다시 던짐
 6. 테스트
 7. 오류 코드를 반환하는 곳 모두에서 예외를 던지도록 수정. 수정할 때마다 테스트
 8. 모두 수정했다면 그 오류 코드를 콜스택 위로 전달하는 코드를 모두 제거. 수정할때마다 테스트
  */

object CountryData {
    val shippingRules = mapOf(
        "USA" to 10,
        "Canada" to 15,
        // 다른 국가에 대한 배송 규칙을 추가할 수 있습니다.
    )
}

fun main(){
    class OrderProcessingError(errorCode: Int) : Exception("주문처리 오류: $errorCode") {
        val code: Int = errorCode
    }

    data class ShippingRules(val data: Int)

    data class Order(val country: String) // Order 클래스를 적절하게 정의해야 합니다.

    fun localShippingRules(country: String): ShippingRules {
        val data = CountryData.shippingRules[country]

        return if (data != null) ShippingRules(data) else throw OrderProcessingError(-23)
    }

    fun calculateShippingCosts(anOrder: Order) {
        // 관련 없는 코드
        val shippingRules = localShippingRules(anOrder.country)
        // 더 관련 없는 코드
    }

    val orderData = Order("USA") // 예시 Order 데이터
    val errorList = mutableListOf<Map<String,Any>>()
    try {
        calculateShippingCosts(orderData)
    } catch (e: OrderProcessingError) {
        errorList.add(mapOf("order" to orderData, "errorCode" to e.code))
    } catch (e: Exception) {
        throw e
    }


}