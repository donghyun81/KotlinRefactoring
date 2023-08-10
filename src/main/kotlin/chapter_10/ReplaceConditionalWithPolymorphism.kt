package chapter_10
/*
 이름 : 10.4 조건부 로직을 다형성으로 바꾸기
 개요 : 다형성을 통해 조건부에 따라 특정 형태의 객체로 반환하도록 바꾸기
 배경 : 복잡한 조건부 로직은 해석하기 가장 난해한 대상에 속한다. 클래스와 다형성을 이용해서 더 직관적인 코드로 만들수 있다.
 1.타입을 여러개 만들고 각 타입이 조건부 로직을 자신만의 방식으로 처리하도록 구성
 2.기본 동작을 위한 case와 변형 동작일 경우 기본 동작을 super class로 사용하고
 변형 동작을 서브 클래스로 처리하도록 한다.
 좋은 기능이지만 남용하지 않는게 좋다 복잡하다고 생각되는 로직에서 사용하는게 제일 이상적이다.
 */

/*
 절차
 1. 다형적 동작을 표현하는 클래스들이 아직 없다면 만들어준다. 적합한 인스턴스를 알아서 만들어 반환하는 팩터리 함수도 함께 만든다
 2. 호출하는 코드에서 팩터리 함수를 사용하게 한다.
 3. 조건부 로직 함수를 슈퍼클래스로 옮긴다.
 4. 서브클래스 중 하나를 선택. 서브클래스에서 슈퍼클래스의 조건부 로직 메서드를 오버라이드한다.
    조건부 문장 중 선택된 서브클래스에 해당하는 조건절을 서브클래스 메서드로 복사한 다음 적절히 수정
 5. 같은 방식으로 각 조건절을 해당 서브클래스에서 메서드로 구현
 6. 슈퍼 클래스 메서드에는 기본 동작 부분만 남긴다. 혹슨 슈퍼클래스가 추상 클래스여야 한다면,
 이 메서드를 추상으로 선언하거나 서브클래스에서 처리해야함을 알리는 에러를 던진다
 */

open class Bird(name:String, type:String, numberOfCoconuts :Int, voltage :Int, isNailed:Boolean){
    val name = name
    val type = type
    val numberOfCoconuts = numberOfCoconuts
    val voltage = voltage
    val isNailed = isNailed

    open fun plumage():String{
        return when(type){
            "유럽 제비" -> "보통이다"
            "아프리카 제비" -> if (numberOfCoconuts>2) "지쳤다" else "보통이다"
            "노르웨이 파랑 앵무" -> if (voltage>100) "그을렸다" else "예쁘다"
            else -> "알 수 없다"
        }
    }

    open fun airSpeedVelocity():Double?{
        return when(type){
            "유럽 제비" -> 35.0
            "아프리카 제비" -> 40.0 - 2 * numberOfCoconuts
            "노르웨이 파랑 앵무" -> if (isNailed) 0.0 else 10.0 + voltage / 10.0
            else -> null
        }
    }
}

fun createBird(name:String, type:String, numberOfCoconuts :Int, voltage :Int, isNailed:Boolean) :Bird{
    val bird = Bird(name,type,numberOfCoconuts, voltage, isNailed)
    return when(bird.type){
        "유럽 제비" -> EuropeanSwallow(bird)
        "아프리카 제비" -> AfricanSwallow(bird)
        "노르웨이 파랑 앵무" -> NorwegianBlueSwallow(bird)
        else -> bird
    }
}

class EuropeanSwallow(bird:Bird) :
    Bird(bird.name,bird.type,bird.numberOfCoconuts,bird.voltage,bird.isNailed) {
    override fun plumage():String = "보통이다"
    override fun airSpeedVelocity(): Double? = 35.0
    }

class AfricanSwallow(bird:Bird) :
    Bird(bird.name,bird.type,bird.numberOfCoconuts,bird.voltage,bird.isNailed) {
    override fun plumage():String = if (numberOfCoconuts>2) "지쳤다" else "보통이다"
    override fun airSpeedVelocity(): Double? = 40.0 - 2 * numberOfCoconuts
    }
class NorwegianBlueSwallow(bird:Bird) :
    Bird(bird.name,bird.type,bird.numberOfCoconuts,bird.voltage,bird.isNailed) {
    override fun plumage():String= if (voltage>100) "그을렸다" else "예쁘다"
    override fun airSpeedVelocity(): Double? = if (isNailed) 0.0 else 10.0 + voltage / 10.0
}
fun plumages(birds:MutableList<Bird>):Map<String,String> {
    return birds.associate {b ->  val bird = createBird(b.name,b.type,b.numberOfCoconuts,b.voltage,b.isNailed)
        bird.name to bird.plumage()
    }
}

fun speeds(birds: MutableList<Bird>):Map<String,Double?>{
    return birds.associate {b ->  val bird = createBird(b.name,b.type,b.numberOfCoconuts,b.voltage,b.isNailed)
        bird.name to bird.airSpeedVelocity()
    }

}
