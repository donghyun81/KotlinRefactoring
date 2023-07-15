import chapter_1.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StatementTest {
    val plays = listOf(
        Play("Hamlet","tragedy"),
        Play("As you Like it","comedy"),
        Play("Othello","tragedy")
    )
    val invoices = Invoice("BigCo",
        listOf(
            Performance("Hamlet",55),
            Performance("As you Like it",35),
            Performance("Othello",40)
        )
    )

    @Test
    fun `statement 테스트`(){ // 리팩터링이 잘 되었는지 확인할때마다 사용(간단하게 만들었는데도 매우 유용함)
        val expected = "청구 내약 (고객명: BigCo)\n" +
                "Hamlet: \$650 (55석)\n" +
                "As you Like it: \$580 (35석)\n" +
                "Othello: \$500 (40석)\n" +
                "총액: 1730\n" +
                "적립포인트: 47.0점\n"
        assertThat(expected).isEqualTo(Statement(invoices,plays).statement())
    }
}