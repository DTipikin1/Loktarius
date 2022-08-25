package com.loktarius.feature_activity.domain.util

sealed class TagOrder(val orderType: OrderType ) {
    class Name(orderType: OrderType): TagOrder(orderType)
    class Date(orderType: OrderType): TagOrder(orderType)
}
