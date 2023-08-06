package chapter_7

/*
 이름 : 레코드 캡슐화하기
 개요 : 레코드를 캡슐화해서 보호한다
 배경 : 가변데이터를 저장하는 용도로는 레코드보다 객체를 선호한다(표현식을 통한 계산식이나 그렇지 않은 값을 구분해야한다는점이 번거롭다)
 */

/*
 절차
 1. 레코드를 담은 변수를 캡슐화한다.
 2. 레코드를 감싼 단순한 클래스로 해당 변수의 내용을 교체한다. 이 클래스에 원본 레코드를 반환하는 접근자도 정의하고,
  변수를 캡슐화하는 함수들이 이 접근자를 사용하도록 수정
 3. 테스트
 4. 원본 레코드 대신 새로 정의한 클래스 타입의 객체를 반환하는 함수들을 새로 생성
 5. 레코드를 반환하는 예전 함수를 사용하는 코드를 4에서 만든 새 함수를 사용하도록 변경. 필드에 접근할 때는 객체의 접근자를 사용.
 적절한 접근자가 없다면 추가. 한 부분을 바꿀 때마다 테스트
 6. 클래스에서 원본 데이터를 반환하는 접근자와 원본 레코드를 반환하는 함수들을 제거
 7. 테스트
 8. 레코드의 필드도 데이터 구조인 중첩 구조라면 레코드 캡슐화하기와 컬렉션 캡슐화하기를 재귀적으로 적용.
 */


fun main(){
    val organization = Organization("애크미 구스베리","GB")
    val production = Production("12345", ProductionInfo("빵",2000))
    println("${organization.copy("바보").name} ${organization.name}")
    println("${production.info.name} ${production.info.copy(name = "asd").name}")
}
data class Organization(val name:String,val country:String) // 레코드 캡슐화
data class Production(val id:String ,val info:ProductionInfo) // 중첩 레코드 캡슐화
data class ProductionInfo(val name:String,val price:Int)
class EncapsulateRecord {

}
