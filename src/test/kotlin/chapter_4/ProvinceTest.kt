package chapter_4

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.test.assertEquals

class ProvinceTest : StringSpec({
    val provinceData = sampleProvinceData()
    val province = Province(provinceData)
    "부족분 검사 테스트" {
        province.shortfall shouldBe 5
        assertSoftly(province) {
            assertEquals(shortfall,5)
        }
    }
    "생산량에 따른 이윤과 부족분 테스트" {
        province.producers[0].production = 20
        province.shortfall shouldBe -6
        province.profit shouldBe 292
    }
    "province가 변경되었는지 테스트" {
        province.shortfall shouldBe -6 // 테스트 내부 변경은 내부에서만 변경된 데이터를 사용
    }
})

