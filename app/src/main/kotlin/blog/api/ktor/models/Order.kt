package blog.api.ktor.models

import kotlinx.serialization.Serializable

@Serializable
data class OrderItem(val item: String, val amount: Int, val price: Double)

@Serializable
data class Order(val orderNumber: String, val contents: List<OrderItem>)

val orderStorage = listOf(
    Order("2023-01-13-17:40", listOf(
        OrderItem("Ham Sandwich", 3, 7.80),
        OrderItem("Apple Juice", 1, 2.00),
        OrderItem("Cheesecake", 3, 10.10)
    )),
    Order("2023-01-13-17:40", listOf(
        OrderItem("Cheeseburger", 3, 15.23),
        OrderItem("Orange Juice", 1, 2.00),
        OrderItem("Ice Cream", 2, 5.40)
    )),
)