package com.test.bydrec.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.test.bydrec.view.ItemsListFragment
import com.test.bydrec.viewmodel.ItemsListViewMvvm

class PageAdapter(
    fm: FragmentManager,
    private val numOfTabs: Int,
    private val views: List<ItemsListViewMvvm.View>
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return views[position] as ItemsListFragment
    }

    override fun getCount(): Int {
        return numOfTabs
    }
}
