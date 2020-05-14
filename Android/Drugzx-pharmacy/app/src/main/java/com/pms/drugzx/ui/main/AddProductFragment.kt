package com.pms.drugzx.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pms.drugzx.R
import com.pms.drugzx.databinding.FragmentAddProductBinding


/**
 * A simple [Fragment] subclass.
 */
class AddProductFragment : Fragment(),View.OnClickListener {
lateinit var navController: NavController
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
            R.id.btn_proceed ->{
                navController!!.navigate(
                    R.id.productListingFragment
                )
            }

        }
         }

}
