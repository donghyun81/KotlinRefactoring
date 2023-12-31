package chapter_8

/*
 이름 : 8.4 문장을 호출한 곳으로 옮기기
 개요 : 함수는 추상화의 기본 빌딩 블록이다. 코드 베이스의 기능 범위가 달라지면 추상화의 경계도 움직인다.
 함수 관점에서 초기에는 응집도 높고 한 가지 일만 수행하던 함수가 어느새 둘 이상의 다른 일을 수행하게 바뀔 수 있다.
 ex) 여러 곳에서 사용하던 기능이 일부 호출자에게는 다르게 동작해야 할때 ->
 달라진 함수에서 꺼내 해당 호출자로 옮겨야한다. 이때 문장 슬라이드하기로 달라지는 동작을 함수의 시작,끝으로 옮기고 문장을 호출한 곳으로 옮기기 리팩터링을 한다.
 */

/*
 절차
 1. 호출자가 한두 개뿐이고 피호출 함수도 간단한 단순한 상황이면, 피호출 함수의 처음(혹은 마지막)줄(들)을 잘라내어
    호출자(들)로 복사해 넣는다(필요시 적당히 수정). 테스트 통과시 리팩터링 종료
 2. 복잡한 상황시에, 이동하지 '않길' 원하는 모든 문장을 함수로 추출한 다음 다음 검색하기 쉬운 임시 이름을 지어준다.
 3. 원래 함수를 인라인
 4. 추출된 함수의 이름을 원래 함수의 이름으로 변경
 */
class MoveStatementsToCallers {
}