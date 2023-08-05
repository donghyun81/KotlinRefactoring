package chapter_6

/*
 이름 : 6.7 변수 이름 바꾸기
 개요 : 변수의 용도에 맞는 적절한 이름으로 변경(이름)
 배경 : 이름의 중요성은 사용범위에 영향을 많이 받는다
 */
/*
 절차
 1. 폭넓게 쓰이는 변수라면 변수 캡슐화하기를 고려한다.
 2. 이름을 바꿀 변수를 참조하는 곳을 모두 찾아서, 하나씩 변경한다.
 3. 테스트
 */
class RenameVariable {
    val width = 2
    val height = 3
    val a = width * height // 변수 이름이 적절하지 않음
    val area = width * height // 변수 이름이 적절함

}