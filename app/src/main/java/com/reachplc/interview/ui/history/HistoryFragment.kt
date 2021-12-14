package com.reachplc.interview.ui.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentHistoryBinding


/**
 * A simple [Fragment] subclass.
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by activityViewModels{
        HistoryViewModelFactory(
            (activity?.application as BeautyProductsApp).database.historyDao()
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = HistoryAdapter(){}

        binding.recyclerView.layoutManager = GridLayoutManager(this.context,1)
        binding.recyclerView.adapter = adapter
        viewModel.retrieveAllProductsHistory().observe(this.viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}