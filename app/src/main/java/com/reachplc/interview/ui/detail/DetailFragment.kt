package com.reachplc.interview.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentDetailBinding
import com.reachplc.interview.databinding.FragmentListBinding
import com.reachplc.interview.ui.list.ListViewModel
import com.reachplc.interview.ui.list.ListViewModelFactory


class DetailFragment : Fragment() {

    private val viewModel by viewModels<ListViewModel> {
        ListViewModelFactory((requireContext().applicationContext as BeautyProductsApp).appContainer)
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}