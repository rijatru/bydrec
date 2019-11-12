package com.test.data.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.test.data.NotNullObject

open class DataResponse<T>(@SerializedName("data") @Expose val data: T) : NotNullObject {

    override val isEmpty: Boolean
        get() = false

    companion object {

        val EMPTY: DataResponse<Any> = object : DataResponse<Any>(Any()) {
            override val isEmpty: Boolean
                get() = true
        }
    }
}
