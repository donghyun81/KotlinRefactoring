package kotest

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import org.junit.jupiter.api.assertAll
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

data class Speaker(val firstName:String,val lastName:String,val age:Int)
private val Int.ms :Long get() = milliseconds.inWholeMilliseconds

val expected = Speaker("동현","윤",26)
val actual = Speaker("동현","김",54)
class Kotest : StringSpec({
    "비교" {
        val expected = Speaker("동현","윤",26)
        val actual = Speaker("동현","김",54)
        assertSoftly(expected) {
            firstName.equals(actual.firstName)
        }
    }
    "확장 함수 활용" {
        val a = 10.ms
        val b = 10.ms

        assertSoftly(a) {// true,false 반환으로 테스트가 아니라 assertAll처럼 실행,테스트할 a로 apply처럼 사용할뿐
            assertEquals(a,b) // 통과 코드
            assertEquals(a,b+10) // 불합격 코드  true,false가 아니라
        }

    }
})