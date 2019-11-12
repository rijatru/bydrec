package com.test.bydrec.viewmodel

import androidx.lifecycle.ViewModel
import com.test.bydrec.model.ListHeaderItem

class ListHeaderItemViewViewModel(private var item: ListHeaderItem) : ViewModel(),
    ListHeaderItemViewMvvm.ViewModel {

    override fun getDate(): String? {
        return item.date
    }
}
