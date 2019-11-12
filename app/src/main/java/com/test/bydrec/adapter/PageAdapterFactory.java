package com.test.bydrec.adapter;

import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;

import com.test.bydrec.viewmodel.ItemsListViewMvvm;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface PageAdapterFactory {

    @Nullable
    PagerAdapter getPageAdapter(@NotNull FragmentManager supportFragmentManager, int tabCount, List<ItemsListViewMvvm.View> views);
}
