package com.test.bydrec.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.bydrec.databinding.ListHeaderItemBinding
import com.test.bydrec.model.Item
import com.test.bydrec.model.ListHeaderItem
import com.test.bydrec.viewmodel.ListHeaderItemViewMvvm
import com.test.bydrec.viewmodel.ListHeaderItemViewViewModel


class HeaderItemListView : FrameLayout, ItemView, ListHeaderItemViewMvvm.View {

    private var binding: ListHeaderItemBinding? = null
    private lateinit var viewModel: ListHeaderItemViewMvvm.ViewModel

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(
            inflater,
            com.test.bydrec.R.layout.list_header_item,
            this,
            true
        )
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rootView.layoutParams = lp
    }

    override fun bind(item: Item, position: Int) {
        viewModel = ListHeaderItemViewViewModel(item as ListHeaderItem)
        binding!!.viewmodel = viewModel
    }
}
