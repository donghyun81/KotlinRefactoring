package chapter_12
/*
 이름 : 12.8 슈퍼클래스 추출하기
 배경 : 비슷한 일을 하는 두 클래스가 보이면 상속 메커니즘을 이용해서 공통 필드는 필드 올리기, 동작은 메서드 올리기를 활용
 객체 지향 상속 구조는 '현실 세계에서 활용하는 어떤 분류 체계에 기초하여 구현에 들어가기 앞서 부모.자식 관계를 신중히 설계해야 한다.'
 상속을 적용하는데 힌트가 될 수 있기 때문이다.하지만 경험상 프로그램 성장시에 깨우쳐가며 공통 요소를 찾았을때 수행하는 사례가 더 많았다.
 슈퍼 클래스 추출하기가 클래스 추출하기 보다 간단하니 먼저 시행해보고 슈퍼클래스를 위임으로 바꾸기를 통해 클래스로 추출한다.
 */
/*
 절차
 1. 빈 슈퍼클래스를 만든다. 원래의 클래스들이 새 클래스를 상속하도록 한다.
 2. 테스트
 3. 생성자 본문 올리기, 메서드 올리기, 필드 올리기를 차례로 적용하여 공통 원소를 슈퍼 클래스로 옮긴다.
 4. 서브클래스에 남은 메서드들을 검토. 공통되는 부분은 함수로 추출한 다음 메서드 올리기 적용
 5. 원래 클래스들을 사용하는 코드를 검토하여 슈퍼클래스의 인터페이스를 사용하게 할지 고민
 */
fun main(){
    open class Party(val monthlyCost: Int){
        fun annualCost(): Int {
            return monthlyCost * 12
        }

    }
    class Employee(val id: Long,monthlyCost: Int ,val name: String) : Party(monthlyCost) {

    }

    class Staff(val employees: List<Employee>) {
        val length: Int
            get() = employees.size
    }


    class Department(val name: String, val staff: Staff) {
        fun length(): Int = staff.length

        fun totalMonthlyCost(): Int = staff.employees.sumOf { it.monthlyCost }

    }




}