package com.loktarius.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()

}
