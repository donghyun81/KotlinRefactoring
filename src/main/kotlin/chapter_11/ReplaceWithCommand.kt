package chapter_11

/*
 이름 : 11.9 함수를 명령으로 바꾸기
 개요 :
 배경 : 함수를 그 함수만을 위한 객체 안으로 캡슐화시에 더 유용해진 상황 -> 이 객체를 '명령 객체' , 명령이라 한다.
 명령 객체는 메서드 하나로 구성되며 이 메서드를 요청해 실행하는것이 목적이다.
 명령은 함수보다 유연하고 제어하고 표현 할 수 있다.
 1. 되돌리기(undo)와 같은 보조 연산 제공
 2. 수명주기를 더 정밀하게 제어하는데 필요한 매개변수를 만들어주는 메소드 제공
 3. 상속과 훅(?)을 이용해 맞춤형으로 만들 수 있다.
 4. 객체는 지원하지만 일급 함수(?)를 지원하지 않는 프로그래밍 언어를 사용할 때는 명령을 통해 기능 대부분을 흉내 낼 수 있다.
 하지만 유연성은 (언제나) 복잡성을 키우고 얻는 대가다 그래서 일급 함수와 명령중에 선택한다면 95프로는 일급 함수를 사용하는데 손을 들어준다
 */
/*
 절차
 1. 대상 함수의 기능을 옮길 빈 클래스를 생성. 클래스 이름은 하수 이름에 기초해 짓는다.
 2. 방금 생성한 빈 클래스로 함수를 옮긴다
 3. 함수의 인수들 각각은 명령의 필드로 만들어 생성자를 통해 설정할지 고민해본다.
 */
fun score(candidate: Candidate, medicalExam: MedicalExam, scoringGuide: ScoringGuide): Int {
    var result = 0
    var healthLevel = 0
    var highMedicalRiskFlag = false

    if (medicalExam.isSmoker) {
        healthLevel += 10
        highMedicalRiskFlag = true
    }

    var certificationGrade = "regular"
    if (scoringGuide.stateWithLowCertification(candidate.originState)) {
        certificationGrade = "low"
        result -= 5
    }

    // 비슷한 코드가 한참 이어짐

    result -= maxOf(healthLevel - 5, 0)
    return result
}
class Scorer(
    private val candidate: Candidate,
    private val medicalExam: MedicalExam,
    private val scoringGuide: ScoringGuide
    ){
    fun excute() : Int{
        var result = 0
        var healthLevel = 0
        var highMedicalRiskFlag = false
        fun scoreSmoking(){
            if (medicalExam.isSmoker) {
                healthLevel += 10
                highMedicalRiskFlag = true
            }
        }
        scoreSmoking()

        var certificationGrade = "regular"
        if (scoringGuide.stateWithLowCertification(candidate.originState)) {
            certificationGrade = "low"
            result -= 5
        }

        // 비슷한 코드가 한참 이어짐

        result -= maxOf(healthLevel - 5, 0)
        return result
    }

}

data class Candidate(val originState: String)
data class MedicalExam(val isSmoker: Boolean)
class ScoringGuide {
    fun stateWithLowCertification(state: String): Boolean {
        // 해당 기능을 구현해야 합니다.
        return false
    }
}

fun main() {
    val medicalExam = MedicalExam(isSmoker = true)
    val candidate = Candidate(originState = "XYZ")
    val scoringGuide = ScoringGuide()

    val result = score(candidate, medicalExam, scoringGuide)
    println("Score: $result")
}
