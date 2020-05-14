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
import com.pms.drugzsm.databinding.FragmentUpdateSupplierBinding
import com.pms.drugzsm.datamodels.api.Suppliers
import com.pms.drugzsm.utils.ValidatorUtils


/**
 * A simple [Fragment] subclass.
 */
class UpdateSupplierFragment : Fragment(), View.OnClickListener  {
    private var _binding:FragmentUpdateSupplierBinding?=null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private lateinit var viewModel:ManageVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateSupplierBinding.inflate(inflater, container, false)
        val view = binding.root
        return view }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_update_supplier).setOnClickListener(this)
        val update=viewModel.getSupplierForUpdate()
        _binding?.edtSupplierId?.setText(update?.supplierId.toString())
        _binding?.edtSupplierAddress?.setText(update?.address)
        _binding?.edtSupplierContact?.setText(update?.contactNo.toString())
        _binding?.edtSupplierEmail?.setText(update?.email)
        _binding?.edtSupplierLicense?.setText(update?.license)
        _binding?.edtSupplierName?.setText(update?.supplierName)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)


    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_update_supplier ->{
                if(ValidatorUtils.isValidName(_binding?.edtSupplierName,true) && ValidatorUtils.isValidPhone(_binding?.edtSupplierContact,true) && ValidatorUtils.isValidName(_binding?.edtSupplierAddress,true) && ValidatorUtils.isValidEmail(_binding?.edtSupplierEmail,true) && ValidatorUtils.isValidName(_binding?.edtSupplierLicense,true) && ValidatorUtils.isValidNumber(_binding?.edtSupplierId,true))
                {
                    val supplier= Suppliers(address = _binding!!.edtSupplierAddress.text.toString(),contactNo = _binding!!.edtSupplierContact.text.toString().toLong(),email = _binding!!.edtSupplierEmail.text.toString(),license = _binding!!.edtSupplierLicense.text.toString(),supplierName = _binding!!.edtSupplierName.text.toString(),supplierId =_binding!!.edtSupplierId.text.toString().toInt() )
                    println("UPDATEsupplier"+supplier)
                    viewModel.updateSupplier(_binding!!.edtSupplierId.text.toString().toInt(),supplier)
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
