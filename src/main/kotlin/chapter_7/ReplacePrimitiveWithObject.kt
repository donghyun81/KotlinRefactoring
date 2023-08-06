package chapter_7

/*
 이름 : 7.3 기본형을 객체로 바꾸기
 개요 : 기본형을 객체로 바꾸기 ex) phoneNum:String -> phoneNum:PhoneNum
 배경 : 전화번호를 문자열로 표현했으나 포메팅,추가 작업이 필요한 경우가 많을경우 사용할때마다 추가 작업을 하지 않기 위해서 사용하는 기법
 전화번호 데이터가 출력 이상으로 작업이 필요한 경우 기본형을 사용할시에 추가 작업이 모두 중복될 수 있어서 매우 유용한 리팩터링 작업이다.
 */
/*
 절차
 1. 아직 변수를 캡슐화하지 않았다면 캡슐화한다.
 2. 단순한 값 클래스를 만든다. 생성자는 기존 값을 인수로 받아서 저장, 이 값을 반환하는 게터를 추가
 3. 정적 검사 수행
 4. 값 클래스의 인스턴스를 새로 만들어 필드에 저장하도록 세터 수정. 이미 있다면 필드의 타입을 적절히 변경
 5. 새로 만든 클래스의 게터를 호출한 결과를 반환하도록 게터 수정
 6. 테스트
 7. 함수 이름을 바꾸면 원본 접근자의 동작을 더 잘 드러낼 수 있는지 검토
  */
// data class Order(var priority:Priority)

class Priority(rating:Int){
    val rating = rating.toString()
}