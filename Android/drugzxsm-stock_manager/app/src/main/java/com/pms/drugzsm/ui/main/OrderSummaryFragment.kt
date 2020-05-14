package com.pms.drugzsm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pms.drugzsm.R
import com.pms.drugzsm.databinding.FragmentOrderSummaryBinding

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
//        viewModel.getOrderDetails().observe(viewLifecycleOwner, Observer {
//_binding?.tvCustNameValue?.text =it.customerName
//            _binding?.tvItemCountValue ?.text =""
//        })
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
