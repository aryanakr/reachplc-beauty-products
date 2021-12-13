package com.reachplc.interview.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.R
import com.reachplc.interview.data.Product
import com.reachplc.interview.databinding.FragmentDetailBinding
import com.reachplc.interview.databinding.FragmentListBinding
import com.reachplc.interview.ui.list.ListViewModel
import com.reachplc.interview.ui.list.ListViewModelFactory
import androidx.navigation.fragment.navArgs


class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory((requireContext().applicationContext as BeautyProductsApp).appContainer)
    }

    private lateinit var product: Product

    private val navigationArgs: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // TODO: Update Navbar Title
        viewModel.fetchProduct(navigationArgs.productId)
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.product.observe(this.viewLifecycleOwner){ product ->
            // TODO: Update views
            binding.txt.text = navigationArgs.productTitle

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}