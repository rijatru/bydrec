package com.test.bydrec.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.test.bydrec.model.Item
import com.test.bydrec.viewmodel.providers.ItemsProvider

interface ItemsListViewMvvm {

    interface ViewModel {

        fun setContext(context: Context?)

        fun setItemsProvider(itemsProvider: ItemsProvider)

        fun getListItems()

        fun getItems(): MutableLiveData<List<Item>>

        fun getFilterItems(items: List<Item>): List<String>

        fun filterItemsBy(itemsFilter: String)
    }

    interface View {

        fun getApplicationContext(): Context?

        fun filterItemsBy(filterPosition: Int)
    }
}
