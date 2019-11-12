package com.test.data.api

interface NetworkController {

    fun <T> get(request: DataRequest): DataResponse<*>
}
