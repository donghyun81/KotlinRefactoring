package chapter_4

import kotlin.time.Duration.Companion.milliseconds

data class ProvinceData(
    val name: String,
    val producers: List<ProducerData>,
    var demand: Int,
    var price: Int
)

data class ProducerData(
    val name: String,
    var cost: Int,
    var production: Int
)

class Province(private val data: ProvinceData) {
    private val _producers = mutableListOf<Producer>()
    private var _totalProduction: Int = 0

    init {
        data.producers.forEach { d ->
            addProducer(Producer(this, d))
        }
    }

    fun addProducer(arg: Producer) {
        _producers.add(arg)
        _totalProduction += arg.production
    }

    val name: String
        get() = data.name

    val producers: List<Producer>
        get() = _producers.toList()

    var totalProduction: Int
        get() = _totalProduction
        set(value) {
            _totalProduction = value
        }

    var demand: Int
        get() = data.demand
        set(value) {
            data.demand = value
        }

    var price: Int
        get() = data.price
        set(value) {
            data.price = value
        }

    val shortfall: Int
        get() = demand - totalProduction
    //  get() = demand - totalProduction *2 오류 주입

    val profit: Int
        get() = demandValue - demandCost

    val demandValue: Int
        get() = satisfiedDemand * price

    val satisfiedDemand: Int
        get() = minOf(demand, totalProduction)

    val demandCost: Int
        get() {
            var remainingDemand = demand
            var result = 0
            producers.sortedBy { it.cost }.forEach { p ->
                val contribution = minOf(remainingDemand, p.production)
                remainingDemand -= contribution
                result += contribution * p.cost
            }
            return result
        }
}

class Producer(private val aProvince: Province, private val data: ProducerData) {
    val name: String
        get() = data.name

    var cost: Int
        get() = data.cost
        set(value) {
            data.cost = value
        }

    var production: Int
        get() = data.production
        set(value) {
            val newProduction = value.coerceAtLeast(0)
            aProvince.totalProduction += newProduction - production
            data.production = newProduction
        }
}

fun sampleProvinceData(): ProvinceData {
    return ProvinceData(
        name = "Asia",
        producers = listOf(
            ProducerData(name = "Byzantium", cost = 10, production = 9),
            ProducerData(name = "Attalia", cost = 12, production = 10),
            ProducerData(name = "Sinope", cost = 10, production = 6)
        ),
        demand = 30,
        price = 20
    )
}

fun main() {
    val provinceData = sampleProvinceData()
    val province = Province(provinceData)
    println("Province Name: ${province.name}")
    println("Total Production: ${province.totalProduction}")
    println("Demand: ${province.demand}")
    println("Price: ${province.price}")
    println("Shortfall: ${province.shortfall}")
    println("Profit: ${province.profit}")
}


