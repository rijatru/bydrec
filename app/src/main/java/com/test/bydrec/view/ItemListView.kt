package com.test.bydrec.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.test.bydrec.R
import com.test.bydrec.databinding.ListItemBinding
import com.test.bydrec.model.Item
import com.test.bydrec.model.ListItem
import com.test.bydrec.viewmodel.ListItemViewMvvm
import com.test.bydrec.viewmodel.ListItemViewViewModel

class ItemListView : FrameLayout, ItemView, ListItemViewMvvm.View {

    private var binding: ListItemBinding? = null
    private lateinit var viewModel: ListItemViewMvvm.ViewModel

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
        binding = DataBindingUtil.inflate(inflater, R.layout.list_item, this, true)
    }

    override fun bind(item: Item, position: Int) {
        viewModel = ListItemViewViewModel(item as ListItem)
        viewModel.setContext(context)
        binding!!.viewmodel = viewModel
    }
}
