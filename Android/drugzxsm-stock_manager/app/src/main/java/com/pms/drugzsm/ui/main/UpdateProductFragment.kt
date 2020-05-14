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
import com.pms.drugzsm.R
import com.pms.drugzsm.databinding.FragmentUpdateProductBinding
import com.pms.drugzsm.datamodels.api.Product
import com.pms.drugzsm.datamodels.api.Products
import com.pms.drugzsm.utils.ValidatorUtils


/**
 * A simple [Fragment] subclass.
 */
class UpdateProductFragment : Fragment(),View.OnClickListener  {
    private var _binding:FragmentUpdateProductBinding?=null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private lateinit var viewModel:ManageVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateProductBinding.inflate(inflater, container, false)
        val view = binding.root
        return view  }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_update_product).setOnClickListener(this)
        val update= viewModel.getProductForUpdate()
        println("UPDATEProduct"+update.toString())
        _binding?.edtProductName?.setText(update?.pName)
        _binding?.edtProductId?.setText(update?.pId.toString())
        _binding?.edtCostPrice?.setText(update?.pCostPrice.toString())
        _binding?.edtProductQuantity?.setText(update?.pQuantity.toString())
        _binding?.edtProductDes?.setText(update?.pDescription)
        _binding?.edtManuDate?.setText(update?.pManufactureDate)
        _binding?.edtExpDate?.setText(update?.pExpiryDate)
        _binding?.edtSellingPrice?.setText(update?.pSellingPrice.toString())
        _binding?.edtSellerId?.setText(update?.sId.toString())
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_update_product ->{
                if(ValidatorUtils.isValidName(_binding?.edtProductName,true)&& ValidatorUtils.isValidName(_binding?.edtProductDes,true) && ValidatorUtils.isValidNumber(_binding?.edtProductQuantity,true) && _binding?.edtManuDate?.text?.isNotEmpty()!! && _binding?.edtExpDate?.text?.isNotEmpty()!! && ValidatorUtils.isValidNumber(_binding?.edtCostPrice,true) && ValidatorUtils.isValidNumber(_binding?.edtSellingPrice,true) && ValidatorUtils.isValidNumber(_binding?.edtProductId,true) && ValidatorUtils.isValidNumber(_binding?.edtSellerId,true) )
                {
                    val product= Products(pCostPrice =  _binding!!.edtCostPrice.text.toString().toDouble(),pDescription = _binding!!.edtProductDes.text.toString(),pExpiryDate = _binding!!.edtExpDate.text.toString(),pManufactureDate = _binding!!.edtManuDate.text.toString(),pName = _binding!!.edtProductName.text.toString(),pQuantity = _binding!!.edtProductQuantity.text.toString().toInt(),pSellingPrice =  _binding!!.edtSellingPrice.text.toString().toDouble(),sId =  _binding!!.edtSellerId.text.toString().toInt()  ,pId =binding!!.edtProductId.text.toString().toInt() )
                    println("UPDATEProductFINAL"+product)
                    viewModel.updateProduct(product,_binding!!.edtProductId.text.toString().toInt())
                    navController!!.navigate(
                        R.id.productListingFragment
                    )
                }

            }

        }
    }
}
