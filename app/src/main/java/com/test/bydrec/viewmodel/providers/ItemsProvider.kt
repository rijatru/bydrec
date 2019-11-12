package com.test.bydrec.viewmodel.providers

import com.test.bydrec.model.Item
import java.util.*

interface ItemsProvider : Injectable {

    val items: List<Item>

    fun filterItemsBy(
        items: List<Item>,
        itemsFilter: String,
        defaultValue: String
    ): ArrayList<Item>
}
