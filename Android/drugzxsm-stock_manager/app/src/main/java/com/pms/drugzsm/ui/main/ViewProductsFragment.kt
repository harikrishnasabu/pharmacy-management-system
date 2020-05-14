package com.pms.drugzsm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pms.drugzsm.R
import com.pms.drugzsm.adapters.ProductsRecyclerAdapter
import com.pms.drugzsm.adapters.ViewProductsRecyclerAdapter
import com.pms.drugzsm.databinding.FragmentViewProductsBinding
import com.pms.drugzsm.datamodels.api.Products
import com.pms.drugzsm.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_product_listing.*
import kotlinx.android.synthetic.main.fragment_view_products.*


/**
 * A simple [Fragment] subclass.
 */
class ViewProductsFragment : Fragment() {
    private var _binding:FragmentViewProductsBinding?= null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private lateinit var recyclerAdapter:ViewProductsRecyclerAdapter
    private lateinit var viewModel:ManageVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewProductsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.refreshProducts()
        viewModel._productList.observe(viewLifecycleOwner, Observer {
            initRecyclerView()
            recyclerAdapter.setProductList(it as ArrayList<Products>)
        })

        viewModel._updateProduct.observe(viewLifecycleOwner, Observer {
            navController!!.navigate(
                R.id.updateProductFragment
            )
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)

    }



    private fun initRecyclerView() {
        rv_view_products.apply {
            layoutManager= LinearLayoutManager(this.context)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            recyclerAdapter= ViewProductsRecyclerAdapter(viewModel)
            adapter=recyclerAdapter
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
