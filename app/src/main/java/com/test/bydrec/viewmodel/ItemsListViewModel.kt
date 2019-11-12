package com.test.bydrec.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.bydrec.R
import com.test.bydrec.model.Item
import com.test.bydrec.model.ListHeaderItem
import com.test.bydrec.viewmodel.providers.ItemsProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ItemsListViewModel : ViewModel(),
    ItemsListViewMvvm.ViewModel {

    private lateinit var context: Context
    private lateinit var listItems: List<Item>
    private lateinit var filterItems: java.util.ArrayList<String>
    private lateinit var itemsProvider: ItemsProvider

    private val items: MutableLiveData<List<Item>> = MutableLiveData()

    override fun setContext(context: Context?) {
        context?.let { this.context = it }
    }

    override fun setItemsProvider(itemsProvider: ItemsProvider) {
        this.itemsProvider = itemsProvider
    }

    override fun getItems(): MutableLiveData<List<Item>> = items

    override fun getFilterItems(items: List<Item>): List<String> {
        filterItems = ArrayList()
        filterItems.add(context.getString(R.string.all_items))
        items.forEach {
            if (it is ListHeaderItem) {
                filterItems.add(it.date)
            }
        }
        return filterItems
    }

    override fun filterItemsBy(itemsFilter: String) {
        val filteredListItems: ArrayList<Item> = itemsProvider.filterItemsBy(listItems, itemsFilter, context.getString(R.string.all_items))
        items.postValue(filteredListItems)
    }

    override fun getListItems() {
        viewModelScope.launch {
            listItems = getListData()
            items.postValue(listItems)
        }
    }

    private suspend fun getListData(): List<Item> = withContext(Dispatchers.IO) {
        return@withContext suspendCoroutine { response: Continuation<List<Item>> ->
            val items = itemsProvider.items
            response.resume(items)
        }
    }
}
