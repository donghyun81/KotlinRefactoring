package chapter_12

/*
 이름 : 12.3 생성자 본문 올리기
 개요 :
 배경 : 생성자는 다루기 까다롭다 그래서 나는 제약을 많이 두는 편이다.
 서브클래스들에서 기능이 같은 메서드를 발견하면 함수 추출하기, 메서드 올리기를 적용하여 슈퍼클래스로 옮기곤한다.
 하지만 생성자라면 할 수 있는일과 호출 순서에 제약이 있기때문에 다른식으로 접근해야한다.
 */

/*
 절차
 1. 슈퍼클래스에서 생성자가 없다면 하나 정의. 서브클래스의 생성자들에서 생성자가 호출되는지 확인
 2. 문장 슬라이드하기로 공통 문장 모두를 super() 호출 직후로 옮긴다.
 3. 공통 코드를 슈퍼클래스에 추가하고 서브클래스들에서는 제거. 생성자 매개변수 중 공통 코드에서 참조하는 값들을 모두 super()로 건낸다.
 4. 테스트
 5. 생성자 시작 부분으로 옮길 수 없는 공통 코드에는 함수 추출하기, 메서드 올리기를 차례로 적용
 */
fun main(){
    open class Party(val name: String)

    class Employee(id: Long, name: String, val monthlyCost: Int) : Party(name) {
        val id: Long = id

        // Employee 클래스의 초기화 로직
        // (다른 메서드나 생성자에서 초기화 로직을 추가할 수 있음)
    }
    class Staff {
        // Staff 클래스의 내용을 정의해야 합니다.
        // (Staff에 대한 구현이 필요함)
    }

    class Department(name: String, val staff: Staff) : Party(name) {
        // Department 클래스의 초기화 로직
        // (다른 메서드나 생성자에서 초기화 로직을 추가할 수 있음)
    }





}