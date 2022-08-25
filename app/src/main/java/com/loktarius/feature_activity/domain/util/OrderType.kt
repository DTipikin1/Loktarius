package com.loktarius.feature_activity.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()

}
