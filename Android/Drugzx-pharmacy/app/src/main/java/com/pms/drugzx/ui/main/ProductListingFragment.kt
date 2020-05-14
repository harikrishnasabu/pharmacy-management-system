package com.pms.drugzx.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pms.drugzx.R
import com.pms.drugzx.adapters.ProductsRecyclerAdapter
import com.pms.drugzx.databinding.FragmentProductListingBinding
import com.pms.drugzx.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_product_listing.*
class ProductListingFragment : Fragment() ,View.OnClickListener{
    private var _binding: FragmentProductListingBinding? = null
    private lateinit var viewModel: ProductListingVM
    private lateinit var recyclerAdapter: ProductsRecyclerAdapter
    lateinit var navController: NavController
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    // or super.onActivityCreated
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProductListingVM::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProducts()

        viewModel._products.observe(viewLifecycleOwner, Observer {
            initRecyclerView()
            println("API"+it.toString())
            recyclerAdapter.setProductList(it as ArrayList)

        })
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_proceed).setOnClickListener(this)
        view.findViewById<ImageButton>(R.id.btn_product_search).setOnClickListener(this)


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    private fun initRecyclerView() {
        rv_products.apply {
            layoutManager= LinearLayoutManager(this.context)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            recyclerAdapter= ProductsRecyclerAdapter(viewModel)
            adapter=recyclerAdapter
        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_proceed -> {
                recyclerAdapter.setSelectedProducts()
                navController!!.navigate(
                    R.id.selectedProductsFragment
                )

            }
            R.id.btn_product_search ->{
                viewModel.searchchProduct(binding.edtSearch.text.toString())
            }
            // R.id.cancel_btn -> activity!!.onBackPressed()

        }
        viewModel._searchProduct.observe(viewLifecycleOwner, Observer {
            println("API"+it.toString())
            recyclerAdapter.setProductList(it as ArrayList)

        })
    }

}