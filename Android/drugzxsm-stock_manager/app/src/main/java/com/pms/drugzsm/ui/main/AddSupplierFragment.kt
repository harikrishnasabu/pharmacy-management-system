package com.pms.drugzsm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pms.drugzsm.R
import com.pms.drugzsm.databinding.FragmentAddSupplierBinding
import com.pms.drugzsm.datamodels.api.Supplier
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.utils.ValidatorUtils

/**
 * A simple [Fragment] subclass.
 */
class AddSupplierFragment : Fragment(),View.OnClickListener  {

    private var _binding:FragmentAddSupplierBinding?=null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private lateinit var viewModel:ManageVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSupplierBinding.inflate(inflater, container, false)
        val view = binding.root
        return view }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_add_supplier).setOnClickListener(this)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_add_supplier ->{
                if(ValidatorUtils.isValidName(_binding?.edtSupplierName,true) && ValidatorUtils.isValidPhone(_binding?.edtSupplierContact,true) && ValidatorUtils.isValidName(_binding?.edtSupplierAddress,true) && ValidatorUtils.isValidEmail(_binding?.edtSupplierEmail,true) && ValidatorUtils.isValidName(_binding?.edtSupplierLicense,true))
                {
                   val supplier= Supplier(address = _binding!!.edtSupplierAddress.text.toString(),contactNo = _binding!!.edtSupplierContact.text.toString().toLong(),email = _binding!!.edtSupplierEmail.text.toString(),license = _binding!!.edtSupplierLicense.text.toString(),supplierName = _binding!!.edtSupplierName.text.toString())
                    println("supplier"+supplier)
                    viewModel.addSupplier(supplier)
                    navController!!.navigate(
                        R.id.productListingFragment
                    )
                }

            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}
