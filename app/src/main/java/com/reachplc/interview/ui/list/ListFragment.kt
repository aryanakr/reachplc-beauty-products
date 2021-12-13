package com.reachplc.interview.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import coil.compose.rememberImagePainter
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.data.Product
import com.reachplc.interview.databinding.FragmentListBinding
import com.reachplc.interview.di.AppContainer
import kotlin.math.max


class ListFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel> {
        ListViewModelFactory((requireContext().applicationContext as BeautyProductsApp).appContainer)
    }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    // private val args: ListFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @ExperimentalFoundationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Add fab button logic

//        val listAdapter = ProductListAdapter{}

//        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 1)
//        binding.recyclerView.adapter = listAdapter
//        viewModel.products.observe(this.viewLifecycleOwner) {items ->
//            println("////////////////////////// items count : ${items.size}")
//            if (items.size > 0){
//                listAdapter.submitList(items)
//            } else {
//                binding.message.visibility = View.VISIBLE
//            }
//
//        }

        // For match parent items better to use gridlayoutmanager so it won't be overridden by wrap content
        binding.composeView.setContent {
            ProductsGrid(viewModel)
        }


    }

}



