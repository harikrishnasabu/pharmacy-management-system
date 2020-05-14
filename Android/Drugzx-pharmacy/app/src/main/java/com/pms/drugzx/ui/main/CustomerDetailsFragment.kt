package com.pms.drugzx.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pms.drugzx.R
import com.pms.drugzx.datamodels.api.CustomerOrder
import com.pms.drugzx.utils.DateUtils
import com.pms.drugzx.utils.ValidatorUtils
import com.pms.drugzx.databinding.FragmentCustomerDetailsBinding

/**
 * A simple [Fragment] subclass.
 */
class CustomerDetailsFragment : Fragment() ,View.OnClickListener{
    private var _binding:FragmentCustomerDetailsBinding? = null
    lateinit var navController: NavController
    private lateinit var viewModel: CustomerOrderVM
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
                _binding =FragmentCustomerDetailsBinding.inflate(inflater, container, false)
                val view = binding.root
                return view
            }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CustomerOrderVM::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_cust_fragment).setOnClickListener(this)
        navController = Navigation.findNavController(view)
    }

    override fun onClick(v: View?) {

        println("CustomerDetailsFragment"+ DateUtils.convertLongToStringDate(System.currentTimeMillis()))
        when(v!!.id) {
            R.id.btn_cust_fragment -> {

                if(ValidatorUtils.isValidName(_binding?.edtCustomerAddress,true)&& ValidatorUtils.isValidName(_binding?.edtCustomerName,true)&& ValidatorUtils.isValidEmail(_binding?.edtCustomerEmail,true) && ValidatorUtils.isValidPhone(_binding?.edtCustomerPhone,true))
                {
                    val customerOrder= CustomerOrder(
                        _binding?.edtCustomerAddress?.text.toString(),
                        _binding?.edtCustomerEmail?.text.toString(), _binding?.edtCustomerName?.text.toString(),
                        _binding?.edtCustomerPhone?.text.toString(),null,1,DateUtils.convertLongToStringDate(System.currentTimeMillis()),"paid")
                    Log.i("CustomerDetailsFragment",customerOrder.toString())
                    Toast.makeText(getActivity(), customerOrder.toString(),Toast.LENGTH_LONG).show();

                    viewModel.placeOrder(customerOrder)
                    navController!!.navigate(
                        R.id.orderSummaryFragment
                    )
                }


            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


//fun addCustomerDetails(view: View){
//    println("CustomerDetailsFragment");
//}

}
