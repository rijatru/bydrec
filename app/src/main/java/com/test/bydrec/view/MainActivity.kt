package com.test.bydrec.view

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.test.bydrec.R
import com.test.bydrec.adapter.OnItemSelectedListenerAdapter
import com.test.bydrec.adapter.OnTabSelectedListenerAdapter
import com.test.bydrec.adapter.PageAdapterFactory
import com.test.bydrec.databinding.ActivityMainBinding
import com.test.bydrec.viewmodel.ItemsListViewMvvm
import com.test.bydrec.viewmodel.providers.FixturesItemsProviderImpl
import com.test.bydrec.viewmodel.providers.Injectable
import com.test.bydrec.viewmodel.providers.ResultsItemsProviderImpl
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity(), ItemsListParentView {

    private lateinit var spinnerAdapter: ArrayAdapter<String>
    private lateinit var filterItems: MutableMap<Int, MutableList<String>>
    private lateinit var binding: ActivityMainBinding
    private lateinit var pages: ArrayList<ItemsListViewMvvm.View>

    @Inject
    lateinit var pageAdapterFactory: PageAdapterFactory
    @Inject
    lateinit var fixturesItemsProvider: FixturesItemsProviderImpl
    @Inject
    lateinit var resultsItemsProvider: ResultsItemsProviderImpl
    @Inject
    lateinit var itemsListViewFactory: ItemsListViewFactory

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!::pageAdapterFactory.isInitialized) {
            AndroidInjection.inject(this)
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initPages()

        val pageAdapter = getPagerAdapter()

        binding.viewPager.adapter = pageAdapter
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tablayout))
        binding.viewPager.offscreenPageLimit = 2

        binding.tablayout.addOnTabSelectedListener(getOnTabSelectedListenerAdapter())
        binding.spinner.onItemSelectedListener = getOnItemSelectedListenerAdapter()

        filterItems = mutableMapOf()
    }

    private fun getOnTabSelectedListenerAdapter(): OnTabSelectedListenerAdapter {
        return object : OnTabSelectedListenerAdapter() {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
                if (filterItems.containsKey(tab.position)) {
                    resetFilterItems(filterItems.getValue(tab.position), tab.position)
                }
            }
        }
    }

    private fun getPagerAdapter(): PagerAdapter? {
        return pageAdapterFactory.getPageAdapter(
            supportFragmentManager,
            binding.tablayout.tabCount,
            pages
        )
    }

    private fun getOnItemSelectedListenerAdapter(): OnItemSelectedListenerAdapter {
        return object : OnItemSelectedListenerAdapter() {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                when (binding.tablayout.selectedTabPosition) {
                    0 -> pages[binding.tablayout.selectedTabPosition].filterItemsBy(position)
                    1 -> pages[binding.tablayout.selectedTabPosition].filterItemsBy(position)
                }
            }
        }
    }

    private fun initPages() {
        val fixturesInjectables = ArrayList<Injectable>()
        fixturesInjectables.add(fixturesItemsProvider)

        val resultsInjectables = ArrayList<Injectable>()
        resultsInjectables.add(resultsItemsProvider)

        val fixturesView = itemsListViewFactory.getItemsListView(fixturesInjectables)
        val resultsView = itemsListViewFactory.getItemsListView(resultsInjectables)

        pages = ArrayList()
        pages.add(fixturesView)
        pages.add(resultsView)
    }

    override fun onFilterItems(filterItems: MutableList<String>, position: Int) {
        if (this.filterItems[position] == null) {
            setSpinnerAdapter(position, filterItems)
        }
    }

    private fun resetFilterItems(filterItems: MutableList<String>, position: Int) {
        setSpinnerAdapter(position, filterItems)
    }

    private fun setSpinnerAdapter(
        position: Int,
        filterItems: MutableList<String>
    ) {
        this.filterItems[position] = filterItems
        if (!::spinnerAdapter.isInitialized) {
            spinnerAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, android.R.id.text1)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = spinnerAdapter
        }
        spinnerAdapter.clear()
        spinnerAdapter.addAll(this.filterItems.getValue(position))
        spinnerAdapter.notifyDataSetChanged()
    }
}
