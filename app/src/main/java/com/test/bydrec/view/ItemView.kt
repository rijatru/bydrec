package com.test.bydrec.view

import com.test.bydrec.model.Item

interface ItemView {

    fun bind(item: Item, position: Int)
}
