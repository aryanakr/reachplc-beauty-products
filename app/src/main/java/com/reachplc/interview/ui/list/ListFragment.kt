package com.reachplc.interview.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.databinding.FragmentListBinding


class ListFragment() : Fragment() {

    private val viewModel by viewModels<ListViewModel> {
        ListViewModelFactory((requireContext().applicationContext as BeautyProductsApp).appContainer)
    }

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
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

}



