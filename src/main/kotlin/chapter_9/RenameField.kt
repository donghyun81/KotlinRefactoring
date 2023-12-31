package chapter_9
/*
 이름 : 9.2 필드 이름 바꾸기
 개요 : 필드의 이름을 역할,목적에 맞게 변경
 배경 : 데이터 구조는 무슨 일이 벌어지는지를 이해하는 열쇠
 */

/*
 절차
 1. 레코드의 유효 범위가 제한적이라면 필드에 접근하는 모든 코드를 수정한 후 테스트. 이후 단계는 필요x
 2. 레코드가 캡슐화되지 않았다면 우선 레코드를 캡슐화
 3. 캡슐화된 객체 안의 private 필드명 변경, 그에 맞게 내부 메서드 수정
 4. 테스트
 5. 생성자의 매개변수 중 필드와 이름이 겹치는 게 있다면 함수 선언 바꾸기로 변경
 6. 접근자들의 이름도 변경
 */
class RenameField {
}