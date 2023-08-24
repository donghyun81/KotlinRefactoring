package chapter_12

/*
 이름 : 12.1 메서드 올리기
 개요 : 함수를 상위 계층으로 옮긴다
 배경 : 중복 코드 제거는 중요하다. 당장은 문제가 없더라도 bug가 꼬이는 음식물 쓰레기가 된다.
 중복은 한쪽의 변경이 다른쪽에는 반영되지 않을 수 있다는 위험을 가진다.
 메서드 올리기 적용이 이상하고 복잡한 상황은 메서드의 본문에서 참조하는 필드들이 서브클래스에만 있는 경우이다.
 필드를 먼저 슈퍼클래스로 올린후에 메서드를 올린다.
 세부 내용이 다를 경우 템플릿 메서드(기능의 뼈대(템플릿)와 실제 구현을 분리하는 패턴입니다) 만들기를 고려해보자
 */
/*
 절차
 1. 똑같이 동작하는 메서드인지 살핀다.
 2. 메서드 안에서 호출하는 다른 메서드와 참조하는 필드들을 슈퍼클래스에서도 호출하고 참조할 수 있는지 확인한다.
 3. 메서드 시그니처가 다르다면 함수 선언 바꾸기로 슈퍼클래스에서도 호출하고 참조할 수 있는지 확인
 4. 슈퍼클래스에 새로운 메서드 생성, 대상 메서드의 코드를 복사해넣는다.
 5. 정적 검사 수행
 6. 서브클래스 중 하나의 메서드 제거
 7. 테스트
 8. 모든 서브클래스의 메서드가 없어질 때까지 서브클래스의 메서드를 하나씩 제거
 */

