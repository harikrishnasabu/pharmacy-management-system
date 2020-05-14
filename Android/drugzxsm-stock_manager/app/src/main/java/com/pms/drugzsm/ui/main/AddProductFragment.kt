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
import com.pms.drugzsm.databinding.FragmentAddProductBinding
import com.pms.drugzsm.datamodels.api.Product
import com.pms.drugzsm.utils.ValidatorUtils


/**
 * A simple [Fragment] subclass.
 */
class AddProductFragment : Fragment(),View.OnClickListener {
lateinit var navController: NavController
    private lateinit var viewModel:ManageVM
    private var _binding: FragmentAddProductBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddProductBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btn_add_product).setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_add_product ->{
                if(ValidatorUtils.isValidName(_binding?.edtProductName,true)&& ValidatorUtils.isValidName(_binding?.edtProductDes,true) && ValidatorUtils.isValidNumber(_binding?.edtProductQuantity,true) && _binding?.edtManuDate?.text?.isNotEmpty()!! && _binding?.edtExpDate?.text?.isNotEmpty()!! && ValidatorUtils.isValidNumber(_binding?.edtCostPrice,true) && ValidatorUtils.isValidNumber(_binding?.edtSellingPrice,true) && ValidatorUtils.isValidNumber(_binding?.edtSellerId,true) )
                {
val product=Product(pCostPrice =  _binding!!.edtCostPrice.text.toString().toDouble(),pDescription = _binding!!.edtProductDes.text.toString(),pExpiryDate = _binding!!.edtExpDate.text.toString(),pManufactureDate = _binding!!.edtManuDate.text.toString(),pName = _binding!!.edtProductName.text.toString(),pQuantity = _binding!!.edtProductQuantity.text.toString().toInt(),pSellingPrice =  _binding!!.edtSellingPrice.text.toString().toDouble(),sId =  _binding!!.edtSellerId.text.toString().toInt()  )
println("Product"+product)
viewModel.addProduct(product)
                    navController!!.navigate(
                        R.id.productListingFragment
                    )
                }

            }

        }
         }

}
