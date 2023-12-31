package chapter_6

/*
 이름 : 6.10 여러 함수를 변환 함수로 묶기
 개요 : 여러 함수를 한 변환 함수로 묶는다.
 배경 :
 이점 -> 같은 도출 로직이 반복되는 경우 묶는게 검색과 갱신에 용이하고 로직 중복도 방지 할 수 있다.
 클래스,변환함수 묶는점 차이 : 원본 데이터가코드 안에서 갱신될때는 클래스로 묶는게 훨씬 용이하다(원본 데이터 수정시 일관성이 깨지기 때문에)
 */

/*
 절차
 1. 변환할 레코드를 입력받아서 값을 그대로 반환하는 변환 함수를 만든다.
 2. 묶을 함수 중 함수 하나를 골라서 본문 코드를 변환 함수로 옮기고. 처리 결과를 레코드에 새 필드로 기록. 클라이언트 코드가 이 필드를 사용하도록 수정
 3. 테스트
 4. 나머지 관련 함수도 위 과정에 따라 처리.
 */

class CombineFunctionsIntoTransform {


}