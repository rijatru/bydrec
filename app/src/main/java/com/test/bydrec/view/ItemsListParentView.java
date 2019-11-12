package com.test.bydrec.view;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface ItemsListParentView {

    void onFilterItems(@NotNull List<String> filterItems, int position);
}
