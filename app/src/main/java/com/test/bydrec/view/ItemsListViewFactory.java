package com.test.bydrec.view;

import com.test.bydrec.viewmodel.ItemsListViewMvvm;
import com.test.bydrec.viewmodel.providers.Injectable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ItemsListViewFactory {

    @NotNull
    ItemsListViewMvvm.View getItemsListView(List<Injectable> injectables);
}
