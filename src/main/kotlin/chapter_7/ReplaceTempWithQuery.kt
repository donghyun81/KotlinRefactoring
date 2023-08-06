package chapter_7

/*
 이름 : 7.4 임시 변수를 질의 함수로 바꾸기
 개요 : 임시 변수를 질의 함수(get)으로 바꾼다
 배경 :
 이점
 1. 추출한 함수에 변수를 따로 전달할 필요가 없어진다(매개변수로 전달하지 않고 다른 함수에서 접근한다)
 2. 부자연스러운 의존 관계나 부수효과를 찾고 제거하기 쉽다(매개변수로 전달되지 않기 때문에)
 사용 시점:
 1.여러 곳에서 똑같이 사용되는 변수일 경우
 2.클래스 내부에서 위 현상이 생길경우 효과가 가장큼
 3. 변수의 여러차례 다시 대입되는 경우
 사용 하면 안되는 시점 :
 1. 옛날 주소처럼 스냅숏 용도로 사용될경우
 */
/*
 절차
 1. 변수가 사용되기 전에 값이 확실히 결정되는지, 변수를 사용할 때마다 계산 로직이 매번 다른 결과를 내지는 않는지 확인한다.
 2. 읽기전용으로 만들 수 있는 변수는 읽기전용으로 만든다.
 3. 테스트
 4. 변수 대입문을 함수로 추출
 5. 테스트
 6. 변수 인라인하기로 임시 변수제거
 */
fun main() {

}

data class Item(val name: String, val price: Int)
class Order(val quantity: Int, val item: Item) {
    fun getPrice() :Double {
        var basePrice = this.quantity * item.price
        var discountFactor = 0.98

        if(basePrice > 1000) discountFactor -= 0.03
        return basePrice * discountFactor
    }
}
