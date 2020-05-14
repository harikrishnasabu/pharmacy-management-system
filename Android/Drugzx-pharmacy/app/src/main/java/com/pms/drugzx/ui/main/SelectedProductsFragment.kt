package com.pms.drugzx.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.pms.drugzx.R
import com.pms.drugzx.adapters.SelectedProductsRecyclerAdapter
import com.pms.drugzx.databinding.FragmentSelectedProductsBinding
import com.pms.drugzx.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_selected_products.*

/**
 * A simple [Fragment] subclass.
 */
class SelectedProductsFragment : Fragment() ,View.OnClickListener {
    private var _binding: FragmentSelectedProductsBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProductListingVM
    lateinit var navController: NavController
    private lateinit var recyclerAdapter: SelectedProductsRecyclerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSelectedProductsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductListingVM::class.java)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSelectedProducts()?.let {
            initRecyclerView()
            recyclerAdapter.setProductList(it) }
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_order).setOnClickListener(this)

    }

    private fun initRecyclerView() {
        rv_selected_products.apply {
            layoutManager= LinearLayoutManager(this.context)
            val topSpacingDecorator = TopSpacingItemDecoration(20)
            addItemDecoration(topSpacingDecorator)
            val spnrList= arrayOf(1,2,3,4,5)
            var spinnerAdapter = ArrayAdapter(
                context, // Context
                android.R.layout.simple_spinner_item, // Layout
                spnrList // Array
            )
            recyclerAdapter= SelectedProductsRecyclerAdapter(viewModel,spinnerAdapter)
            adapter=recyclerAdapter
        }
    }

    override fun onClick(v: View?) {

recyclerAdapter.setSelectedProducts()

        navController!!.navigate(
            R.id.customerDetailsFragment
        )
    }

}



