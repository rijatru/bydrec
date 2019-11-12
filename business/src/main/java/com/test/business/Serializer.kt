package com.test.business

interface Serializer {

    fun <T> deserializeData(data: T): Any
}
