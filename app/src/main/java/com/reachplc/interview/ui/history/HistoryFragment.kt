package com.reachplc.interview.ui.history

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.R
import com.reachplc.interview.data.Product
import com.reachplc.interview.data.local.ProductHistory
import com.reachplc.interview.databinding.FragmentHistoryBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

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

        // Setup the Recycler view
        val adapter = HistoryAdapter(this.requireContext(),{ context: Context, productHistory: ProductHistory ->
            showProductHistoryDetail(context, productHistory)
        })

        binding.recyclerView.layoutManager = GridLayoutManager(this.context,1)
        binding.recyclerView.adapter = adapter
        viewModel.retrieveAllProductsHistory().observe(this.viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun showProductHistoryDetail(context: Context, productHistory: ProductHistory) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.product_history_detail)

        val nameTextView = dialog.findViewById<TextView>(R.id.name_txt)
        val accessTextView = dialog.findViewById<TextView>(R.id.access_txt)
        val priceTextView = dialog.findViewById<TextView>(R.id.price_txt)
        val descriptionTextView = dialog.findViewById<TextView>(R.id.description_txt)

        // Bind dialog views
        nameTextView.text = productHistory.product.name
        priceTextView.text = NumberFormat.getCurrencyInstance().format(productHistory.product.price)
        descriptionTextView.text = productHistory.product.description
        accessTextView.text = productHistory.lastVisit!!.visit.dateToString("dd/MM/yyyy HH:mm")

        dialog.show()
    }

    private fun Date.dateToString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(this)
    }

}