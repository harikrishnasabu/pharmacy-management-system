package com.pms.drugzx.ui.main

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

import com.pms.drugzx.R
import com.pms.drugzx.databinding.FragmentOrderSummaryBinding
import com.pms.drugzx.utils.DateUtils

/**
 * A simple [Fragment] subclass.
 */
class OrderSummaryFragment : Fragment(),View.OnClickListener {

    private var _binding: FragmentOrderSummaryBinding? = null
    private lateinit var viewModel:OrderSummaryVM
    private val binding get() = _binding!!
    lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentOrderSummaryBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(OrderSummaryVM::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getOrderSummary().observe(viewLifecycleOwner, Observer {

_binding?.tvOrderCustomerName?.text =it.customerName
            _binding?.tvOrderCustomerEmail?.text =it.customerEmail
            _binding?.tvOrderCustomerPhone?.text =it.customerPhone
            _binding?.tvOrderCustomerAddress?.text =it.customerAddress
            _binding?.tvOrderId?.text =it.soId.toString()
            _binding?.tvOrderSupplierId?.text =it.sellerId.toString()
            _binding?.tvOrderDate?.text =DateUtils.convertLongToStringDate(it.soDate.toLong())
            _binding?.tvOrderQuantity?.text =it.totalQuantity.toString()
            _binding?.tvOrderStatus?.text =it.soStatus
            _binding?.tvOrderSubtotal?.text =it.subTotal.toString()
            _binding?.tvOrderTax?.text =it.tax.toString()
            _binding?.tvOrderTotal?.text =it.total.toString()

        })
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_place_order).setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onClick(v: View?) {
        navController!!.navigate(
            R.id.productListingFragment
        )    }

}
