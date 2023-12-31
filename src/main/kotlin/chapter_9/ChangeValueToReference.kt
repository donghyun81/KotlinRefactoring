package chapter_9

import chapter_6.Customer

/*
 이름 : 9.5 값을 참조로 바꾸기
 개요 : 모듈안 참조 객체를 값으로 바꾸기
 배경 : 데이터 구조안 논리적으로 같은 제3의 데이터 구조를 참조하는 레코드가 여러개 있을 수 있다.이때 참조,값으로 다룰수 있다.
 참조 : 여러 기능이 하나의 데이터를 참조한다 객체(속성)
 논리적으로 같은 데이터를 복제해 사용시 그 데이터를 갱신해야 할때다. 모든 복제본을 갱신해야하고 갱신하지 않으면 일관성이 깨진다.
 이런 상황시 복제된 데이터들을 참조로 바꿔주는게 좋다(데이터 갱신이 필요할때)
 값 : 데이터가 각 기능에 복사된다. 객체 저장소.호출(호출 대상 속성)
 엔티티 하나당 객체도 하나만 조재하며 보통 이런 객체들을 한데 모아서 클라이언트들의 접근을 관리해주는 저장소(repository)가 필요하다.
 각 엔티티를 표현하는 객체를 한 번만 생성, 객체가 필요한 곳에서는 모두 이 저장소로부터 얻어쓴다.
 */

/*
 절차
 1. 같은 부류에 속하는 객체들을 보관할 저장소를 만든다
 2. 생성자에서 이 부류의 객체들 중 특정 객체를 정확히 찾아내는 방법이 있는지 확인
 3. 호스트 객체의 생성자들을 수정하여 필요한 객체를 이 저장소에서 찾도록 한다. 수정마다 테스트
 */
fun main(){
    data class Customer(val id:String)

    class CustomersRepository(){
        var customers = mutableListOf<Customer>()

        fun initialize(){
            customers.clear()
        }
        fun registerCustomer(id:String):Customer?{
            if (!customers.contains(Customer(id))) customers.add(Customer(id))
            return findCustomer(id)
        }
        fun findCustomer(id:String): Customer? {
            return customers.find { it.id==id }
        }
    }
    class Order(val num:Int,var id:String){
        var customer = CustomersRepository().registerCustomer(id)
    }

}