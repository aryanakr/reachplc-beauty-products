package com.reachplc.interview.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import coil.compose.rememberImagePainter
import java.text.NumberFormat
import java.util.*


class DetailFragment : Fragment() {

    private val viewModel by viewModels<DetailViewModel> {
        DetailViewModelFactory(
            (requireContext().applicationContext as BeautyProductsApp).appContainer,
            (activity?.application as BeautyProductsApp).database.historyDao()
        )
    }

    private lateinit var product: Product

    private val navigationArgs: DetailFragmentArgs by navArgs()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fetch Product
        viewModel.fetchProduct(navigationArgs.productId)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.product.observe(this.viewLifecycleOwner){ product ->
            product!!.let {
                binding.composeImage.setContent {
                    ProductDetailImage(it)

                }

                binding.title.text = it.name
                binding.price.text = NumberFormat.getCurrencyInstance().format(it.price)
                binding.description.text = it.description
            }

            viewModel.saveProductHistory(product!!, Calendar.getInstance().time)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

@Composable
fun ProductDetailImage(product: Product) {
    Image(
        painter = rememberImagePainter(product.image),
        contentDescription = product.name + " thumbnail",
        modifier = Modifier.background(Color.Black).size(128.dp)
    )
}