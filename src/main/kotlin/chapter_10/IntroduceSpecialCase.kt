package chapter_10

/*
 이름 : 10.5 특이 케이스 추가하기
 개요 : 특수한 경우의 공통 동작을 요소 하나에 모아서 사용하는 기법
 배경 : 특정한 값에 똑같은 반응을 하는 코드가 곳곳에 등장하면 중복 코드중 하나가 된다.
 특이 케이스는 여러 형태로 표현될 수 있다.
 1.케이스 객체에 데이터를 읽기만 하면 반환 값을 담은 리터럴 객체 형태만 준비한다.
 2.그 이상의 동작을 수행한다면 필요한 메서드를 다음 객체를 생성한다
 특이 케이스 객체는 캡슐화한 클래스가 반환되거나, 변환을 거쳐 데이터 구조에 추가시키는 형태도 될 수 있다.
 */
/*
 절차
 1. 컨테이너에 특이 케이스인지를 검사하는 속성을 추가,false를 반환하게 한다.
 2. 특이 케이스 객체를 만든다. 이 객체는 특이 케이스인지를 검사하는 속성만 포함, 이 속성은 true를 반환
 3. 클라이언트에서 특이 케이스인지를 검사하는 코드를 함수로 추출. 모든 클라이언트가 값을 직접 비교하는 대신 방금 추출한 함수를 사용하도록 고친다.
 4. 코드에 새로운 특이 케이스 대상 추가. 함수의 반환 값으로 받거나 변환 함수를 적용하면 된다.
 5. 특이 케이스를 검사하는 함수 본문을 수정하여 특이 케이스 객체의 속성을 사용하도록 한다.
 6. 테스트
 7. 여러 함수를 클래스로 묶기나 여러 함수를 변환 함수로 묶기를 적용하여 특이 케이스를 처리하는 공통 동작을 새로운 요소로 옮긴다.
 8. 아직도 특이 케이스 검사 함수를 이용하는 곳이 남아 있다면 검사 함수를 인라인한다
 */
interface BillingPlan {
    // BillingPlan 관련 속성 및 동작 정의
}

class BasicBillingPlan : BillingPlan {
    // BasicBillingPlan 관련 속성 및 동작 정의
}
class PaymentHistory {
    val weeksDelinquentInLastYear: Int = 0
    // PaymentHistory 관련 속성 및 동작 정의
}

class Site {
    val customer: Customer
        get() {
            val _customer = customer
            return if (_customer.name == "미확인 고객") UnknownCustomer() else _customer
        }
}

fun isUnknown(arg: Customer): Boolean {
    if (arg !is Customer || arg is UnknownCustomer) {
        throw Error("잘못된 값 비교: <$arg>")
    }
    return arg.name == "미확인 고객"
}

interface Customer {
    val name: String
    var billingPlan: BillingPlan
    val paymentHistory: PaymentHistory
    val isUnknown: Boolean
}

class RegularCustomer : Customer {
    override val name: String
        get() = ""
    override var billingPlan: BillingPlan = BasicBillingPlan()
    override val paymentHistory: PaymentHistory
        get() = PaymentHistory()
    override val isUnknown: Boolean
        get() = false
}

class UnknownCustomer : Customer {
    override val name: String
        get() = "거주자"
    override var billingPlan: BillingPlan = BasicBillingPlan() // registry.billingPlans.basic 대신에 BasicBillingPlan()을 반환
    override var paymentHistory: PaymentHistory = PaymentHistory()
    override val isUnknown: Boolean
        get() = true
}


fun main() {
    val site = Site()

    val aCustomer: Customer = site.customer
    val customerName = aCustomer.name

    val plan = aCustomer.billingPlan

    if (isUnknown(aCustomer)) {
        // UnknownCustomer의 경우 처리
    } else {
        // RegularCustomer의 경우 처리
        aCustomer.billingPlan = BasicBillingPlan()
    }

    val weeksDelinquent = aCustomer.paymentHistory.weeksDelinquentInLastYear
}
