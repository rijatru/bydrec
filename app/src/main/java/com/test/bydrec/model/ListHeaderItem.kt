package com.test.bydrec.model

import com.test.bydrec.R

class ListHeaderItem(
    val date: String
) :
    Item {

    override fun getType(): Int {
        return R.integer.list_header_item
    }
}
