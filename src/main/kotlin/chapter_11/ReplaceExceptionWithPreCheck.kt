package chapter_11

/*
 이름 : 11.13 예외를 사전확인으로 바꾸기
 배경 : 예외 코드를 남용되지 않도록 함수 호출전에 검사 할 수 있는 부분은 호출하는곳에서 조건을 검사해야한다
 */

/*
 절차
 1. 예외를 유발하는 상황을 검사할 수 있는 조건문을 추가한다. catch 블록의 코드를 조건문의 조건절중 하나로 옮기고, 남은 try 블록의 코드를 다른 조건절로 옮긴다.
 2. catch 블록에 어서션을 추가하고 테스트
 3. try문과 catch 블록을 제거
 4. 테스트
 */

fun main(){
    open class Employee{
        fun getName(){

        }

    }

    class SalesPerson : Employee() {
    }

    class  Engineer :Employee(){
    }
    val person1 : SalesPerson = SalesPerson()
    person1.getName()
    val person2 : Engineer = Engineer()
    person2.getName()
}