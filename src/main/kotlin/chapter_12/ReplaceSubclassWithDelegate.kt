package chapter_12

import java.time.LocalDateTime
import kotlin.math.roundToInt

/*
 이름 : 12.10 서브클래스를 위임으로 바꾸기
 개요 : 결합도를 낮추기위해 서브클래스에서 위임으로 바꾼다
 베경 : 상속은 한 번만 쓸 수 있다는 단점이 있다. 무언가 달라지는 이유가 여러개여도 상속에서는 단 하나의 이유만 선택해 기준으로 삼는다.
 사람 객체의 동작을 나이대,소득 수준에 따라 달리 하고 싶다면 서브클래스는 젊은이와 어른, 부자와 서민이 되어야 한다. 둘 다가 불가능하다.
 상속은 클래스들의 관계를 긴밀하게 결합한다.
 1. 부모를 수정시 이미 존재하는 자식들의 기능을 해치기가 쉽기 때문에 주의해야한다.
 2. 자식들은 부모 클래스를 어떻게 상속해서 써야하는지 신경써야한다.
 부모와 자식이 서로 다른 모듈에 속하거나 다른 팀에서 구현한다면 문제가 더 심해진다.
 위임(delegate)는 두 문제를 해결한다
 1.다양한 클래스에 서로 다른 이유로 위임할 수 있다.
 2. 위임은 객체 사이의 일반적인 관계이므로 상호작용에 필요한 인터페이스를 명확히 정의 할 수 있다.
 서브클래싱 관련 문제가 생기면 흔히들 위임으로 바꾼다.
 중요 원칙 "상속 보다는 컴포지션(객체) = (위임)을 사용하라"
 */

/*
 1. 생성자를 호출하는 곳이 많다면 생성자를 팩터리 함수로 바꾼다.
 2. 위임으로 활용할 빈 클래스를 만든다. 이 클래스의 생성자는 서브클래스에 특화된 데이터를 전부 받아야 하며, 보통은 슈퍼클래스를 가리키는 역참조도 필요하다.
 3. 위임을 저장할 필드를 슈퍼클래스에 추가한다.
 4. 서브클래스 생성 코드를 수정하여 위임 인스턴스를 생성하고 위임 필드에 대입해 초기화한다.
 5. 서브클래스의  메서드 중 위임클래스로 이동할 것을 고른다.
 6. 함수 옮기기를 적용해 위임 클래스로 옮긴다. 원래 메서드에서 위임하는 코드는 지우지 않는다.
 7. 서브클래스 외부에도 원래 메서드를 호출하는 코드가 있다면 서브클래스의 위임코드를 슈퍼클래스로 옮긴다.
 이때 위임이 존재하는지를 검사하는 보호 코드로 감싸야 한다. 호출하는 외부 코드가 없다면 원래 메서드는 죽은 코드가 되서 제거
 8.
 테스트
 9. 서브클래스의 모든 메서드가 옮겨질 때까지 5~8 과정을 반복
 10. 서브클래스들의 생성자를 호출하는 코드를 찾아서 슈퍼클래스의 생성자를 사용하도록 수정
 11. 테스트
 12. 서브클래스를 삭제
 */
    class Show(val price:Int,val properties :List<String>){
        fun hasOwnProperty(property:String) = properties.contains(property)

    }
    class Extras(val premiumFee:Double,val properties :List<String>){
        fun hasOwnProperty(property:String) = properties.contains(property)
    }


    open class Booking(val show: Show, val date: LocalDateTime) {
        val isPeakDay = LocalDateTime.now() == date
        val premiumDelegate = this
        open fun hasTalkback() =  show.hasOwnProperty("talkBack") && !isPeakDay
        open fun basePrice() = if(isPeakDay) show.price+ (show.price * 0.15).roundToInt() else show.price
        fun bePremiumDelegate(extras: Extras) =  PremiumBookingDelegate(this,extras)
    }

class PremiumBookingDelegate(val booking: Booking,val extras: Extras){
    fun hasTalkback() = booking.show.hasOwnProperty("talkBack")
    fun basePrice() = (booking.basePrice() + extras.premiumFee).roundToInt()
    fun getDinner() = extras.hasOwnProperty("dinner") && booking.isPeakDay
}

    fun createBooking(show:Show,date:LocalDateTime) = Booking(show,date)
    fun createPremiumBooking(show:Show,date:LocalDateTime, extras: Extras) : PremiumBookingDelegate{
        val result = Booking(show,date)
        return result.bePremiumDelegate(extras)
    }

