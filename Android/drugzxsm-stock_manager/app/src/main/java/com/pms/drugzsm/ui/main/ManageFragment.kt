package com.pms.drugzsm.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pms.drugzsm.R
import com.pms.drugzsm.databinding.FragmentManageBinding


/**
 * A simple [Fragment] subclass.
 */
class ManageFragment : Fragment(),View.OnClickListener {
    private var _binding:FragmentManageBinding?=null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private lateinit var viewModel:ManageVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentManageBinding.inflate(inflater, container, false)
        val view = binding.root
        return view   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        _binding?.cvAddProduct?.setOnClickListener(this)
        _binding?.cvAddSupplier?.setOnClickListener(this)
        _binding?.cvEditProduct?.setOnClickListener(this)
        _binding?.cvEditSupplier?.setOnClickListener(this)

    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ManageVM::class.java)

    }
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.cv_add_product ->
                navController!!.navigate(
                    R.id.addProductFragment)
            R.id.cv_add_supplier -> navController!!.navigate(
                R.id.addSupplierFragment)
            R.id.cv_edit_product -> navController!!.navigate(
                R.id.viewProductsFragment)
                R.id.cv_edit_supplier -> navController!!.navigate(
                    R.id.viewSuppliersFragment)

            // R.id.cancel_btn -> activity!!.onBackPressed()
        }   }

}
