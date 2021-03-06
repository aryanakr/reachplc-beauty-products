package com.reachplc.interview.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history -> navigateToHistory()
        }

        return super.onOptionsItemSelected(item)
    }

    @ExperimentalFoundationApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.composeView.setContent {
            ProductsGrid(viewModel, {id: String, title: String ->
                navigateToProductDetail(id, title)
            })
        }

    }

    /**
     * Handler for navigation from ListFragment to DetailFragment
     */
    private fun navigateToProductDetail(id: String, title: String) {
        val action = ListFragmentDirections.actionFragmentListToDetailFragment(id, title)
        this.findNavController().navigate(action)
    }

    /**
     * Handler for navigation from ListFragment to HistoryFragment
     */
    private fun navigateToHistory() {
        val action = ListFragmentDirections.actionFragmentListToHistoryFragment()
        this.findNavController().navigate(action)
    }

}



