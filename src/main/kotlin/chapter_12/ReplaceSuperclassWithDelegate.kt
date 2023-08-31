package chapter_12

/*
 이름 : 12.11 슈퍼클래스를 위임으로 바꾸기
 배경 : 상속은 기능 재활용에 큰 장점이 있으나 혼란과 복잡도를 키우는 방식으로 이뤄지기도 한다.
 1. 자바의 스택 클래스를 잘못된 예로 들 수 있다.
 문제 : 자바의 스택은 리스트를 상속하고 있는데 스택은 리스트의 필요없는 인터페이스에 그대로 노출되게 된다.
 해결 : 스택에 리스트 객체를 필드에 저장해두고 필요한 기능만 위임하면된다.
 2.
 문제 : 이름과 엔진 크기등을 가진 자동차 모델 클레스(타입)에 차량 식별 번호와 제조일자 메서드를 더하면 물리적인 자동차(인스턴스)를 표현하는데
 재활용 할 수 있을거라 착각할 수 있다. 이는 미묘한 모델링 실수로 타입-인스턴스 동형이의어라고 부른다.

 권장사항 : 직관적으로 만들기 쉬운 상속을 적용시키고 문제가 생길 경우나 위임이 더 좋을거라고 생각될때 슈퍼클래스를 위임으로 바꾼다.
 */

/*
 절차
 1. 슈퍼클래스 객체를 참조하는 필드를 서브클래스에 담는다(이번 리팩터링을 끝마치면 슈퍼클래스가 위임 객체가 될 것이므로 이 필드를 '위임 참조'라 부르자.
 위임 참조를 새로운 슈퍼클래스 인스턴스로 초기화한다.
 2. 슈퍼클래스의 동작 각각에 대응하는 전달 함수를 서브클래스에 만든다 (물론 위임 참조로 전달)
 서로 관련된 함수끼리 그룹으로 묶어 진행하며, 그룹을 하나씩 만들 때마다 테스트.
 3. 슈퍼클래스의 동작 모두가 전달 함수로 오버라이드되었다면 상속 관계를 끊는다.
 */

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

open class CatalogItem(val id: Long, val title: String, val tags: List<String>) {
    fun hashTag(tag: String): Boolean {
        return tags.contains(tag)
    }
}

class Scroll(id: Long, title: String, tags: List<String>, private val lastCleaned: LocalDateTime) :
    CatalogItem(id, title, tags) {

    fun needsCleaning(targetDate: LocalDateTime): Boolean {
        val threshold = if (hashTag("revered")) 700 else 1500
        return daysSinceLastCleaning(targetDate) > threshold
    }

    private fun daysSinceLastCleaning(targetDate: LocalDateTime): Long {
        return lastCleaned.until(targetDate, ChronoUnit.DAYS)
    }
}

class ReplaceSuperclassWithDelegate {

}