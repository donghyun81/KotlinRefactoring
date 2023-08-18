package chapter_11

/*
 이름 : 11.8 생성자를 팩터리 함수로 바꾸기
 개요 : 제약사항을 줄이기 위해 생성자를 팩터리 함수로 변경한다
 배경 : 생성자와 다르게 함수에 이름을 붙일 수 있다. 생성되는 방법과 아규먼트로 무엇이 필요한지 설명 가능하다.
    원하는 형태의 타입을 리턴할 수 있다. 인터페이스 뒤에 실제 객체의 구현을 숨길 때 유용하게 사용할 수 있다.
    lisOf는 List인터페이스를 리턴한다. 실제로 리턴하는 객체는 플랫폼에 따라 다름
    호출될 때마다 새로운 객체를 만들 필요가 없다. 싱클턴 패턴처럼 객체를 하나만 생성하게 강제가능하다.
    아직 존재하지 않는 객체를 리턴할 수 있다. 앞으로 만들어질 객체를 사용하거나,프록시를 통해 만들어지는 객체를 사용할 수 있다.
    Proxy.*newProxyInstance*()
    팩토리 함수는 인라인으로 만들 수 잇으며, 파라미터들을 reified로 만들수 있다
    https://sungjk.github.io/2019/09/07/kotlin-reified.html
    생성자는 즉시 슈퍼클래스 또는 기본 생성자를 호출해야 하지만, 팩토리 함수를 사용하면 원하는 때에 호출가능
 */
/*
 절차
 1. 팩터리 함수 생성, 팩터리 함수의 본문에서는 원래 생성자를 호출
 2. 생성자를 호출하던 코드를 팩터리 함수 호출로 변경
 3. 하나씩 수정마다 테스트
 4. 생성자의 가시 범위가 최소가 되도록 제한
 */

class Employee(private val _name: String, private val _typeCode: String) {
    val name: String
        get() = _name

    val type: String
        get() = legalTypeCodes[_typeCode] ?: "Unknown"

    companion object {
        val legalTypeCodes = mapOf(
            "E" to "Engineer",
            "M" to "Manager",
            "S" to "Salesperson"
        )
    }
}

fun main() {
    val candidate = createEmployee(getDocumentName(), getDocumentEmpType())
    val leadEngineer = createEmployee(getDocumentLeadEngineer(), "E")
}

fun createEmployee(name:String, typeCode:String):Employee{
    return Employee(name,typeCode)
}

fun getDocumentName(): String {
    // Document에서 이름을 얻는 로직
    return ""
}

fun getDocumentEmpType(): String {
    // Document에서 직원 유형을 얻는 로직
    return ""
}

fun getDocumentLeadEngineer(): String {
    // Document에서 리드 엔지니어 정보를 얻는 로직
    return ""
}
