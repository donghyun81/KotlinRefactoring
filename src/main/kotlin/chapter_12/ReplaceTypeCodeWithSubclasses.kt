package chapter_12

/*
 이름 : 12.6 타입 코드를 서브클래스로 바꾸기
 개요 :
 배경 : 비슷한 대상들을 특정 특성에 따라 구분해야할 때가 자주 있다.
 ex) 직원을 담당 업무로 구분(엔지니어,관리자,영업자 등) 주문의 시급성(급함,보통 등)
 이런일을 다루는 수단은 타입 코드 필드가 있다. 타입 코드는 프로그래밍 언어에 따라 열거형이나 심볼,문자열,숫자등으로 표현
 외부 서비스가 제공하는 데이터를 다룰때 딸려오는 일이 흔하다.
 타입 코드로 대부분 가능하지만 그 이상이 필요한 경우는 서브클래스를 사용한다.
 서브 클래스의 매력적인면은 두가지이다.
 1.조건에 따라 다르게 동작하도록 하는 다형성을 제공한다.
 타입 코드에 따라 동작이 달라져야하는 함수가 여러개일 경우 특히 유용하며 이런 함수들에 조건부 로직을 다형성으로 바꾸기를 적용 가능하다
 2. 특정 타입에서만 의미가 있는 값을 사용하는 필드나 메서드가 있을 때 발현
 '판매 목표'는 '영업자' 유형일 때만 의미 있다. 서브클래스를 만들고 필요한 서브클래스만 필드를 갖도록 정리한다.
 타입 코드도 가능하지만 서브클래스 방식이 관계를 더 명확히 드러낸다.
 */
/*
 절차
 1. 타입 코드 필드를 자가 캡슐화한다.
 2. 타입 코드 값 하나를 선택하여 그 값에 해당하는 서브클래스를 만든다. 타입 코드 게터 메서드를 오버라이드하여 해당 타입 코드의 리터럴 값을 반환.
 3. 매개변수로 받은 타입코드와 방금 만든 서브클래스를 매핑하는 로직 생성
 4. 테스트
 5. 타입 코드 값 각각에 대해 서브클래스 생성과 선택 로직 추가를 반복. 클래스 하나가 완성될 때마다 테스트
 6. 타입 코드 필드를 제거
 7. 테스트
 8. 타입 코드 접근자를 이용하는 메서드 모두에 메서드 내리기와 조건부 로직을 다형성으로 바꾸기 적용
 */


    open class Employee(name: String) {
        fun createEmployee(type:String) : Employee{
            return when(type){
                "engineer" -> Engineer(name)
                "salesperson" -> SalesPerson(name)
                "manager" -> Manager(name)
                else ->  throw IllegalArgumentException("$type 에 해당하는 직원 유형은 없습니다.")
            }
        }
        var name: String = name
            private set

        override fun toString(): String {
            return "Employee{" +
                    "name='$name'" +
                    '}'
        }
    }

    class Engineer (name:String): Employee(name) {
        fun type() = "engineer"
    }
    class SalesPerson(name:String): Employee(name){
        fun type() = "salesperson"
    }
    class Manager (name:String): Employee(name){
        fun type() = "manager"
    }
