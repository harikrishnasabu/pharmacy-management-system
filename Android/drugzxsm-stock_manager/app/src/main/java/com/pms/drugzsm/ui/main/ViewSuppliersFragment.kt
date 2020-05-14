package com.pms.drugzsm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.pms.drugzsm.R
import com.pms.drugzsm.adapters.ViewProductsRecyclerAdapter
import com.pms.drugzsm.adapters.ViewSuppliersRecyclerAdapter
import com.pms.drugzsm.databinding.FragmentViewSuppliersBinding
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.utils.TopSpacingItemDecoration

import kotlinx.android.synthetic.main.fragment_view_products.*
import kotlinx.android.synthetic.main.fragment_view_suppliers.*

/**
 * A simple [Fragment] subclass.
 */
class ViewSuppliersFragment : Fragment() {
    private var _binding:FragmentViewSuppliersBinding?= null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private lateinit var recyclerAdapter: ViewSuppliersRecyclerAdapter
    private lateinit var viewModel:ManageVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewSuppliersBinding.inflate(inflater, container, false)
        val view = binding.root
        return view }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.refreshSuppliers()
        viewModel._supplierList.observe(viewLifecycleOwner, Observer {
            initRecyclerView()
            recyclerAdapter.setSupplierList(it as ArrayList<Suppliers>)
        })

        viewModel._updateSupplier.observe(viewLifecycleOwner, Observer {
            navController!!.navigate(
                R.id.updateSupplierFragment
            )
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)

    }



    private fun initRecyclerView() {
        rv_view_suppliers.apply {
            layoutManager= LinearLayoutManager(this.context)
            val topSpacingDecorator = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecorator)
            recyclerAdapter= ViewSuppliersRecyclerAdapter(viewModel)
            adapter=recyclerAdapter
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}

