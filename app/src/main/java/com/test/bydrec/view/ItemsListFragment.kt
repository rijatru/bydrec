package com.test.bydrec.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.bydrec.R
import com.test.bydrec.adapter.ItemAdapter
import com.test.bydrec.databinding.FragmentListBinding
import com.test.bydrec.model.Item
import com.test.bydrec.viewmodel.ItemsListViewModel
import com.test.bydrec.viewmodel.ItemsListViewMvvm
import com.test.bydrec.viewmodel.providers.Injectable
import com.test.bydrec.viewmodel.providers.ItemsProvider
import com.test.bydrec.viewmodel.providers.ResultsItemsProviderImpl

class ItemsListFragment : Fragment(), ItemsListViewMvvm.View {

    private lateinit var filterItems: List<String>
    private lateinit var viewModel: ItemsListViewMvvm.ViewModel
    private lateinit var binding: FragmentListBinding
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemsProvider: ItemsProvider
    private lateinit var parentView: ItemsListParentView

    companion object {
        fun newInstance(
            injectables: List<Injectable>
        ): ItemsListFragment {
            val fragment = ItemsListFragment()
            injectables.forEach {
                when (it) {
                    is ItemsProvider -> fragment.itemsProvider = it
                }
            }
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentView = context as ItemsListParentView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[ItemsListViewModel::class.java]

        if (::itemsProvider.isInitialized) {
            viewModel.setContext(getApplicationContext())
            viewModel.setItemsProvider(itemsProvider)
        }

        if (savedInstanceState == null) {
            viewModel.getListItems()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list, container, false
        )
        val view = binding.root

        val activity = activity
        if (activity != null) {
            setupBinding()
            itemAdapter = ItemAdapter(activity, ArrayList())
            subscribeToObservables()
        }
        return view
    }

    private fun setupBinding() {
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.list.layoutManager = LinearLayoutManager(activity)
        binding.list.itemAnimator = DefaultItemAnimator()

        binding.pullToRefresh.isRefreshing = true
        binding.pullToRefresh.setOnRefreshListener {
            itemAdapter.clearItems()
            viewModel.getListItems()
        }
        binding.pullToRefresh.setColorSchemeColors(
            ContextCompat.getColor(
                context!!,
                R.color.colorAccent
            )
        )
    }

    private fun subscribeToObservables() {
        val items = viewModel.getItems()
        items.observe(viewLifecycleOwner, Observer { onListItems(it) })
    }

    private fun onListItems(items: List<Item>) {
        binding.pullToRefresh.isRefreshing = false
        itemAdapter.setItems(items)
        binding.list.adapter = itemAdapter
        binding.list.scheduleLayoutAnimation()

        if (items.isNotEmpty() && !::filterItems.isInitialized && ::itemsProvider.isInitialized) {
            filterItems = viewModel.getFilterItems(items)
            var position = 0
            if (itemsProvider is ResultsItemsProviderImpl) {
                position = 1
            }
            parentView.onFilterItems(filterItems, position)
        }
    }

    override fun getApplicationContext(): Context? {
        return activity?.applicationContext
    }

    override fun filterItemsBy(filterPosition: Int) {
        if (::filterItems.isInitialized) {
            viewModel.filterItemsBy(filterItems[filterPosition])
        }
    }
}
