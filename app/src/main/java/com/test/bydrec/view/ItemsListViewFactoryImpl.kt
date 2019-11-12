package com.test.bydrec.view

import com.test.bydrec.viewmodel.ItemsListViewMvvm
import com.test.bydrec.viewmodel.providers.Injectable

class ItemsListViewFactoryImpl : ItemsListViewFactory {

    override fun getItemsListView(injectables: List<Injectable>): ItemsListViewMvvm.View {
        return ItemsListFragment.newInstance(injectables)
    }
}
