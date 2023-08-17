import kotlin.math.sqrt

fun main(args: Array<String>) {
    for (i in 1 until 6){
        println()
    }
}

fun isPrime(targetNum: Int) = targetNum > 1 && (2..sqrt(targetNum.toDouble()).toInt()).none { targetNum % it == 0 }

fun gdc(a:Int,b:Int):Int{
    return if(b != 0) gdc(b,a%b) else a
}